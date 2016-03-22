package classFive;

public class StringInterLeaved {
	public static boolean isInterleaved(String aim, String str1, String str2) {
		if (str1 == null || str2 == null || aim == null) {
			return false;
		}
		char[] charsAim = aim.toCharArray();
		char[] charsStr1 = str1.toCharArray();
		char[] charsStr2 = str2.toCharArray();
		if (charsAim.length != charsStr1.length + charsStr2.length) {
			return false;
		}
		boolean[][] dpMap = new boolean[charsStr1.length + 1][charsStr2.length + 1];
		dpMap[0][0] = true;

		// dpMap[1..n][0]一系列值如何求解
		for (int i = 1; i != dpMap.length; i++) {
			if (charsStr1[i - 1] == charsAim[i - 1]) {
				dpMap[i][0] = true;
			} else {
				break;
			}
		}

		// dpMap[0][1..m]一系列值如何求解
		for (int j = 1; j != dpMap[0].length; j++) {
			if (charsStr2[j - 1] == charsAim[j - 1]) {
				dpMap[0][j] = true;
			} else {
				break;
			}
		}

		// 普通情况，既有左边的值，也有上边的值
		for (int i = 1; i != dpMap.length; i++) {
			for (int j = 1; j != dpMap[0].length; j++) {
				if ((charsStr1[i - 1] == charsAim[i + j - 1] && dpMap[i - 1][j])
						|| (charsStr2[j - 1] == charsAim[i + j - 1] && dpMap[i][j - 1])) {
					dpMap[i][j] = true;
				}
			}
		}
		// 最右下角的值为最后结果
		return dpMap[charsStr1.length][charsStr2.length];
	}

	public static boolean isInterleavedBetter(String aim, String str1,
			String str2) {
		if (str1 == null || str2 == null || aim == null) {
			return false;
		}
		char[] charsAim = aim.toCharArray();
		char[] charsLong = str1.length() >= str2.length() ? str1.toCharArray()
				: str2.toCharArray();
		char[] charsShort = str1.length() < str2.length() ? str1.toCharArray()
				: str2.toCharArray();
		if (charsAim.length != charsLong.length + charsShort.length) {
			return false;
		}
		boolean[] record = new boolean[charsShort.length + 1];
		record[0] = true;
		for (int i = 1; i != record.length; i++) {
			if (charsShort[i - 1] == charsAim[i - 1]) {
				record[i] = true;
			} else {
				break;
			}
		}
		for (int i = 1; i != charsLong.length + 1; i++) {
			record[0] = record[0] && charsLong[i - 1] == charsAim[i - 1];
			for (int j = 1; j != record.length; j++) {
				if ((charsLong[i - 1] == charsAim[i + j - 1] && record[j])
						|| (charsShort[j - 1] == charsAim[i + j - 1] && record[j - 1])) {
					record[j] = true;
				} else {
					record[j] = false;
				}
			}
		}
		return record[record.length - 1];
	}

	public static void main(String[] args) {
		String aim = "ab1c23d4";
		String str1 = "1234";
		String str2 = "abcd";
		System.out.println(isInterleaved(aim, str1, str2));
		System.out.println(isInterleavedBetter(aim, str1, str2));
	}

}
