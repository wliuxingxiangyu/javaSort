package classFive;

import java.util.HashSet;

public class ContainsStringProblem {

	public static int getContainsNum(String[] strArr, String word) {
		HashSet<String> set = new HashSet<String>();
		for (String str : strArr) {
			set.add(str);
		}
		char[] wordChars = word.toCharArray();
		int[] containsNum = new int[wordChars.length];
		computeProcess(0, wordChars, set, containsNum);
		return containsNum[0] == -1 ? 0 : containsNum[0];
	}

	public static void computeProcess(int startIndex, char[] wordChars,
			HashSet<String> set, int[] containsNum) {
		int res = 0;
		String current = "";
		for (int i = startIndex; i != wordChars.length - 1; i++) {
			current += String.valueOf(wordChars[i]);
			if (set.contains(current)) {
				if (containsNum[i + 1] == -1) {
					continue;
				}
				if (containsNum[i + 1] == 0) {
					computeProcess(i + 1, wordChars, set, containsNum);
				}
				res += containsNum[i + 1] == -1 ? 0 : containsNum[i + 1];
			}
		}
		current += String.valueOf(wordChars[wordChars.length - 1]);
		if (set.contains(current)) {
			res++;
		}
		containsNum[startIndex] = res == 0 ? -1 : res;
	}

	public static int getContainsNumDP(String[] strArr, String word) {
		HashSet<String> set = new HashSet<String>();
		for (String str : strArr) {
			set.add(str);
		}
		char[] wordChars = word.toCharArray();
		int[] dp = new int[wordChars.length + 1];
		dp[dp.length - 1] = 1;
		for (int i = wordChars.length - 1; i != -1; i--) {
			int num = 0;
			String curStr = "";
			for (int j = i; j != wordChars.length; j++) {
				curStr += String.valueOf(wordChars[j]);
				if (set.contains(curStr) && dp[j + 1] != 0) {
					num += dp[j + 1];
				}
			}
			dp[i] = num;
		}
		return dp[0];
	}

	public static void main(String[] args) {
		String[] strArr = { "11", "12", "3", "1112", "123" };
		String word = "11121231112";
		System.out.println(getContainsNum(strArr, word));
		System.out.println(getContainsNumDP(strArr, word));

	}

}
