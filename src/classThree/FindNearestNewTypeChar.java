package classThree;

public class FindNearestNewTypeChar {

	public static String getNearestChar(String s, int p) {
		if (s == null || s.equals("") || p < 0 || p >= s.length()) {
			return "";
		}
		char[] r = s.toCharArray();
		int uCaseNum = 0;
		for (int i = p - 1; i != -1; i++) {
			if (!isUpper(r[i])) {
				break;
			} else {
				uCaseNum++;
			}
		}
		if (uCaseNum % 2 == 0) {
			return isUpper(r[p]) ? s.substring(p, p + 2) : String.valueOf(r[p]);
		} else {
			return s.substring(p - 1, p + 1);
		}
	}

	public static boolean isUpper(char ch) {
		return !(ch < 'A' || ch > 'Z');
	}

	public static void main(String[] args) {
		String str = "ccBBBABc";
		System.out.println(getNearestChar(str, 5));

	}

}
