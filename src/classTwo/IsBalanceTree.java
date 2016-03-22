package classTwo;

public class IsBalanceTree {//L2P2平衡二叉树，

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanceTree(BinaryTreeNode head) {
		if (head == null) {//此处题目定义空树不是BalanceTree
			return false;
		}
		return getHeightForBalanceTree(head, 1) == -1 ? false : true;
	}//任何一步不为平衡二叉树，返回-1，//第二个参数height=1，

	public static int getHeightForBalanceTree(BinaryTreeNode head, int height) {
		if (head == null) {
			return height-1;//叶子节点往空传了height + 1，所以-1，
		}
		int leftTreeHeight=getHeightForBalanceTree(head.left,height + 1);
		if (leftTreeHeight==-1) {//如果中间节点的孩子返回了-1,则一直向上层返回-1,不执行高度差比较了
			return -1;
		}
		int rightTreeHeight = getHeightForBalanceTree(head.right, height + 1);
		if (rightTreeHeight==-1){//如果中间节点的孩子返回了-1,则一直向上层返回-1,不执行高度差比较了
			return -1;
		}
		if (Math.abs(leftTreeHeight - rightTreeHeight) > 1) {
			return -1;
		} else {//小于1,返回最大值(最大值减固定数才会得到最大值,才会排除):比如Max(4,3)=4,4-2>1.
			return Math.max(leftTreeHeight, rightTreeHeight);//
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode head = new BinaryTreeNode(1);
		head.left = new BinaryTreeNode(2);
		head.right = new BinaryTreeNode(3);
		head.left.left = new BinaryTreeNode(4);
		head.left.right = new BinaryTreeNode(5);
		head.right.left = new BinaryTreeNode(6);
		head.right.left.right = new BinaryTreeNode(7);

		System.out.println(isBalanceTree(head));

	}

}
