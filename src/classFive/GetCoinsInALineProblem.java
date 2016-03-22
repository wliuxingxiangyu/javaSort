package classFive;

public class GetCoinsInALineProblem{

	public static void printWinnerAndValue(int[] arr){
		int player1Value=getOptimalValue(arr,0,arr.length-1);
		int player2Value=getArraySum(arr)-player1Value;
		if(player1Value>player2Value){
			System.out.println("Winner : player1, Value: "+player1Value);
		}else{
			System.out.println("Winner : player2, Value: "+player2Value);
		}
	}

	public static int getOptimalValue(int[] arr,int start,int end){
		if(start==end){
			return arr[start];
		}
		return Math.max(arr[start]+getMinRestValue(arr,start+1,end),arr[end]
				+getMinRestValue(arr,start,end-1));
	}

	public static int getMinRestValue(int[] arr,int start,int end){
		if(start==end){
			return 0;
		}
		return Math.min(getOptimalValue(arr,start+1,end),
				getOptimalValue(arr,start,end-1));
	}

	public static void printWinnerAndValueBetter(int[] arr){
		int[][] map=new int[arr.length][arr.length];
		int player1Value=getOptimalValueBetter(arr,0,arr.length-1,map);
		int player2Value=getArraySum(arr)-player1Value;
		if(player1Value>player2Value){
			System.out.println("Winner : player1, Value: "+player1Value);
		}else{
			System.out.println("Winner : player2, Value: "+player2Value);
		}
	}

	public static int getOptimalValueBetter(int[] arr,int start,int end,
			int[][] map){
		if(start==end){
			return arr[start];
		}
		if(map[start][end]!=0){
			return map[start][end];
		}
		int result=Math.max(
				arr[start]+getMinRestValueBetter(arr,start+1,end,map),arr[end]
						+getMinRestValueBetter(arr,start,end-1,map));
		map[start][end]=result;
		return result;
	}

	public static int getMinRestValueBetter(int[] arr,int start,int end,
			int[][] map){
		if(start==end){
			return 0;
		}
		return Math.min(getOptimalValueBetter(arr,start+1,end,map),
				getOptimalValueBetter(arr,start,end-1,map));
	}

	public static int getArraySum(int[] arr){
		int result=0;
		for(int i=0;i!=arr.length;i++){
			result+=arr[i];
		}
		return result;
	}

	public static int[] generateRandomArray(int length){
		int[] arr=new int[length];
		for(int i=0;i!=arr.length;i++){
			arr[i]=(int)(Math.random()*10)+1;
		}
		return arr;
	}

	public static void printArray(int[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		int[] coinsArr=generateRandomArray(4);
		printArray(coinsArr);
		printWinnerAndValue(coinsArr);
		printWinnerAndValueBetter(coinsArr);

	}

}
