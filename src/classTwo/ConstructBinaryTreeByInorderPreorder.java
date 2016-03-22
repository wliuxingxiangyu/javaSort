package classTwo;

public class ConstructBinaryTreeByInorderPreorder{// L2P20先根,中根遍历结果，重建此树

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static BinaryTreeNode generateTree(int[] preArr,int[] inArr){
		return generateProcess(preArr,0,preArr.length-1,inArr,0,inArr.length-1);
	}

	public static BinaryTreeNode generateProcess(int[] preArr,int preStart,
			int preEnd,int[] inArr,int inStart,int inEnd){ // Start,end范围
		if(preStart>preEnd){
			return null;
		}
		if(preEnd==preStart){// 剩1个
			return new BinaryTreeNode(preArr[preStart]);
		}
		if(preStart==preEnd-1){// 剩2个
			BinaryTreeNode head=new BinaryTreeNode(preArr[preStart]);
			BinaryTreeNode child=new BinaryTreeNode(preArr[preEnd]);
			if(preArr[preStart]==inArr[inStart]){
				head.right=child;//剩下两个元素时,若两数组第一个元素相等,则第二个元素为右孩子。
			}else{
				head.left=child;
			}
			return head;
		}
		BinaryTreeNode head=new BinaryTreeNode(preArr[preStart]);// 剩3.4..个
		int preMid=preStart;
		int inMid=inStart;
		for(int i=inStart;i<inEnd;i++){
			if(preArr[preStart]==inArr[i]){//在中序遍历中找“根下标”
				inMid=i;//中序遍历"根下标"
				break;
			}
			preMid++;//中序遍历"根下标"对应的先序遍历的位置，即左子树的尾点
		}
		head.left=generateProcess(preArr,preStart+1,preMid,inArr,inStart,inMid-1);
		//数组范围初小尾,要求preStart+1<preMid,即至少两个元素,进递归。即一个元素为basecase。preStart=preMid时为basecase。
		head.right=generateProcess(preArr,preMid+1,preEnd,inArr,inMid+1,inEnd);
		return head;
	}

	public static void printTreePreOrder(BinaryTreeNode head){
		if(head==null){
			return;
		}
		System.out.print(head.value+" ");
		printTreePreOrder(head.left);
		printTreePreOrder(head.right);
	}

	public static void printTreeInOrder(BinaryTreeNode head){
		if(head==null){
			return;
		}
		printTreeInOrder(head.left);
		System.out.print(head.value+" ");
		printTreeInOrder(head.right);
	}

	public static void main(String[] args){
		int[] preArr={7, 10, 4, 3, 1, 2, 8, 11};
		int[] inArr={4, 10, 3, 1, 7, 11, 8, 2};
		BinaryTreeNode head=generateTree(preArr,inArr);
		System.out.println("前序遍历的结果如下：");
		printTreePreOrder(head);
		System.out.println( );
		System.out.println("中序遍历的结果如下：");
		printTreeInOrder(head);

	}
}
