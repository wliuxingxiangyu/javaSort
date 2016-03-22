package classTwo;

import java.util.ArrayList;
import java.util.Stack;

public class ZigZagLevelPrint{//L2P11֮���δ�ӡ������

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
		generateLists(head,0,levelLists);// ������
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
		boolean fromLeftToRight=false;//ͷ�ڵ�ĺ����� ���ҵ����ӡ
		while(!leftToright.isEmpty()||!rightToleft.isEmpty()){//ֻҪһջ�ǿս�
			if(fromLeftToRight){//�����ң�
				while(!leftToright.isEmpty()){//����ջ �ǿ�
					BinaryTreeNode current=leftToright.pop();
					System.out.print(current.value+" ");
					if(current.left!=null){//�ҵ���ջ ��ѹ����ӡ��
						rightToleft.push(current.left);
					}
					if(current.right!=null){
						rightToleft.push(current.right);
					}
				}
				System.out.println();//��ӡ��һ�㻻��
			}else{//���ҵ���
				while(!rightToleft.isEmpty()){//�ҵ���ջ �ǿ�
					BinaryTreeNode current=rightToleft.pop();
					System.out.print(current.value+" ");//��ӡ1��ͬʱ,ѹ��1.��1.��
					if(current.right!=null){//����ջ ��ѹ�Һ��ӡ��
						leftToright.push(current.right);
					}
					if(current.left!=null){
						leftToright.push(current.left);
					}
				}
				System.out.println();//��ӡ��һ�㻻��
			}
			fromLeftToRight=!fromLeftToRight;//��ӡ˳��  ����
		}
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.right=new BinaryTreeNode(3);
		head.left.left=new BinaryTreeNode(4);//2����
		head.left.right=new BinaryTreeNode(5);//2����
		head.right.left=new BinaryTreeNode(6);//3����
		head.right.right=new BinaryTreeNode(7);//3����
		head.left.left.left=new BinaryTreeNode(8);//4����
		head.left.left.right=new BinaryTreeNode(9);//4����
		head.left.right.left=new BinaryTreeNode(10);//5����

		printTreeZigZagUsingList(head);
		printTreeZigZagUsingStack(head);
	}

}
