package classThree;

public class GenerateAllStringPalindromeByLPSubsequence{//L3P4╩ьнд

	public static String generateASPByLPS(String str,String strLPS){
		if(str==null||str.equals("")){
			return "";
		}
		char[] charArr=str.toCharArray();
		char[] charLPSArr=strLPS.toCharArray();
		char[] result=new char[2*charArr.length-charLPSArr.length];
		int lpsLeft=0;
		int lpsRight=charLPSArr.length-1;
		int charLeft=0;
		int charRight=charArr.length-1;
		int resultLeft=0;
		int resultRight=result.length-1;
		while(lpsLeft<=lpsRight){
			int tmpCharLeft=charLeft;
			int tmpCharRight=charRight;
			while(charArr[charLeft]!=charLPSArr[lpsLeft]){
				charLeft++;
			}
			while(charArr[charRight]!=charLPSArr[lpsRight]){
				charRight--;
			}
			setTwoUnPalindromePartToResult(result,resultLeft,resultRight,
					charArr,tmpCharLeft,charLeft,charRight,tmpCharRight);
			resultLeft+=charLeft-tmpCharLeft+tmpCharRight-charRight;
			resultRight-=charLeft-tmpCharLeft+tmpCharRight-charRight;
			result[resultLeft++]=charArr[charLeft++];
			result[resultRight--]=charArr[charRight--];
			lpsLeft++;
			lpsRight--;
		}
		return String.valueOf(result);
	}

	public static void setTwoUnPalindromePartToResult(char[] result,
			int resultLeft,int resultRight,char[] charArr,int leftPartStart,
			int leftPartEnd,int rightPartStart,int rightPartEnd){
		for(int i=leftPartStart;i!=leftPartEnd;i++){
			result[resultLeft++]=charArr[i];
			result[resultRight--]=charArr[i];
		}
		for(int i=rightPartEnd;i!=rightPartStart;i--){
			result[resultLeft++]=charArr[i];
			result[resultRight--]=charArr[i];
		}
	}

	public static void main(String[] args){
		String str="B1G2TY34I3OPX2S1";
		String strLPS="123I321";
		String result=generateASPByLPS(str,strLPS);
		System.out.println(str+" shortest all palindrome string: "+result);
	}

}
