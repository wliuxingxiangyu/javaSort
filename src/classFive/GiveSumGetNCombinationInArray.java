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
	 * 在原来函数的基础上，增加了要求组合由多少个元素组成的限制 所以多了一个变量：nCombination(num)
	 */
	public static void findAllResult(int currentIndex,int aim,int[] arr,
			LinkedList<Integer> sumList,int nCombination,HashSet<String> result){
		if(currentIndex==arr.length){// 和原问题一样，表示遍历完成
			return;
		}

		/*
		 * 增加的第一个剪枝条件，如果之前所选的元素已经等于num， 那么以后不管怎么选都会超过num，所以杀掉所有后续分支
		 */
		if(sumList.size()==nCombination){
			return;
		}

		/*
		 * 增加的第二个剪枝条件，如果（之前所选的元素的个数+剩下的所有元素的个数）都小于num， 说明以后怎么选都不够num个元素，杀掉所有后续分支
		 */
		if(sumList.size()+arr.length-currentIndex<nCombination){
			return;
		}

		int currentSum=(sumList.isEmpty()?0:sumList.getLast())
				+arr[currentIndex];
		/*
		 * 和原问题一样的剪枝条件。
		 */
		if(currentSum>aim){
			return;
		}

		sumList.add(currentSum);
		// 得到解的判断条件，比原问题多了一个对组合元素个数的判断。
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
