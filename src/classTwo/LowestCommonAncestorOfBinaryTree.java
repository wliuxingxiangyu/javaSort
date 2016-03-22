package classTwo;

import java.util.HashMap;
import java.util.HashSet;

public class LowestCommonAncestorOfBinaryTree{// L2P20

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static BinaryTreeNode findLowestCommonAncestor(BinaryTreeNode head,
			BinaryTreeNode o1,BinaryTreeNode o2){// 单次查询，o1=5，o2=8，
		if(head==null){
			return null;
		}
		if(head==o1||head==o2){// 若 一个为另一的为父节点,直接返回父节点。
			return head;
		}
		BinaryTreeNode left=findLowestCommonAncestor(head.left,o1,o2);
		BinaryTreeNode right=findLowestCommonAncestor(head.right,o1,o2);
		if(left!=null&&right!=null){// 左右同时非空,即为左右孩子,返回父head,
			return head;
		}
		return left!=null?left:right;// left非空,返回left，否则返回right。
	}

	public static HashMap<BinaryTreeNode,BinaryTreeNode> generateChildrenParentMap(
			BinaryTreeNode head){// 生成一个预处理后的ParentMap，方便多次查询
		HashMap<BinaryTreeNode,BinaryTreeNode> result=new HashMap<BinaryTreeNode,BinaryTreeNode>();
		if(head!=null){
			result.put(head,null);// 树根节点的父为空
			generateMapProcess(head,result);
		}
		return result;
	}

	public static void generateMapProcess(BinaryTreeNode head,
			HashMap<BinaryTreeNode,BinaryTreeNode> map){
		if(head.left!=null){
			map.put(head.left,head);
			generateMapProcess(head.left,map);
		}
		if(head.right!=null){
			map.put(head.right,head);
			generateMapProcess(head.right,map);
		}
	}

	public static BinaryTreeNode queryLowestCommonAncestorByMap(
			// 用map查询
			HashMap<BinaryTreeNode,BinaryTreeNode> map,BinaryTreeNode a,
			BinaryTreeNode b){
		if((!map.containsKey(a))||(!map.containsKey(b))){
			return null;// map中既不包含a也不包含b,返回空。
		}
		HashSet<BinaryTreeNode> aAncestorsSet=new HashSet<BinaryTreeNode>();
		BinaryTreeNode ancestor=a;
		while(ancestor!=null){// Set中 不断向上 加入a的所有祖先节点ancestor
			aAncestorsSet.add(ancestor);
			ancestor=map.get(ancestor);// 取map的value(key的父节点),
		}
		ancestor=b;
		while(ancestor!=null){
			if(aAncestorsSet.contains(ancestor)){
				return ancestor;// a的祖先节点Set中,是否包含b的祖先节点,不断向上判断
			}else{
				ancestor=map.get(ancestor);
			}
		}
		return null;
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.right=new BinaryTreeNode(3);
		head.left.left=new BinaryTreeNode(4);// 2的左为4
		head.left.right=new BinaryTreeNode(5);// 2的右为5
		head.right.left=new BinaryTreeNode(6);// 3的左为6
		head.right.right=new BinaryTreeNode(7);// 3的右为7
		head.right.right.left=new BinaryTreeNode(8);// 7的左为8
		// 单次查询
		BinaryTreeNode LCA6and8=findLowestCommonAncestor(head,head.left.right,
				head.right.right.left);// head.left.right.value=5,head.right.right.left=8,
		System.out.println("单次查询，5和8的最近祖先：");
		System.out.println("Lowest common ancestor of 5 and 8 is "
				+LCA6and8.value);

		// 生成一个预处理后的map，方便多次查询
		HashMap<BinaryTreeNode,BinaryTreeNode> map=generateChildrenParentMap(head);
		BinaryTreeNode LCA4and8=queryLowestCommonAncestorByMap(map,
				head.left.left,head.right.right.left);
		System.out.println("多次查询，4和8的最近祖先：");
		System.out.println("Lowest common ancestor of 4 and 8 is "
				+LCA4and8.value);
		BinaryTreeNode LCA3and8=queryLowestCommonAncestorByMap(map,head.right,
				head.right.right.left);
		System.out.println("多次查询，3和8的最近祖先：");
		System.out.println("Lowest common ancestor of 3 and 8 is "
				+LCA3and8.value);

	}

}
