package classTwo;

public class MirrorBinaryTree {//L2P3镜子树，任何左右子树互换，即可。
	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static BinaryTreeNode createNewMirrorTree(BinaryTreeNode head) {
		if (head == null) {//此函数为创建 一颗新树--镜子树
			return null;
		}
		BinaryTreeNode newHead=new BinaryTreeNode(head.value);//根节点赋值
		newHead.left=createNewMirrorTree(head.right);//左孩子赋值
		newHead.right = createNewMirrorTree(head.left);//右孩子赋值
		return newHead;
	}

	public static BinaryTreeNode changeToMirrorTree(BinaryTreeNode head) {
		if (head == null) {
			return null;
		}
		BinaryTreeNode tmp = head.left;
		head.left = head.right;
		head.right = tmp;//先交换,再递归到叶子,可以不用递归到叶子再交换，
		changeToMirrorTree(head.left);
		changeToMirrorTree(head.right);
		return head;
	}
	
	public static BinaryTreeNode changeToMirrorTree2(BinaryTreeNode head) {
		if(head==null){//此函数为在原树上做改变--变成镜子树
			return null;
		}
		changeToMirrorTree2(head.left);
		changeToMirrorTree2(head.right);//递归到叶子再交换
		BinaryTreeNode tmp = head.left;
		head.left = head.right;
		head.right = tmp;
		return head;
	}

	public static void printTreePreOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		printTreePreOrder(head.left);
		printTreePreOrder(head.right);
	}

	public static void printTreeInOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		printTreeInOrder(head.left);
		System.out.print(head.value + " ");
		printTreeInOrder(head.right);
	}

	public static void main(String[] args) {
		BinaryTreeNode head = new BinaryTreeNode(3);
		head.left = new BinaryTreeNode(2);
		head.right = new BinaryTreeNode(5);
		head.left.left = new BinaryTreeNode(1);
		head.right.left = new BinaryTreeNode(4);
		head.right.right = new BinaryTreeNode(6);
		
		System.out.println("============Original Tree============");
		printTreeInOrder(head);//中序遍历输出 原树
		System.out.println();
		
		System.out.println("===========New Mirror Tree===========");
		BinaryTreeNode mirror=createNewMirrorTree(head);//此函数为创建 一颗新树--镜子树
		printTreeInOrder(mirror);//中序遍历输出镜子树
		System.out.println();
		
		System.out.println("========Change to Mirror Tree========");
		BinaryTreeNode head1=changeToMirrorTree(head);//此函数为在原树上做改变--变成镜子树
		printTreeInOrder(head1);
		System.out.println();

	}
}
