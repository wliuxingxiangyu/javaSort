package classTwo;

public class TraversalTreeByMorris{//L2P7先根和中根主过程一样,只是打印时间不同,时间复杂度O(N),额外空间复杂度O(1)

	public static class BinaryTreeNode{
		public int value;
		BinaryTreeNode left;
		BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void traversalPreOrderMorris(BinaryTreeNode head){//先根遍历树
		if(head==null){
			return;
		}
		BinaryTreeNode cur1Node=head;
		while(cur1Node!=null){// 找前驱
			BinaryTreeNode cur2Node=cur1Node.left;
			if(cur2Node!=null){
				while(cur2Node.right!=null&&cur2Node.right!=cur1Node){
					  //cur2右子树非空 且 右子树不为cur1
					cur2Node=cur2Node.right;//cur2往右子树上移
				}//以上表示:一个节点在中序遍历的前趋节点是其左子树的最右边的节点
				if(cur2Node.right==null){
					cur2Node.right=cur1Node;
					System.out.print(cur1Node.value+" ");
					cur1Node=cur1Node.left;
					continue;
				}else{
					cur2Node.right=null;//断链恢复原树
				}
			}else{
				System.out.print(cur1Node.value+" ");// 某一树最左节点
			}
			cur1Node=cur1Node.right;
		}
		System.out.println();
	}

	public static void traversalInOrderMorris(BinaryTreeNode head){//中序遍历
		if(head==null){
			return;
		}
		BinaryTreeNode cur1Node=head;
		while(cur1Node!=null){
			BinaryTreeNode cur2Node=cur1Node.left;
			if(cur2Node!=null){//作为cur1Node.left的cur2Node,如果非空,中序应是先左cur2Node
				while(cur2Node.right!=null&&cur2Node.right!=cur1Node){
					cur2Node=cur2Node.right; 
				}//以上表示:一个节点在中序遍历的前趋节点   是其左子树的最右边的节点
				if(cur2Node.right==null){
					cur2Node.right=cur1Node;//把根节点强行作为左孩子的右孩子
					cur1Node=cur1Node.left;//有左孩子,移动到左孩子上
					continue;
				}else{
					cur2Node.right=null;//断链恢复原树
				}
			}
			System.out.print(cur1Node.value+" ");
			cur1Node=cur1Node.right;//打印根后再打印右孩子
		}
		System.out.println();
	}

	public static void traversalProOrderMorris(BinaryTreeNode head){//后序遍历
		if(head==null){
			return;
		}
		BinaryTreeNode helpNode=new BinaryTreeNode(Integer.MIN_VALUE);
		helpNode.left=head;
		BinaryTreeNode curNode1=helpNode;
		while(curNode1!=null){
			BinaryTreeNode curNode2=curNode1.left;
			if(curNode2!=null){
				while(curNode2.right!=null&&curNode2.right!=curNode1){
					curNode2=curNode2.right;
				}
				if(curNode2.right==null){
					curNode2.right=curNode1;
					curNode1=curNode1.left;
					continue;
				}else{
					printProPath(curNode1.left,curNode2);
					curNode2.right=null;
				}
			}
			curNode1=curNode1.right;
		}
		helpNode.left=null;
		System.out.println();
	}

	public static void printProPath(BinaryTreeNode from,BinaryTreeNode to){
		reversePath(from,to);
		BinaryTreeNode cur=to;
		while(true){
			System.out.print(cur.value+" ");
			if(cur==from){
				break;
			}
			cur=cur.right;
		}
		reversePath(to,from);
	}

	public static void reversePath(BinaryTreeNode from,BinaryTreeNode to){
		if(from==to){// 逆序
			return;
		}
		BinaryTreeNode cur=from;
		BinaryTreeNode curNext=from.right;
		while(cur!=to){
			BinaryTreeNode tmp=curNext.right;
			curNext.right=cur;
			cur=curNext;
			curNext=tmp;
		}
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.right=new BinaryTreeNode(3);
		head.left.left=new BinaryTreeNode(4);//2的左
		head.left.right=new BinaryTreeNode(5);//2的右
		head.right.left=new BinaryTreeNode(6);//3的左
		head.right.right=new BinaryTreeNode(7);//3的右

		traversalPreOrderMorris(head);
		traversalInOrderMorris(head);
		traversalProOrderMorris(head);

	}

}
