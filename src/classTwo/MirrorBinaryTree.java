package classTwo;

public class MirrorBinaryTree {//L2P3���������κ������������������ɡ�
	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static BinaryTreeNode createNewMirrorTree(BinaryTreeNode head) {
		if (head == null) {//�˺���Ϊ���� һ������--������
			return null;
		}
		BinaryTreeNode newHead=new BinaryTreeNode(head.value);//���ڵ㸳ֵ
		newHead.left=createNewMirrorTree(head.right);//���Ӹ�ֵ
		newHead.right = createNewMirrorTree(head.left);//�Һ��Ӹ�ֵ
		return newHead;
	}

	public static BinaryTreeNode changeToMirrorTree(BinaryTreeNode head) {
		if (head == null) {
			return null;
		}
		BinaryTreeNode tmp = head.left;
		head.left = head.right;
		head.right = tmp;//�Ƚ���,�ٵݹ鵽Ҷ��,���Բ��õݹ鵽Ҷ���ٽ�����
		changeToMirrorTree(head.left);
		changeToMirrorTree(head.right);
		return head;
	}
	
	public static BinaryTreeNode changeToMirrorTree2(BinaryTreeNode head) {
		if(head==null){//�˺���Ϊ��ԭ�������ı�--��ɾ�����
			return null;
		}
		changeToMirrorTree2(head.left);
		changeToMirrorTree2(head.right);//�ݹ鵽Ҷ���ٽ���
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
		printTreeInOrder(head);//���������� ԭ��
		System.out.println();
		
		System.out.println("===========New Mirror Tree===========");
		BinaryTreeNode mirror=createNewMirrorTree(head);//�˺���Ϊ���� һ������--������
		printTreeInOrder(mirror);//����������������
		System.out.println();
		
		System.out.println("========Change to Mirror Tree========");
		BinaryTreeNode head1=changeToMirrorTree(head);//�˺���Ϊ��ԭ�������ı�--��ɾ�����
		printTreeInOrder(head1);
		System.out.println();

	}
}
