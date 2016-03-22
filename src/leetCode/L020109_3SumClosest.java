package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L020109_3SumClosest {
	public static int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3)
			return 0;
		Arrays.sort(nums);
		int minSum=nums[0]+nums[1]+nums[2];
		int len=nums.length;
		int maxSum=nums[len-3]+nums[len-2]+nums[len-1];
		int res = 0;
		if (target<=minSum) {
			res=minSum;
		} else if (target>=maxSum) {
			res=maxSum;
		}else{//minSum<target<maxSum
			int minGap=Integer.MAX_VALUE,sum,curGap;
			for (int a = 0; a <= nums.length-3; a++) {
				int b=a+1,c=nums.length-1;//a,b,c均为元素下标
				while (b<c) {
					sum=nums[a]+nums[b]+nums[c];
					if (sum==target) {
						return sum;
					} 
					curGap=Math.abs(sum-target);
					if(curGap <minGap){
						minGap=curGap;//还需？
						res=sum;
					}
					
					if(sum<target){
						b++;
					}else if(sum>target){
						c--;
					}
				}
			}
		}
		return res;
	}


	public static void main(String[] args) {
		int[] arr = { -1, 2, 1, -4 };
		int target = 1;
		System.out.println("找到如下" + threeSumClosest(arr, target));

	}
}
