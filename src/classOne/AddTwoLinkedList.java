package classOne;

public class AddTwoLinkedList {//L1P17两个链表求和（可能会有进位）
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode generateSumLinkedList(LinkedListNode head1,
			LinkedListNode head2) {//求和
		LinkedListNode reverseHead1=reverseLinkedList(head1);//因为靠近空的个位先加,
		LinkedListNode reverseHead2=reverseLinkedList(head2);
		LinkedListNode current1 = reverseHead1;
		LinkedListNode current2 = reverseHead2;
		LinkedListNode result = null;
		boolean carry = false;
		while(current1!=null&&current2!=null){//同时不为空
			int num = current1.value + current2.value + (carry ? 1 : 0);
			LinkedListNode sumCurrent = new LinkedListNode(num % 10);//个位
			sumCurrent.next = result;//从尾部result=null开始倒着建链
			result = sumCurrent;//移result
			carry = num > 9 ? true : false;
			current1=current1.next;//每个节点都有next域,只不过空节点next域为空,while判
			current2 = current2.next;
		}
		current1=current1!=null?current1:(current2!=null?current2:null);
	//current1非空时,为current1;空时,为current2.从此非空current1或2只要讨论current1即可。
		while (current1 != null) {
			int num = current1.value + (carry ? 1 : 0);
			LinkedListNode sumCurrent = new LinkedListNode(num % 10);
			sumCurrent.next = result;//从尾部result=null开始倒着建链
			result = sumCurrent;//移result
			carry = num > 9 ? true : false;
			current1 = current1.next;
		}
		if (carry) { 
			LinkedListNode sumCurrent = new LinkedListNode(1);
			sumCurrent.next = result;//从尾部result=null开始倒着建链
			result = sumCurrent;//移result
		}
		reverseLinkedList(reverseHead1);//因为reverseLinkedList是在原链上做的
		reverseLinkedList(reverseHead2);
		return result;
	}

	public static LinkedListNode reverseLinkedList(LinkedListNode head){//逆置链表
		if (head == null || head.next == null) {
			return head;
		}
		LinkedListNode result = head;
		LinkedListNode current = head.next;
		result.next = null;
		while (current != null) {
			LinkedListNode nextNode=current.next;//记下即将断链的目的节点
			current.next=result;//挂链
			result=current;//更新头节点result
			current=nextNode;//更新current
		}
		return result;
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
		LinkedListNode head1 = new LinkedListNode(9);
		head1.next = new LinkedListNode(8);

		LinkedListNode head2 = new LinkedListNode(2);
		head2.next = new LinkedListNode(1);
		head2.next.next = new LinkedListNode(3);

		LinkedListNode result=generateSumLinkedList(head1,head2);//求和
		printLinkedList(head1);
		printLinkedList(head2);
		printLinkedList(result);

	}

}
