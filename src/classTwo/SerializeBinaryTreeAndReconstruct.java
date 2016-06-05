package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeBinaryTreeAndReconstruct{//L2P26���л������������ַ���
	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static String serializeTree(BinaryTreeNode head){
		if(head==null){
			return "#";
		}
		String serializeString=String.valueOf(head.value);
		serializeString+=serializeTree(head.left);
		serializeString+=serializeTree(head.right);
		return serializeString;
	}

	public static BinaryTreeNode reConstructTree(String treeStr){
		Queue<Character> queue=new LinkedList<Character>();//LinkedList˫������,����������ʵ��
		char[] charArr=treeStr.toCharArray();
		for(int i=0;i!=charArr.length;i++){
			queue.offer(charArr[i]);
		}
		return reConstructProcess(queue);
	}

	public static BinaryTreeNode reConstructProcess(Queue<Character> queue){
		if(queue.isEmpty()){//������
			return null;
		}
		if(queue.peek().equals('#')){
			queue.poll();
			return null;
		}
		int value=Integer.valueOf(String.valueOf(queue.poll()));
		BinaryTreeNode head=new BinaryTreeNode(value);//�����ڵ�
		head.left=reConstructProcess(queue);//��ڵ�//����Ҫ����
		head.right=reConstructProcess(queue);//�ҽڵ�
		return head;
	}

	public static void printTreePreOrder(BinaryTreeNode head){
		if(head==null){
			return;
		}
		System.out.print(head.value+" ");
		printTreePreOrder(head.left);
		printTreePreOrder(head.right);
	}

	public static void printInOrder(BinaryTreeNode head){
		if(head==null){
			return;
		}
		printInOrder(head.left);
		System.out.print(head.value+" ");
		printInOrder(head.right);
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.left.left=new BinaryTreeNode(3);
		head.right=new BinaryTreeNode(4);
		head.right.left=new BinaryTreeNode(5);
		head.right.right=new BinaryTreeNode(6);
		System.out.println("====�������л���========");
		String result=serializeTree(head);
		System.out.println(result);
		System.out.println("����printTreePreOrder��");
		printTreePreOrder(head);
		System.out.println();
		System.out.println("����printInOrder��");
		printInOrder(head);
		System.out.println();
		System.out.println();//��һ��
		System.out.println("=======================");
		System.out.println("====����   �����л���========");
		BinaryTreeNode newTree=reConstructTree(result);
		System.out.println("����printTreePreOrder��");
		printTreePreOrder(newTree);
		System.out.println();
		System.out.println("����printInOrder��");
		printInOrder(newTree);
		System.out.println();
	}
}
