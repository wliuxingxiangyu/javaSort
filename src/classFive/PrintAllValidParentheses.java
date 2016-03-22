package classFive;

public class PrintAllValidParentheses{

	public static void printAllValidParentheses(int parenNum){
		if(parenNum<1){
			return;
		}
		char[] charArr=new char[parenNum*2];
		printProcess(parenNum,parenNum,0,charArr);
	}

	public static void printProcess(int leftRestNum,int rightRestNum,int index,
			char[] charArr){
		if(index==charArr.length){
			System.out.println(String.valueOf(charArr));
			return;
		}
		if(leftRestNum>0){
			charArr[index]='(';
			printProcess(leftRestNum-1,rightRestNum,index+1,charArr);
		}
		if(rightRestNum>leftRestNum){
			charArr[index]=')';
			printProcess(leftRestNum,rightRestNum-1,index+1,charArr);
		}
	}

	public static void main(String[] args){
		int parenNum=3;
		printAllValidParentheses(parenNum);
	}

}
