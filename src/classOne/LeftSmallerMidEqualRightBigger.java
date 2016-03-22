package classOne;
//值得做，边界讨论能力，////L1p9左边小，中间等，右边大，链表
public class LeftSmallerMidEqualRightBigger {
	public static class LinkedListNode {//定义链表类
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {
			this.value = data;
		}
	}

	public static LinkedListNode leftSmallerMidEqualRightBigger(
			LinkedListNode head, int apartValue) {//传两个参数
		if (head == null || head.next == null) {//0个或个节点的链表，不讨论，返回
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
		}//把所有值穿起来
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
			LinkedListNode head, int apartValue) {//传两个参数
		LinkedListNode firstSmallerNode = null;//不用容器，
		LinkedListNode firstEqualNode = null;
		LinkedListNode firstBiggerNode = null;
		LinkedListNode current = head;
		while (current != null) {//找firstSmallerNode
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
		while (current != null) {//找firstBiggerNode
			if (current.value > apartValue) {
				firstBiggerNode = current;
				break;
			}
			current = current.next;
		}
		current = head;
		while (current != null) {
			LinkedListNode nextNode = current.next;//重新定义局部变量nextNode
			if (current != firstSmallerNode && current != firstEqualNode
					&& current != firstBiggerNode) {//不是第一个小且等且大，进入if
				if (current.value == apartValue) {//当前等于apartValue
					LinkedListNode nextEqualNode=firstEqualNode.next;
					current.next = nextEqualNode;
					firstEqualNode.next = current;
				} else if (current.value < apartValue) {//当前小于apartValue
					LinkedListNode nextSmallerNode=firstSmallerNode.next;
					current.next = nextSmallerNode;
					firstSmallerNode.next = current;
				} else {//当前大于apartValue
					LinkedListNode nextBiggerNode=firstBiggerNode.next;
					current.next = nextBiggerNode;//9时为空节点，
					firstBiggerNode.next = current;
					//以上3行作用:在firstBiggerNode和nextBiggerNode间插入current
				}
			} else {//是第一个小或等或大，
				if(current!=null){//调试
				System.out.println("current.value="+current.value);}
				current.next = null;//断链
			}
			current = nextNode;
		}
		
		printLinkedList(firstBiggerNode);//调试
		printLinkedList(firstEqualNode);//调试
		printLinkedList(firstSmallerNode);//调试
		
		current = firstSmallerNode;
		LinkedListNode lastSmallerNode = null;
		while (current != null) {//找到Small链中 最后一个 节点，
			lastSmallerNode = current;
			current = current.next;
		}
		
		current = firstEqualNode;
		LinkedListNode lastEqualNode = null;
		while (current != null) {//找到Equal链中 最后一个 节点
			lastEqualNode = current;
			current = current.next;
		}
		
		if (lastSmallerNode != null) {
			if (firstEqualNode != null) {//lastSmall挂上，存在的firstEqualNode,
				lastSmallerNode.next = firstEqualNode;
			} else {//不存在firstEqualNode,直接挂上firstBiggerNode
				lastSmallerNode.next = firstBiggerNode;
			}
		}
		if (lastEqualNode != null) {
			lastEqualNode.next = firstBiggerNode;
		}
		if (firstSmallerNode != null) {
			return firstSmallerNode;//返回左侧的firstSmallerNode
		} else {
			return firstEqualNode != null ? firstEqualNode : firstBiggerNode;
		}
	}

	public static void printLinkedList(LinkedListNode head) {//打印链表
		LinkedListNode current = head;
		System.out.println("Linked List:");
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedListNode head1 = new LinkedListNode(8);//8为head1数据域
		head1.next = new LinkedListNode(9);//创链,因为数是自己设置的,所以不能写while创链
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
