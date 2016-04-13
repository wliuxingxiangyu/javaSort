package classSeven;

public class FindMaxSumSubArray{ //累加和,代码curSum<0有问题。

	public static int[] getMaxSumLongestSubArray(int[] arr){
		int maxSum=Integer.MIN_VALUE,curSum=0;
		int start=0,end=0;//以前头，尾;
		int startCur=0,endCur;//当前头，尾;
		for(int i=0;i!=arr.length;i++){
			curSum+=arr[i];
			endCur=i;
			if(curSum<0){//当前和为负数,字数组左边肯定不包含"和为负数的左边元素"
				curSum=0;
				startCur=i+1;
			}else{//当前和为正数(有可能加的元素是负数arr[i]=-1,但-1包含"和为正数的左边元素")
				if(curSum>maxSum){//全是负数,不进该else.下标没法更新。
					maxSum=curSum;
					start=startCur;
					end=endCur;
				}
			}
		}  
		if(maxSum>=0){
			int[] resArr=new int[end-start+1];//终-起+1.
			for(int i=0;i!=resArr.length;i++){
				resArr[i]=arr[start++];
			}
			return resArr;// 返回数组
		}else{//  maxSum<0时与end,start无关。
			int[] resArr=new int[1];
			resArr[0]=arr[0];
			for(int i=1;i!=arr.length;i++){
				resArr[0]=Math.max(resArr[0],arr[i]);
			}
			return resArr;// 返回数组
		}
	}

	public static void printArray(int[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		int[] arr1={-3, 1, 3, -3, 4};
		printArray(getMaxSumLongestSubArray(arr1));

		int[] arr2={-2, -3, -5, 0};
		printArray(getMaxSumLongestSubArray(arr2));

		int[] arr3={-2, -3, -5, -1};
		printArray(getMaxSumLongestSubArray(arr3));
		
		int[] arr4={-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
		printArray(getMaxSumLongestSubArray(arr4));
		
		
	}

}
