package classOne;

import java.util.Stack;

public class IsPalindromeLinkedList {//L1p10检查链表是否回文
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	// need n/2 space
	public static boolean isPalindromeNeedExtra(LinkedListNode head) {
		if (head == null)
			return true;
		if (head.next == null)
			return true;
		//以下句:找到链表中间的节点
		LinkedListNode midInPalindrome = findMidNodeInLinkedListForPalindrome(head);
		Stack<LinkedListNode> rightPart = new Stack<LinkedListNode>();//定义栈rightPart
		midInPalindrome = midInPalindrome.next;
		while (midInPalindrome != null) {
			rightPart.add(midInPalindrome);//将节点加入栈rightPart中
			midInPalindrome = midInPalindrome.next;
		}
		LinkedListNode current = head;
		while (!rightPart.isEmpty()) {
			LinkedListNode fromRightToMidNode = rightPart.pop();//因为压栈是从中到右,弹栈从右到中
			if (fromRightToMidNode.value != current.value) {//右！=左
				return false;
			}
			current = current.next;
		}
		return true;
	}

	// need no extra data structure
	public static boolean isPalindromeNeedNoExtra(LinkedListNode head) {
		if (head == null) {
			return false;
		}
		if (head.next == null) {
			return true;
		}
		LinkedListNode midInPalindrome = findMidNodeInLinkedListForPalindrome(head);
		LinkedListNode previousNode = midInPalindrome;
		LinkedListNode current = midInPalindrome.next;
		midInPalindrome.next = null;
		// 1->2->3->2->1 To : 1->2->3<-2<-1
		while (current != null) {//重链
			LinkedListNode nextNode = current.next;
			current.next = previousNode;
			previousNode = current;
			current = nextNode;
		}
		LinkedListNode lastNodeRecord = previousNode;
		LinkedListNode currentFromLeftToMid = head;
		LinkedListNode currentFromRightToMid = lastNodeRecord;
		// check Palindrome
		boolean result = true;
		while (currentFromRightToMid != midInPalindrome) {
			if (currentFromLeftToMid.value != currentFromRightToMid.value) {
				result = false;
				break;
			}
			currentFromLeftToMid = currentFromLeftToMid.next;
			currentFromRightToMid = currentFromRightToMid.next;
		}
		previousNode = lastNodeRecord;
		currentFromRightToMid = lastNodeRecord.next;
		lastNodeRecord.next = null;
		// recover linked list恢复链表
		while (currentFromRightToMid != null) {
			LinkedListNode nextNode = currentFromRightToMid.next;
			currentFromRightToMid.next = previousNode;
			previousNode = currentFromRightToMid;
			currentFromRightToMid = nextNode;
		}
		return result;
	}

	public static LinkedListNode findMidNodeInLinkedListForPalindrome(
			LinkedListNode head) {
		LinkedListNode midInPalindrome = head;
		LinkedListNode current = head;
		int times = 0;
		while (current != null) {
			if(times++==2){//current走两步,midInPalindrome移动一步
				midInPalindrome = midInPalindrome.next;
				times = 1;
			}//midInPalindrome只有当times=2时才移动,
			current=current.next;//current随times++而动
		}
		return midInPalindrome;
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
		// odd numbers of elements奇数个元素，有轴
		LinkedListNode head1 = new LinkedListNode(1);
		head1.next = new LinkedListNode(2);
		head1.next.next = new LinkedListNode(3);
		head1.next.next.next = new LinkedListNode(4);
		head1.next.next.next.next = new LinkedListNode(5);
		head1.next.next.next.next.next = new LinkedListNode(4);
		head1.next.next.next.next.next.next = new LinkedListNode(3);
		head1.next.next.next.next.next.next.next = new LinkedListNode(2);
		head1.next.next.next.next.next.next.next.next = new LinkedListNode(1);
		printLinkedList(head1);
		System.out.println(isPalindromeNeedExtra(head1));
		System.out.println(isPalindromeNeedNoExtra(head1));

		// even numbers of elements偶数个元素，无轴
		LinkedListNode head2 = new LinkedListNode(1);
		head2.next = new LinkedListNode(2);
		head2.next.next = new LinkedListNode(3);
		head2.next.next.next = new LinkedListNode(3);
		head2.next.next.next.next = new LinkedListNode(2);
		head2.next.next.next.next.next = new LinkedListNode(1);
		printLinkedList(head2);
		System.out.println(isPalindromeNeedExtra(head2));
		System.out.println(isPalindromeNeedNoExtra(head2));

	}

}
