package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class IST1HasT2{//L2P13��T1���Ƿ����T2�������˽ṹ(Ҫ��ֵ��ͬ)
//˼·:��T2����ʱ,��T1�Ƿ�����?�޷���,��T2����ʱ,��T1�Ƿ�����?�޷���.��T2Ϊ׼����T1��
	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static boolean containTopology(BinaryTreeNode T1,BinaryTreeNode T2){
		Queue<BinaryTreeNode> nodeQueue=new LinkedList<BinaryTreeNode>();
		nodeQueue.offer(T1);
		while(!nodeQueue.isEmpty()){//T1���зǿս�
			BinaryTreeNode currentNode=nodeQueue.poll();
			System.out.println("currentNode="+currentNode.value);
			if(currentNode.value==T2.value){//?��ֵ��
				if(checkEveryNodeInT1(currentNode,T2)){
					return true;
				}
			}
			if(currentNode.left!=null){//������� �����
				nodeQueue.offer(currentNode.left);
			}
			if(currentNode.right!=null){//���Ҽ��� �����
				nodeQueue.offer(currentNode.right);
			}
		}
		return false;
	}

	public static boolean checkEveryNodeInT1(BinaryTreeNode nodeInT1,
			BinaryTreeNode T2){//ʵ��(currentNode,T2)
		Queue<BinaryTreeNode> T1Queue=new LinkedList<BinaryTreeNode>();
		T1Queue.offer(nodeInT1);
		Queue<BinaryTreeNode> T2Queue=new LinkedList<BinaryTreeNode>();
		T2Queue.offer(T2);
		while(!T2Queue.isEmpty()){//T2�ǿս�
			BinaryTreeNode currentT1Node=T1Queue.poll();
			BinaryTreeNode currentT2Node=T2Queue.poll();
			if(currentT1Node.value!=currentT2Node.value){
				return false;
			}
			if(currentT2Node.left!=null){//T2����ʱ,
				if(currentT1Node.left==null){//��T1�Ƿ�����
					return false;
				}
				T1Queue.offer(currentT1Node.left);//T1����
				T2Queue.offer(currentT2Node.left);//T1����
			System.out.println("currentT1Node.left="+currentT1Node.left.value);
			}
			if(currentT2Node.right!=null){
				if(currentT1Node.right==null){
					return false;
				}
				T1Queue.offer(currentT1Node.right);
				T2Queue.offer(currentT2Node.right);
			}
		}
		return true;
	}

	public static void main(String[] args){
		BinaryTreeNode T1=new BinaryTreeNode(1);
		T1.left=new BinaryTreeNode(2);
		T1.right=new BinaryTreeNode(3);
		T1.left.left=new BinaryTreeNode(4);//2����
		T1.left.right=new BinaryTreeNode(5);//2����
		T1.right.left=new BinaryTreeNode(6);//3����
		T1.right.right=new BinaryTreeNode(7);//3����
		T1.left.left.left=new BinaryTreeNode(8);//4����
		T1.left.left.right=new BinaryTreeNode(9);//4����
		T1.left.right.left=new BinaryTreeNode(10);//4����

		BinaryTreeNode T2=new BinaryTreeNode(2);
		T2.left=new BinaryTreeNode(4);
		T2.left.left=new BinaryTreeNode(8);
		T2.right=new BinaryTreeNode(5);
		
		System.out.println(containTopology(T1,T2));

	}

}
