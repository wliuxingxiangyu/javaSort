package Lesson0;

public class RestructBSTByPreorderArray{//题目已知数组为前序遍历的结果
	
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
		return generateProcess(preArr,0,preArr.length-1);
	}

	public static BinaryTreeNode generateProcess(int[] preArr, int start,int end) {
		BinaryTreeNode head = new BinaryTreeNode(preArr[start]);
		if(start==end) {return head;}
		int fristLess=-1,lastLess=-1,fristMore=Integer.MAX_VALUE,lastMore=Integer.MAX_VALUE;
		for(int i=start;i<=end;i++){//左子树
			if(preArr[i]<preArr[start]){
			fristLess=i;break;
			}
		}
		for(int i=start;i<=end;i++){
			if(preArr[i]<preArr[start]){
				lastLess=Math.max(fristLess,i);
			}
		}
		
		if(fristLess!=-1){
			head.left=generateProcess(preArr,fristLess,lastLess);
		}else{
			head.left=null;
		}
		
		for(int i=start;i<=end;i++){//右子树
			if(preArr[i]>preArr[start]){
				fristMore=i;break;
			}			
			
		}
		for(int i=start;i<=end;i++){
			if(preArr[i]>preArr[start]){
				lastMore=Math.max(fristMore,i);
			}				
		}
		
		if(fristMore!=Integer.MAX_VALUE){
			head.right=generateProcess(preArr,fristMore,lastMore);
		}else{
			head.right=null;
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
		int[] arr = new int[] { 6, 4,3, 5, 7, 9, 8, 10 };//左,右子树都有
//		int[] arr = new int[] { 6, 7, 9, 8, 10 };//无左子树
//		int[] arr = new int[] { 6, 4,3, 5};//无右子树
		BinaryTreeNode head = generateTree(arr);
		System.out.println("先根遍历如下");
		printTreePreOrder(head);
		System.out.println();
		System.out.println("中根遍历如下");
		printTreeInOrder(head);
		System.out.println();

	}

}
