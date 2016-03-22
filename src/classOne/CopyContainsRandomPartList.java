package classOne;

import java.util.HashMap;
import java.util.Map;

public class CopyContainsRandomPartList {//L1p11�����������ָ��ڵ������,��������next�׻��״�
	public static class Node {
		public int value;
		public Node next;
		public Node random;
		//���캯����random���ø�ֵ
		public Node(int data) {
			this.value = data;
		}
	}

	public static Node copyLinkedListContainsRandomLessSpace(Node head) {
		// head : 1->2->3->4->5->6   //�ɶԸ��ƽڵ㣬�ٳɶԶ���
		if (head == null)
			return null;
		Node current = head;//head���ܶ�
		while (current != null) {//����insertNode������next�����
			Node insertNode = new Node(current.value);
			Node nextNode=current.next;//nextNodeΪcurrent��һ�ڵ�
			current.next=insertNode;//current������insertNode
			insertNode.next=nextNode;//insertNode������nextNode
			current=nextNode;//current��1ָ��2
		}
		
		printListByNext(head);//����:ͨ��Next���ϵ��ӡ����
		//head�Ѿ����:1->1->2->2->3->3->4->4->5->5->6->6
		
		current = head;
		while (current != null) {//����randomNode�����
			Node copiedNode=current.next;//copiedNodeΪcurrent��һ�ڵ�
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
		previousOldNode.next = null;//����
		previousNewNode.next = null;//����
		while (current != null) {//����
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

	public static Node copyLinkedListContainsRandomLessTraversal(Node head) {//��Map
		if (head == null)
			return null;
		Node result = new Node(head.value);
		Node current = head.next;
		Node previousNewNode = result;
		Map<Node, Node> mapOldNew = new HashMap<Node, Node>();
		mapOldNew.put(head, result);
		while (current != null) {
			Node currentNewNode = new Node(current.value);//�½�2`
			mapOldNew.put(current, currentNewNode);//map�м�(2,2`)
			previousNewNode.next = currentNewNode;//1`->2`
			previousNewNode = currentNewNode;//ָ��2`
			current = current.next;//���Ʊ�����current�� ָ��3
		}
		current = head;
		while (current != null) {
			Node currentNewNode = mapOldNew.get(current);//ȡ��1`
			Node randomOldNode = current.random;//ȡ��1��random=6
			Node randomNewNode = null;
			if (randomOldNode != null)
				randomNewNode = mapOldNew.get(randomOldNode);//map��6->6`
			currentNewNode.random = randomNewNode;//1��random=6`
			current = current.next;//���Ʊ�����current��
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

	public static void printListByRandom(Node head){//ͨ��random���ӡ����
		Node current = head;
		while (current != null) {
			Node currentRandom = current.random;//ͨ��random���ҵ��¸��ڵ�
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

//		printListByNext(head);//ͨ��Next���ϵ��ӡ����
//		printListByRandom(head);//ͨ��random���ϵ��ӡ����
//		System.out.println("=========================");
//		Node result1=copyLinkedListContainsRandomLessSpace(head);//���ٿռ�
//		printListByNext(head);//headû��
//		printListByRandom(head);
//		System.out.println("=========================");
//		printListByNext(result1);
//		printListByRandom(result1);
		System.out.println("=========================");
		Node result2=copyLinkedListContainsRandomLessTraversal(head);//��map�����ƶ�
		printListByNext(head);
		printListByRandom(head);
		System.out.println("=========================");
		printListByNext(result2);
		printListByRandom(result2);

	}

}
