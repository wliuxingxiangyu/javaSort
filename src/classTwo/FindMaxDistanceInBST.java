package classTwo;
public class FindMaxDistanceInBST {//L2P5���������Ľڵ����:3�ֱȽϣ�����ڵ�h(h��������,h��������,��h�ڵ������)
//������ "��һ��"���� ���ڵ�root.
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getMaxDistance(Node head) {
		int[] record = new int[1];//һ��Ԫ�ص�����record,Ϊ�˴���ַ
		return computeProcess(head,record);
		//getMaxDistance��������������:computeProcess�ķ���ֵ
	}

	public static int computeProcess(Node head, int[] record) {
		if(head==null){//��headΪ��,��������;//record��head���������
			record[0] = 0;//Ҷ�ӽڵ㸳Ϊ0
			return 0;//�����ľ���Ϊ0
		}
		int leftMax=computeProcess(head.left,record);//leftMax��head.left���������
		int fromLeftHeadMax=record[0];//����1:��������������
		int rightMax=computeProcess(head.right,record);//rightMax��head.right���������
		int fromRightHeadMax=record[0];//����2:��������������
		record[0]=Math.max(fromLeftHeadMax,fromRightHeadMax)+1;//�ݹ鴫�����ڵ�,���ڱȽ�
		int curNodeMax=fromLeftHeadMax+fromRightHeadMax+1;//����3:���������,Ҫ�ӱ���
		return Math.max(Math.max(leftMax, rightMax), curNodeMax);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);//3����
		head1.right.right = new Node(7);//3����
		head1.left.left.left = new Node(8);//4����
		head1.right.left.right = new Node(9);//6����
		System.out.println(getMaxDistance(head1));

//		Node head2 = new Node(1);
//		head2.left = new Node(2);
//		head2.right = new Node(3);
//		head2.right.left = new Node(4);
//		head2.right.right = new Node(5);
//		head2.right.left.left = new Node(6);
//		head2.right.right.right = new Node(7);
//		head2.right.left.left.left = new Node(8);
//		head2.right.right.right.right = new Node(9);
//		System.out.println(getMaxDistance(head2));
	}

}
