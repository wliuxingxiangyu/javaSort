package classSeven;

import java.util.HashSet;

public class FindLongestIntegratedSubArray {

	public static int getLongestIntegratedSubArrayLength(int[] array) {
		int len = 0;
		for (int i = 0; i != array.length; i++) {
			int max = array[i];
			int min = array[i];
			HashSet<Integer> numSet = new HashSet<Integer>();
			for (int j = i; j != array.length; j++) {
				if (numSet.contains(array[j])) {
					break;
				} else {
					numSet.add(array[j]);
				}
				min = Math.min(min, array[j]);
				max = Math.max(max, array[j]);
				if (max - min == j - i) {
					int curLen = j - i + 1;
					if (curLen > len) {
						len = curLen;
					}
				}
			}
		}
		return len;
	}

	public static void main(String[] args) {
		int[] arr = { 13, 14, 9, 11, 2, 3, 10, 8, 6, 7, 5, 4, 12, 1 };
		System.out.println(getLongestIntegratedSubArrayLength(arr));

	}

}
