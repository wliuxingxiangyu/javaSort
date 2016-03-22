package classTwo;

public class CreateMinHeightBST{//L2P8，通过升序数组，生成高度最小的搜索二叉树

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static BinaryTreeNode generateBSTFromArray(int[] aim){
		BinaryTreeNode head=generateProcess(aim,0,aim.length-1);
		                              //形参(aim,begin,begin)
		return head;
	}

	public static BinaryTreeNode generateProcess(int[] aim,int begin,int end){
		if(begin==end){// 一个元素
			return new BinaryTreeNode(aim[begin]);
		}
		if(begin==end-1){// 两个元素
			BinaryTreeNode head=new BinaryTreeNode(aim[begin]);
			head.right=new BinaryTreeNode(aim[end]);
			return head;
		}
		int mid=(begin+end)/2;//(0+9)/2=4
		System.out.print("mid="+mid+" ");//调试
		System.out.println( );//调试
		BinaryTreeNode head=new BinaryTreeNode(aim[mid]);
		head.left=generateProcess(aim,begin,mid-1);// 左子树
		head.right=generateProcess(aim,mid+1,end);// 右子树
		return head;
	}

	public static void printTreeInOrder(BinaryTreeNode head){
		inOrderProcess(head);
		System.out.println();
	}

	public static void inOrderProcess(BinaryTreeNode head){
		if(head==null){
			return;
		}
		inOrderProcess(head.left);
		System.out.print(head.value+" ");
		inOrderProcess(head.right);
	}

	public static void main(String[] args){
		int[] arr={0,1,2,3,4,5,6,7,8,9};
		printTreeInOrder(generateBSTFromArray(arr));

	}

}
