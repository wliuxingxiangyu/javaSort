package classThree;

import java.util.Arrays;
import java.util.Comparator;

class MergeStringsComparator implements Comparator<String> {
	@Override
	public int compare(String arg0, String arg1) {
		String beforeArg0AfterArg1 = arg0 + arg1;
		return beforeArg0AfterArg1.compareTo(arg1 + arg0);
	}
}

public class MergeStringsLowestLexicography {
	public static String geLowestLexicoString(String[] strs) {
		Arrays.sort(strs, new MergeStringsComparator());
		String result = "";
		for (int i = 0; i != strs.length; i++) {
			result += strs[i];
		}
		return result;
	}

	public static void main(String[] args) {
		String[] strArr = { "jibw", "ji", "jp", "bw", "jibw" };
		String result = geLowestLexicoString(strArr);
		System.out.println(result);

	}

}
