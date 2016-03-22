package classThree;

public class LongestNoRepeatSubstring {

	public static String getLongestNoRepeatSubString(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		char[] charArr = str.toCharArray();
		int[] record = new int[256];
		int maxLength = -1;
		int endIndex = -1;
		int lastRepeatIndex = -1;
		for (int i = 0; i != 256; i++) {
			record[i] = -1;
		}
		for (int i = 0; i != charArr.length; i++) {
			lastRepeatIndex = Math.max(lastRepeatIndex, record[charArr[i]]);
			int curLength = i - lastRepeatIndex;
			if (curLength > maxLength) {
				maxLength = curLength;
				endIndex = i;
			}
			record[charArr[i]] = i;
		}
		return str.substring(endIndex - maxLength + 1, endIndex + 1);
	}

	public static String generateRandomString(int length) {
		char[] result = new char[length];
		int base = 'a';
		int range = 'z' - 'a' + 1;
		for (int i = 0; i != length; i++) {
			result[i] = (char) ((int) (Math.random() * range) + base);
		}
		return String.valueOf(result);
	}

	public static void main(String[] args) {
		String str = generateRandomString(20);
		System.out.println(str);
		System.out.println(getLongestNoRepeatSubString(str));
	}
}
