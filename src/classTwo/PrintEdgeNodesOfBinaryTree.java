package classTwo;

public class PrintEdgeNodesOfBinaryTree{//L2P9边界节点的逆时针打印,不会一次解决所有问题时,分情况最左,叶子,最右, 

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
		setLevelArray(head,0,levelArr);//levelArr的0列保存行最左端节点,0列保存行最右端节点
		print2DimensionArray(levelArr);//调试
		printLeftEdge1(levelArr);//打印第0列,即每行的最左节点
		printLeafNodesNotInLevelArr(head,0,levelArr);//叶子非 行最左最右节点
		printRightEdge1(levelArr);//打印第1列,即每行的最右节点
		System.out.println();
	}

	public static int getTreeHeight(BinaryTreeNode head,int level){//求树高
		if(head==null){//叶子节点的,直到上层时,往上返回一直加1的level值，
			return level;
		}
		return Math.max(getTreeHeight(head.left,level+1),//每调一次,level+1
				        getTreeHeight(head.right,level+1));
	}

	public static void setLevelArray(BinaryTreeNode head,int level,
			BinaryTreeNode[][] levelArr){//形参(head,0,levelArr)
		
		if(head==null){
			return;
		}
		
		if(levelArr[level][0]==null){
		//第0列赋值,若已经赋过值,则保持原值不变(先调head.left,先存每行的最左节点)
			levelArr[level][0]=head;
		}
		
		levelArr[level][1]=head;//第1列赋值,每次都赋值,(后调head.right,保证存每行的最左节点)
		setLevelArray(head.left,level+1,levelArr);//实参head.left充当head
		setLevelArray(head.right,level+1,levelArr);//实参head.right充当head
	}
	
	public static void print2DimensionArray(BinaryTreeNode[][] levelArr){//打印二维数组,调试
		for(int i=0;i<levelArr.length;i++){
			for(int j=0;j<levelArr[i].length;j++){
			System.out.print("levelArr["+i+"]["+j+"]="+levelArr[i][j].value+"  ");
			}
		System.out.println();
		}
	}

	public static void printLeftEdge1(BinaryTreeNode[][] levelArr){
		for(int i=0;i!=levelArr.length;i++){//因为第0列存每行的最左节点
			System.out.print(levelArr[i][0].value+" ");
		}
		
	}

	public static void printLeafNodesNotInLevelArr(BinaryTreeNode head,
			int level,BinaryTreeNode[][] levelArr){
		if(head==null){
			return;
		}
		if(head.left==null&&head.right==null&&head!=levelArr[level][0]
		   &&head!=levelArr[level][1]){//打印叶子节点,但不在levelArr数组中
			System.out.print(head.value+" ");
		}
		printLeafNodesNotInLevelArr(head.left,level+1,levelArr);
		printLeafNodesNotInLevelArr(head.right,level+1,levelArr);
	}

	public static void printRightEdge1(BinaryTreeNode[][] levelArr){
		for(int i=levelArr.length-1;i!=-1;i--){//逆序打印,每行最右端节点从下往上
			if(levelArr[i][0]!=levelArr[i][1]){//不重复打印，
				System.out.print(levelArr[i][1].value+" ");
			}
		}
	}
//------------------------------------------------------------------------
	
	public static void printEdge2(BinaryTreeNode head){// 第二种标准(未看)
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
		if(p==null){// boolean标记是否为叶子节点//两个子树，不会重
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

		printEdge1(head);//按  第一种标准  打印
		printEdge2(head);//按  第二种标准  打印

	}
}
