package classTwo;
import java.util.HashMap;
public class PrintLongestPathBT{
	public static class BinaryTreeNode{
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void getLongestPathLength(BinaryTreeNode head,int sum){// main����ʵ��sum=6.
		HashMap<Integer,Integer> sumMinIndexMap=new HashMap<Integer,Integer>();
		sumMinIndexMap.put(0,-1);
		BinaryTreeNode end=null;
		int longestPathLength;
		longestPathLength=computeProcess(head,sum,0,0,0,sumMinIndexMap,end);
		System.out.println("longestPathLength="+longestPathLength);
		HashMap<BinaryTreeNode,BinaryTreeNode> map=generateChildrenParentMap(head);
		printLongestPathByMap(map,end,longestPathLength);
		return;
	}

	public static int computeProcess(BinaryTreeNode head,int sum,// head,sum=6=target,
			int previousSum,int levelNum,int longestPathLength,// ʵ��0,0,0
			HashMap<Integer,Integer> sumMinIndexMap,BinaryTreeNode end){//
		if(head==null){
			return longestPathLength;
		}
		int currentSum=previousSum+head.value;// currentSum�൱��currentSum[j]��
		if(!sumMinIndexMap.containsKey(currentSum)){
			sumMinIndexMap.put(currentSum,levelNum);// ����levelNumΪ0
		}

		if(sumMinIndexMap.containsKey(currentSum-sum)){
			// ����currentSum[i]=currentSum[j]-target
			int lastIndexI=sumMinIndexMap.get(currentSum-sum);
			// ��map��(currentSum[j]-sum)=currentSum[i]��Ӧ��i=levelNumi=lastIndexI,
			// ���������lastIndexI+1��,map��(currentSum-sum)��ӦlastIndexI=(levelNum-longestPathLength).
			if(levelNum-lastIndexI>longestPathLength){// �Ϊi+1,i+2,,j
				longestPathLength=levelNum-lastIndexI;// ����=ĩ��-����+1����levelNum-(lastIndexI+1)+1;
				end=head;
			}
		}

		longestPathLength=computeProcess(head.left,sum,currentSum,levelNum+1,
				longestPathLength,sumMinIndexMap,end);
		longestPathLength=computeProcess(head.right,sum,currentSum,levelNum+1,
				longestPathLength,sumMinIndexMap,end);

		if(levelNum==sumMinIndexMap.get(currentSum)){
			sumMinIndexMap.remove(currentSum);
		}
		return longestPathLength;
	}

	public static HashMap<BinaryTreeNode,BinaryTreeNode> generateChildrenParentMap(// ����ParentMap
			BinaryTreeNode head){// ����һ��Ԥ������ParentMap�������β�ѯ
		HashMap<BinaryTreeNode,BinaryTreeNode> result=new HashMap<BinaryTreeNode,BinaryTreeNode>();
		if(head!=null){
			result.put(head,null);// �����ڵ�ĸ�Ϊ��
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

	public static void printLongestPathByMap(
			// ��map��ѯ
			HashMap<BinaryTreeNode,BinaryTreeNode> map,BinaryTreeNode end,
			int longestPathLength){

		BinaryTreeNode ancestor=end;
		System.out.println("·������");
		while(longestPathLength!=1){// Set�� �������� ����a���������Ƚڵ�ancestor
			System.out.println(ancestor.value);
			ancestor=map.get(ancestor);// ȡmap��value(key�ĸ��ڵ�),
			longestPathLength--;
		}
		return;
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(-3);
		head.left=new BinaryTreeNode(3);
		head.right=new BinaryTreeNode(4);
		head.left.left=new BinaryTreeNode(1);// 3����Ϊ1
		head.left.right=new BinaryTreeNode(0);// 3����Ϊ0
		head.left.right.left=new BinaryTreeNode(1);// 0����Ϊ1
		head.left.right.right=new BinaryTreeNode(6);// 0����Ϊ6
		head.right.left=new BinaryTreeNode(2);// 4����Ϊ2
		head.right.right=new BinaryTreeNode(3);// 4����Ϊ3
		System.out.println("��Ϊ6���·�������ȡ�Ϊ");
		getLongestPathLength(head,6);// ���Ϊ6���·�������ȡ�
	}

}
