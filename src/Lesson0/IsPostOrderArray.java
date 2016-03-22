package Lesson0;

public class IsPostOrderArray{//�Լ�д�����󣬽��������±꣬����{14, 5, 7}�������ж�
	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static boolean CanGenerateBST(int[] postArr){
		if(postArr==null||postArr.length==0){
			return true;
		}
		return JudgeProcess(postArr,0,postArr.length-1);
	}

	public static boolean JudgeProcess(int[] postArr,int start,int end){
		boolean leftFlag,rightFlag;
		if(start==end){
			return true;
		}
		int firstLess=-1,lastLess=-1,firstMore=Integer.MAX_VALUE,lastMore=Integer.MAX_VALUE;
		for(int i=start;i<=end;i++){// ������
			if(postArr[i]<postArr[end]){//���������±꣬����{14, 5, 7}
				firstLess=i;
				break;
			}
		}
		for(int i=start;i<=end;i++){
			if(postArr[i]<postArr[end]){
				lastLess=Math.max(firstLess,i);
			}
		}

		if(firstLess==-1){
			leftFlag=true;
		}else{
			leftFlag=JudgeProcess(postArr,firstMore,lastMore);
		}
		
		for(int i=start;i<=end;i++){// ������
			if(postArr[i]>postArr[end]){
				firstMore=i;
				break;
			}
		}
		
		for(int i=start;i<=end;i++){
			if(postArr[i]>postArr[end]){
				lastMore=Math.max(firstMore,i);
			}
		}

		if(firstMore==Integer.MAX_VALUE){
			rightFlag=true;
		}else{
			rightFlag=JudgeProcess(postArr,firstMore,lastMore);
		}
		
		return leftFlag&&rightFlag;
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
		int[] arr=new int[]{14, 5, 7,6,19,11,10, 8};// ��,����������
		// int[] arr = new int[] { 6, 7, 9, 8, 10 };//��������
		// int[] arr = new int[] { 6, 4,3, 5};//��������
//		BinaryTreeNode head=generateTree(arr);
		System.out.println("CanGenerateBST�ܲ�����   "+CanGenerateBST(arr));
//		printTreePreOrder(head);
//		System.out.println();
//		System.out.println("�и���������");
//		printTreeInOrder(head);
//		System.out.println();

	}

}
