package leetCode;

public class strstr {
	public static int strStrMy(String source, String target) {
		if (source == null || target == null) {
			return -1;
		} else if (target.equals("")) {//target空串""!=null.字符串要用equals判等
			return 0;
		}
		int i = 0, j = 0;
		while(i < source.length()) {
			if (j < target.length() && source.charAt(i) == target.charAt(j)) {
				i++;//相等时，都往后加加。
				j++;
			} else if (j == target.length()) {
				break;//加加到小串结尾时，跳出
			} else {//匹配中途有一个不等时,拉回i到(与j相等的下一个)即j-1。
				i = i - (j - 1);
				j = 0;
			}
		}

		if (j == target.length()) {//判断上面出循环的条件，
			return i - target.length();//完全匹配子串
		} else {//没找到子串
			return -1;
		}
	}

	public static int strStr(String haystack, String needle) {
		if (needle.length() == 0)
			return 0;
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				else if (i + j >= haystack.length())
					return -1;
				else if (needle.charAt(j) != haystack.charAt(i + j))
					break;// 不等时，跳出 执行外层循环i++
			}
		}
	}

	public static void main(String[] args) {
		// String source = "lintcode";
		// String target = "lintcode";
		String source = "mississippi";
		String target = "issip";
//		String source = "";
//		String target = "a";
		// String source = "a";
		// String target = "";
		// String source = "abcdabcdefg";
		// String target = "bcd";
		System.out.println("输出结果:" + strStr(source, target));//4
		System.out.println("输出结果--strStrMy" + strStrMy(source, target));//4
		System.out.println("输出结果空串.equals(null) :" + "".equals(null));//false
	}
}
