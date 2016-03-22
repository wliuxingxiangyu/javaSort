package classFour;

public class KnapsackProblem{//P4背包,参数意义
	public static int getMaxValuePrintSolution(int[] wArr,int[] vArr,int allW){
		int[][] dp=new int[wArr.length+1][allW+1];
		for(int i=0;i!=wArr.length;i++){
			for(int j=0;j!=allW+1;j++){
				if(j<wArr[i]){
					dp[i+1][j]=dp[i][j];
				}else{
					dp[i+1][j]=Math.max(dp[i][j],dp[i][j-wArr[i]]+vArr[i]);
				}
			}
		}
		printSolution(wArr,vArr,dp);
		return dp[wArr.length][allW];
	}

	public static void printSolution(int[] wArr,int[] vArr,int[][] dp){
		int ri=dp.length-1;
		int ci=dp[0].length-1;
		System.out.print("select: ");
		while(ri!=0){
			if(dp[ri][ci]!=dp[ri-1][ci]){
				System.out.print("(w:"+wArr[ri-1]+", "+"v:"+vArr[ri-1]+") ");
				ci=ci-wArr[ri-1];
			}
			ri--;
		}
		System.out.println();
	}

	public static int getMaxValue(int[] wArr,int[] vArr,int allW){
		int[] previousMap=new int[allW+1];
		int[] currentMap=new int[allW+1];
		for(int i=0;i!=wArr.length;i++){
			for(int j=0;j!=allW+1;j++){
				if(j<wArr[i]){
					currentMap[j]=previousMap[j];
				}else{
					currentMap[j]=Math.max(previousMap[j],
							previousMap[j-wArr[i]]+vArr[i]);
				}
			}
			int[] tmp=currentMap;
			currentMap=previousMap;
			previousMap=tmp;
		}
		return previousMap[allW];
	}

	public static int getMaxValueBest(int[] wArr,int[] vArr,int allW){
		int[] map=new int[allW+1];//第0列全为0，不打印方案
		for(int i=0;i!=wArr.length;i++){
			for(int j=allW;j!=-1;j--){
				if(j>=wArr[i]){
					map[j]=Math.max(map[j], map[j-wArr[i]] +vArr[i]  );
				}
			}
		}
		return map[allW];
	}

	public static void main(String[] args){
		int[] wArr={3,6,4,2,8,5};
		int[] vArr={4,5,7,6,8,9};
		int allW=28;
		System.out.println(getMaxValuePrintSolution(wArr,vArr,allW));
		System.out.println(getMaxValue(wArr,vArr,allW));
		System.out.println(getMaxValueBest(wArr,vArr,allW));

	}
}