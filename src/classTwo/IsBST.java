package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class IsBST{// L2P16
//给定一棵二叉树的头节点，用三种方法实现函数判断一棵二叉树是否为搜索二叉树。
//	三种方法分别符合以下三个要求：
//	初始要求：时间复杂度为O(n),空间复杂度为O(n)
//	进阶要求：时间复杂度为O(n),空间复杂度最低可为O(logn)，最高为O(n)
//	特别要求：空间复杂度为O(1)


	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static boolean isBST1(BinaryTreeNode head){
    //初始要求：时间复杂度为O(n),空间复杂度为O(n)
		if(head==null){
			return false;
		}
		Queue<Integer> inOrderQueue=generateInOrderQueue(head);
		int previous=inOrderQueue.poll();
		while(!inOrderQueue.isEmpty()){
			int cur=inOrderQueue.poll();
			if(cur<previous){
				return false;
			}
			previous=cur;
		}
		return true;
	}

	public static Queue<Integer> generateInOrderQueue(BinaryTreeNode head){
		if(head==null){
			return null;
		}
		Queue<Integer> inOrderQueue=new LinkedList<Integer>();
		inOrderProcess(head,inOrderQueue);
		return inOrderQueue;
	}

	public static void inOrderProcess(BinaryTreeNode head,
			Queue<Integer> inOrderQueue){
		if(head==null){
			return;
		}
		inOrderProcess(head.left,inOrderQueue);
		inOrderQueue.offer(head.value);
		inOrderProcess(head.right,inOrderQueue);
	}

	public static boolean isBST2(BinaryTreeNode head){// 方法2morris遍历
     //进阶要求：时间复杂度为O(n),空间复杂度最低可为O(logn)，最高为O(n)
		if(head==null){
			return false;
		}
		int[] previous=new int[1];// 全局变量
		previous[0]=Integer.MIN_VALUE;
		return inOrderCompare(head,previous);
	}

	public static boolean inOrderCompare(BinaryTreeNode head,int[] previous){
		if(head==null){
			return true;
		}
		if(!inOrderCompare(head.left,previous)){
			return false;
		}
		if(head.value<previous[0]){
			return false;
		}
		previous[0]=head.value;
		if(!inOrderCompare(head.right,previous)){
			return false;
		}
		return true;
	}

	// best solution -- Joseph Marries 1979
	public static boolean isBST3(BinaryTreeNode head){
	//特别要求：空间复杂度为O(1)
		if(head==null){
			return false;
		}
		BinaryTreeNode cur1Node=head;
		int previousValue=Integer.MIN_VALUE;
		while(cur1Node!=null){
			BinaryTreeNode cur2Node=cur1Node.left;
			if(cur2Node!=null){
				while(cur2Node.right!=null&&cur2Node.right!=cur1Node){
					cur2Node=cur2Node.right;
				}
				if(cur2Node.right==null){
					cur2Node.right=cur1Node;
					cur1Node=cur1Node.left;
					continue;
				}else{
					cur2Node.right=null;
				}
			}
			if(cur1Node.value>=previousValue){
				previousValue=cur1Node.value;
				cur1Node=cur1Node.right;
			}else{
				return false;
			}
		}
		return true;
	}
	
	public static boolean isBST4(BinaryTreeNode head){//自己写的判断是否为BST
		boolean tagLeft=true,tagRight=true;
		if(head.left!=null){//左子树
			if((head.left.value<=head.value)&&isBST4(head.left)){
				tagLeft=true;
			}else{
				tagLeft=false;
			}
		}
		
		if(head.right!=null){//右子树
			if((head.value<head.right.value)&&isBST4(head.right)){
				tagRight=true;
			}else{
				tagRight=false;
			}
		}
		return (tagLeft&&tagRight)?true:false;
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(5);
		head.left=new BinaryTreeNode(2);
		head.right=new BinaryTreeNode(9);
		head.left.left=new BinaryTreeNode(1);//2的左为1
		head.left.right=new BinaryTreeNode(3);//2的右为3
		head.left.right.right=new BinaryTreeNode(4);//3的右为4
		head.right.left=new BinaryTreeNode(7);//9的左为7
		head.right.right=new BinaryTreeNode(10);//9的右为10
		head.right.left.left=new BinaryTreeNode(6);//7的左为6
		head.right.left.right=new BinaryTreeNode(8);//7的右为8

		System.out.println(isBST1(head));
		System.out.println(isBST2(head));
		System.out.println(isBST3(head));
		System.out.println(isBST4(head));//自己写的判断是否为BST
	}
}