package classTwo;

public class PrintEdgeNodesOfBinaryTree{//L2P9�߽�ڵ����ʱ���ӡ,����һ�ν����������ʱ,���������,Ҷ��,����, 

	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void printEdge1(BinaryTreeNode head){
		if(head==null){
			return;
		}
		int height=getTreeHeight(head,0);
		BinaryTreeNode[][] levelArr=new BinaryTreeNode[height][2];
		setLevelArray(head,0,levelArr);//levelArr��0�б���������˽ڵ�,0�б��������Ҷ˽ڵ�
		print2DimensionArray(levelArr);//����
		printLeftEdge1(levelArr);//��ӡ��0��,��ÿ�е�����ڵ�
		printLeafNodesNotInLevelArr(head,0,levelArr);//Ҷ�ӷ� ���������ҽڵ�
		printRightEdge1(levelArr);//��ӡ��1��,��ÿ�е����ҽڵ�
		System.out.println();
	}

	public static int getTreeHeight(BinaryTreeNode head,int level){//������
		if(head==null){//Ҷ�ӽڵ��,ֱ���ϲ�ʱ,���Ϸ���һֱ��1��levelֵ��
			return level;
		}
		return Math.max(getTreeHeight(head.left,level+1),//ÿ��һ��,level+1
				        getTreeHeight(head.right,level+1));
	}

	public static void setLevelArray(BinaryTreeNode head,int level,
			BinaryTreeNode[][] levelArr){//�β�(head,0,levelArr)
		
		if(head==null){
			return;
		}
		
		if(levelArr[level][0]==null){
		//��0�и�ֵ,���Ѿ�����ֵ,�򱣳�ԭֵ����(�ȵ�head.left,�ȴ�ÿ�е�����ڵ�)
			levelArr[level][0]=head;
		}
		
		levelArr[level][1]=head;//��1�и�ֵ,ÿ�ζ���ֵ,(���head.right,��֤��ÿ�е�����ڵ�)
		setLevelArray(head.left,level+1,levelArr);//ʵ��head.left�䵱head
		setLevelArray(head.right,level+1,levelArr);//ʵ��head.right�䵱head
	}
	
	public static void print2DimensionArray(BinaryTreeNode[][] levelArr){//��ӡ��ά����,����
		for(int i=0;i<levelArr.length;i++){
			for(int j=0;j<levelArr[i].length;j++){
			System.out.print("levelArr["+i+"]["+j+"]="+levelArr[i][j].value+"  ");
			}
		System.out.println();
		}
	}

	public static void printLeftEdge1(BinaryTreeNode[][] levelArr){
		for(int i=0;i!=levelArr.length;i++){//��Ϊ��0�д�ÿ�е�����ڵ�
			System.out.print(levelArr[i][0].value+" ");
		}
		
	}

	public static void printLeafNodesNotInLevelArr(BinaryTreeNode head,
			int level,BinaryTreeNode[][] levelArr){
		if(head==null){
			return;
		}
		if(head.left==null&&head.right==null&&head!=levelArr[level][0]
		   &&head!=levelArr[level][1]){//��ӡҶ�ӽڵ�,������levelArr������
			System.out.print(head.value+" ");
		}
		printLeafNodesNotInLevelArr(head.left,level+1,levelArr);
		printLeafNodesNotInLevelArr(head.right,level+1,levelArr);
	}

	public static void printRightEdge1(BinaryTreeNode[][] levelArr){
		for(int i=levelArr.length-1;i!=-1;i--){//�����ӡ,ÿ�����Ҷ˽ڵ��������
			if(levelArr[i][0]!=levelArr[i][1]){//���ظ���ӡ��
				System.out.print(levelArr[i][1].value+" ");
			}
		}
	}
//------------------------------------------------------------------------
	
	public static void printEdge2(BinaryTreeNode head){// �ڶ��ֱ�׼(δ��)
		if(head==null){
			System.out.println();
			return;
		}
		System.out.print(head.value+" ");
		if(head.left!=null&&head.right!=null){
			printLeftEdge2(head.left,true);
			printRightEdge2(head.right,true);
			System.out.println();
		}else{
			printEdge2(head.left!=null?head.left:head.right);
		}
	}

	public static void printLeftEdge2(BinaryTreeNode p,boolean print){
		if(p==null){// boolean����Ƿ�ΪҶ�ӽڵ�//����������������
			return;
		}
		if(print||(p.left==null&&p.right==null)){
			System.out.print(p.value+" ");
		}
		printLeftEdge2(p.left,print);
		printLeftEdge2(p.right,(print&&p.left==null?true:false));
	}

	public static void printRightEdge2(BinaryTreeNode p,boolean print){
		if(p==null){
			return;
		}
		printRightEdge2(p.left,(print&&p.right==null?true:false));
		printRightEdge2(p.right,print);
		if(print||(p.left==null&&p.right==null)){
			System.out.print(p.value+" ");
		}
	}

	public static void main(String[] args){
		/*
		 * _______________1_______________ 
		 * / \ 2____ ____3 \ / __4__ __5__ / \ /
		 * \ __6 7__ __8 __9__ / \ / / \ 10_ __11 __12 __13 14 \ / / / 15 __16
		 * 17 18 / 19__ \ 20
		 */
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.left.right=new BinaryTreeNode(4);
		head.left.right.left=new BinaryTreeNode(6);
		head.left.right.right=new BinaryTreeNode(7);
		head.left.right.left.left=new BinaryTreeNode(10);
		head.left.right.left.left.right=new BinaryTreeNode(15);
		head.left.right.right.right=new BinaryTreeNode(11);
		head.left.right.right.right.left=new BinaryTreeNode(16);
		head.left.right.right.right.left.left=new BinaryTreeNode(19);
		head.left.right.right.right.left.left.right=new BinaryTreeNode(20);

		head.right=new BinaryTreeNode(3);
		head.right.left=new BinaryTreeNode(5);
		head.right.left.left=new BinaryTreeNode(8);
		head.right.left.left.left=new BinaryTreeNode(12);
		head.right.left.left.left.left=new BinaryTreeNode(17);
		head.right.left.right=new BinaryTreeNode(9);
		head.right.left.right.left=new BinaryTreeNode(13);
		head.right.left.right.left.left=new BinaryTreeNode(18);
		head.right.left.right.right=new BinaryTreeNode(14);

		printEdge1(head);//��  ��һ�ֱ�׼  ��ӡ
		printEdge2(head);//��  �ڶ��ֱ�׼  ��ӡ

	}
}
