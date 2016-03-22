package classTwo;

import java.util.HashMap;

public class GetLongestPathLength{// L2P21求树中和为sum的最长路径的长度。
	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void getLongestPathLength(BinaryTreeNode head,int sum){// main函数实参sum=6.
		HashMap<Integer,Integer> sumMinIndexMap=new HashMap<Integer,Integer>();
		sumMinIndexMap.put(0,-1); // 一个值都不选时(即层为-1),当前和为0。除8种基本类型外,Map是全局变量
		BinaryTreeNode start=null,end=null;
		int longestPathLength;
		longestPathLength=computeProcess(head,sum,0,0,0,sumMinIndexMap,start,end);
		System.out.println("longestPathLength="+longestPathLength);
		HashMap<BinaryTreeNode,BinaryTreeNode> map=generateChildrenParentMap(head);
		printLongestPathByMap(map,start,end,longestPathLength);
		return ;
	}

	public static int computeProcess(BinaryTreeNode head,int sum,// head,sum=6=target,
			int previousSum,int levelNum,int longestPathLength,// 形参0,0,0
			HashMap<Integer,Integer> sumMinIndexMap,BinaryTreeNode start,BinaryTreeNode end ){//
		if(head==null){
			return longestPathLength;
		}
		int currentSum=previousSum+head.value;// currentSum相当于currentSum[j]，
		if(!sumMinIndexMap.containsKey(currentSum)){
			// 不包含currentSum,就加入map;包含不加,保持原纪录,因为要最长.
			sumMinIndexMap.put(currentSum,levelNum);// 根层levelNum为0
		}

		if(sumMinIndexMap.containsKey(currentSum-sum)){
			// 包含currentSum[i]=currentSum[j]-target
			// 因为target从a[i+1]。。a[j]所以i+1可能为0,i可能为-1,所以put(0,-1)
			int lastIndexI=sumMinIndexMap.get(currentSum-sum);
			start=head;
			// 即map中(currentSum[j]-sum)=currentSum[i]对应的i=levelNumi=lastIndexI,
			// 最早出现在lastIndexI+1层,map中(currentSum-sum)对应lastIndexI=(levelNum-longestPathLength).
			if(levelNum-lastIndexI>longestPathLength){// 最长为i+1,i+2,,j
				longestPathLength=levelNum-lastIndexI;// 长度=末标-初标+1，即levelNum-(lastIndexI+1)+1;
				end=head;
				
			}
		}

		longestPathLength=computeProcess(head.left,sum,currentSum,levelNum+1,
				longestPathLength,sumMinIndexMap,start,end);
		longestPathLength=computeProcess(head.right,sum,currentSum,levelNum+1,
				longestPathLength,sumMinIndexMap,start,end);

		if(levelNum==sumMinIndexMap.get(currentSum)){
			// Map中只求某一分支的和currentSum[j],当子树遍历完了,求下一分支前须删除
			sumMinIndexMap.remove(currentSum);// 当currentSum=1时,移除{1:2}
		}
		return longestPathLength;
	}
	
	public static HashMap<BinaryTreeNode,BinaryTreeNode> generateChildrenParentMap(//生成ParentMap
			BinaryTreeNode head){// 生成一个预处理后的ParentMap，方便多次查询
		HashMap<BinaryTreeNode,BinaryTreeNode> result=new HashMap<BinaryTreeNode,BinaryTreeNode>();
		if(head!=null){
			result.put(head,null);//树根节点的父为空
			generateMapProcess(head,result);
		}
		return result;
	}

	public static void generateMapProcess(BinaryTreeNode head,
			HashMap<BinaryTreeNode,BinaryTreeNode> map){
		if(head.left!=null){
			map.put(head.left,head);
			generateMapProcess(head.left,map);
		}
		if(head.right!=null){
			map.put(head.right,head);
			generateMapProcess(head.right,map);
		}
	}

	public static void printLongestPathByMap(//用map查询
			HashMap<BinaryTreeNode,BinaryTreeNode> map,BinaryTreeNode start,
			BinaryTreeNode end,int longestPathLength){

		BinaryTreeNode ancestor=end;
		System.out.println("路径如下");
		while(longestPathLength!=1){//Set中 不断向上  加入a的所有祖先节点ancestor
			System.out.println(ancestor.value);
			ancestor=map.get(ancestor);//取map的value(key的父节点),
			longestPathLength--;
		}
//	    System.out.println(start.value);
		return ;
	}
	
//	//打印所有为6的可能路径
//	public static void fidSum(BinaryTreeNode head,int sum,ArrayList<Integer> buffer,int level){
//		//fidSum(head,6,buffer,0)
//		if(head==null)
//			return;
//		int tmp=sum;
//		buffer.add(head.value);
//		for(int i=level;i>-1;i--){
//			tmp-=buffer.get(i);
//			if(tmp==0)
//				print(buffer,i,level);
//		}
//		ArrayList<Integer> c1=(ArrayList<Integer>)buffer.clone();
//		ArrayList<Integer> c2=(ArrayList<Integer>)buffer.clone();
//		fidSum(head.left,sum,c1,level+1);
//		fidSum(head.right,sum,c2,level+1);
//	}
//
//	public static void print(ArrayList<Integer> buffer,int level,int i2){
//		System.out.print("找到路径为：");
//		for(int i=level;i<=i2;i++){
//			System.out.print(buffer.get(i)+" ");
//		}
//		System.out.println();
//	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(-3);
		head.left=new BinaryTreeNode(3);
		head.right=new BinaryTreeNode(4);
		head.left.left=new BinaryTreeNode(1);// 3的左为1
		head.left.right=new BinaryTreeNode(0);// 3的右为0
		head.left.right.left=new BinaryTreeNode(1);// 0的左为1
		head.left.right.right=new BinaryTreeNode(6);// 0的右为6
		head.right.left=new BinaryTreeNode(2);// 4的左为2
		head.right.right=new BinaryTreeNode(3);// 4的右为3
		System.out.println("和为6的最长路径“长度”为");
		getLongestPathLength(head,6);// 求和为6的最长路径“长度”
		
		
		
//		ArrayList<Integer> buffer=new ArrayList<Integer>();
//		fidSum(head,6,buffer,0);//所有为6的可能路径
	}

}
