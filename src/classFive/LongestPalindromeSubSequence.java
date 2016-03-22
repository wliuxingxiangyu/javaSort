package classFive;

public class LongestPalindromeSubSequence {

	// method 1
	public static String getLPSubSequence1(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		char[] charArr1 = str.toCharArray();
		char[] charArr2 = generateReverseCharArray(charArr1);
		return getLCSubSequence(charArr1, charArr2);
	}

	public static char[] generateReverseCharArray(char[] charArr) {
		char[] result = new char[charArr.length];
		int index = result.length - 1;
		for (int i = 0; i != charArr.length; i++) {
			result[index--] = charArr[i];
		}
		return result;
	}

	public static String getLCSubSequence(char[] charArr1, char[] charArr2) {
		int[][] dpMap = generateLCSubSequenceDPMap(charArr1, charArr2);
		return generateLCSubSequenceFromDPMap(dpMap, charArr1, charArr2);
	}

	public static int[][] generateLCSubSequenceDPMap(char[] chars1,
			char[] chars2) {
		int[][] map = new int[chars1.length][chars2.length];
		map[0][0] = chars1[0] == chars2[0] ? 1 : 0;
		for (int i = 1; i != chars1.length; i++) {
			map[i][0] = Math.max(map[i - 1][0], chars1[i] == chars2[0] ? 1 : 0);
		}
		for (int i = 1; i != chars2.length; i++) {
			map[0][i] = Math.max(map[0][i - 1], chars2[i] == chars1[0] ? 1 : 0);
		}
		for (int i = 1; i != chars1.length; i++) {
			for (int j = 1; j != chars2.length; j++) {
				int tmp = Math.max(map[i - 1][j], map[i][j - 1]);
				map[i][j] = chars1[i] == chars2[j] ? Math.max(
						map[i - 1][j - 1] + 1, tmp) : tmp;
			}
		}
		return map;
	}

	public static String generateLCSubSequenceFromDPMap(int[][] dpMap,
			char[] chars1, char[] chars2) {
		int row = dpMap.length - 1;
		int col = dpMap[0].length - 1;
		char[] result = new char[dpMap[row][col]];
		while (row != 0 && col != 0) {
			if (dpMap[row][col] > dpMap[row - 1][col]
					&& dpMap[row][col] > dpMap[row][col - 1]) {
				result[dpMap[row][col] - 1] = chars1[row];
				row--;
				col--;
			} else {
				if (dpMap[row - 1][col] > dpMap[row][col - 1]) {
					row--;
				} else {
					col--;
				}
			}
		}
		if (dpMap[row][col] == 1) {
			result[0] = row == 0 ? chars1[0] : chars2[0];
		}
		return String.valueOf(result);
	}

	// method 2
	public static String getLPSubSequence2(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		char[] charArr = str.toCharArray();
		int[][] map = getLPSubSequenceDPMap(charArr);
		char[] result = generateLPSubSequenceByDPMap(charArr, map);
		return String.valueOf(result);
	}

	public static int[][] getLPSubSequenceDPMap(char[] charArr) {
		int[][] map = new int[charArr.length][charArr.length];
		for (int i = map.length - 2; i != -1; i--) {
			for (int j = i + 1; j != map[0].length; j++) {
				if (charArr[i] == charArr[j]) {
					map[i][j] = map[i + 1][j - 1];
				} else {
					map[i][j] = Math.min(map[i + 1][j], map[i][j - 1]) + 1;
				}
			}
		}
		return map;
	}

	public static char[] generateLPSubSequenceByDPMap(char[] charArr,
			int[][] map) {
		char[] result = new char[charArr.length - map[0][charArr.length - 1]];
		int start = 0;
		int end = charArr.length - 1;
		int left = 0;
		int right = result.length - 1;
		while (start <= end) {
			if (charArr[start] == charArr[end]) {
				result[left++] = charArr[start++];
				result[right--] = charArr[end--];
			} else if (map[start + 1][end] + 1 == map[start][end]) {
				start++;
			} else {
				end--;
			}
		}
		return result;
	}

	// get the shortest all palindrome string
	public static String getSAPS(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		char[] charArr = str.toCharArray();
		int[][] map = getLPSubSequenceDPMap(charArr);
		char[] result = generateSAPSByDPMap(charArr, map);
		return String.valueOf(result);
	}

	public static char[] generateSAPSByDPMap(char[] charArr, int[][] map) {
		char[] result = new char[charArr.length + map[0][charArr.length - 1]];
		int start = 0;
		int end = charArr.length - 1;
		int left = 0;
		int right = result.length - 1;
		while (start <= end) {
			if (charArr[start] == charArr[end]) {
				result[left++] = charArr[start++];
				result[right--] = charArr[end--];
			} else if (map[start + 1][end] + 1 == map[start][end]) {
				result[left++] = charArr[start];
				result[right--] = charArr[start++];
			} else {
				result[left++] = charArr[end];
				result[right--] = charArr[end--];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String str = "B1G2D34I3K2S1";
		String result1 = getLPSubSequence1(str);
		System.out.println(str + " longest palindrome subsequence: " + result1);
		String result2 = getLPSubSequence2(str);
		System.out.println(str + " longest palindrome subsequence: " + result2);
		String result3 = getSAPS(str);
		System.out.println(str + " shortest all palindrome string: " + result3);

	}

}
