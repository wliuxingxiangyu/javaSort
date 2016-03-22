package classThree;

public class StringXReplaceWordProblem{//P10

	public static String xReplaceWord(String str,String word){
		if(str==null||word==null){
			return null;
		}
		char[] charArrStr=str.toCharArray();
		char[] charArrWord=word.toCharArray();
		int matchIndex=0;//和子串同步加加，
		int apearIndex=-1;//第一次出现子串的位置
		for(int i=0;i!=charArrStr.length;i++){
			if(charArrStr[i]==charArrWord[matchIndex++]){
				if(matchIndex==charArrWord.length){
					matchIndex=0;
					setZeroToCharArr(charArrStr,i,charArrWord.length);
					if(apearIndex==-1){
						apearIndex=i-charArrWord.length+1;
						charArrStr[apearIndex]='X';
					}
				}
			}else{//匹配不上
				apearIndex=-1;
				matchIndex=0;
			}
		}
		return String.valueOf(charArrStr);
	}

	public static void setZeroToCharArr(char[] charArr,int end,int zeroNum){
		while(zeroNum--!=0){
			charArr[end--]=0;
		}
	}

	public static void main(String[] args){
		String str="kabcabcabddabcabcabdc";
		String word="abc";
		System.out.println(xReplaceWord(str,word));

	}

}
