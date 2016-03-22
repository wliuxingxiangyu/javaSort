package classFive;

import java.util.HashSet;

public class FindStringMaxComb{

	public static String[] getMaxCombNum(String aim,String[] words){
		if(aim==null||aim.equals("")){
			return new String[0];
		}
		HashSet<String> wordSet=new HashSet<String>();
		for(String str:words){
			wordSet.add(str);
		}
		return generateResByDPRecord(aim,wordSet,getDPRecord(aim,wordSet));
	}

	public static int[] getDPRecord(String aim,HashSet<String> wordSet){
		int[] dp=new int[aim.length()];
		for(int i=0;i!=dp.length;i++){
			int max=0;
			for(int j=0;j!=i;j++){
				if(dp[j]!=0&&wordSet.contains(aim.substring(j+1,i+1))){
					max=Math.max(dp[j]+1,max);
				}
			}
			if(max==0&&wordSet.contains(aim.substring(0,i+1))){
				max=1;
			}
			dp[i]=max;
		}
		return dp;
	}

	public static String[] generateResByDPRecord(String aim,
			HashSet<String> wordSet,int[] dp){
		int combNum=dp[dp.length-1];
		String[] res=new String[combNum];
		if(combNum==0){
			return res;
		}
		int curEnd=dp.length-2;
		int preEnd=dp.length-1;
		while(dp[preEnd]!=1){
			String currentStr=aim.substring(curEnd+1,preEnd+1);
			if(dp[curEnd]==dp[preEnd]-1&&wordSet.contains(currentStr)){
				res[--combNum]=currentStr;
				preEnd=curEnd;
			}
			curEnd--;
		}
		res[0]=aim.substring(0,preEnd+1);
		return res;
	}

	public static void main(String[] args){
		String[] words=new String[]{"1", "11", "2", "3", "12", "123"};
		String aim="1123";
		String[] result=getMaxCombNum(aim,words);
		for(String str:result){
			System.out.println(str);
		}

	}

}
