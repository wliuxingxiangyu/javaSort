package classOne;

public class BSTToDoubleLinkedList{//L1P16将BST转双向链表

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static BinaryTreeNode convert(BinaryTreeNode head) {
		if (head == null)
			return null;
		BinaryTreeNode mostRight = convertToDoubleList(head);
		BinaryTreeNode mostLeft=mostRight.right;//为了统一返回最右的节点
		mostRight.right = null;//断:最右指向最左的那根链.标准的双向链表
		return mostLeft;//返回 标准的双向链表
	}

	public static BinaryTreeNode convertToDoubleList(BinaryTreeNode head) {
		if (head == null) {
			return null;
		}
		if (head.left == null && head.right == null){//左右子树同时为空:叶子
			head.right = head;
			return head;//叶子节点 右指针域   指向自己后，返回
		}
		BinaryTreeNode leftPartMostRight=convertToDoubleList(head.left);//5的左返回
		BinaryTreeNode rightPartMostRight=convertToDoubleList(head.right);//5的右返回
		if(leftPartMostRight!=null&&rightPartMostRight!=null){//有左右子树,进if
			BinaryTreeNode mostLeft=leftPartMostRight.right;//左部分的最左端
			BinaryTreeNode thisLeftChild=leftPartMostRight;//左部分的最右端
			BinaryTreeNode thisRightChild=rightPartMostRight.right;//右部分的最左端
			BinaryTreeNode mostRight=rightPartMostRight;//右部分的最右端
			head.left = thisLeftChild;thisLeftChild.right = head;//挂相邻双向链
			head.right = thisRightChild;thisRightChild.left = head;//挂相邻双向链
			mostRight.right = mostLeft;//最右节点的右指针域指向  最左节点
			return mostRight;//返回最右节点
		} else if(rightPartMostRight==null){//右子树为空,左非空,返回的是leftPartMostRight
			BinaryTreeNode thisLeftChild = leftPartMostRight;
			BinaryTreeNode mostLeft = leftPartMostRight.right;
			head.left=thisLeftChild;thisLeftChild.right=head;//挂相邻双向链
			head.right = mostLeft;//最右节点的右指针域指向  最左节点
			return head;//返回最右节点（因为右子树为空，所以head为最右节点）
		} else {//右子树非空,返回的是rightPartMostRight
			BinaryTreeNode thisRightChild = rightPartMostRight.right;
			BinaryTreeNode mostRight = rightPartMostRight;
			head.right=thisRightChild;thisRightChild.left=head;//挂相邻双向链
			mostRight.right = head;//最右节点的右指针域指向  最左节点
			return mostRight;//返回最右节点
		}
	}

	public static void printBST(BinaryTreeNode head){//BST中序遍历
		if (head == null) {
			return;
		}
		printBST(head.left);
		System.out.print(head.value + " ");
		printBST(head.right);
	}

	public static void printDoubleList(BinaryTreeNode head) {
		BinaryTreeNode current = head;
		if (head == null) {
			System.out.println("No data");
			return;
		}
		BinaryTreeNode last = null;
		System.out.println();
		
		System.out.print("Show DoubleList from left to right: ");
		while (current != null) {
			System.out.print(current.value + " ");
			last=current;//为了下面  从右往左打印,最后的非空current即last
			current = current.right;//从左往右打印，利用right
		}
		System.out.println();
		
		System.out.print("Show DoubleList from right to left: ");
		while (last != null) {
			System.out.print(last.value + " ");
			last = last.left;//从右往左打印，利用left
		}
		System.out.println();
	}

	public static void main(String[] args) {
		BinaryTreeNode head = new BinaryTreeNode(5);
		head.left = new BinaryTreeNode(2);
		head.right = new BinaryTreeNode(9);
		head.left.left = new BinaryTreeNode(1);
		head.right.right = new BinaryTreeNode(10);
		head.right.left = new BinaryTreeNode(7);
		head.right.left.right = new BinaryTreeNode(8);
//		head.left.right = new BinaryTreeNode(3);//少节点:方便调试
//		head.left.right.right = new BinaryTreeNode(4);
//		head.right.left.left = new BinaryTreeNode(6);
		System.out.print("Show BST tree in-order: ");
		printBST(head);
		BinaryTreeNode doubleList=convert(head);//转换
		System.out.println();
		System.out.print("Show DoubleList: ");
		printDoubleList(doubleList);
	}

}