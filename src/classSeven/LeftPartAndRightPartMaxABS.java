package classSeven;

public class LeftPartAndRightPartMaxABS{

	public static int getMaxABSLeftAndRight(int[] arr){
		int res=Integer.MIN_VALUE;
		for(int i=0;i!=arr.length-1;i++){
			int maxLeft=Integer.MIN_VALUE;
			for(int j=0;j!=i+1;j++){
				maxLeft=Math.max(arr[j],maxLeft);
			}
			int maxRight=Integer.MIN_VALUE;
			for(int j=i+1;j!=arr.length;j++){
				maxRight=Math.max(arr[j],maxRight);
			}
			res=Math.max(Math.abs(maxLeft-maxRight),res);
		}
		return res;
	}

	public static int getMaxABSLeftAndRightBetter(int[] arr){
		// leftMaxArr[i]代表arr[0..i]中的最大值
		int[] leftMaxArr=new int[arr.length];
		leftMaxArr[0]=arr[0];
		// rightMaxArr[i]代表arr[i..arr.length-1]的最大值
		int[] rightMaxArr=new int[arr.length];
		rightMaxArr[arr.length-1]=arr[arr.length-1];
		for(int i=1;i!=arr.length;i++){
			leftMaxArr[i]=Math.max(leftMaxArr[i-1],arr[i]);
		}
		for(int i=arr.length-2;i!=-1;i--){
			rightMaxArr[i]=Math.max(rightMaxArr[i+1],arr[i]);
		}
		int max=0;
		for(int i=0;i!=arr.length-1;i++){
			max=Math.max(max,Math.abs(leftMaxArr[i]-rightMaxArr[i+1]));
		}
		return max;
	}

	public static int getMaxABSLeftAndRightBest(int[] arr){
		int max=Integer.MIN_VALUE;
		for(int i=0;i!=arr.length;i++){
			max=Math.max(arr[i],max);
		}
		return max-Math.min(arr[0],arr[arr.length-1]);
	}

	public static int[] generateRandomArray(int length){
		int[] arr=new int[length];
		for(int i=0;i!=arr.length;i++){
			arr[i]=(int)(Math.random()*1000)-499;
		}
		return arr;
	}

	public static void main(String[] args){
		int[] arr=generateRandomArray(200);
		System.out.println(getMaxABSLeftAndRight(arr));
		System.out.println(getMaxABSLeftAndRightBetter(arr));
		System.out.println(getMaxABSLeftAndRightBest(arr));
	}
}
