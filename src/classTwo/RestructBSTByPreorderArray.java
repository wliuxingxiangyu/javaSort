package classTwo;

public class RestructBSTByPreorderArray {

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static BinaryTreeNode generateTree(int[] preArr) {
		if (preArr == null || preArr.length == 0) {
			return null;
		}
		int[] index = new int[1];
		index[0] = 0;
		return generateProcess(preArr, index, Integer.MAX_VALUE);
	}

	public static BinaryTreeNode generateProcess(int[] preArr, int[] index,
			int previousValue) {
		BinaryTreeNode head = new BinaryTreeNode(preArr[index[0]++]);
		if (index[0] != preArr.length && head.value > preArr[index[0]]) {
			head.left = generateProcess(preArr, index, head.value);
		}
		if (index[0] != preArr.length && previousValue > preArr[index[0]]) {
			head.right = generateProcess(preArr, index, previousValue);
		}
		return head;
	}

	public static void printTreePreOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		printTreePreOrder(head.left);
		printTreePreOrder(head.right);
	}

	public static void printTreeInOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printTreeInOrder(head.left);
		System.out.print(head.value + " ");
		printTreeInOrder(head.right);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 6, 3, 4, 5, 7, 9, 8, 10 };
		BinaryTreeNode head = generateTree(arr);
		printTreePreOrder(head);
		System.out.println();
		printTreeInOrder(head);
		System.out.println();

	}

}
