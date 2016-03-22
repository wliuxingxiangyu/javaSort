package classTwo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class IsAncestorForAllChildren{//L2P17��������SetΪ����

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static boolean isAncestor(BinaryTreeNode node,//��������4�ڵ�
			List<BinaryTreeNode> nodeList){
		if(node==null){
			return false;
		}
		HashSet<BinaryTreeNode> nodeSet=new HashSet<BinaryTreeNode>();
		generateChildrenSet(node.left,nodeSet);//��������4.left
		generateChildrenSet(node.right,nodeSet);//��������4.right
		for(BinaryTreeNode cur:nodeList){
			if(!nodeSet.contains(cur)){
				return false;//�����������Set�в�����"nodeList��ǰ�ڵ�cur",ֱ����
			}//��������SetΪ����
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
		head.left.left.left=new BinaryTreeNode(1);//2����Ϊ1
		head.left.left.right=new BinaryTreeNode(3);//2����Ϊ3
		head.left.right.left=new BinaryTreeNode(5);//6����Ϊ5
		head.left.right.right=new BinaryTreeNode(7);//6����Ϊ7
		head.right=new BinaryTreeNode(12);
		head.right.left=new BinaryTreeNode(10);
		head.right.right=new BinaryTreeNode(14);
		head.right.left.left=new BinaryTreeNode(9);//10����Ϊ9
		head.right.left.right=new BinaryTreeNode(11);//10����Ϊ11
		head.right.right.left=new BinaryTreeNode(13);//14����Ϊ13
		head.right.right.right=new BinaryTreeNode(15);//14����Ϊ15
		List<BinaryTreeNode> list=new LinkedList<BinaryTreeNode>();
		list.add(head.left.left);//��2
		list.add(head.left.right);//��6
		list.add(head.left.right.left);//��5
		list.add(head.left.left.right);//��3
		System.out.println(isAncestor(head.left,list));

	}

}
