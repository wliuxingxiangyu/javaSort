package classOne;

import java.util.Stack;

public class IsPalindromeLinkedList {//L1p10��������Ƿ����
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
		//���¾�:�ҵ������м�Ľڵ�
		LinkedListNode midInPalindrome = findMidNodeInLinkedListForPalindrome(head);
		Stack<LinkedListNode> rightPart = new Stack<LinkedListNode>();//����ջrightPart
		midInPalindrome = midInPalindrome.next;
		while (midInPalindrome != null) {
			rightPart.add(midInPalindrome);//���ڵ����ջrightPart��
			midInPalindrome = midInPalindrome.next;
		}
		LinkedListNode current = head;
		while (!rightPart.isEmpty()) {
			LinkedListNode fromRightToMidNode = rightPart.pop();//��Ϊѹջ�Ǵ��е���,��ջ���ҵ���
			if (fromRightToMidNode.value != current.value) {//�ң�=��
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
		while (current != null) {//����
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
		// recover linked list�ָ�����
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
			if(times++==2){//current������,midInPalindrome�ƶ�һ��
				midInPalindrome = midInPalindrome.next;
				times = 1;
			}//midInPalindromeֻ�е�times=2ʱ���ƶ�,
			current=current.next;//current��times++����
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
		// odd numbers of elements������Ԫ�أ�����
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

		// even numbers of elementsż����Ԫ�أ�����
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
