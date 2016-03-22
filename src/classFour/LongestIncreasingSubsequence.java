package classFour;

public class LongestIncreasingSubsequence{
	public static int[] getLISubSequence(int[] arr){
		if(arr==null||arr.length==0)
			return null;
		// 以下为步骤一
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
			// 检查更新maxLength和theIndex
			if(increaseNumRecord[i]>maxLength){
				maxLength=increaseNumRecord[i];
				theIndex=i;
			}
		}
		// generateLISubSequence方法为步骤二
		return generateLISubSequence(increaseNumRecord,arr,maxLength,theIndex);
	}

	public static int[] getLISubSequenceBetter(int[] arr){
		if(arr==null||arr.length==0)
			return null;
		// 以下为步骤一
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
			while(left<=right){ // 在smallestEnd中找到第一个大于arr[i]的值
				int mid=left+(right-left)/2;
				if(smallestEnd[mid]<arr[i]){
					left=mid+1;
				}else{
					right=mid-1;
				}
			}
			smallestEnd[left]=arr[i]; // 在smallestEnd中插入当前arr[i]的值
			if(left>currentLength){ // 说明当前arr[i]的值比smallestEnd中所有值都大，更新递增序列的个数
				currentLength=left;
			}
			// left表示当前的arr[i]的插入位置，同样也表示之前的最大LIS长度
			increaseNumRecord[i]=left+1;
			// 检查更新maxLength和theIndex
			if(increaseNumRecord[i]>maxLength){
				maxLength=increaseNumRecord[i];
				theIndex=i;
			}
		}
		// generateLISubSequence方法为步骤二
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