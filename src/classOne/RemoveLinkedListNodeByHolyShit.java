package classOne;

import java.util.HashMap;

public class RemoveLinkedListNodeByHolyShit {//L1P14删除a/b处的节点
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode removeMid(LinkedListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			LinkedListNode newHead = head.next;
			head.next = null;
			return newHead;
		}
		LinkedListNode previousDelete = head;
		LinkedListNode current = head.next.next;
		int times = -1;
		while (current != null) {
			times++;
			if (times == 2) {
				previousDelete = previousDelete.next;
				times = 0;
			}
			current = current.next;
		}
		LinkedListNode deleteNode = previousDelete.next;
		LinkedListNode nextNode = deleteNode.next;
		previousDelete.next = nextNode;
		deleteNode.next = null;
		return head;
	}

	public static LinkedListNode removeOneThird(LinkedListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		if (head.next.next.next == null || head.next.next.next.next == null
				|| head.next.next.next.next.next == null) {
			LinkedListNode newHead = head.next;
			head.next = null;
			return newHead;
		}
		LinkedListNode previousDelete = head;
		LinkedListNode current = head.next.next.next.next.next;
		int times = -1;
		while (current != null) {
			times++;
			if (times == 3) {
				previousDelete = previousDelete.next;
				times = 0;
			}
			current = current.next;
		}
		LinkedListNode deleteNode = previousDelete.next;
		LinkedListNode nextNode = deleteNode.next;
		previousDelete.next = nextNode;
		deleteNode.next = null;
		return head;
	}

	public static LinkedListNode removeADevideBLessSpace(LinkedListNode head,
			int a, int b) {//花最少空间
		if (head == null || a >= b)
			return head;
		int length = 0;
		LinkedListNode current = head;
		while (current != null) {
			length++;
			current = current.next;
		}
		int deleteIndex = (int) ((float) (length * a) / (float) b + 0.5); 
		//X.4->X,//X.5->X+1//对任意数(包括整数),四舍五入的方法,先加0.5,再取整
		if (deleteIndex == 0) {
			return head;
		} else if (deleteIndex == 1) {
			LinkedListNode newHead = head.next;
			head.next = null;
			return newHead;
		} else {
			int currentTime = 1;
			LinkedListNode previousDelete = head;
			while (++currentTime != deleteIndex) {
				previousDelete = previousDelete.next;
			}
			LinkedListNode deleteNode = previousDelete.next;
			previousDelete.next = deleteNode.next;
			deleteNode.next = null;
			return head;
		}
	}

	public static LinkedListNode removeADevideBLessTime(LinkedListNode head,
			int a, int b) {//花最少时间
		if (head == null || a >= b)
			return head;
		int length = 0;
		LinkedListNode current = head;
		HashMap<Integer, LinkedListNode> nodeMap = new HashMap<Integer, LinkedListNode>();
		while (current != null) {
			length++;
			nodeMap.put(length, current);
			current = current.next;
		}
		int deleteIndex = (int) ((float) (length * a) / (float) b + 0.5); // X.4->X,
																			// X.5->X+1
		if (deleteIndex == 0) {
			return head;
		} else if (deleteIndex == 1) {
			LinkedListNode newHead = head.next;
			head.next = null;
			return newHead;
		} else {
			LinkedListNode previousDelete = nodeMap.get(deleteIndex - 1);
			LinkedListNode deleteNode = previousDelete.next;
			LinkedListNode nextNode = deleteNode.next;
			previousDelete.next = nextNode;
			deleteNode.next = null;
			return head;
		}
	}

	public static void printLikeLinkedList(LinkedListNode head) {
		LinkedListNode current = head;
		System.out.println("Linked list:");
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
//		head.next.next.next.next.next = new LinkedListNode(6);
//		head.next.next.next.next.next.next = new LinkedListNode(7);
//		head.next.next.next.next.next.next.next = new LinkedListNode(8);
//		head.next.next.next.next.next.next.next.next = new LinkedListNode(9);
		printLikeLinkedList(head);
//		LinkedListNode head2 = removeMid(head);
//		printLikeLinkedList(head2);
//		LinkedListNode head3 = removeOneThird(head);
//		printLikeLinkedList(head3);
		LinkedListNode head1_2 = removeADevideBLessSpace(head, 1, 2);
		printLikeLinkedList(head1_2);
		LinkedListNode head5_6=removeADevideBLessTime(head, 5, 6);
		printLikeLinkedList(head5_6);
	}

}
