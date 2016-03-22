package classTwo;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeByDepth{//L2P6按层打印树

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
		Queue<BinaryTreeNode> nodeQueue=new LinkedList<BinaryTreeNode>();//使用LinkedList实现队列
		BinaryTreeNode levelLastNode=head;//本层的最后一个节点
		BinaryTreeNode nextLevelLastNode=null;//下一层的最后一个节点
		int levelNum=0;
		nodeQueue.add(head);
		System.out.print("Level 0 nodes: ");
		while(!nodeQueue.isEmpty()){//nodeQueue非空
			BinaryTreeNode current=nodeQueue.poll();//移除队列头给current
			System.out.print(current.value+" ");//一进来就打印根节点的值,
			if(current.left!=null){//有左孩子
				nextLevelLastNode=current.left;
				//因为不知道是否有右孩子,暂时将左孩子设置为该行最后节点,有右再更新
				nodeQueue.add(current.left);//将左孩子加入队列
			}
			if(current.right!=null){//有右孩子
				nextLevelLastNode=current.right;//将右孩子设置为该行最后节点
				nodeQueue.add(current.right);//将右孩子加入队列
			}
			if(current==levelLastNode){//当前节点=层最后节点,说明该层打印结束，
				levelLastNode=nextLevelLastNode;//更新当前层
				nextLevelLastNode=null;
				System.out.println();
				if(levelLastNode!=null){
				 System.out.print("Level "+(++levelNum)+" nodes: ");//打印层编号12..
				}
			}
		}
	}

	public static void printBinaryTreeByDepthLessSpaceMoreTime(// 空间复杂度O(h)
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
