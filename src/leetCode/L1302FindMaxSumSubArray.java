package leetCode;

import java.util.ArrayList;

public class L1302FindMaxSumSubArray {
	public static ArrayList<Integer> getMaxSumLongestSubArray(int[] arr){
		int maxSum=Integer.MIN_VALUE,curSum=0;
		int startRes=0,endRes=0;//��ǰͷ��β;
		int startCur=0,endCur;//��ǰͷ��β;
		for(int i=0;i!=arr.length;i++){
			curSum+=arr[i];
			endCur=i;
			if(curSum<0){//��ǰ��Ϊ����,��������߿϶�������"��Ϊ���������Ԫ��"
				curSum=0;
				startCur=i+1;
			}
			//��ǰ��Ϊ����(�п��ܼӵ�Ԫ���Ǹ���arr[i]=-1,��-1����"��Ϊ���������Ԫ��")
			if(curSum>maxSum){//ȫ�Ǹ���ʱ,
				maxSum=curSum;
				startRes=startCur;
				endRes=endCur;
			}
		}  
		
		ArrayList<Integer> res=new ArrayList<Integer>();
		if(maxSum>=0){
			res.add(startRes);
			res.add(endRes);
		}else{//  maxSum<0ʱ��end,start�޹ء�
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
