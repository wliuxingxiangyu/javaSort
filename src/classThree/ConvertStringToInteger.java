package classThree;

public class ConvertStringToInteger{//L3P2字符转整数

	public static int convertInteger(String str){
		if(str==null||str.equals("")){
			System.out.println("can not convert!");
			return 0;
		}
		char[] charArr=str.toCharArray();//转化为数组
		if(!isValid(charArr)){
			System.out.println("can not convert!");
			return 0;
		}
		boolean isPos=charArr[0]=='-'?false:true;
		int res=0;
		int max=Integer.MAX_VALUE/10;
		int maxLast=Integer.MAX_VALUE%10;
		int min=Integer.MIN_VALUE/10;
		int minLast=Integer.MIN_VALUE%10;
		for(int i=isPos?0:1;i!=charArr.length;i++){
			int cur=isPos?charArr[i]-'0':'0'-charArr[i];
			if((res>max)||(res==max&&cur>maxLast)){//正
				System.out.println("can not convert!");
				return 0;
			}
			if((res<min)||(res==min&&cur<minLast)){//负
				System.out.println("can not convert!");
				return 0;
			}
			res=res*10+cur;
		}
		return res;
	}

	public static boolean isValid(char[] charArr){
		if(charArr[0]=='-'){
			if(charArr.length==1||charArr[1]=='0'){
				return false;
			}
		}else{
			if(charArr[0]=='0'&&charArr.length>1){
				return false;
			}
			if(charArr[0]<'0'||charArr[0]>'9'){
				return false;
			}
		}
		for(int i=1;i!=charArr.length;i++){
			if(charArr[i]<'0'||charArr[i]>'9'){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		String test1="2147483647"; // max int in java
		System.out.println(convertInteger(test1));
		System.out.println("===================");

		String test2="2147483648"; // overflow
		System.out.println(convertInteger(test2));
		System.out.println("===================");

		String test3="-2147483648"; // min int in java
		System.out.println(convertInteger(test3));
		System.out.println("===================");

		String test4="-2147483649"; // overflow
		System.out.println(convertInteger(test4));
		System.out.println("===================");

	}

}
