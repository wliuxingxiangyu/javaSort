package classFour;

public class CoinsProblem1{//能用无限张P14

	public static int getMinCoinsNumAndPrintSolution(int[] coins,int aim){//P14
		int[][] dp=getMinCoinsNumDPMap(coins,aim);
		printSolution(coins,dp);
		return dp[coins.length][aim];
	}

	public static int[][] getMinCoinsNumDPMap(int[] coins,int aim){
		int[][] dp=new int[coins.length+1][aim+1];
		for(int i=0;i!=dp.length;i++){
			for(int j=0;j!=dp[0].length;j++){
				dp[i][j]=Integer.MAX_VALUE;//找不开
			}
		}
		for(int i=0;i!=dp.length;i++){
			dp[i][0]=0;//找的钱数为0，时张数为0
		}
		for(int i=1;i!=dp.length;i++){//i和j都从（1,1）开始//i++不断往下走
			int curCoin=coins[i-1];
			for(int j=1;j!=dp[0].length;j++){
				int up=dp[i-1][j];
				int left=Integer.MAX_VALUE;
				if(j-curCoin>-1&&dp[i][j-curCoin]!=Integer.MAX_VALUE){//有效&&找的开
					left=dp[i][j-curCoin]+1;
				}
				dp[i][j]=Math.min(up,left);
			}
		}
		return dp;
	}

	public static void printSolution(int[] coins,int[][] dp){
		int ri=dp.length-1;
		int ci=dp[0].length-1;
		if(dp[ri][ci]==Integer.MAX_VALUE){
			System.out.println("No solution!");
		}else{
			System.out.print("Solution: ");
			while(ci!=0){
				if(dp[ri][ci]==dp[ri-1][ci]){//不需要ri种货币
					ri--;
				}else{
					System.out.print(coins[ri-1]+" ");//需要ri种货币
					ci-=coins[ri-1];
				}
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
			for(int j=1;j!=dp.length;j++){
				int up=dp[j];
				int left=Integer.MAX_VALUE;
				if(j-curCoin>=0&&dp[j-curCoin]!=Integer.MAX_VALUE){
					left=dp[j-curCoin]+1;
				}
				dp[j]=Math.min(up,left);
			}
		}
		return dp[aim];
	}

	public static void main(String[] args){
		int[] coins={2, 5, 10, 20, 50, 100};
		int aim=380;
		System.out.println(getMinCoinsNumAndPrintSolution(coins,aim));
		System.out.println(getMinCoinsNumBetter(coins,aim));

	}
}
