package classOne;

import java.util.HashSet;

public class FindFirstIntersectNodeBetweenTwoLinkedList {
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode findFirstIntersectNode(LinkedListNode head1,
			LinkedListNode head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		LinkedListNode loopNode1 = findFirstLoopNodeBetter(head1);
		LinkedListNode loopNode2 = findFirstLoopNodeBetter(head2);
		if (loopNode1 == null && loopNode2 == null) {
			return findFirstIntersectNodeNoLoop(head1, head2);
		} else if (loopNode1 != null && loopNode2 != null) {
			return findFirstIntersectNodeHaveLoop(head1, loopNode1, head2,
					loopNode2);//都有环
		} else {
			return null;
		}
	}

	public static LinkedListNode findFirstLoopNode(LinkedListNode head) {
		HashSet<LinkedListNode> table = new HashSet<LinkedListNode>();
		LinkedListNode current = head;
		while (current != null) {
			if (table.contains(current)) {
				return current;
			} else {
				table.add(current);
			}
			current = current.next;
		}
		return null;
	}

	public static LinkedListNode findFirstLoopNodeBetter(LinkedListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		LinkedListNode slow = head.next;
		LinkedListNode fast = head.next.next;
		while (fast != slow) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;//fast走两步
			slow = slow.next;//slow走一步
		}
		LinkedListNode current = head;
		while (current != slow) {
			current = current.next;
			slow = slow.next;
		}
		return current;
	}

	public static LinkedListNode findFirstIntersectNodeNoLoop(
			LinkedListNode head1, LinkedListNode head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		LinkedListNode current1 = head1;
		LinkedListNode current2 = head2;
		int length = 0;
		while (current1.next != null) {
			length++;
			current1 = current1.next;
		}
		while (current2.next != null) {
			length--;
			current2 = current2.next;
		}
		if (current1 != current2) {
			return null;
		}
		current1 = length > 0 ? head1 : head2;
		current2 = current1 == head1 ? head2 : head1;
		length = Math.abs(length);
		while (length != 0) {//长链表先走
			length--;
			current1 = current1.next;
		}
		while (current1 != current2) {//同步走
			current1 = current1.next;
			current2 = current2.next;
		}
		return current1;
	}

	public static LinkedListNode findFirstIntersectNodeHaveLoop(
			LinkedListNode head1, LinkedListNode loopBegin1,
			LinkedListNode head2, LinkedListNode loopBegin2) {
		if (head1 == null || head2 == null || loopBegin1 == null
				|| loopBegin2 == null) {
			return null;
		}
		if (loopBegin1 == loopBegin2) {
			LinkedListNode current1 = head1;
			LinkedListNode current2 = head2;
			int length = 0;
			while (current1 != loopBegin1) {
				length++;
				current1 = current1.next;
			}
			while (current2 != loopBegin2) {
				length--;
				current2 = current2.next;
			}
			current1 = length > 0 ? head1 : head2;
			current2 = current1 == head1 ? head2 : head1;
			length = Math.abs(length);
			while (length != 0) {
				length--;
				current1 = current1.next;
			}
			while (current1 != current2) {
				current1 = current1.next;
				current2 = current2.next;
			}
			return current1;
		} else {
			LinkedListNode current1 = loopBegin1.next;
			while (current1 != loopBegin1) {
				if (current1 == loopBegin2) {
					return loopBegin1;// or return loopBegin2
				}
				current1 = current1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		LinkedListNode head1 = new LinkedListNode(1);
		head1.next = new LinkedListNode(2);
		head1.next.next = new LinkedListNode(3);
		head1.next.next.next = new LinkedListNode(4);
		head1.next.next.next.next = new LinkedListNode(5);
		head1.next.next.next.next.next = new LinkedListNode(6);
		head1.next.next.next.next.next.next = new LinkedListNode(7);
		// 0->9->8->6->7->null
		LinkedListNode head2 = new LinkedListNode(0);
		head2.next = new LinkedListNode(9);
		head2.next.next = new LinkedListNode(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findFirstIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new LinkedListNode(1);
		head1.next = new LinkedListNode(2);
		head1.next.next = new LinkedListNode(3);
		head1.next.next.next = new LinkedListNode(4);
		head1.next.next.next.next = new LinkedListNode(5);
		head1.next.next.next.next.next = new LinkedListNode(6);
		head1.next.next.next.next.next.next = new LinkedListNode(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
		// 0->9->8->2...
		head2 = new LinkedListNode(0);
		head2.next = new LinkedListNode(9);
		head2.next.next = new LinkedListNode(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(findFirstIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new LinkedListNode(0);
		head2.next = new LinkedListNode(9);
		head2.next.next = new LinkedListNode(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findFirstIntersectNode(head1, head2).value);

	}

}
