package classTwo;

public class LargestBSTInBinaryTree {

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static BinaryTreeNode getLargestBST(BinaryTreeNode head) {
		int[] recordArr = new int[3];
		recordArr[0] = 0; // max BST size for current subtree
		return findProcess(head, recordArr);
	}

	public static BinaryTreeNode findProcess(BinaryTreeNode head,
			int[] recordArr) {
		if (head == null) {
			recordArr[0] = 0;
			return null;
		}
		BinaryTreeNode leftMaxBSTNode = findProcess(head.left, recordArr);
		int leftMaxSize = recordArr[0];
		int leftMin = leftMaxBSTNode == null ? head.value : recordArr[1];
		int leftMax = leftMaxBSTNode == null ? head.value : recordArr[2];
		BinaryTreeNode rightMaxBSTNode = findProcess(head.right, recordArr);
		int rightMaxSize = recordArr[0];
		int rightMin = rightMaxBSTNode == null ? head.value : recordArr[1];
		int rightMax = rightMaxBSTNode == null ? head.value : recordArr[2];
		if (head.left == leftMaxBSTNode && head.right == rightMaxBSTNode
				&& leftMax <= head.value && head.value <= rightMin) {
			recordArr[0] = leftMaxSize + rightMaxSize + 1;
			recordArr[1] = leftMin;
			recordArr[2] = rightMax;
			return head;
		} else if (leftMaxSize > rightMaxSize) {
			recordArr[0] = leftMaxSize;
			return leftMaxBSTNode;
		} else {
			recordArr[0] = rightMaxSize;
			return rightMaxBSTNode;
		}
	}

	public static void printTreeInOrder(BinaryTreeNode head) {
		inOrderProcess(head);
		System.out.println();
	}

	public static void inOrderProcess(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		inOrderProcess(head.left);
		System.out.print(head.value + " ");
		inOrderProcess(head.right);
	}

	public static void main(String[] args) {
		BinaryTreeNode head = new BinaryTreeNode(6);
		head.left = new BinaryTreeNode(1);
		head.left.left = new BinaryTreeNode(0);
		head.left.right = new BinaryTreeNode(3);
		head.right = new BinaryTreeNode(12);
		head.right.left = new BinaryTreeNode(10);
		head.right.left.left = new BinaryTreeNode(4);
		head.right.left.left.left = new BinaryTreeNode(2);
		head.right.left.left.right = new BinaryTreeNode(5);
		head.right.left.right = new BinaryTreeNode(14);
		head.right.left.right.left = new BinaryTreeNode(11);
		head.right.left.right.right = new BinaryTreeNode(15);
		head.right.right = new BinaryTreeNode(13);
		head.right.right.left = new BinaryTreeNode(20);
		head.right.right.right = new BinaryTreeNode(16);

		printTreeInOrder(head);
		BinaryTreeNode result = getLargestBST(head);
		printTreeInOrder(result);

	}

}
