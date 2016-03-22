package classThree;

public class ParenthesesStringValidAndLongestValidSubString{
//L3P5有效括号组合,和最长子串(一定连续)长度

	public static boolean isValidParenthesesString(String str){
		if(str==null||str.equals("")){
			return false;
		}
		char[] charArr=str.toCharArray();
		if(!isAllParenthesesChar(charArr)){
			return false;
		}
		return isValid(charArr,0,charArr.length-1);
	}

	public static boolean isAllParenthesesChar(char[] charArr){
		for(int i=0;i!=charArr.length;i++){
			if(charArr[i]!=')'&&charArr[i]!='('){
				return false;
			}
		}
		return true;
	}

	public static boolean isValid(char[] charArr,int start,int end){
		int status=0;
		for(int i=start;i!=end+1;i++){
			if(charArr[i]=='('){
				status++;
			}else{
				status--;
				if(status<0){
					return false;
				}
			}
		}
		return status==0;
	}

	public static int absoluteRightButBrute(String str){//正确但,效率差
		if(str==null||str.equals("")){
			return 0;
		}
		char[] charArr=str.toCharArray();
		if(!isAllParenthesesChar(charArr)){
			return 0;
		}
		int max=0;
		for(int i=0;i!=charArr.length;i++){
			for(int j=charArr.length-1;j!=i-1;j--){
				if(isValid(charArr,i,j)){
					if(j-i+1>max){
						max=j-i+1;
					}
					break;
				}
			}
		}
		return max;
	}

	public static int getLongestValidParenLength(String str){
		if(str==null||str.equals("")){
			return 0;
		}
		char[] charArr=str.toCharArray();
		if(!isAllParenthesesChar(charArr)){
			return 0;
		}
		int[] dp=new int[charArr.length];
		for(int i=1;i!=charArr.length;i++){
			if(charArr[i]==')'){
				int j=i-dp[i-1]-1;
				if(j>-1&&charArr[j]=='('){//>-1数组防越界
					dp[i]=dp[i-1]+2;//左右括号，一对
					if(j-1>-1){//>-1数组防越界
						dp[i]+=dp[j-1];
					}
				}
			}
		}
		int max=0;
		for(int i=1;i!=dp.length;i++){
			max=Math.max(max,dp[i]);
		}
		return max;
	}

	public static String generateRandomString(int size){
		char[] result=new char[size];
		for(int i=0;i!=result.length;i++){
			result[i]=(int)(Math.random()*2)==0?'(':')';
		}
		return String.valueOf(result);
	}

	public static void main(String[] args){
		String str1="((())())";
		System.out.println(isValidParenthesesString(str1));

		String str2="(())(()(()))";
		System.out.println(getLongestValidParenLength(str2));
		System.out.println(absoluteRightButBrute(str2));

		// 随机测试10000组长度为100的字符串
		for(int i=0;i!=10000;i++){
			String testStr=generateRandomString(100);
			if(absoluteRightButBrute(testStr)!=getLongestValidParenLength(testStr)){
				System.out.println("Err: "+testStr);
			}
		}

	}
}
