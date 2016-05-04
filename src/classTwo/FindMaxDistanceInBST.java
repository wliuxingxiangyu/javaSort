package classTwo;
public class FindMaxDistanceInBST {//L2P5找树中最大的节点距离:3种比较，任意节点h(h的左子树,h的右子树,含h节点从左到右)
//最大距离 "不一定"包含 根节点root.
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getMaxDistance(Node head) {
		int[] record = new int[1];//一个元素的数组record,为了传地址
		return computeProcess(head,record);
		//getMaxDistance给被调函数返回:computeProcess的返回值
	}

	public static int computeProcess(Node head, int[] record) {
		if(head==null){//以head为根,的最大距离;//record存head的最长链长度
			record[0] = 0;//叶子节点赋为0
			return 0;//空树的距离为0
		}
		int leftMax=computeProcess(head.left,record);//leftMax存head.left的最长链长度
		int fromLeftHeadMax=record[0];//情形1:左子树的最大距离
		int rightMax=computeProcess(head.right,record);//rightMax存head.right的最长链长度
		int fromRightHeadMax=record[0];//情形2:右子树的最大距离
		record[0]=Math.max(fromLeftHeadMax,fromRightHeadMax)+1;//递归传给父节点,用于比较
		int curNodeMax=fromLeftHeadMax+fromRightHeadMax+1;//情形3:从左到右最大,要加本身
		return Math.max(Math.max(leftMax, rightMax), curNodeMax);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);//3的左
		head1.right.right = new Node(7);//3的右
		head1.left.left.left = new Node(8);//4的左
		head1.right.left.right = new Node(9);//6的右
		System.out.println(getMaxDistance(head1));

//		Node head2 = new Node(1);
//		head2.left = new Node(2);
//		head2.right = new Node(3);
//		head2.right.left = new Node(4);
//		head2.right.right = new Node(5);
//		head2.right.left.left = new Node(6);
//		head2.right.right.right = new Node(7);
//		head2.right.left.left.left = new Node(8);
//		head2.right.right.right.right = new Node(9);
//		System.out.println(getMaxDistance(head2));
	}

}
