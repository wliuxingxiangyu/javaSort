package classOne;

public class AddTwoLinkedList {//L1P17����������ͣ����ܻ��н�λ��
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode generateSumLinkedList(LinkedListNode head1,
			LinkedListNode head2) {//���
		LinkedListNode reverseHead1=reverseLinkedList(head1);//��Ϊ�����յĸ�λ�ȼ�,
		LinkedListNode reverseHead2=reverseLinkedList(head2);
		LinkedListNode current1 = reverseHead1;
		LinkedListNode current2 = reverseHead2;
		LinkedListNode result = null;
		boolean carry = false;
		while(current1!=null&&current2!=null){//ͬʱ��Ϊ��
			int num = current1.value + current2.value + (carry ? 1 : 0);
			LinkedListNode sumCurrent = new LinkedListNode(num % 10);//��λ
			sumCurrent.next = result;//��β��result=null��ʼ���Ž���
			result = sumCurrent;//��result
			carry = num > 9 ? true : false;
			current1=current1.next;//ÿ���ڵ㶼��next��,ֻ�����սڵ�next��Ϊ��,while��
			current2 = current2.next;
		}
		current1=current1!=null?current1:(current2!=null?current2:null);
	//current1�ǿ�ʱ,Ϊcurrent1;��ʱ,Ϊcurrent2.�Ӵ˷ǿ�current1��2ֻҪ����current1���ɡ�
		while (current1 != null) {
			int num = current1.value + (carry ? 1 : 0);
			LinkedListNode sumCurrent = new LinkedListNode(num % 10);
			sumCurrent.next = result;//��β��result=null��ʼ���Ž���
			result = sumCurrent;//��result
			carry = num > 9 ? true : false;
			current1 = current1.next;
		}
		if (carry) { 
			LinkedListNode sumCurrent = new LinkedListNode(1);
			sumCurrent.next = result;//��β��result=null��ʼ���Ž���
			result = sumCurrent;//��result
		}
		reverseLinkedList(reverseHead1);//��ΪreverseLinkedList����ԭ��������
		reverseLinkedList(reverseHead2);
		return result;
	}

	public static LinkedListNode reverseLinkedList(LinkedListNode head){//��������
		if (head == null || head.next == null) {
			return head;
		}
		LinkedListNode result = head;
		LinkedListNode current = head.next;
		result.next = null;
		while (current != null) {
			LinkedListNode nextNode=current.next;//���¼���������Ŀ�Ľڵ�
			current.next=result;//����
			result=current;//����ͷ�ڵ�result
			current=nextNode;//����current
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

		LinkedListNode result=generateSumLinkedList(head1,head2);//���
		printLinkedList(head1);
		printLinkedList(head2);
		printLinkedList(result);

	}

}
