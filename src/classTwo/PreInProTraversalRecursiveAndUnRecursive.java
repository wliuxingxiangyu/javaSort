package classTwo;//预习0

import java.util.Stack;

public class PreInProTraversalRecursiveAndUnRecursive {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	public static void proOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		proOrderRecur(head.left);
		proOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	public static void preOrderUnRecur(Node head){//非递归
		if (head == null) {
			return;
		}
		Stack<Node> path = new Stack<Node>();
		path.add(head);
		System.out.print("Pre-order:");
		while (!path.isEmpty()) {
			Node tmp = path.pop();
			System.out.print(tmp.value + " ");
			if (tmp.right != null){//先压右，后弹右
				path.push(tmp.right);
			}
			if (tmp.left != null) {
				path.push(tmp.left);
			}
		}
		System.out.println();
	}

	public static void inOrderUnRecur(Node head) {//中序
		Stack<Node> nodeStack = new Stack<Node>();
		Node current = head;
		System.out.print("In-order:");
		while (!nodeStack.isEmpty() || current != null) {
			if (current != null) {
				nodeStack.push(current);
				current = current.left;//先压左边界，
			} else {
				current = nodeStack.pop();
				System.out.print(current.value + " ");
				current = current.right;//先压右子树的左边界，
			}
		}
		System.out.println();
	}

	public static void proOrderUnRecur(Node head) {//后序遍历
		System.out.print("Pro-order: ");
		if (head == null) {
			return;
		}
		Stack<Node> s1 = new Stack<Node>();//找头
		Stack<Node> s2 = new Stack<Node>();//存头
		s1.push(head);
		while (!s1.isEmpty()) {
			Node currentNode = s1.pop();
			s2.push(currentNode);
			if (currentNode.left != null)
				s1.push(currentNode.left);
			if (currentNode.right != null)
				s1.push(currentNode.right);

		}
		while (!s2.isEmpty()) {
			System.out.print(s2.pop().value + " ");
		}
		System.out.println();
	}

	public static void proOrderUnRecurBetter(Node head){//用一个栈实现后序遍历
		if (head == null) {
			return;
		}
		Stack<Node> nodeStack = new Stack<Node>();
		nodeStack.push(head);
		Node previous = null;
		System.out.print("Pro-order: ");
		while (!nodeStack.isEmpty()) {
			Node current = nodeStack.peek();
			Node previousLeft = null;
			Node previousRight = null;//保存上一节点，来自哪儿
			if (previous != null) {
				previousLeft = previous.left;
				previousRight = previous.right;
			}
			if (previous == null || previousLeft == current
					|| previousRight == current) {
				if (current.left != null) {//有左才压左
					nodeStack.push(current.left);
				} else if (current.right != null) {
					nodeStack.push(current.right);
				}
			} else if (current.left == previous) {
				if (current.right != null) {
					nodeStack.push(current.right);
				}
			} else {
				System.out.print(current.value + " ");
				nodeStack.pop();
			}
			previous = current;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// Recursive way
		System.out.println("==============recursive==============");
		System.out.print("Pre-order:");
		preOrderRecur(head);
		System.out.println();
		System.out.print("In-order:");
		inOrderRecur(head);
		System.out.println();
		System.out.print("Pro-order:");
		proOrderRecur(head);
		System.out.println();

		// Without Recursive way
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
		inOrderUnRecur(head);
		proOrderUnRecur(head);
		proOrderUnRecurBetter(head);
	}

}
