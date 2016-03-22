package classFive;

public class PrintAllPermutations{

	public static void printAllPermutations(String str){
		if(str==null||str.equals("")||str.length()==1){
			System.out.println(str);
			return;
		}
		printProcess(str.toCharArray(),0);
	}

	public static void printProcess(char[] strChars,int currentIndex){
		if(currentIndex==strChars.length-1){
			System.out.println(String.valueOf(strChars));
			return;
		}
		boolean[] hasChoosen=new boolean[256];//asc码默认0~255，如果不止255，用纯map
		for(int i=currentIndex;i!=strChars.length;i++){
			if(!hasChoosen[strChars[i]]){//重的不要换，adca,首a和尾a不要换
				hasChoosen[strChars[i]]=true;
				swap(strChars,currentIndex,i);
				printProcess(strChars,currentIndex+1);
				swap(strChars,currentIndex,i);
			}
		}
	}

	public static void swap(char[] charArr,int index1,int index2){
		char tmp=charArr[index1];
		charArr[index1]=charArr[index2];
		charArr[index2]=tmp;
	}

	public static void main(String[] args){
		String str="abcd";
		printAllPermutations(str);
	}
}
