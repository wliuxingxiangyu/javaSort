package classFive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class GiveSumGetNCombinationInArray{
	public static void printNCombinationForSum(int[] arr,int sum,
			int nCombination){
		int[] cloneArr=cloneArray(arr);
		Arrays.sort(cloneArr);
		LinkedList<Integer> sumList=new LinkedList<Integer>();
		HashSet<String> result=new HashSet<String>();
		findAllResult(0,sum,cloneArr,sumList,nCombination,result);
		printResult(result);
	}

	/*
	 * ��ԭ�������Ļ����ϣ�������Ҫ������ɶ��ٸ�Ԫ����ɵ����� ���Զ���һ��������nCombination(num)
	 */
	public static void findAllResult(int currentIndex,int aim,int[] arr,
			LinkedList<Integer> sumList,int nCombination,HashSet<String> result){
		if(currentIndex==arr.length){// ��ԭ����һ������ʾ�������
			return;
		}

		/*
		 * ���ӵĵ�һ����֦���������֮ǰ��ѡ��Ԫ���Ѿ�����num�� ��ô�Ժ󲻹���ôѡ���ᳬ��num������ɱ�����к�����֧
		 */
		if(sumList.size()==nCombination){
			return;
		}

		/*
		 * ���ӵĵڶ�����֦�����������֮ǰ��ѡ��Ԫ�صĸ���+ʣ�µ�����Ԫ�صĸ�������С��num�� ˵���Ժ���ôѡ������num��Ԫ�أ�ɱ�����к�����֧
		 */
		if(sumList.size()+arr.length-currentIndex<nCombination){
			return;
		}

		int currentSum=(sumList.isEmpty()?0:sumList.getLast())
				+arr[currentIndex];
		/*
		 * ��ԭ����һ���ļ�֦������
		 */
		if(currentSum>aim){
			return;
		}

		sumList.add(currentSum);
		// �õ�����ж���������ԭ�������һ�������Ԫ�ظ������жϡ�
		if(sumList.getLast()==aim&&sumList.size()==nCombination){
			updateResult(sumList,result);
		}
		findAllResult(currentIndex+1,aim,arr,sumList,nCombination,result);
		sumList.removeLast();
		findAllResult(currentIndex+1,aim,arr,sumList,nCombination,result);
	}

	public static int[] cloneArray(int[] arr){
		int[] result=new int[arr.length];
		for(int i=0;i!=arr.length;i++){
			result[i]=arr[i];
		}
		return result;
	}

	public static void updateResult(LinkedList<Integer> sumList,
			HashSet<String> result){
		int previousSum=0;
		String currentResult="";
		for(Integer currentSum:sumList){
			currentResult+=String.valueOf(currentSum-previousSum)+" ";
			previousSum=currentSum;
		}
		result.add(currentResult);
	}

	public static void printResult(HashSet<String> result){
		for(String record:result){
			System.out.println(record);
		}
	}

	public static void main(String[] args){
		int[] arr={5, 2, 6, 3, 4, 2, -1};
		int num=3;
		int given=10;
		printNCombinationForSum(arr,given,num);
	}
}
