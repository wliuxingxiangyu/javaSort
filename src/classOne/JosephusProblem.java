package classOne;

public class JosephusProblem {//L1P18单链表的约瑟夫问题(报数被杀)

	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode killOtherReturnSurviveNode(
			LinkedListNode head, int times){//报数times时杀人
		if (head.next == head) {
			return head;
		}
		
		//以下为了：找到最后一节点,进函数时head为环状链表,但是单向的,只能从头往后,直到倒数第一个
		LinkedListNode current=head.next;//current开始跑初试化
		while (current.next != head){
			current = current.next;
		}//current指“倒数第一个”,
		LinkedListNode previous = current;//previous也指“倒数第一个”,
		
		current = head;
		int killtimes = 1;
		while (current.next!=current){//指向自己,即只剩下一个
			if (killtimes==times) {
				previous.next = current.next;//挂链
				current.next = null;//断链
				current=previous.next;//断链后的第一个初试化继续跑
				killtimes = 1;//从1开始报数
			}else{//报数killtimes++的同时,previous跟着current跑，
				previous = current;
				current = current.next;
				killtimes++;
			}
		}
		return current;
	}

	public static LinkedListNode killOtherReturnSurviveNodeBetter(
			LinkedListNode head, int k){//报数1--k
		if (head == null)
			return null;
		int index = 1;
		LinkedListNode current = head.next;
		if (current == head)//只有一个节点
			return head;
		while (current != head) {
			if (current == null)
				return null;
			index++;
			current = current.next;
		}//while出来后记下current=head时index值
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
	//all每轮报数总人数index=5,killCount=100被杀的编号 k,
		if (all == 1)
			return 1;
		int newpos=joseph(all - 1, killCount);
		int oldpos=(newpos+killCount-1)%all+1;//公式难推
		return oldpos;
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->1
		LinkedListNode head = new LinkedListNode(1);
		head.next = new LinkedListNode(2);
		head.next.next = new LinkedListNode(3);
		head.next.next.next = new LinkedListNode(4);
		head.next.next.next.next = new LinkedListNode(5);
		head.next.next.next.next.next = head;//5人报数100下，看谁最后存活
		System.out.println(killOtherReturnSurviveNode(head,100).value);

//		// 1->2->3->4->5->1
//		head = new LinkedListNode(1);//第二种方法公式难推
//		head.next = new LinkedListNode(2);
//		head.next.next = new LinkedListNode(3);
//		head.next.next.next = new LinkedListNode(4);
//		head.next.next.next.next = new LinkedListNode(5);
//		head.next.next.next.next.next = head;//5人报数100下，看谁最后存活
//		System.out.println(killOtherReturnSurviveNodeBetter(head,100).value);

	}

}