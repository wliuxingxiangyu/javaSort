package classEight;

public class IsMagicArray {

	public static boolean isMagicArrayUnique(int[] arr) {
		int left = 0;
		int right = arr.length;
		int mid = -1;
		while (left <= right) {
			mid = (left + right) / 2;
			if (arr[mid] == mid) {
				return true;
			} else if (arr[mid] > mid) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return false;
	}

	public static boolean isMagicArrayNormal(int[] arr) {
		return isMagicProcess(arr, 0, arr.length - 1);
	}

	public static boolean isMagicProcess(int[] arr, int left, int right) {
		if (left > right) {
			return false;
		}
		if (left == right) {
			return arr[left] == left;
		}
		int mid = (left + right) / 2;
		if (arr[mid] == mid) {
			return true;
		} else if (arr[mid] < mid) {
			if (arr[right] < mid + 1) {
				return isMagicProcess(arr, mid + 1, right);
			}
		} else {
			if (arr[left] > mid - 1) {
				return isMagicProcess(arr, left, mid - 1);
			}
		}
		return isMagicProcess(arr, left, mid - 1) || isMagicProcess(arr, mid + 1, right);
	}

	public static void main(String[] args) {
		int[] arr = { -3, -2, 0, 2, 4, 7 };
		System.out.println(isMagicArrayUnique(arr));
		System.out.println(isMagicArrayNormal(arr));
	}

}
