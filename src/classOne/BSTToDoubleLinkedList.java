package classOne;

public class BSTToDoubleLinkedList{//L1P16��BSTת˫������

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
		BinaryTreeNode mostLeft=mostRight.right;//Ϊ��ͳһ�������ҵĽڵ�
		mostRight.right = null;//��:����ָ��������Ǹ���.��׼��˫������
		return mostLeft;//���� ��׼��˫������
	}

	public static BinaryTreeNode convertToDoubleList(BinaryTreeNode head) {
		if (head == null) {
			return null;
		}
		if (head.left == null && head.right == null){//��������ͬʱΪ��:Ҷ��
			head.right = head;
			return head;//Ҷ�ӽڵ� ��ָ����   ָ���Լ��󣬷���
		}
		BinaryTreeNode leftPartMostRight=convertToDoubleList(head.left);//5���󷵻�
		BinaryTreeNode rightPartMostRight=convertToDoubleList(head.right);//5���ҷ���
		if(leftPartMostRight!=null&&rightPartMostRight!=null){//����������,��if
			BinaryTreeNode mostLeft=leftPartMostRight.right;//�󲿷ֵ������
			BinaryTreeNode thisLeftChild=leftPartMostRight;//�󲿷ֵ����Ҷ�
			BinaryTreeNode thisRightChild=rightPartMostRight.right;//�Ҳ��ֵ������
			BinaryTreeNode mostRight=rightPartMostRight;//�Ҳ��ֵ����Ҷ�
			head.left = thisLeftChild;thisLeftChild.right = head;//������˫����
			head.right = thisRightChild;thisRightChild.left = head;//������˫����
			mostRight.right = mostLeft;//���ҽڵ����ָ����ָ��  ����ڵ�
			return mostRight;//�������ҽڵ�
		} else if(rightPartMostRight==null){//������Ϊ��,��ǿ�,���ص���leftPartMostRight
			BinaryTreeNode thisLeftChild = leftPartMostRight;
			BinaryTreeNode mostLeft = leftPartMostRight.right;
			head.left=thisLeftChild;thisLeftChild.right=head;//������˫����
			head.right = mostLeft;//���ҽڵ����ָ����ָ��  ����ڵ�
			return head;//�������ҽڵ㣨��Ϊ������Ϊ�գ�����headΪ���ҽڵ㣩
		} else {//�������ǿ�,���ص���rightPartMostRight
			BinaryTreeNode thisRightChild = rightPartMostRight.right;
			BinaryTreeNode mostRight = rightPartMostRight;
			head.right=thisRightChild;thisRightChild.left=head;//������˫����
			mostRight.right = head;//���ҽڵ����ָ����ָ��  ����ڵ�
			return mostRight;//�������ҽڵ�
		}
	}

	public static void printBST(BinaryTreeNode head){//BST�������
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
			last=current;//Ϊ������  ���������ӡ,���ķǿ�current��last
			current = current.right;//�������Ҵ�ӡ������right
		}
		System.out.println();
		
		System.out.print("Show DoubleList from right to left: ");
		while (last != null) {
			System.out.print(last.value + " ");
			last = last.left;//���������ӡ������left
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
//		head.left.right = new BinaryTreeNode(3);//�ٽڵ�:�������
//		head.left.right.right = new BinaryTreeNode(4);
//		head.right.left.left = new BinaryTreeNode(6);
		System.out.print("Show BST tree in-order: ");
		printBST(head);
		BinaryTreeNode doubleList=convert(head);//ת��
		System.out.println();
		System.out.print("Show DoubleList: ");
		printDoubleList(doubleList);
	}

}