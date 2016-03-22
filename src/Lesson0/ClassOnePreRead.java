package Lesson0;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// ���ļ���main�����������в�������
public class ClassOnePreRead {

	// ������ڵ�Ľṹ
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {//���캯��
			this.value = data;
		}
	}

	// �������˳���ӡ
	public static void printLinkedListNode(LinkedListNode node) {
		LinkedListNode tmp = node;
		while (tmp != null) {
			System.out.print(tmp.value + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}

	// ˫����ڵ�Ľṹ
	public static class DoubleLinkedListNode {
		public int value;
		public DoubleLinkedListNode last;
		public DoubleLinkedListNode next;

		public DoubleLinkedListNode(int data) {
			this.value = data;
		}
	}

	// ˫�����˳��������ӡ
	public static void printDoubleLinkedListNode(DoubleLinkedListNode node) {
		DoubleLinkedListNode tmp = node;
		if (tmp == null) {
			return;
		} else {
			System.out.print(tmp.value + " ");
		}
		while (tmp.next != null) {
			tmp = tmp.next;
			System.out.print(tmp.value + " ");
		}
		System.out.print("| ");
		while (tmp != null) {
			System.out.print(tmp.value + " ");
			tmp = tmp.last;
		}
		System.out.println();
	}

	// չʾջ�ṹ������
	public static void stackPrint() {
		// java��ջ�ṹ�Ĵ���
		// ջ�ṹ(Stack)��java����һ��ʵ����
		Stack<Integer> stack = new Stack<Integer>();

		//��ջ�ṹ��ѹ������
		stack.push(1);
		stack.push(2);

		// ���ջ�ṹ���Ƿ�������
		if (stack.isEmpty()) {
			System.out.println("stack is empty.");
		} else {
			System.out.println("stack is not empty.");
		}

		// ջ�ṹ��������(pop)����ǧ��ע����ʵ�ִ���ʱһ��Ҫ�ȼ��ջ�Ƿ�Ϊ��
		// ���ջΪ��ʱ����ִ�е������ݵĲ����ᷢ������
		if (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}

		// ����ջ��Ԫ��(peek)����ǧ��ע����ʵ�ִ���ʱһ��Ҫ�ȼ��ջ�Ƿ�Ϊ��
		// ���ջΪ��ʱ����ִ�з���ջ��Ԫ�صĲ����ᷢ������
		if (!stack.isEmpty()) {
			System.out.println(stack.peek());
		}

		// ���㷨�����ݽṹˢ��ʱ������ջ�ṹ����ֻ�û�push��pop��peek��isEmpty�����ֲ�������
		// java��Stack�ṹ���⻹�кܶ�Ĳ���������Ȥ��ͬѧ����ȥ�о���������ˢ����˵4�ֲ����㹻�ˣ�

	}

	// չʾ���нṹ������
	public static void queuePrint() {
		// java�ж��нṹ�Ĵ���
		// ���нṹ(Queue)��java����һ���ӿڣ�����������ʵ����ʵ��
		// �����Ƽ���java���е�LinkedList�ṹʵ��
		Queue<Integer> queue = new LinkedList<Integer>();

		// ����нṹ��ѹ������
		queue.offer(1);
		// ����
		queue.add(2);

		// �����нṹ���Ƿ�������
		if (queue.isEmpty()) {
			System.out.println("Queue is empty.");
		} else {
			System.out.println("Queue is not empty.");
		}

		// ���нṹ��������(poll)����ǧ��ע����ʵ�ִ���ʱһ��Ҫ�ȼ������Ƿ�Ϊ��
		// �������Ϊ��ʱ����ִ�е������ݵĲ����ᷢ������
		if (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}

		// ���ض���ͷԪ��(peek)����ǧ��ע����ʵ�ִ���ʱһ��Ҫ�ȼ������Ƿ�Ϊ��
		// �������Ϊ��ʱ����ִ�з��ض���ͷԪ�صĲ����ᷢ������
		if (!queue.isEmpty()) {
			System.out.println(queue.peek());
		}

		// ���㷨�����ݽṹˢ��ʱ�����ڶ��нṹ����ֻ�û�offer(add)��poll��peek��isEmpty�����ֲ�������
		// java��Queue�ṹ���⻹�кܶ�Ĳ���������Ȥ��ͬѧ����ȥ�о���������ˢ����˵4�ֲ����㹻�ˣ�

	}

	// �������ڵ�ṹ
	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	// ���������ӡ������(�У�����)���ݹ鷽ʽ
	public static void printBinaryTreePreOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		printBinaryTreePreOrder(head.left);
		printBinaryTreePreOrder(head.right);
	}

	// ���������ӡ������(���У���)���ݹ鷽ʽ
	public static void printBinaryTreeInOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printBinaryTreePreOrder(head.left);
		System.out.print(head.value + " ");
		printBinaryTreePreOrder(head.right);
	}

	// ���������ӡ������(�ң�����)���ݹ鷽ʽ
	public static void printBinaryTreeProOrder1(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printBinaryTreePreOrder(head.right);
		printBinaryTreePreOrder(head.left);
		System.out.print(head.value + " ");
	}

	// ���������ӡ������(���ң���)���ݹ鷽ʽ
	public static void printBinaryTreeProOrder2(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printBinaryTreePreOrder(head.left);
		printBinaryTreePreOrder(head.right);
		System.out.print(head.value + " ");
	}

	// ����˳���ӡ���������ǵݹ鷽ʽ���ö���ʵ�֡�
	// ���Ȿ��Ϊһ�������⣬�÷�����ΪԤϰ�����ṩ����ʽ���ϻ���ܸ�Ϊţ�Ƶİ����ӡ������
	public static void printBinaryTreeByLevel(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		Queue<BinaryTreeNode> nodesQueue = new LinkedList<BinaryTreeNode>();
		nodesQueue.offer(head);
		while (!nodesQueue.isEmpty()) {
			BinaryTreeNode tmp = nodesQueue.poll();
			System.out.print(tmp.value + " ");
			if (tmp.left != null) {
				nodesQueue.offer(tmp.left);
			}
			if (tmp.right != null) {
				nodesQueue.offer(tmp.right);
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedListNode node1 = new LinkedListNode(1);
		node1.next = new LinkedListNode(2);
		node1.next.next = new LinkedListNode(3);
		node1.next.next.next = new LinkedListNode(4);
		printLinkedListNode(node1);

		DoubleLinkedListNode node2 = new DoubleLinkedListNode(1);
		node2.next = new DoubleLinkedListNode(2);
		node2.next.last = node2;
		node2.next.next = new DoubleLinkedListNode(3);
		node2.next.next.last = node2.next;
		node2.next.next.next = new DoubleLinkedListNode(4);
		node2.next.next.next.last = node2.next.next;
		printDoubleLinkedListNode(node2);

		stackPrint();

		queuePrint();

		BinaryTreeNode head = new BinaryTreeNode(1);
		head.left = new BinaryTreeNode(2);
		head.right = new BinaryTreeNode(3);
		head.left.left = new BinaryTreeNode(4);
		head.left.right = new BinaryTreeNode(5);
		head.right.left = new BinaryTreeNode(6);
		head.right.right = new BinaryTreeNode(7);

		printBinaryTreePreOrder(head);
		System.out.println();
		printBinaryTreeInOrder(head);
		System.out.println();
		printBinaryTreeProOrder1(head);
		System.out.println();
		printBinaryTreeProOrder2(head);
		System.out.println();
		printBinaryTreeByLevel(head);

	}

}
