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
			BinaryTreeNode o1,BinaryTreeNode o2){// ���β�ѯ��o1=5��o2=8��
		if(head==null){
			return null;
		}
		if(head==o1||head==o2){// �� һ��Ϊ��һ��Ϊ���ڵ�,ֱ�ӷ��ظ��ڵ㡣
			return head;
		}
		BinaryTreeNode left=findLowestCommonAncestor(head.left,o1,o2);
		BinaryTreeNode right=findLowestCommonAncestor(head.right,o1,o2);
		if(left!=null&&right!=null){// ����ͬʱ�ǿ�,��Ϊ���Һ���,���ظ�head,
			return head;
		}
		return left!=null?left:right;// left�ǿ�,����left�����򷵻�right��
	}

	public static HashMap<BinaryTreeNode,BinaryTreeNode> generateChildrenParentMap(
			BinaryTreeNode head){// ����һ��Ԥ������ParentMap�������β�ѯ
		HashMap<BinaryTreeNode,BinaryTreeNode> result=new HashMap<BinaryTreeNode,BinaryTreeNode>();
		if(head!=null){
			result.put(head,null);// �����ڵ�ĸ�Ϊ��
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
			// ��map��ѯ
			HashMap<BinaryTreeNode,BinaryTreeNode> map,BinaryTreeNode a,
			BinaryTreeNode b){
		if((!map.containsKey(a))||(!map.containsKey(b))){
			return null;// map�мȲ�����aҲ������b,���ؿա�
		}
		HashSet<BinaryTreeNode> aAncestorsSet=new HashSet<BinaryTreeNode>();
		BinaryTreeNode ancestor=a;
		while(ancestor!=null){// Set�� �������� ����a���������Ƚڵ�ancestor
			aAncestorsSet.add(ancestor);
			ancestor=map.get(ancestor);// ȡmap��value(key�ĸ��ڵ�),
		}
		ancestor=b;
		while(ancestor!=null){
			if(aAncestorsSet.contains(ancestor)){
				return ancestor;// a�����Ƚڵ�Set��,�Ƿ����b�����Ƚڵ�,���������ж�
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
		head.left.left=new BinaryTreeNode(4);// 2����Ϊ4
		head.left.right=new BinaryTreeNode(5);// 2����Ϊ5
		head.right.left=new BinaryTreeNode(6);// 3����Ϊ6
		head.right.right=new BinaryTreeNode(7);// 3����Ϊ7
		head.right.right.left=new BinaryTreeNode(8);// 7����Ϊ8
		// ���β�ѯ
		BinaryTreeNode LCA6and8=findLowestCommonAncestor(head,head.left.right,
				head.right.right.left);// head.left.right.value=5,head.right.right.left=8,
		System.out.println("���β�ѯ��5��8��������ȣ�");
		System.out.println("Lowest common ancestor of 5 and 8 is "
				+LCA6and8.value);

		// ����һ��Ԥ������map�������β�ѯ
		HashMap<BinaryTreeNode,BinaryTreeNode> map=generateChildrenParentMap(head);
		BinaryTreeNode LCA4and8=queryLowestCommonAncestorByMap(map,
				head.left.left,head.right.right.left);
		System.out.println("��β�ѯ��4��8��������ȣ�");
		System.out.println("Lowest common ancestor of 4 and 8 is "
				+LCA4and8.value);
		BinaryTreeNode LCA3and8=queryLowestCommonAncestorByMap(map,head.right,
				head.right.right.left);
		System.out.println("��β�ѯ��3��8��������ȣ�");
		System.out.println("Lowest common ancestor of 3 and 8 is "
				+LCA3and8.value);

	}

}
