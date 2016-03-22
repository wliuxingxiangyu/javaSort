package Lesson0;




public class ReverseLinkedList {//逆置单链表,先写中间while一般情况,再考虑头尾。
	
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}
	
	public static void printLinkedList(LinkedListNode head) {
		LinkedListNode current = head;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}
	
	public static LinkedListNode Reverse(LinkedListNode head){
		LinkedListNode p,q,r;
		r=head;
		p=head.next;
		q=head.next.next;
		head.next=null;

		while(p!= null){
			p.next=r;//挂链
			r=p;
			p=q;
			if(q!= null){
			q=q.next;
			}else{
				return r;	
			}
		}
		return r;	
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
		head.next.next.next.next.next.next.next.next = null;
		
		System.out.println("逆置前");
		printLinkedList(head);
		LinkedListNode head1;
		head1 = Reverse(head);//不需要额外空间
		System.out.println("逆置后");
		printLinkedList(head1);
		

	}

}
