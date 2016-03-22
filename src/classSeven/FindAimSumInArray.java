package classSeven;

import java.util.Arrays;

public class FindAimSumInArray {// L7P10

	public static void printAllUniquePair(int[] arr, int k) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		int prev = Integer.MIN_VALUE;// prev should be a value that arr has not
		while (left < right) {
			if (arr[left] + arr[right] < k) {
				left++;
			} else if (arr[left] + arr[right] > k) {
				right--;
			} else {
				if (prev != arr[left]) {
					prev = arr[left];
					System.out
							.println("(" + arr[left] + "," + arr[right] + ")");
				}
				left++;
				right--;
			}
		}
	}

	public static void printAllUniqueTriad(int[] arr, int k) {
		if (arr == null || arr.length < 3) {
			return;
		}
		int prev1 = arr[0];
		printRest(arr, 1, arr.length - 1, k - prev1, prev1);
		for (int i = 1; i < arr.length - 2; i++) {
			if (arr[i] != prev1) {
				prev1 = arr[i];
				printRest(arr, i + 1, arr.length - 1, k - prev1, prev1);
			}
		}
	}

	public static void printRest(int[] arr, int start, int end, int k,
			int firstNum) {
		int left = start;
		int right = end;
		int prev2 = Integer.MIN_VALUE;// prev should be a value that arr has not
		while (left < right) {
			if (arr[left] + arr[right] < k) {
				left++;
			} else if (arr[left] + arr[right] > k) {
				right--;
			} else {
				if (prev2 != arr[left]) {
					prev2 = arr[left];
					System.out.println("(" + firstNum + "," + arr[left] + ","
							+ arr[right] + ")");
				}
				left++;
				right--;
			}
		}
	}

	public static int[] getRandomSortArray(int length) {
		int[] result = new int[length];
		for (int i = 0; i != length; i++) {
			result[i] = (int) (Math.random() * 21) - 10;
		}
		Arrays.sort(result);
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// int[] arr = getRandomSortArray(10);
		int[] arr = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
		printArray(arr);
		System.out.println("二元组如下：");
		printAllUniquePair(arr, 10);
		System.out.println("三元组如下：");
		printAllUniqueTriad(arr, 10);

	}

}
