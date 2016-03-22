package classEight;

public class FindValueInSortedRatateArray {

	public static boolean isContains(int[] arr, int num) {
		return findProcess(arr, 0, arr.length - 1, num);
	}

	public static boolean findProcess(int[] arr, int start, int end, int num) {
		if (start > end) {
			return false;
		}
		if (start == end) {
			return arr[start] == num;
		}
		if (arr[start] < arr[end]) {
			return findNoRatated(arr, start, end, num);
		}
		int mid = (start + end) / 2;
		if (arr[mid] == num) {
			return true;
		} else if (arr[mid] > num) {
			if (arr[start] > arr[mid]) {
				return findProcess(arr, start, mid - 1, num);
			}
			if (arr[mid] > arr[end]) {
				if (arr[start] == num) {
					return true;
				} else if (arr[start] > num) {
					return findProcess(arr, mid + 1, end, num);
				} else {
					return findProcess(arr, start, mid - 1, num);
				}
			}
		} else {
			if (arr[start] > arr[mid]) {
				if (arr[end] == num) {
					return true;
				} else if (arr[end] < num) {
					return findProcess(arr, start, mid - 1, num);
				} else {
					return findProcess(arr, mid + 1, end, num);
				}
			}
			if (arr[mid] > arr[end]) {
				return findProcess(arr, mid + 1, end, num);
			}
		}
		return findProcess(arr, start, mid - 1, num) || findProcess(arr, mid + 1, end, num);
	}

	public static boolean findNoRatated(int[] arr, int start, int end, int num) {
		int mid = -1;
		while (start <= end) {
			mid = (start + end) / 2;
			if (arr[mid] == num) {
				return true;
			} else if (arr[mid] > num) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 2, 3, 4, 5 };
		int num = 10;
		System.out.println(isContains(arr, num));

	}
}
