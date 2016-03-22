package classEight;

public class NumToStringAndStringToNum {

	public static String getString(char[] base, int n) {
		if (n < 1) {
			return "";
		}
		int curRange = 1;
		int baseRange = base.length;
		int len = 0;
		while (n >= curRange) {
			len++;
			n -= curRange;
			curRange *= baseRange;
		}
		char[] res = new char[len];
		int index = 0;
		do {
			curRange /= baseRange;
			int nCurRange = n / curRange;
			res[index++] = getKthCharAtBase(base, nCurRange + 1);
			n -= curRange * nCurRange;
		} while (index != res.length);
		return String.valueOf(res);
	}

	public static char getKthCharAtBase(char[] base, int k) {
		if (k < 1 || k > base.length) {
			return 0;
		}
		return base[k - 1];
	}

	public static int getNum(char[] base, String str) {
		char[] charArr = str.toCharArray();
		int baseRange = base.length;
		int curRange = 1;
		int res = 0;
		for (int i = charArr.length - 1; i != -1; i--) {
			res += getNthFromChar(base, charArr[i]) * curRange;
			curRange *= baseRange;
		}
		return res;
	}

	public static int getNthFromChar(char[] base, char ch) {
		for (int i = 0; i != base.length; i++) {
			if (base[i] == ch) {
				return i + 1;
			}
		}
		throw new RuntimeException("your string is invalid");
	}

	public static void main(String[] args) {
		// make sure every char of base is unique
		char[] base = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		int len = 1;
		String res = "";
		for (int i = 1; i != 705; i++) {
			res = getString(base, i);
			if (res.length() != len) {
				len = res.length();
				System.out.println("================");
			}
			System.out.print(res + " ");
			if (i % base.length == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("========================");
		int testNum = 78128712;
		System.out.println(getNum(base, getString(base, testNum)));
		String testStr = "BZZA";
		System.out.println(getString(base, getNum(base, testStr)));

	}
}
