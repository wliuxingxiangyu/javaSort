package classSeven;

public class FindMaxSumSubArray{

	public static int[] getMaxSumLongestSubArray(int[] arr){
		int maxSum=-1;
		int curSum=0;
		int start=0;//以前头
		int end=0;
		int startCur=0;//当前头
		int endCur=0;
		for(int i=0;i!=arr.length;i++){
			curSum+=arr[i];
			endCur=i;
			if(curSum<0){
				curSum=0;
				startCur=i+1;
			}else{
				if(curSum>maxSum){
					maxSum=curSum;
					start=startCur;
					end=endCur;
				}
			}
		}
		if(maxSum>=0){
			int[] res=new int[end-start+1];
			for(int i=0;i!=res.length;i++){
				res[i]=arr[start++];
			}
			return res;
		}else{
			int[] res=new int[1];
			res[0]=arr[0];
			for(int i=1;i!=arr.length;i++){
				res[0]=Math.max(res[0],arr[i]);
			}
			return res;
		}
	}

	public static void printArray(int[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		int[] arr1={-2, -3, -5, 40, -10, -10, 100, 1};
		printArray(getMaxSumLongestSubArray(arr1));

		int[] arr2={-2, -3, -5, 0};
		printArray(getMaxSumLongestSubArray(arr2));

		int[] arr3={-2, -3, -5, -1};
		printArray(getMaxSumLongestSubArray(arr3));

	}

}
