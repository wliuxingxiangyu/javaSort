package classSeven;

import java.util.HashMap;

public class FindMaxSubArrayTargetSum {

	public static int getSumKMaxSubArrayLength(int[] arr, int k) {
		HashMap<Integer, Integer> valueFirstIndexMap = new HashMap<Integer, Integer>();
		valueFirstIndexMap.put(0, -1);
		int len = 0;
		int sum = 0;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			if (valueFirstIndexMap.containsKey(sum - k)) {
				int minIndex = valueFirstIndexMap.get(sum - k);
				if (i - minIndex > len) {
					len = i - minIndex;
				}
			}
			if (!valueFirstIndexMap.containsKey(sum)) {
				valueFirstIndexMap.put(sum, i);
			}
		}
		return len;
	}

	public static int[] generateArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 11) - 5;
		}
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = generateArray(20);
		printArray(arr);
		System.out.println(getSumKMaxSubArrayLength(arr, 10));

	}

}
