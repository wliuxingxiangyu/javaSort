package classFour;

public class CoinsProblem2{//P15只能用一张

	public static int getMinCoinsNumAndPrintSolution(int[] coins,int aim){
		int[][] dp=getMinCoinsNumDPMap(coins,aim);
		printSolution(coins,dp);
		return dp[coins.length][aim];
	}

	public static int[][] getMinCoinsNumDPMap(int[] coins,int aim){
		int[][] dp=new int[coins.length+1][aim+1];
		for(int i=0;i!=dp.length;i++){//找不开
			for(int j=0;j!=dp[0].length;j++){
				dp[i][j]=Integer.MAX_VALUE;
			}
		}
		for(int i=0;i!=dp.length;i++){
			dp[i][0]=0;
		}
		for(int i=1;i!=dp.length;i++){
			int curCoin=coins[i-1];
			for(int j=1;j!=dp[0].length;j++){
				int up=dp[i-1][j];
				int leftUp=Integer.MAX_VALUE;
				if(j-curCoin>-1&&dp[i-1][j-curCoin]!=Integer.MAX_VALUE){
					leftUp=dp[i-1][j-curCoin]+1;
				}
				dp[i][j]=Math.min(up,leftUp);
			}
		}
		return dp;
	}

	public static void printSolution(int[] coins,int[][] dpMap){
		int ri=dpMap.length-1;
		int ci=dpMap[0].length-1;
		if(dpMap[ri][ci]==Integer.MAX_VALUE){
			System.out.println("No solution!");
		}else{
			System.out.print("Solution: ");
			while(ci!=0){
				if(dpMap[ri][ci]!=dpMap[ri-1][ci]){//P15限制一张
					System.out.print(coins[ri-1]+" ");
					ci-=coins[ri-1];
				}
				ri--;
			}
			System.out.println();
		}
	}

	public static int getMinCoinsNumBetter(int[] coins,int aim){
		int[] dp=new int[aim+1];
		for(int i=1;i!=dp.length;i++){
			dp[i]=Integer.MAX_VALUE;
		}
		for(int i=0;i!=coins.length;i++){
			int curCoin=coins[i];
			for(int j=dp.length-1;j!=-1;j--){
				int up=dp[j];
				int leftUp=Integer.MAX_VALUE;
				if(j-curCoin>-1&&dp[j-curCoin]!=Integer.MAX_VALUE){
					leftUp=dp[j-curCoin]+1;
				}
				dp[j]=Math.min(up,leftUp);
			}
		}
		return dp[dp.length-1];
	}

	public static void main(String[] args){
		int[] coins={1, 1, 1, 2, 2, 5, 5, 5, 10, 10, 100, 100};
		int aim=230;
		System.out.println(getMinCoinsNumAndPrintSolution(coins,aim));
		System.out.println(getMinCoinsNumBetter(coins,aim));

	}
}
