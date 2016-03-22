package classThree;

public class ReverseString{//L3P3»á

	public static String getReverseString(String str){
		char[] charArr=str.toCharArray();
		int start=0;
		int end=charArr.length-1;
		while(start<end){
			char tmp=charArr[start];
			charArr[start]=charArr[end];
			charArr[end]=tmp;
			start++;
			end--;
		}
		return String.valueOf(charArr);
	}

	public static void main(String[] args){
		String a="1234";
		System.out.println(getReverseString(a));
	}

}
