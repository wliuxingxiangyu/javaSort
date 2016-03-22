package classTwo;

public class IsBalanceTree {//L2P2ƽ���������

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanceTree(BinaryTreeNode head) {
		if (head == null) {//�˴���Ŀ�����������BalanceTree
			return false;
		}
		return getHeightForBalanceTree(head, 1) == -1 ? false : true;
	}//�κ�һ����Ϊƽ�������������-1��//�ڶ�������height=1��

	public static int getHeightForBalanceTree(BinaryTreeNode head, int height) {
		if (head == null) {
			return height-1;//Ҷ�ӽڵ����մ���height + 1������-1��
		}
		int leftTreeHeight=getHeightForBalanceTree(head.left,height + 1);
		if (leftTreeHeight==-1) {//����м�ڵ�ĺ��ӷ�����-1,��һֱ���ϲ㷵��-1,��ִ�и߶Ȳ�Ƚ���
			return -1;
		}
		int rightTreeHeight = getHeightForBalanceTree(head.right, height + 1);
		if (rightTreeHeight==-1){//����м�ڵ�ĺ��ӷ�����-1,��һֱ���ϲ㷵��-1,��ִ�и߶Ȳ�Ƚ���
			return -1;
		}
		if (Math.abs(leftTreeHeight - rightTreeHeight) > 1) {
			return -1;
		} else {//С��1,�������ֵ(���ֵ���̶����Ż�õ����ֵ,�Ż��ų�):����Max(4,3)=4,4-2>1.
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
