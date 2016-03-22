package classFive;

public class PrintStringCombination{
	public static void printAllCombination(String str){
		char[] charArr=str.toCharArray();// 转化string为char类型数组，在递归时省空间。
		printAllProcess(charArr,0,""); // 递归函数开始
	}

	/*
	 * 打印所有子序列的递归函数： 参数： charArr : 代表遍历的对象（原来的那个String） currentIndex :
	 * 代表遍历到了charArr的第currentIndex个字符 trace :
	 * 一个String类型的变量，当一个字符加入进来，就把这个字符加到trace的后面
	 */
	public static void printAllProcess(char[] charArr,int currentIndex,
			String trace){
		// currentIndex等于charArr.length时表示后续没有字符了，递归结束
		if(currentIndex==charArr.length){
			return;
		}
		// 将charArr中的第currentIndex字符加入到trace，生成新的String
		// 含义是（将原来已经选的字符+当前的字符）结合在一起--traceAddCurrentChar
		String traceAddCurrentChar=trace+String.valueOf(charArr[currentIndex]);
		// 打印与当前字符结合后的字符串
		System.out.println(traceAddCurrentChar);
		//每次两条路：“包”或“不包”当前字符
		// 走递归的过程中“包”含当前字符的分支(让traceAddCurrentChar往下传值)
		printAllProcess(charArr,currentIndex+1,traceAddCurrentChar);
		// 走递归的过程中“不包”含当前字符的分支(让trace往下传值)
		printAllProcess(charArr,currentIndex+1,trace);
	}

	public static void printKLengthCombination(String str,int K){
		char[] charArr=str.toCharArray();
		// 与原问题的递归函数一样，只是加了一个参数K，表示需要打印的子序列长度
		printKLengthProcess(charArr,0,"",K);
	}

	public static void printKLengthProcess(char[] charArr,int currentIndex,
			String trace,int K){
		if(trace.length()==K){ // 剪枝条件A
			return;
		}
		if(trace.length()+charArr.length-currentIndex<K){ // 剪枝条件B：不足K
			return;
		}
		if(currentIndex==charArr.length){
			return;
		}
		String traceAddCurrentChar=trace+String.valueOf(charArr[currentIndex]);
		if(traceAddCurrentChar.length()==K){ // 打印条件C
			System.out.println(traceAddCurrentChar);
		}
		printKLengthProcess(charArr,currentIndex+1,traceAddCurrentChar,K);
		printKLengthProcess(charArr,currentIndex+1,trace,K);
	}

	public static void main(String[] args){
		String str="ABC";
		printAllCombination(str);
		System.out.println("==============");
		printKLengthCombination(str,2);
	}
}
