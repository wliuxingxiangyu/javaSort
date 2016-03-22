package classEight;

public class FindMinInSortedRotatedArray {

	public static int findMinInSortedRotatedArray(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		int mid = -1;
		while (left < right) {
			if (left == right - 1) {
				break;
			}
			if (arr[left] < arr[right]) {
				return arr[left];
			}
			mid = (left + right) / 2;
			if (arr[left] > arr[mid]) {
				right = mid;
				continue;
			}
			if (arr[mid] > arr[right]) {
				left = mid;
				continue;
			}
			return findByTraversal(arr, left, right);
		}
		return Math.min(arr[left], arr[right]);
	}

	public static int findByTraversal(int[] arr, int start, int end) {
		int min = Integer.MAX_VALUE;
		for (int i = start; i != end + 1; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	public static void main(String[] args) {
		int[] test = { 6, 1, 1, 2, 2, 3, 5 };
		System.out.println(findMinInSortedRotatedArray(test));

	}

}
