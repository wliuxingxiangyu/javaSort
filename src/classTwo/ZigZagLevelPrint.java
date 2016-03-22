package classTwo;

import java.util.ArrayList;
import java.util.Stack;

public class ZigZagLevelPrint{//L2P11之字形打印二叉树

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void printTreeZigZagUsingList(BinaryTreeNode head){
		ArrayList<ArrayList<BinaryTreeNode>> levelLists=new ArrayList<ArrayList<BinaryTreeNode>>();
		generateLists(head,0,levelLists);// 层链表
		boolean fromLeftToRight=true;
		for(int i=0;i!=levelLists.size();i++){
			if(fromLeftToRight){
				for(int j=0;j!=levelLists.get(i).size();j++){
					System.out.print(levelLists.get(i).get(j).value+" ");
				}
			}else{
				for(int j=levelLists.get(i).size()-1;j>=0;j--){
					System.out.print(levelLists.get(i).get(j).value+" ");
				}
			}
			fromLeftToRight=!fromLeftToRight;
			System.out.println();
		}
	}

	public static void generateLists(BinaryTreeNode head,int level,
			ArrayList<ArrayList<BinaryTreeNode>> levelLists){
		if(head==null){
			return;
		}
		if(levelLists.size()>level){
			levelLists.get(level).add(head);
		}else{
			levelLists.add(new ArrayList<BinaryTreeNode>());
			levelLists.get(level).add(head);
		}
		generateLists(head.left,level+1,levelLists);
		generateLists(head.right,level+1,levelLists);
	}
//---------------------------------------------------------------------
	public static void printTreeZigZagUsingStack(BinaryTreeNode head){
		Stack<BinaryTreeNode> leftToright=new Stack<BinaryTreeNode>();
		Stack<BinaryTreeNode> rightToleft=new Stack<BinaryTreeNode>();
		leftToright.push(head);
		boolean fromLeftToRight=false;//头节点的孩子是 从右到左打印
		while(!leftToright.isEmpty()||!rightToleft.isEmpty()){//只要一栈非空进
			if(fromLeftToRight){//从左到右，
				while(!leftToright.isEmpty()){//左到右栈 非空
					BinaryTreeNode current=leftToright.pop();
					System.out.print(current.value+" ");
					if(current.left!=null){//右到左栈 先压左后打印左
						rightToleft.push(current.left);
					}
					if(current.right!=null){
						rightToleft.push(current.right);
					}
				}
				System.out.println();//打印了一层换行
			}else{//从右到左，
				while(!rightToleft.isEmpty()){//右到左栈 非空
					BinaryTreeNode current=rightToleft.pop();
					System.out.print(current.value+" ");//打印1的同时,压入1.左1.右
					if(current.right!=null){//左到右栈 先压右后打印右
						leftToright.push(current.right);
					}
					if(current.left!=null){
						leftToright.push(current.left);
					}
				}
				System.out.println();//打印了一层换行
			}
			fromLeftToRight=!fromLeftToRight;//打印顺序  反向
		}
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.right=new BinaryTreeNode(3);
		head.left.left=new BinaryTreeNode(4);//2的左
		head.left.right=new BinaryTreeNode(5);//2的右
		head.right.left=new BinaryTreeNode(6);//3的左
		head.right.right=new BinaryTreeNode(7);//3的右
		head.left.left.left=new BinaryTreeNode(8);//4的左
		head.left.left.right=new BinaryTreeNode(9);//4的右
		head.left.right.left=new BinaryTreeNode(10);//5的左

		printTreeZigZagUsingList(head);
		printTreeZigZagUsingStack(head);
	}

}
