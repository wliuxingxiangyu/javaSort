package classEight;

public class MakeSortedArrLeftPartUnique {

	public static void leftUnique(int[] arr) {
		if (arr == null || arr.length == 1) {
			return;
		}
		int uniqueIndex = 0;
		int index = 1;
		while (index != arr.length) {
			if (arr[index] != arr[uniqueIndex]) {
				swap(arr, ++uniqueIndex, index);
			}
			index++;
		}
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] test = new int[] { 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8, 8, 9 };
		printArray(test);
		leftUnique(test);
		printArray(test);

	}

}
