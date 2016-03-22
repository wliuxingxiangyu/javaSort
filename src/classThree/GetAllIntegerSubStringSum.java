package classThree;

public class GetAllIntegerSubStringSum{

	public static int getAllIntSubStringSum(String str){
		char[] charArr=str.toCharArray();
		int result=0;
		int previousValue=0;
		boolean positive=true;
		for(int i=0;i!=charArr.length;i++){
			int curValue=charArr[i]-'0';
			if(curValue<0||curValue>9){
				result+=previousValue;
				previousValue=0;
				if(charArr[i]=='-'){
					if(i-1>-1&&charArr[i-1]=='-'){
						positive=positive?false:true;
					}else{
						positive=false;
					}
				}else{
					positive=true;//既不是，又不是，
				}
			}else{
				previousValue=previousValue*10+(positive?curValue:-curValue);
			}
		}
		result+=previousValue;
		return result;
	}

	public static void main(String[] args){
		String test="1K-100ABC500D-T--100F200G!!100H---300";
		System.out.println(getAllIntSubStringSum(test));

	}

}
