package classOne;

public class JosephusProblem {//L1P18�������Լɪ������(������ɱ)

	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode killOtherReturnSurviveNode(
			LinkedListNode head, int times){//����timesʱɱ��
		if (head.next == head) {
			return head;
		}
		
		//����Ϊ�ˣ��ҵ����һ�ڵ�,������ʱheadΪ��״����,���ǵ����,ֻ�ܴ�ͷ����,ֱ��������һ��
		LinkedListNode current=head.next;//current��ʼ�ܳ��Ի�
		while (current.next != head){
			current = current.next;
		}//currentָ��������һ����,
		LinkedListNode previous = current;//previousҲָ��������һ����,
		
		current = head;
		int killtimes = 1;
		while (current.next!=current){//ָ���Լ�,��ֻʣ��һ��
			if (killtimes==times) {
				previous.next = current.next;//����
				current.next = null;//����
				current=previous.next;//������ĵ�һ�����Ի�������
				killtimes = 1;//��1��ʼ����
			}else{//����killtimes++��ͬʱ,previous����current�ܣ�
				previous = current;
				current = current.next;
				killtimes++;
			}
		}
		return current;
	}

	public static LinkedListNode killOtherReturnSurviveNodeBetter(
			LinkedListNode head, int k){//����1--k
		if (head == null)
			return null;
		int index = 1;
		LinkedListNode current = head.next;
		if (current == head)//ֻ��һ���ڵ�
			return head;
		while (current != head) {
			if (current == null)
				return null;
			index++;
			current = current.next;
		}//while���������current=headʱindexֵ
		int resultIndex=joseph(index, k);
		current = head;
		while (resultIndex != 1) {
			resultIndex--;
			current = current.next;
		}
		current.next = current;
		return current;
	}

	public static int joseph(int all,int killCount){
	//allÿ�ֱ���������index=5,killCount=100��ɱ�ı�� k,
		if (all == 1)
			return 1;
		int newpos=joseph(all - 1, killCount);
		int oldpos=(newpos+killCount-1)%all+1;//��ʽ����
		return oldpos;
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->1
		LinkedListNode head = new LinkedListNode(1);
		head.next = new LinkedListNode(2);
		head.next.next = new LinkedListNode(3);
		head.next.next.next = new LinkedListNode(4);
		head.next.next.next.next = new LinkedListNode(5);
		head.next.next.next.next.next = head;//5�˱���100�£���˭�����
		System.out.println(killOtherReturnSurviveNode(head,100).value);

//		// 1->2->3->4->5->1
//		head = new LinkedListNode(1);//�ڶ��ַ�����ʽ����
//		head.next = new LinkedListNode(2);
//		head.next.next = new LinkedListNode(3);
//		head.next.next.next = new LinkedListNode(4);
//		head.next.next.next.next = new LinkedListNode(5);
//		head.next.next.next.next.next = head;//5�˱���100�£���˭�����
//		System.out.println(killOtherReturnSurviveNodeBetter(head,100).value);

	}

}