package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class IST1HasT2{//L2P13判T1树是否包含T2树的拓扑结构(要求值相同)
//思路:当T2有左时,判T1是否有左?无返假,当T2有右时,判T1是否有右?无返假.以T2为准，走T1。
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
		while(!nodeQueue.isEmpty()){//T1队列非空进
			BinaryTreeNode currentNode=nodeQueue.poll();
			System.out.println("currentNode="+currentNode.value);
			if(currentNode.value==T2.value){//?判值等
				if(checkEveryNodeInT1(currentNode,T2)){
					return true;
				}
			}
			if(currentNode.left!=null){//有左加左 入队列
				nodeQueue.offer(currentNode.left);
			}
			if(currentNode.right!=null){//有右加右 入队列
				nodeQueue.offer(currentNode.right);
			}
		}
		return false;
	}

	public static boolean checkEveryNodeInT1(BinaryTreeNode nodeInT1,
			BinaryTreeNode T2){//实参(currentNode,T2)
		Queue<BinaryTreeNode> T1Queue=new LinkedList<BinaryTreeNode>();
		T1Queue.offer(nodeInT1);
		Queue<BinaryTreeNode> T2Queue=new LinkedList<BinaryTreeNode>();
		T2Queue.offer(T2);
		while(!T2Queue.isEmpty()){//T2非空进
			BinaryTreeNode currentT1Node=T1Queue.poll();
			BinaryTreeNode currentT2Node=T2Queue.poll();
			if(currentT1Node.value!=currentT2Node.value){
				return false;
			}
			if(currentT2Node.left!=null){//T2有左时,
				if(currentT1Node.left==null){//判T1是否有左
					return false;
				}
				T1Queue.offer(currentT1Node.left);//T1加左
				T2Queue.offer(currentT2Node.left);//T1加左
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
		T1.left.left=new BinaryTreeNode(4);//2的左
		T1.left.right=new BinaryTreeNode(5);//2的右
		T1.right.left=new BinaryTreeNode(6);//3的左
		T1.right.right=new BinaryTreeNode(7);//3的右
		T1.left.left.left=new BinaryTreeNode(8);//4的左
		T1.left.left.right=new BinaryTreeNode(9);//4的右
		T1.left.right.left=new BinaryTreeNode(10);//4的左

		BinaryTreeNode T2=new BinaryTreeNode(2);
		T2.left=new BinaryTreeNode(4);
		T2.left.left=new BinaryTreeNode(8);
		T2.right=new BinaryTreeNode(5);
		
		System.out.println(containTopology(T1,T2));

	}

}
