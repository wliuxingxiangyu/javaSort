package classTwo;

public class ConstructBinaryTreeByInorderPreorder{// L2P20�ȸ�,�и�����������ؽ�����

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static BinaryTreeNode generateTree(int[] preArr,int[] inArr){
		return generateProcess(preArr,0,preArr.length-1,inArr,0,inArr.length-1);
	}

	public static BinaryTreeNode generateProcess(int[] preArr,int preStart,
			int preEnd,int[] inArr,int inStart,int inEnd){ // Start,end��Χ
		if(preStart>preEnd){
			return null;
		}
		if(preEnd==preStart){// ʣ1��
			return new BinaryTreeNode(preArr[preStart]);
		}
		if(preStart==preEnd-1){// ʣ2��
			BinaryTreeNode head=new BinaryTreeNode(preArr[preStart]);
			BinaryTreeNode child=new BinaryTreeNode(preArr[preEnd]);
			if(preArr[preStart]==inArr[inStart]){
				head.right=child;//ʣ������Ԫ��ʱ,���������һ��Ԫ�����,��ڶ���Ԫ��Ϊ�Һ��ӡ�
			}else{
				head.left=child;
			}
			return head;
		}
		BinaryTreeNode head=new BinaryTreeNode(preArr[preStart]);// ʣ3.4..��
		int preMid=preStart;
		int inMid=inStart;
		for(int i=inStart;i<inEnd;i++){
			if(preArr[preStart]==inArr[i]){//������������ҡ����±ꡱ
				inMid=i;//�������"���±�"
				break;
			}
			preMid++;//�������"���±�"��Ӧ�����������λ�ã�����������β��
		}
		head.left=generateProcess(preArr,preStart+1,preMid,inArr,inStart,inMid-1);
		//���鷶Χ��Сβ,Ҫ��preStart+1<preMid,����������Ԫ��,���ݹ顣��һ��Ԫ��Ϊbasecase��preStart=preMidʱΪbasecase��
		head.right=generateProcess(preArr,preMid+1,preEnd,inArr,inMid+1,inEnd);
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

	public static void printTreeInOrder(BinaryTreeNode head){
		if(head==null){
			return;
		}
		printTreeInOrder(head.left);
		System.out.print(head.value+" ");
		printTreeInOrder(head.right);
	}

	public static void main(String[] args){
		int[] preArr={7, 10, 4, 3, 1, 2, 8, 11};
		int[] inArr={4, 10, 3, 1, 7, 11, 8, 2};
		BinaryTreeNode head=generateTree(preArr,inArr);
		System.out.println("ǰ������Ľ�����£�");
		printTreePreOrder(head);
		System.out.println( );
		System.out.println("��������Ľ�����£�");
		printTreeInOrder(head);

	}
}
