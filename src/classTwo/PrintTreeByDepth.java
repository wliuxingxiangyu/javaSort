package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeByDepth{//L2P6�����ӡ��

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void printBinaryTreeByDepth(BinaryTreeNode head){
		if(head==null){
			return;
		}
		Queue<BinaryTreeNode> nodeQueue=new LinkedList<BinaryTreeNode>();//ʹ��LinkedListʵ�ֶ���
		BinaryTreeNode levelLastNode=head;//��������һ���ڵ�
		BinaryTreeNode nextLevelLastNode=null;//��һ������һ���ڵ�
		int levelNum=0;
		nodeQueue.add(head);
		System.out.print("Level 0 nodes: ");
		while(!nodeQueue.isEmpty()){//nodeQueue�ǿ�
			BinaryTreeNode current=nodeQueue.poll();//�Ƴ�����ͷ��current
			System.out.print(current.value+" ");//һ�����ʹ�ӡ���ڵ��ֵ,
			if(current.left!=null){//������
				nextLevelLastNode=current.left;
				//��Ϊ��֪���Ƿ����Һ���,��ʱ����������Ϊ�������ڵ�,�����ٸ���
				nodeQueue.add(current.left);//�����Ӽ������
			}
			if(current.right!=null){//���Һ���
				nextLevelLastNode=current.right;//���Һ�������Ϊ�������ڵ�
				nodeQueue.add(current.right);//���Һ��Ӽ������
			}
			if(current==levelLastNode){//��ǰ�ڵ�=�����ڵ�,˵���ò��ӡ������
				levelLastNode=nextLevelLastNode;//���µ�ǰ��
				nextLevelLastNode=null;
				System.out.println();
				if(levelLastNode!=null){
				 System.out.print("Level "+(++levelNum)+" nodes: ");//��ӡ����12..
				}
			}
		}
	}

	public static void printBinaryTreeByDepthLessSpaceMoreTime(// �ռ临�Ӷ�O(h)
			BinaryTreeNode head){
		if(head==null){
			return;
		}
		int printLevel=0;
		System.out.print("Level 0 nodes: ");
		while(printKLevelNodesAndReturnHasNextLevelNodes(head,printLevel)){
			System.out.println();
			System.out.print("Level "+(++printLevel)+" nodes: ");
		}
		System.out.println();
	}

	public static boolean printKLevelNodesAndReturnHasNextLevelNodes(
			BinaryTreeNode head, int levelK){//

		return computeProcess(head,levelK,0);
	}

	public static boolean computeProcess(BinaryTreeNode head, int levelK,
			int currentLevel){
		if(head==null){
			return false;
		}
		if(currentLevel==levelK){
			System.out.print(head.value+" ");
			return head.left!=null||head.right!=null;
		}
		boolean leftHasNextLevel=computeProcess(head.left,levelK,currentLevel+1);
		boolean rightHasNextLevel=computeProcess(head.right,levelK,
				currentLevel+1);
		return leftHasNextLevel||rightHasNextLevel;
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(16);
		head.left=new BinaryTreeNode(12);
		head.right=new BinaryTreeNode(18);
		head.left.left=new BinaryTreeNode(6);
		head.left.right=new BinaryTreeNode(14);
		head.left.right.left=new BinaryTreeNode(13);
		head.left.right.right=new BinaryTreeNode(15);
		head.right.left=new BinaryTreeNode(17);
		head.right.right=new BinaryTreeNode(24);
		printBinaryTreeByDepth(head);
		// System.out.println("=====================");
		// printBinaryTreeByDepthLessSpaceMoreTime(head);

	}

}
