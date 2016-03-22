package classSeven;

public class OddInOddEvenInEven {

	public static void oddInOddEvenInEven(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int evenIndex = 0;
		int oddIndex = 1;
		int endIndex = arr.length - 1;
		while (evenIndex < arr.length && oddIndex < arr.length) {
			if (arr[endIndex] % 2 == 0) {
				swap(arr, endIndex, evenIndex);
				evenIndex += 2;
			} else {
				swap(arr, endIndex, oddIndex);
				oddIndex += 2;
			}
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static boolean isValid(int[] arr) {
		boolean oddInOdd = true;
		boolean evenInEven = true;
		for (int i = 0; i != arr.length; i++) {
			if (i % 2 == 0 && arr[i] % 2 != 0) {
				evenInEven = false;
			}
			if (i % 2 != 0 && arr[i] % 2 == 0) {
				oddInOdd = false;
			}
		}
		return oddInOdd || evenInEven;
	}

	public static int[] creatArray(int size) {
		int[] test = new int[size];
		for (int i = 0; i != size; i++) {
			test[i] = (int) (Math.random() * 10) + 1;
		}
		return test;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = creatArray(10);
		printArray(arr);
		oddInOddEvenInEven(arr);
		printArray(arr);
		System.out.println(isValid(arr));

	}

}
