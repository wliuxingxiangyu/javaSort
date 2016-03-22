package classTwo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class IsAncestorForAllChildren{//L2P17用树建的Set为祖先

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static boolean isAncestor(BinaryTreeNode node,//传入树根4节点
			List<BinaryTreeNode> nodeList){
		if(node==null){
			return false;
		}
		HashSet<BinaryTreeNode> nodeSet=new HashSet<BinaryTreeNode>();
		generateChildrenSet(node.left,nodeSet);//传入树根4.left
		generateChildrenSet(node.right,nodeSet);//传入树根4.right
		for(BinaryTreeNode cur:nodeList){
			if(!nodeSet.contains(cur)){
				return false;//如果用树建的Set中不包含"nodeList当前节点cur",直返假
			}//用树建的Set为祖先
		}
		return true;
	}

	public static void generateChildrenSet(BinaryTreeNode head,
			HashSet<BinaryTreeNode> nodeSet){
		if(head==null){
			return;
		}
		nodeSet.add(head);
		generateChildrenSet(head.left,nodeSet);
		generateChildrenSet(head.right,nodeSet);
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(8);
		head.left=new BinaryTreeNode(4);
		head.left.left=new BinaryTreeNode(2);
		head.left.right=new BinaryTreeNode(6);
		head.left.left.left=new BinaryTreeNode(1);//2的左为1
		head.left.left.right=new BinaryTreeNode(3);//2的右为3
		head.left.right.left=new BinaryTreeNode(5);//6的左为5
		head.left.right.right=new BinaryTreeNode(7);//6的右为7
		head.right=new BinaryTreeNode(12);
		head.right.left=new BinaryTreeNode(10);
		head.right.right=new BinaryTreeNode(14);
		head.right.left.left=new BinaryTreeNode(9);//10的左为9
		head.right.left.right=new BinaryTreeNode(11);//10的右为11
		head.right.right.left=new BinaryTreeNode(13);//14的左为13
		head.right.right.right=new BinaryTreeNode(15);//14的右为15
		List<BinaryTreeNode> list=new LinkedList<BinaryTreeNode>();
		list.add(head.left.left);//加2
		list.add(head.left.right);//加6
		list.add(head.left.right.left);//加5
		list.add(head.left.left.right);//加3
		System.out.println(isAncestor(head.left,list));

	}

}
