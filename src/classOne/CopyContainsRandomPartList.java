package classOne;

import java.util.HashMap;
import java.util.Map;

public class CopyContainsRandomPartList {//L1p11拷贝具有随机指针节点的链表,挂链过程next易混易错，
	public static class Node {
		public int value;
		public Node next;
		public Node random;
		//构造函数中random域不用赋值
		public Node(int data) {
			this.value = data;
		}
	}

	public static Node copyLinkedListContainsRandomLessSpace(Node head) {
		// head : 1->2->3->4->5->6   //成对复制节点，再成对断链
		if (head == null)
			return null;
		Node current = head;//head不能动
		while (current != null) {//创建insertNode并挂上next域的链
			Node insertNode = new Node(current.value);
			Node nextNode=current.next;//nextNode为current下一节点
			current.next=insertNode;//current挂链到insertNode
			insertNode.next=nextNode;//insertNode挂链到nextNode
			current=nextNode;//current由1指向2
		}
		
		printListByNext(head);//调试:通过Next域关系打印链表
		//head已经变成:1->1->2->2->3->3->4->4->5->5->6->6
		
		current = head;
		while (current != null) {//挂上randomNode域的链
			Node copiedNode=current.next;//copiedNode为current下一节点
			Node randomNode = current.random;
			if (randomNode == null) {
				copiedNode.random = null;
			} else {
				copiedNode.random = randomNode.next;
			}
			current = copiedNode.next;
		}
		current = head.next.next;
		Node result = head.next;
		Node previousOldNode = head;
		Node previousNewNode = result;
		previousOldNode.next = null;//断链
		previousNewNode.next = null;//断链
		while (current != null) {//断链
			Node copiedNode = current.next;
			Node nextCurrent = current.next.next;
			previousOldNode.next = current;
			previousNewNode.next = copiedNode;
			previousOldNode = current;
			previousNewNode = copiedNode;
			previousOldNode.next = null;
			previousNewNode.next = null;
			current = nextCurrent;
		}
		return result;
	}

	public static Node copyLinkedListContainsRandomLessTraversal(Node head) {//用Map
		if (head == null)
			return null;
		Node result = new Node(head.value);
		Node current = head.next;
		Node previousNewNode = result;
		Map<Node, Node> mapOldNew = new HashMap<Node, Node>();
		mapOldNew.put(head, result);
		while (current != null) {
			Node currentNewNode = new Node(current.value);//新建2`
			mapOldNew.put(current, currentNewNode);//map中加(2,2`)
			previousNewNode.next = currentNewNode;//1`->2`
			previousNewNode = currentNewNode;//指向2`
			current = current.next;//后移遍历的current， 指向3
		}
		current = head;
		while (current != null) {
			Node currentNewNode = mapOldNew.get(current);//取出1`
			Node randomOldNode = current.random;//取出1的random=6
			Node randomNewNode = null;
			if (randomOldNode != null)
				randomNewNode = mapOldNew.get(randomOldNode);//map中6->6`
			currentNewNode.random = randomNewNode;//1的random=6`
			current = current.next;//后移遍历的current，
		}
		return result;
	}

	public static void printListByNext(Node head) {
		Node current = head;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void printListByRandom(Node head){//通过random域打印链表
		Node current = head;
		while (current != null) {
			Node currentRandom = current.random;//通过random域找到下个节点
			if (currentRandom == null)
				System.out.print("null ");
			else
				System.out.print(currentRandom.value + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.random = head.next.next.next.next.next; // 1 -> 6
		head.next.random = head.next.next.next.next.next; // 2 -> 6
		head.next.next.random = head.next.next.next.next; // 3 -> 5
		head.next.next.next.random = head.next.next; // 4 -> 3
		head.next.next.next.next.random = null; // 5 -> null
		head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

//		printListByNext(head);//通过Next域关系打印链表
//		printListByRandom(head);//通过random域关系打印链表
//		System.out.println("=========================");
//		Node result1=copyLinkedListContainsRandomLessSpace(head);//较少空间
//		printListByNext(head);//head没变
//		printListByRandom(head);
//		System.out.println("=========================");
//		printListByNext(result1);
//		printListByRandom(result1);
		System.out.println("=========================");
		Node result2=copyLinkedListContainsRandomLessTraversal(head);//用map较少移动
		printListByNext(head);
		printListByRandom(head);
		System.out.println("=========================");
		printListByNext(result2);
		printListByRandom(result2);

	}

}
