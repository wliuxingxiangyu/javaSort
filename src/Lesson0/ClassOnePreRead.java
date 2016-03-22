package Lesson0;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 本文件的main函数包含所有测试用例
public class ClassOnePreRead {

	// 单链表节点的结构
	public static class LinkedListNode {
		public int value;
		public LinkedListNode next;

		public LinkedListNode(int data) {//构造函数
			this.value = data;
		}
	}

	// 单链表的顺序打印
	public static void printLinkedListNode(LinkedListNode node) {
		LinkedListNode tmp = node;
		while (tmp != null) {
			System.out.print(tmp.value + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}

	// 双链表节点的结构
	public static class DoubleLinkedListNode {
		public int value;
		public DoubleLinkedListNode last;
		public DoubleLinkedListNode next;

		public DoubleLinkedListNode(int data) {
			this.value = data;
		}
	}

	// 双链表的顺序和逆序打印
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

	// 展示栈结构的内容
	public static void stackPrint() {
		// java中栈结构的创建
		// 栈结构(Stack)在java中是一个实体类
		Stack<Integer> stack = new Stack<Integer>();

		//向栈结构中压入数据
		stack.push(1);
		stack.push(2);

		// 检查栈结构中是否有数据
		if (stack.isEmpty()) {
			System.out.println("stack is empty.");
		} else {
			System.out.println("stack is not empty.");
		}

		// 栈结构弹出数据(pop)，请千万注意在实现代码时一定要先检查栈是否为空
		// 如果栈为空时，还执行弹出数据的操作会发生错误
		if (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}

		// 返回栈顶元素(peek)，请千万注意在实现代码时一定要先检查栈是否为空
		// 如果栈为空时，还执行返回栈顶元素的操作会发生错误
		if (!stack.isEmpty()) {
			System.out.println(stack.peek());
		}

		// 在算法和数据结构刷题时，对于栈结构，你只用会push、pop、peek和isEmpty这四种操作即可
		// java中Stack结构额外还有很多的操作，有兴趣的同学可以去研究，但对于刷题来说4种操作足够了！

	}

	// 展示队列结构的内容
	public static void queuePrint() {
		// java中队列结构的创建
		// 队列结构(Queue)在java中是一个接口，必须用其他实体类实现
		// 这里推荐用java库中的LinkedList结构实现
		Queue<Integer> queue = new LinkedList<Integer>();

		// 向队列结构中压入数据
		queue.offer(1);
		// 或者
		queue.add(2);

		// 检查队列结构中是否有数据
		if (queue.isEmpty()) {
			System.out.println("Queue is empty.");
		} else {
			System.out.println("Queue is not empty.");
		}

		// 队列结构弹出数据(poll)，请千万注意在实现代码时一定要先检查队列是否为空
		// 如果队列为空时，还执行弹出数据的操作会发生错误
		if (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}

		// 返回队列头元素(peek)，请千万注意在实现代码时一定要先检查队列是否为空
		// 如果队列为空时，还执行返回队列头元素的操作会发生错误
		if (!queue.isEmpty()) {
			System.out.println(queue.peek());
		}

		// 在算法和数据结构刷题时，对于队列结构，你只用会offer(add)、poll、peek和isEmpty这四种操作即可
		// java中Queue结构额外还有很多的操作，有兴趣的同学可以去研究，但对于刷题来说4种操作足够了！

	}

	// 二叉树节点结构
	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	// 先序遍历打印二叉树(中，左，右)，递归方式
	public static void printBinaryTreePreOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		printBinaryTreePreOrder(head.left);
		printBinaryTreePreOrder(head.right);
	}

	// 中序遍历打印二叉树(左，中，右)，递归方式
	public static void printBinaryTreeInOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printBinaryTreePreOrder(head.left);
		System.out.print(head.value + " ");
		printBinaryTreePreOrder(head.right);
	}

	// 后序遍历打印二叉树(右，左，中)，递归方式
	public static void printBinaryTreeProOrder1(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printBinaryTreePreOrder(head.right);
		printBinaryTreePreOrder(head.left);
		System.out.print(head.value + " ");
	}

	// 后序遍历打印二叉树(左，右，中)，递归方式
	public static void printBinaryTreeProOrder2(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printBinaryTreePreOrder(head.left);
		printBinaryTreePreOrder(head.right);
		System.out.print(head.value + " ");
	}

	// 按层顺序打印二叉树，非递归方式，用队列实现。
	// 本题本身为一道面试题，该方法仅为预习材料提供，正式课上会介绍更为牛逼的按层打印方法。
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
