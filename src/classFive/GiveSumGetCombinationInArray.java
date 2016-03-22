package classFive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class GiveSumGetCombinationInArray{
	public static void printCombinationForSum(int[] arr,int given){
		int[] cloneArr=cloneArray(arr); // 我选择把传进来的数组拷贝一份处理，这样就可以避免改变原始数组
		Arrays.sort(cloneArr); // 把拷贝的数组排序作为我想处理的对象
		LinkedList<Integer> sumList=new LinkedList<Integer>(); // 一个对于递归函数来说的全局变量，可以跟踪我的每一次决策。
		HashSet<String> result=new HashSet<String>(); // 保存每一种方案，因为是HashSet，所以可以将相同的组合去重
		findAllResult(0,given,cloneArr,sumList,result); // 递归函数，找所有组合
		printResult(result); // 打印所有去重的结果
	}

	/*
	 * 主要的递归函数 参数： currentIndex : 从0到arr.length-1,表示目前遍历到了第几号元素 given : 我想要找到的和
	 * arr : 从小到大排序后的数组 sumList : 里面放着所有元素累加的结果，比如我依次选择了2,2,3,4四个元素.
	 * 那么在sumList中的值为,2->4->7->11.
	 * 这样我不仅可以知道目前我所选的元素总共的和是多少，而且可以通过简单的减法，计算得到是哪些元素。 result :
	 * 当我得到given的时候，我把这一种方案记录在result中。
	 */
	public static void findAllResult(int currentIndex,int given,int[] arr,
			LinkedList<Integer> sumList,HashSet<String> result){
		if(currentIndex==arr.length){ // 表示已经遍历完所有的元素，直接返回，//currentIndex为加到了第几个
			return;
		}
		int currentSum=(sumList.isEmpty()?0:sumList.getLast())
				+arr[currentIndex]; // 之前所选元素的总和，加上遍历到当前元素的值。

		/*
		 * 下面的if是重要的一次剪枝，因为我的arr是从小到大排序过的，同时given是一个不小于0的数
		 * 所以如果（之前所选元素的总和+当前元素的值）大于given，那么（之后的任何元素+之前所选元素的总和）都会大于given。
		 * 所以我们把接下来的分支全部杀死，因为接下来的逻辑展开都不可能得到given了。
		 */
		if(currentSum>given){
			return;
		}

		sumList.add(currentSum);// 将（当前元素+之前所选元素的总和）的值之前放入sumList，表示当前解包含当前元素。
		if(sumList.getLast()==given){ // 符合条件了，我们得到了given
			updateResult(sumList,result); // 记录当前解
		}
		findAllResult(currentIndex+1,given,arr,sumList,result);// 当前解包含当前元素,继续逻辑展开
		sumList.removeLast();// 将当前元素不作为当前解的一部分
		findAllResult(currentIndex+1,given,arr,sumList,result);// 当前解不包含当前元素,继续逻辑展开
	}

	/*
	 * 简单的拷贝数组的函数 为什么要拷贝出一份处理呢？ 为了剪枝条件成立，我们需要一个排序过的数组，但是如果直接把原始数组排序并不合适。
	 * 举个例子，也许你仅仅是想看看自己的数组有多少种方案可以组成given值， 然后你调用了我的函数， 然后你发现你的原始数组被我排序了...
	 * 你会不会咆哮呢？ 因为你只是想看你要的结果，并不想把你的数组排序啊。
	 */
	public static int[] cloneArray(int[] arr){
		int[] result=new int[arr.length];
		for(int i=0;i!=arr.length;i++){
			result[i]=arr[i];
		}
		return result;
	}

	/*
	 * 把sumList中的状态，还原成真正解的状态。 比如sumList目前的状态是2->4->7->11 解的状态就是：2 2 3 4
	 * 注意sumList中的每一个元素的含义是，之前所选元素的累加值
	 */
	public static void updateResult(LinkedList<Integer> sumList,
			HashSet<String> result){
		int previousSum=0;
		String currentResult="";
		for(Integer currentSum:sumList){
			currentResult+=String.valueOf(currentSum-previousSum)+" ";
			previousSum=currentSum;
		}
		result.add(currentResult);// result加入一个新的记录，因为是HashSet，所以自然会去除重复。
	}

	public static void printResult(HashSet<String> result){
		for(String record:result){
			System.out.println(record);
		}
	}

	public static void main(String[] args){
		int[] test={5, 2, 6, 3, 4, 2, -1};
		int given=10;
		printCombinationForSum(test,given);
	}
}
