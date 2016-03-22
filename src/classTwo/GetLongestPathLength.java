package classTwo;

import java.util.HashMap;

public class GetLongestPathLength{// L2P21�����к�Ϊsum���·���ĳ��ȡ�
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
		sumMinIndexMap.put(0,-1); // һ��ֵ����ѡʱ(����Ϊ-1),��ǰ��Ϊ0����8�ֻ���������,Map��ȫ�ֱ���
		BinaryTreeNode start=null,end=null;
		int longestPathLength;
		longestPathLength=computeProcess(head,sum,0,0,0,sumMinIndexMap,start,end);
		System.out.println("longestPathLength="+longestPathLength);
		HashMap<BinaryTreeNode,BinaryTreeNode> map=generateChildrenParentMap(head);
		printLongestPathByMap(map,start,end,longestPathLength);
		return ;
	}

	public static int computeProcess(BinaryTreeNode head,int sum,// head,sum=6=target,
			int previousSum,int levelNum,int longestPathLength,// �β�0,0,0
			HashMap<Integer,Integer> sumMinIndexMap,BinaryTreeNode start,BinaryTreeNode end ){//
		if(head==null){
			return longestPathLength;
		}
		int currentSum=previousSum+head.value;// currentSum�൱��currentSum[j]��
		if(!sumMinIndexMap.containsKey(currentSum)){
			// ������currentSum,�ͼ���map;��������,����ԭ��¼,��ΪҪ�.
			sumMinIndexMap.put(currentSum,levelNum);// ����levelNumΪ0
		}

		if(sumMinIndexMap.containsKey(currentSum-sum)){
			// ����currentSum[i]=currentSum[j]-target
			// ��Ϊtarget��a[i+1]����a[j]����i+1����Ϊ0,i����Ϊ-1,����put(0,-1)
			int lastIndexI=sumMinIndexMap.get(currentSum-sum);
			start=head;
			// ��map��(currentSum[j]-sum)=currentSum[i]��Ӧ��i=levelNumi=lastIndexI,
			// ���������lastIndexI+1��,map��(currentSum-sum)��ӦlastIndexI=(levelNum-longestPathLength).
			if(levelNum-lastIndexI>longestPathLength){// �Ϊi+1,i+2,,j
				longestPathLength=levelNum-lastIndexI;// ����=ĩ��-����+1����levelNum-(lastIndexI+1)+1;
				end=head;
				
			}
		}

		longestPathLength=computeProcess(head.left,sum,currentSum,levelNum+1,
				longestPathLength,sumMinIndexMap,start,end);
		longestPathLength=computeProcess(head.right,sum,currentSum,levelNum+1,
				longestPathLength,sumMinIndexMap,start,end);

		if(levelNum==sumMinIndexMap.get(currentSum)){
			// Map��ֻ��ĳһ��֧�ĺ�currentSum[j],��������������,����һ��֧ǰ��ɾ��
			sumMinIndexMap.remove(currentSum);// ��currentSum=1ʱ,�Ƴ�{1:2}
		}
		return longestPathLength;
	}
	
	public static HashMap<BinaryTreeNode,BinaryTreeNode> generateChildrenParentMap(//����ParentMap
			BinaryTreeNode head){// ����һ��Ԥ������ParentMap�������β�ѯ
		HashMap<BinaryTreeNode,BinaryTreeNode> result=new HashMap<BinaryTreeNode,BinaryTreeNode>();
		if(head!=null){
			result.put(head,null);//�����ڵ�ĸ�Ϊ��
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

	public static void printLongestPathByMap(//��map��ѯ
			HashMap<BinaryTreeNode,BinaryTreeNode> map,BinaryTreeNode start,
			BinaryTreeNode end,int longestPathLength){

		BinaryTreeNode ancestor=end;
		System.out.println("·������");
		while(longestPathLength!=1){//Set�� ��������  ����a���������Ƚڵ�ancestor
			System.out.println(ancestor.value);
			ancestor=map.get(ancestor);//ȡmap��value(key�ĸ��ڵ�),
			longestPathLength--;
		}
//	    System.out.println(start.value);
		return ;
	}
	
//	//��ӡ����Ϊ6�Ŀ���·��
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
//		System.out.print("�ҵ�·��Ϊ��");
//		for(int i=level;i<=i2;i++){
//			System.out.print(buffer.get(i)+" ");
//		}
//		System.out.println();
//	}

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
		
		
		
//		ArrayList<Integer> buffer=new ArrayList<Integer>();
//		fidSum(head,6,buffer,0);//����Ϊ6�Ŀ���·��
	}

}
