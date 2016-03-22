package classFour;

public class CoinsProblem3{//P15能用无限张，但求总解决方法  数

	public static int solution1(int n){
		if(n<1){
			return 0;
		}
		return process1(n,25);
	}

	public static int process1(int n,int denom){
		int nextDenom=0;
		switch(denom){
			case 25://这次用了25,下次用10的
				nextDenom=10;
				break;
			case 10:
				nextDenom=5;
				break;
			case 5:
				nextDenom=1;
				break;
			case 1:
				return 1;
		}
		int res=0;
		for(int i=0;i*denom<=n;i++){
			res+=process1(n-i*denom,nextDenom);
		}
		return res;
	}

	public static int solution2(int[] coins,int aim){
		int[][] map=new int[aim+1][coins.length];
		int res=process2(coins,aim,coins.length-1,map);
		return res;
	}

	public static int process2(int[] coins,int aim,int curIndex,int[][] map){
		if(aim==0){//当前    剩余货币
			if(curIndex>=0){
				map[aim][curIndex]=1;
			}
			return 1;
		}
		if(curIndex<0){
			return 0;
		}
		int res=0;
		for(int i=0;coins[curIndex]*i<=aim;i++){
			int curCoins=coins[curIndex]*i;
			if(curIndex-1>=0&&map[aim-curCoins][curIndex-1]!=0){//计算过
				res+=map[aim-curCoins][curIndex-1]==-1
						?0
						:map[aim-curCoins][curIndex-1];
			}else{//没计算过
				res+=process2(coins,aim-curCoins,curIndex-1,map);
			}
		}
		map[aim][curIndex]=res==0?-1:res;
		return res;
	}

	public static void main(String[] args){
		int aim=20000;

		long start=System.currentTimeMillis();
		int res=solution1(aim);
		long end=System.currentTimeMillis();
		System.out.println("Solution num : "+res);
		System.out.println("cost time : "+(end-start)+"(ms)");

		int[] coins={1, 5, 10, 25};
		start=System.currentTimeMillis();
		res=solution2(coins,aim);
		end=System.currentTimeMillis();
		System.out.println("Solution num : "+res);
		System.out.println("cost time : "+(end-start)+"(ms)");

	}
}
