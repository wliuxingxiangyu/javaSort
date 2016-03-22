package classEight;

public class FindMinPointLessThanNeighbor {

	public static int getLessNeighborIndex(int[] arr) {
		if (arr.length == 1) {
			return 0;
		}
		if (arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return left;
	}

	public static int[] generateRandomArrayNeighborDiff(int len, int max) {
		if (len < 1 || max < 1) {
			return null;
		}
		int[] res = new int[len];
		res[0] = (int) (Math.random() * (max + 1));
		for (int i = 1; i != res.length; i++) {
			do {
				res[i] = (int) (Math.random() * (max + 1));
			} while (res[i] == res[i - 1]);
		}
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = generateRandomArrayNeighborDiff(10, 20);
		printArray(arr);
		int index = getLessNeighborIndex(arr);
		System.out.println("index: " + index + " , value: " + arr[index]);

	}

}
