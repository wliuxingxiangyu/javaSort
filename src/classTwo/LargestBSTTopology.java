package classTwo;

import java.util.HashMap;

public class LargestBSTTopology {//L2P24最长BST拓扑结构

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	// need more time solution
	public static int getLongestBSTTopologySize1(BinaryTreeNode head) {
		if (head == null) {
			return 0;
		}
		int fromHead = getLongestBSTSizeFromHead(head, head);
		int fromLeft = getLongestBSTSizeFromHead(head.left, head.left);
		int fromRight = getLongestBSTSizeFromHead(head.right, head.right);
		return Math.max(fromHead, Math.max(fromLeft, fromRight));//来自 左子树,自己,右子树
	}

	public static int getLongestBSTSizeFromHead(BinaryTreeNode head,
			BinaryTreeNode node) {
		if (head != null && node != null && isBSTNode(head, node, node.value)) {
			return getLongestBSTSizeFromHead(head, node.left) + getLongestBSTSizeFromHead(head, node.right) + 1;
		}
		return 0;
	}

	public static boolean isBSTNode(BinaryTreeNode head, BinaryTreeNode node,
			int value) {//以
		if (head == null) {
			return false;
		}
		if (head == node) {
			return true;
		}
		if (head.value > value) {//左子树
			return isBSTNode(head.left, node, value);
		} else if (head.value < value) {//右子树
			return isBSTNode(head.right, node, value);
		} else {//head.value = value
			return isBSTNode(head.left, node, value) || isBSTNode(head.right, node, value);
		}
	}

	// best solution
	public static class Record {
		public int leftSize;
		public int rightSize;

		public Record(int left, int right) {
			this.leftSize = left;
			this.rightSize = right;
		}
	}

	public static int getLongestBSTTopologySize2(BinaryTreeNode head) {
		HashMap<BinaryTreeNode, Record> map = new HashMap<BinaryTreeNode, Record>();
		return computeProcess(head, map);
	}

	public static int computeProcess(BinaryTreeNode head,
			HashMap<BinaryTreeNode, Record> map) {
		if (head == null) {
			return 0;
		}
		int leftSize = computeProcess(head.left, map);
		int rightSize = computeProcess(head.right, map);
		modifyLeftTreeMap(head.left, head.value, map);
		modifyRightTreeMap(head.right, head.value, map);
		Record leftRec = map.get(head.left);
		Record rightRec = map.get(head.right);
		int hasHeadLeftSize = leftRec == null ? 0 : leftRec.leftSize + leftRec.rightSize + 1;
		int hasHeadRightSize = rightRec == null ? 0 : rightRec.leftSize + rightRec.rightSize + 1;
		map.put(head, new Record(hasHeadLeftSize, hasHeadRightSize));
		return Math.max(hasHeadLeftSize + hasHeadRightSize + 1, Math.max(leftSize, rightSize));
	}

	public static int modifyLeftTreeMap(BinaryTreeNode node, int headValue,
			HashMap<BinaryTreeNode, Record> map) {
		if (node == null) {
			return 0;
		}
		if (!map.containsKey(node)) {
			return 0;
		}
		if (node.value > headValue) {
			Record rec = map.get(node);
			map.remove(node);
			return rec.leftSize + rec.rightSize + 1;
		} else {
			int minusValue = modifyLeftTreeMap(node.right, headValue, map);
			Record rec = map.get(node);
			rec.rightSize = rec.rightSize - minusValue;
			map.put(node, rec);
			return minusValue;
		}
	}

	public static int modifyRightTreeMap(BinaryTreeNode node, int headValue,
			HashMap<BinaryTreeNode, Record> map) {
		if (node == null) {
			return 0;
		}
		if (!map.containsKey(node)) {
			return 0;
		}
		if (node.value < headValue) {
			Record rec = map.get(node);
			map.remove(node);
			return rec.leftSize + rec.rightSize + 1;
		} else {
			int minusValue = modifyRightTreeMap(node.left, headValue, map);
			Record rec = map.get(node);
			rec.leftSize = rec.leftSize - minusValue;
			map.put(node, rec);
			return minusValue;
		}
	}

	public static void printTreeInOrder(BinaryTreeNode head) {
		inOrder(head);
		System.out.println();
	}

	public static void inOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		inOrder(head.left);
		System.out.print(head.value + " ");
		inOrder(head.right);
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

		System.out.println(getLongestBSTTopologySize1(head));
		System.out.println(getLongestBSTTopologySize2(head));

	}

}
