package Lesson0;

//在一个字符串的在任意位置添加最少的字符，使得添加后的字符串整体是回文字符串
class Person {
	// lps表示longest最长 Palindrome回文 substring子串
	public String generateASPByLPS(String str, String strLPS) {
		if (str == null || str.equals("")) {
			return "";
		}
		char[] charArr = str.toCharArray();// str转换为字符数组
		char[] charLPSArr = strLPS.toCharArray();// strLPS转换为字符数组
		char[] result = new char[2 * charArr.length - charLPSArr.length];// 因为已回文strLPS无需重复加
		int lpsLeft = 0;// 最长回文子串 左下标
		int lpsRight = charLPSArr.length - 1;// 最长回文子串 右下标
		int charLeft = 0;// 大串 左下标,
		int charRight = charArr.length - 1;// 大串 右下标,
		int resultLeft = 0;// 结果 左下标
		int resultRight = result.length - 1;// 结果 右下标
		while (lpsLeft <= lpsRight) { // 遍历子串
			int tmpCharLeft = charLeft;// 大串 左下标,即左侧洋葱外下标,传给形参leftPartStart
			int tmpCharRight = charRight;// 大串 右下标,即右侧洋葱外下标,传给形参rightPartEnd
			while (charArr[charLeft] != charLPSArr[lpsLeft]) {// 找大串左边字符=回文子串左边字符的左下标
				charLeft++;// 大串 左下标 加加,while中标记大小串相同的左位置,即左侧洋葱内下标
			}
			while (charArr[charRight] != charLPSArr[lpsRight]) {// 找大串右边字符=回文子串左边字符的右下标
				charRight--;// 大串 右下标 加加,while中标记大小串相同的右位置,即右侧洋葱内下标
			}
			System.out.println("tmpCharLeft=" + tmpCharLeft);// 调试
			System.out.println("charLeft=" + charLeft);// 调试
			System.out.println("charRight=" + charRight);// 调试
			System.out.println("tmpCharRight=" + tmpCharRight);// 调试
			setTwoUnPalindromePartToResult(result, resultLeft, resultRight,
					charArr, tmpCharLeft, charLeft, charRight, tmpCharRight);
			// 形参charArr,leftPartStart,leftPartEnd,rightPartStart,rightPartEnd

			resultLeft += charLeft - tmpCharLeft + tmpCharRight - charRight;// 赋值result后,改变位置下标
			resultRight -= charLeft - tmpCharLeft + tmpCharRight - charRight;
			result[resultLeft++] = charArr[charLeft++];// 大串与子串相同时，result两边赋相同的值
			result[resultRight--] = charArr[charRight--];//
			lpsLeft++;
			lpsRight--;
		}
		return String.valueOf(result);// 将result转换成字符串
	}

	public void setTwoUnPalindromePartToResult(char[] result, int resultLeft,
			int resultRight, char[] charArr, int leftPartStart,
			int leftPartEnd, int rightPartStart, int rightPartEnd) {
		// 设置两边的非回文部分到Result中
		for (int i = leftPartStart; i != leftPartEnd; i++) {
			result[resultLeft++] = charArr[i];// 回文result左右都有charArr[i]，
			result[resultRight--] = charArr[i];
			System.out.println("charArr[i]左=" + charArr[i]);// 调试
		}
		for (int i = rightPartEnd; i != rightPartStart; i--) {
			result[resultLeft++] = charArr[i];
			result[resultRight--] = charArr[i];
			System.out.println("charArr[i]右=" + charArr[i]);// 调试
		}
	}
}

public class huiwen {
	// 测试用例
	public static void main(String[] args) {
		Person p = new Person();
		String str = "B1G2TY34I3OPX2S1";
		String strLPS = "123I321";
		String result = p.generateASPByLPS(str, strLPS);
		System.out.println(str + " shortest all palindrome string:result="
				+ result);
	}
}