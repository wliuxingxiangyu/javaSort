package classSeven;

public class FindMaxSumSubArray{ //�ۼӺ�,����curSum<0�����⡣

	public static int[] getMaxSumLongestSubArray(int[] arr){
		int maxSum=Integer.MIN_VALUE,curSum=0;
		int start=0,end=0;//��ǰͷ��β;
		int startCur=0,endCur;//��ǰͷ��β;
		for(int i=0;i!=arr.length;i++){
			curSum+=arr[i];
			endCur=i;
			if(curSum<0){//��ǰ��Ϊ����,��������߿϶�������"��Ϊ���������Ԫ��"
				curSum=0;
				startCur=i+1;
			}else{//��ǰ��Ϊ����(�п��ܼӵ�Ԫ���Ǹ���arr[i]=-1,��-1����"��Ϊ���������Ԫ��")
				if(curSum>maxSum){//ȫ�Ǹ���,������else.�±�û�����¡�
					maxSum=curSum;
					start=startCur;
					end=endCur;
				}
			}
		}  
		if(maxSum>=0){
			int[] resArr=new int[end-start+1];//��-��+1.
			for(int i=0;i!=resArr.length;i++){
				resArr[i]=arr[start++];
			}
			return resArr;// ��������
		}else{//  maxSum<0ʱ��end,start�޹ء�
			int[] resArr=new int[1];
			resArr[0]=arr[0];
			for(int i=1;i!=arr.length;i++){
				resArr[0]=Math.max(resArr[0],arr[i]);
			}
			return resArr;// ��������
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
