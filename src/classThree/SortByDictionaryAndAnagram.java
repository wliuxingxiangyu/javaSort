package classThree;

import java.util.Arrays;
import java.util.Comparator;

public class SortByDictionaryAndAnagram {//16£¬Anagram£º×ÖÃÕ

	public static void sortByDictAndAnagram(String[] strArr) {
		Arrays.sort(strArr, new MyComparator());
	}

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String arg0, String arg1) {
			char[] arr0 = arg0.toCharArray();
			char[] arr1 = arg1.toCharArray();
			int sortByDict = compareByAnagram(arr0, arr1);
			if (sortByDict != 0) {
				return sortByDict;
			}
			return arg0.compareTo(arg1);
		}

		public int compareByAnagram(char[] arr0, char[] arr1) {
			Arrays.sort(arr0);
			Arrays.sort(arr1);
			int minLength = Math.min(arr0.length, arr1.length);
			for (int i = 0; i != minLength; i++) {
				if (arr0[i] < arr1[i]) {
					return -1;
				} else if (arr0[i] > arr1[i]) {
					return 1;
				}
			}
			return arr0.length == arr1.length ? 0 : (arr0.length < arr1.length ? -1 : 1);
		}

	}

	public static void printStringArray(String[] strArr) {
		for (int i = 0; i != strArr.length; i++) {
			System.out.print(strArr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] strArr = new String[] { "yy", "yyz", "ab", "ba", "abc", "cab",
				"cd", "ke", "dc", "ek" };
		printStringArray(strArr);
		sortByDictAndAnagram(strArr);
		printStringArray(strArr);

	}

}
