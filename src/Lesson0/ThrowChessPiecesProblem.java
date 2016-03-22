package Lesson0;

public class ThrowChessPiecesProblem{
	public static int solution1(int nLevel,int kChess){
		if(nLevel<1||kChess<1){
			return 0;
		}
		return Process1(nLevel,kChess);
	}

	public static int Process1(int nLevel,int kChess){
		if(nLevel==0){
			return 0;
		}
		if(kChess==1){
			return nLevel;
		}
		int min=Integer.MAX_VALUE;
		for(int i=1;i!=nLevel+1;i++){
			min=Math.min(
					min,
					Math.max(Process1(i-1,kChess-1),Process1(nLevel-i,kChess))+1);
		}
		return min;
	}

	public static int solution2(int nLevel,int kChess){
		int[][] dp=new int[nLevel+1][kChess+1];
		for(int i=1;i!=dp.length;i++){
			dp[i][1]=i;
		}
		for(int i=1;i!=dp.length;i++){
			for(int j=2;j!=dp[0].length;j++){
				int min=Integer.MAX_VALUE;
				for(int k=1;k!=i+1;k++){
					min=Math.min(min,Math.max(dp[k-1][j-1],dp[i-k][j])+1);
				}
				dp[i][j]=min;
			}
		}
		return dp[nLevel][kChess];
	}

	public static int solution3(int nLevel,int kChess){
		int[] preArr=new int[nLevel+1];
		int[] curArr=new int[nLevel+1];
		for(int i=1;i!=curArr.length;i++){
			curArr[i]=i;
		}
		for(int i=1;i!=kChess;i++){
			int[] tmp=preArr;
			preArr=curArr;
			curArr=tmp;
			for(int j=1;j!=curArr.length;j++){
				int min=Integer.MAX_VALUE;
				for(int k=1;k!=j+1;k++){
					min=Math.min(min,Math.max(preArr[k-1],curArr[j-k])+1);
				}
				curArr[j]=min;
			}
		}
		return curArr[curArr.length-1];
	}

	public static void main(String[] args){
		System.out.println(solution1(21,2));
		System.out.println(solution2(21,2));
		System.out.println(solution3(21,2));
		System.out.println(solution2(105,2));
		System.out.println(solution3(105,2));
		System.out.println(solution2(3000,10));
		System.out.println(solution3(3000,10));

	}

}
