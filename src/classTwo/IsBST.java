package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class IsBST{// L2P16
//����һ�ö�������ͷ�ڵ㣬�����ַ���ʵ�ֺ����ж�һ�ö������Ƿ�Ϊ������������
//	���ַ����ֱ������������Ҫ��
//	��ʼҪ��ʱ�临�Ӷ�ΪO(n),�ռ临�Ӷ�ΪO(n)
//	����Ҫ��ʱ�临�Ӷ�ΪO(n),�ռ临�Ӷ���Ϳ�ΪO(logn)�����ΪO(n)
//	�ر�Ҫ�󣺿ռ临�Ӷ�ΪO(1)


	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static boolean isBST1(BinaryTreeNode head){
    //��ʼҪ��ʱ�临�Ӷ�ΪO(n),�ռ临�Ӷ�ΪO(n)
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

	public static boolean isBST2(BinaryTreeNode head){// ����2morris����
     //����Ҫ��ʱ�临�Ӷ�ΪO(n),�ռ临�Ӷ���Ϳ�ΪO(logn)�����ΪO(n)
		if(head==null){
			return false;
		}
		int[] previous=new int[1];// ȫ�ֱ���
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
	//�ر�Ҫ�󣺿ռ临�Ӷ�ΪO(1)
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
	
	public static boolean isBST4(BinaryTreeNode head){//�Լ�д���ж��Ƿ�ΪBST
		boolean tagLeft=true,tagRight=true;
		if(head.left!=null){//������
			if((head.left.value<=head.value)&&isBST4(head.left)){
				tagLeft=true;
			}else{
				tagLeft=false;
			}
		}
		
		if(head.right!=null){//������
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
		head.left.left=new BinaryTreeNode(1);//2����Ϊ1
		head.left.right=new BinaryTreeNode(3);//2����Ϊ3
		head.left.right.right=new BinaryTreeNode(4);//3����Ϊ4
		head.right.left=new BinaryTreeNode(7);//9����Ϊ7
		head.right.right=new BinaryTreeNode(10);//9����Ϊ10
		head.right.left.left=new BinaryTreeNode(6);//7����Ϊ6
		head.right.left.right=new BinaryTreeNode(8);//7����Ϊ8

		System.out.println(isBST1(head));
		System.out.println(isBST2(head));
		System.out.println(isBST3(head));
		System.out.println(isBST4(head));//�Լ�д���ж��Ƿ�ΪBST
	}
}