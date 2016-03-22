package classOne;

import java.util.Stack;

public class ReverseLinkedListEveryKNodes{//L1p13每K个节点，逆转一次
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode reverseEveryKNodesReverseNeedExtra(//需要额外数据结构
			LinkedListNode head, int K) {
		if (head == null || head.next == null || K < 2) {
			return head;
		}
		Stack<LinkedListNode> kNodesContainer = new Stack<LinkedListNode>();//申请栈
		LinkedListNode current = head;
		LinkedListNode newHead = head;
		LinkedListNode previous = null;
		while (current != null) {
			LinkedListNode nextNode = current.next;
			kNodesContainer.push(current);
			if (kNodesContainer.size() == K) {//剩下K+1不管了，
				previous = resignNodesExtra(kNodesContainer, previous, nextNode);
				if (newHead == head) {
					newHead = current;
				}
			}
			current = nextNode;
		}
		return newHead;
	}

	public static LinkedListNode resignNodesExtra(Stack<LinkedListNode> stack,
			LinkedListNode leftPeek, LinkedListNode rightPeek){//入栈K个，逆转K个
		//stack=kNodesContainer,leftPeek=previous,rightPeek=nextNode
		LinkedListNode stackFirstNode = stack.pop();
		LinkedListNode stackPreviousNode = stackFirstNode;
		while (!stack.isEmpty()) {
			LinkedListNode currentNode = stack.pop();
			stackPreviousNode.next = currentNode;
			stackPreviousNode = currentNode;
		}
		if (leftPeek != null) {
			leftPeek.next = stackFirstNode;
		}
		stackPreviousNode.next = rightPeek;
		return stackPreviousNode;
	}

	public static LinkedListNode reverseEveryKNodesReverseNoExtra(//方法二:不需要额外空间
			LinkedListNode head, int K) {
		if (head == null || head.next == null || K < 2) {
			return head;
		}
		LinkedListNode current = head;
		LinkedListNode newHead = head;
		LinkedListNode firstInKnodes = head;
		LinkedListNode previous = null;
		int times = 1;
		while (current != null) {
			LinkedListNode nextNode = current.next;
			if (times == K) {
				if (previous != null) {
					firstInKnodes = previous.next;
				}
				previous = resignNodesNoExtra(previous, firstInKnodes, current,
						nextNode);
				if (newHead == head) {
					newHead = current;
				}
				times = 0;
			}
			times++;
			current = nextNode;
		}
		return newHead;
	}

	public static LinkedListNode resignNodesNoExtra(LinkedListNode leftPeek,
		LinkedListNode first, LinkedListNode last, LinkedListNode rightPeek) {
	//实参leftPeek=previous,first=firstInKnodes,last=current,rightPeek=nextNode
		LinkedListNode previous = first;
		LinkedListNode current = first.next;
		while (current != rightPeek) {
			LinkedListNode nextNode = current.next;
			current.next = previous;
			previous = current;
			current = nextNode;
		}
		if (leftPeek != null) {//边界处理
			leftPeek.next = last;
		}
		first.next = rightPeek;
		return first;
	}

	public static void printLinkedList(LinkedListNode head) {
		LinkedListNode current = head;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedListNode head = new LinkedListNode(1);
		head.next = new LinkedListNode(2);
		head.next.next = new LinkedListNode(3);
		head.next.next.next = new LinkedListNode(4);
		head.next.next.next.next = new LinkedListNode(5);
		head.next.next.next.next.next = new LinkedListNode(6);
		head.next.next.next.next.next.next = new LinkedListNode(7);
		head.next.next.next.next.next.next.next = new LinkedListNode(8);

		printLinkedList(head);
		LinkedListNode head1;
		head1 = reverseEveryKNodesReverseNeedExtra(head, 3);//需要额外空间
		printLinkedList(head1);
		
//		printLinkedList(head);
//		LinkedListNode head2;
//		head2 = reverseEveryKNodesReverseNoExtra(head, 3);//不需要额外空间，挂链复杂
//		printLinkedList(head2);

	}

}
