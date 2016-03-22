package classThree;

public class ConvertCountString{

	public static String convertCountString(String str){
		if(str==null||str.equals("")){
			return str;
		}
		char[] charArr=str.toCharArray();
		String result=String.valueOf(charArr[0]);
		int count=1;
		for(int i=1;i!=charArr.length;i++){
			if(charArr[i]!=charArr[i-1]){
				result=concat3(result,String.valueOf(count),
						String.valueOf(charArr[i]));
				count=1;
			}else{
				count++;
			}
		}
		return concat2(result,String.valueOf(count));
	}

	public static String concat2(String s1,String s2){
		return s1+"_"+s2;
	}

	public static String concat3(String s1,String s2,String s3){
		return s1+"_"+s2+"_"+s3;
	}

	public static char getCharAt(String countStr,int index){
		if(countStr==null||countStr.length()<3){
			return 0;
		}
		char[] countCharArr=countStr.toCharArray();
		int sum=0;
		int curSum=0;
		char curChar=0;
		boolean isChar=true;
		for(int i=0;i!=countCharArr.length;i++){
			if(countCharArr[i]=='_'){
				isChar=!isChar;//字符，数字，
			}else if(isChar){
				sum+=curSum;
				if(sum>index){
					return curChar;
				}
				curSum=0;
				curChar=countCharArr[i];
			}else{
				curSum=curSum*10+countCharArr[i]-'0';//a_301;
			}
		}
		sum+=curSum;
		if(sum>index){//sum最后大
			return curChar;
		}
		return 0;
	}

	public static void main(String[] args){
		String str="aaabbadddffc";
		String countStr=convertCountString(str);
		System.out.println(countStr);
		System.out.println(str);

		for(int i=0;i!=100;i++){
			System.out.print(getCharAt(countStr,i));
		}

	}

}
