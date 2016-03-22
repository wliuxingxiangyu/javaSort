package classOne;
//ֵ�������߽�����������////L1p9���С���м�ȣ��ұߴ�����
public class LeftSmallerMidEqualRightBigger {
	public static class LinkedListNode {//����������
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode leftSmallerMidEqualRightBigger(
			LinkedListNode head, int apartValue) {//����������
		if (head == null || head.next == null) {//0������ڵ�����������ۣ�����
			return head;
		}
		LinkedListNode current = head;//
		int length = 0;
		while (current != null) {
			length++;
			current = current.next;
		}
		LinkedListNode[] linkedListNodeArr = new LinkedListNode[length];
		length = 0;
		current = head;
		while (current != null) {
			linkedListNodeArr[length++] = current;
			current = current.next;
		}
		partition(linkedListNodeArr, apartValue);
		for (int i = 1; i != linkedListNodeArr.length; i++) {
			linkedListNodeArr[i - 1].next = linkedListNodeArr[i];
		}//������ֵ������
		linkedListNodeArr[linkedListNodeArr.length - 1].next = null;
		return linkedListNodeArr[0];
	}

	public static void partition(LinkedListNode[] linkedListNodeArr,
			int apartValue) {
		int smallerIndex = -1;
		int biggerIndex = linkedListNodeArr.length;
		int index = 0;
		while (index != biggerIndex) {
			if (linkedListNodeArr[index].value < apartValue) {
				swap(linkedListNodeArr, ++smallerIndex, index++);
			} else if (linkedListNodeArr[index].value > apartValue) {
				swap(linkedListNodeArr, --biggerIndex, index);
			} else {
				index++;
			}
		}
	}

	public static void swap(LinkedListNode[] linkedListNodeArr, int index1,
			int index2) {
		LinkedListNode tmp = linkedListNodeArr[index1];
		linkedListNodeArr[index1] = linkedListNodeArr[index2];
		linkedListNodeArr[index2] = tmp;
	}

	public static LinkedListNode leftSmallerMidEqualRightBiggerBetter(
			LinkedListNode head, int apartValue) {//����������
		LinkedListNode firstSmallerNode = null;//����������
		LinkedListNode firstEqualNode = null;
		LinkedListNode firstBiggerNode = null;
		LinkedListNode current = head;
		while (current != null) {//��firstSmallerNode
			if (current.value < apartValue) {
				firstSmallerNode = current;
				break;
			}
			current = current.next;
		}
		current = head;
		while (current != null) {
			if (current.value == apartValue) {
				firstEqualNode = current;
				break;
			}
			current = current.next;
		}
		current = head;
		while (current != null) {//��firstBiggerNode
			if (current.value > apartValue) {
				firstBiggerNode = current;
				break;
			}
			current = current.next;
		}
		current = head;
		while (current != null) {
			LinkedListNode nextNode = current.next;//���¶���ֲ�����nextNode
			if (current != firstSmallerNode && current != firstEqualNode
					&& current != firstBiggerNode) {//���ǵ�һ��С�ҵ��Ҵ󣬽���if
				if (current.value == apartValue) {//��ǰ����apartValue
					LinkedListNode nextEqualNode=firstEqualNode.next;
					current.next = nextEqualNode;
					firstEqualNode.next = current;
				} else if (current.value < apartValue) {//��ǰС��apartValue
					LinkedListNode nextSmallerNode=firstSmallerNode.next;
					current.next = nextSmallerNode;
					firstSmallerNode.next = current;
				} else {//��ǰ����apartValue
					LinkedListNode nextBiggerNode=firstBiggerNode.next;
					current.next = nextBiggerNode;//9ʱΪ�սڵ㣬
					firstBiggerNode.next = current;
					//����3������:��firstBiggerNode��nextBiggerNode�����current
				}
			} else {//�ǵ�һ��С��Ȼ��
				if(current!=null){//����
				System.out.println("current.value="+current.value);}
				current.next = null;//����
			}
			current = nextNode;
		}
		
		printLinkedList(firstBiggerNode);//����
		printLinkedList(firstEqualNode);//����
		printLinkedList(firstSmallerNode);//����
		
		current = firstSmallerNode;
		LinkedListNode lastSmallerNode = null;
		while (current != null) {//�ҵ�Small���� ���һ�� �ڵ㣬
			lastSmallerNode = current;
			current = current.next;
		}
		
		current = firstEqualNode;
		LinkedListNode lastEqualNode = null;
		while (current != null) {//�ҵ�Equal���� ���һ�� �ڵ�
			lastEqualNode = current;
			current = current.next;
		}
		
		if (lastSmallerNode != null) {
			if (firstEqualNode != null) {//lastSmall���ϣ����ڵ�firstEqualNode,
				lastSmallerNode.next = firstEqualNode;
			} else {//������firstEqualNode,ֱ�ӹ���firstBiggerNode
				lastSmallerNode.next = firstBiggerNode;
			}
		}
		if (lastEqualNode != null) {
			lastEqualNode.next = firstBiggerNode;
		}
		if (firstSmallerNode != null) {
			return firstSmallerNode;//��������firstSmallerNode
		} else {
			return firstEqualNode != null ? firstEqualNode : firstBiggerNode;
		}
	}

	public static void printLinkedList(LinkedListNode head) {//��ӡ����
		LinkedListNode current = head;
		System.out.println("Linked List:");
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedListNode head1 = new LinkedListNode(8);//8Ϊhead1������
		head1.next = new LinkedListNode(9);//����,��Ϊ�����Լ����õ�,���Բ���дwhile����
		head1.next.next = new LinkedListNode(10);
		head1.next.next.next = new LinkedListNode(7);
		head1.next.next.next.next = new LinkedListNode(4);
		head1.next.next.next.next.next = new LinkedListNode(5);
		head1.next.next.next.next.next.next = new LinkedListNode(7);
		head1.next.next.next.next.next.next.next = new LinkedListNode(1);
		head1.next.next.next.next.next.next.next.next = new LinkedListNode(6);
		printLinkedList(head1);
//		 head1 = leftSmallerMidEqualRightBigger(head1,7);
		head1 = leftSmallerMidEqualRightBiggerBetter(head1, 7);
		printLinkedList(head1);

	}

}
