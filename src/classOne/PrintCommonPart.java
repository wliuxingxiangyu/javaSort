package classOne;

public class PrintCommonPart {

	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static void printLinkedList(LinkedListNode node) {
		LinkedListNode current = node;
		System.out.print("Linked List: ");
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void printCommonPart(LinkedListNode node1,
			LinkedListNode node2){//node1,2必须有序
		if (node1 == null || node2 == null)
			return;
		LinkedListNode current1 = node1;
		LinkedListNode current2 = node2;
		System.out.print("Common Part: ");
		while (current1!= null&&current2!=null){//同时为空跳出
			if(current1.value==current2.value){//相等时,同时跑
				System.out.print(current1.value + " ");
				current1 = current1.next;
				current2 = current2.next;
			} else if (current1.value < current2.value) {
				current1 = current1.next;//谁小谁跑
			} else {//谁 current2小谁跑
				current2 = current2.next;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedListNode node1 = new LinkedListNode(2);
		node1.next = new LinkedListNode(8);
		node1.next.next = new LinkedListNode(3);
		node1.next.next.next = new LinkedListNode(5);
		
		LinkedListNode node2 = new LinkedListNode(1);
		node2.next = new LinkedListNode(2);
		node2.next.next = new LinkedListNode(5);
		node2.next.next.next = new LinkedListNode(7);
		node2.next.next.next.next = new LinkedListNode(8);

		printLinkedList(node1);
		printLinkedList(node2);
		printCommonPart(node1, node2);

	}

}
