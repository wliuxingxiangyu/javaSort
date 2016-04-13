package leetCode;

import java.util.ArrayList;

public class L1302FindMaxSumSubArray {
	public static ArrayList<Integer> getMaxSumLongestSubArray(int[] arr){
		int maxSum=Integer.MIN_VALUE,curSum=0;
		int startRes=0,endRes=0;//以前头，尾;
		int startCur=0,endCur;//当前头，尾;
		for(int i=0;i!=arr.length;i++){
			curSum+=arr[i];
			endCur=i;
			if(curSum<0){//当前和为负数,字数组左边肯定不包含"和为负数的左边元素"
				curSum=0;
				startCur=i+1;
			}
			//当前和为正数(有可能加的元素是负数arr[i]=-1,但-1包含"和为正数的左边元素")
			if(curSum>maxSum){//全是负数时,
				maxSum=curSum;
				startRes=startCur;
				endRes=endCur;
			}
		}  
		
		ArrayList<Integer> res=new ArrayList<Integer>();
		if(maxSum>=0){
			res.add(startRes);
			res.add(endRes);
		}else{//  maxSum<0时与end,start无关。
			res.add(startRes);
		}
		return res;
	}
	
	public static void main(String[] args){
//		int[] arr1={-3, 1, 3, -3, 4};
//		getMaxSumLongestSubArray(arr1);
//
//		int[] arr2={-2, -3, -5, 0};
//		getMaxSumLongestSubArray(arr2);
//
//		int[] arr3={-2, -3, -5, -1};
//		getMaxSumLongestSubArray(arr3);
		
		int[] arr4={-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
		getMaxSumLongestSubArray(arr4);
	}
}
