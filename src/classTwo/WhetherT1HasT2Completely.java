package classTwo;

public class WhetherT1HasT2Completely {

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static boolean isSubtree(BinaryTreeNode tree1, BinaryTreeNode tree2) {
		String t1String = getStringOfTree(tree1, "");
		String t2String = getStringOfTree(tree2, "");
		return getIndexOf(t1String, t2String) == -1 ? false : true;
	}

	public static String getStringOfTree(BinaryTreeNode head, String treeStr) {
		if (head == null) {
			return treeStr + "#";
		}
		treeStr = treeStr + String.valueOf(head.value);
		treeStr = getStringOfTree(head.left, treeStr);
		treeStr = getStringOfTree(head.right, treeStr);
		return treeStr;
	}

	/*
	 * KMP Algorithm
	 */
	public static int getIndexOf(String str, String match) {
		if (str == "" || str == null || match == "" || match == null) {
			return -1;
		}
		char[] strChars = str.toCharArray();
		char[] matchChars = match.toCharArray();
		int[] nextArray = getNextArray(matchChars);
		int index = 0;
		int matchIndex = 0;
		while (index < strChars.length && matchIndex < matchChars.length) {
			if (strChars[index] == matchChars[matchIndex]) {
				index++;
				matchIndex++;
			} else {
				int nextMatchIndex = nextArray[matchIndex];
				if (nextMatchIndex == -1) {
					index++;
					matchIndex = 0;
				} else {
					matchIndex = nextMatchIndex;
				}
			}
		}
		return matchIndex == matchChars.length ? index - matchIndex : -1;
	}

	public static int[] getNextArray(char[] chars) {
		int[] result = new int[chars.length];
		if (result.length == 1) {
			result[0] = -1;
			return result;
		}
		result[0] = -1;
		result[1] = 0;
		if (result.length == 2) {
			return result;
		}
		for (int i = 2; i != result.length; i++) {
			int lastCheck = i - 1;
			int lastIndex = result[lastCheck];
			while (lastIndex != -1) {
				if (chars[i - 1] == chars[lastIndex]) {
					result[i] = result[lastCheck] + 1;
					break;
				}
				lastCheck = lastIndex;
				lastIndex = result[lastCheck];
			}
		}
		return result;
	}

	public static void main(String[] args) {

		BinaryTreeNode tree1 = new BinaryTreeNode(1);
		tree1.left = new BinaryTreeNode(2);
		tree1.right = new BinaryTreeNode(3);
		tree1.left.left = new BinaryTreeNode(4);
		tree1.left.right = new BinaryTreeNode(5);
		tree1.right.left = new BinaryTreeNode(6);
		tree1.right.right = new BinaryTreeNode(7);
		tree1.left.left.right = new BinaryTreeNode(8);
		tree1.left.right.left = new BinaryTreeNode(9);

		BinaryTreeNode tree2 = new BinaryTreeNode(2);
		tree2.left = new BinaryTreeNode(4);
		tree2.left.right = new BinaryTreeNode(8);
		tree2.right = new BinaryTreeNode(5);
		tree2.right.left = new BinaryTreeNode(9);

		System.out.println(isSubtree(tree1, tree2));

	}

}
