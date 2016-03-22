package classFour;

public class LongestIncreasingSubsequence{
	public static int[] getLISubSequence(int[] arr){
		if(arr==null||arr.length==0)
			return null;
		// ����Ϊ����һ
		int[] increaseNumRecord=new int[arr.length];
		increaseNumRecord[0]=1;
		int maxLength=-1;
		int theIndex=-1;
		for(int i=1;i!=arr.length;i++){
			int increaseNum=1;
			for(int j=i-1;j!=-1;j--){
				if(arr[j]<arr[i]){
					increaseNum=Math.max(increaseNum,increaseNumRecord[j]+1);
				}
			}
			increaseNumRecord[i]=increaseNum;
			// ������maxLength��theIndex
			if(increaseNumRecord[i]>maxLength){
				maxLength=increaseNumRecord[i];
				theIndex=i;
			}
		}
		// generateLISubSequence����Ϊ�����
		return generateLISubSequence(increaseNumRecord,arr,maxLength,theIndex);
	}

	public static int[] getLISubSequenceBetter(int[] arr){
		if(arr==null||arr.length==0)
			return null;
		// ����Ϊ����һ
		int[] smallestEnd=new int[arr.length];
		smallestEnd[0]=arr[0];
		int currentLength=0;
		int[] increaseNumRecord=new int[arr.length];
		increaseNumRecord[0]=1;
		int maxLength=-1;
		int theIndex=-1;
		for(int i=1;i<arr.length;i++){
			int left=0;
			int right=currentLength;
			while(left<=right){ // ��smallestEnd���ҵ���һ������arr[i]��ֵ
				int mid=left+(right-left)/2;
				if(smallestEnd[mid]<arr[i]){
					left=mid+1;
				}else{
					right=mid-1;
				}
			}
			smallestEnd[left]=arr[i]; // ��smallestEnd�в��뵱ǰarr[i]��ֵ
			if(left>currentLength){ // ˵����ǰarr[i]��ֵ��smallestEnd������ֵ���󣬸��µ������еĸ���
				currentLength=left;
			}
			// left��ʾ��ǰ��arr[i]�Ĳ���λ�ã�ͬ��Ҳ��ʾ֮ǰ�����LIS����
			increaseNumRecord[i]=left+1;
			// ������maxLength��theIndex
			if(increaseNumRecord[i]>maxLength){
				maxLength=increaseNumRecord[i];
				theIndex=i;
			}
		}
		// generateLISubSequence����Ϊ�����
		return generateLISubSequence(increaseNumRecord,arr,maxLength,theIndex);
	}

	public static int[] generateLISubSequence(int[] increaseNumRecord,
			int[] arr,int maxLength,int theIndex){
		int[] resultArr=new int[maxLength];
		int resultArrIndex=maxLength-1;
		resultArr[resultArrIndex--]=arr[theIndex];
		int previousIndex=theIndex;
		for(int i=theIndex-1;i!=-1;i--){
			if(arr[i]<arr[previousIndex]
					&&increaseNumRecord[i]==increaseNumRecord[previousIndex]-1){
				resultArr[resultArrIndex--]=arr[i];
				previousIndex=i;
				if(resultArrIndex==-1){
					break;
				}
			}
		}
		return resultArr;
	}

	public static void printArray(int[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		int[] arr={2,1,5,3,6,4,8,9,7};
		System.out.println("====DP Solution O(N^2) ====");
		printArray(arr);
		printArray(getLISubSequence(arr));

		System.out.println("====Better Solution O(N*logN) ====");
		printArray(arr);
		printArray(getLISubSequenceBetter(arr));

	}
}