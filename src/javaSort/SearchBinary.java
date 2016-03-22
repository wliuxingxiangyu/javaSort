package javaSort;

public class SearchBinary {
	public static int BinarySearch(int[] Arr, int target) {
		if (Arr == null || Arr.length == 0) {
			return -1;
		}
		return SearchProcess(Arr, 0, Arr.length - 1, target);
	}

	public static int SearchProcess(int[] Arr, int l, int r, int target) {
		if (l<=r){  //�׺���
			int mid = r+(l-r)/2;//��д��(l+r)/2����ֹ(1+65535)���,��65535-1�������
			if (Arr[mid] == target) {
				return mid;
			} else if (Arr[mid] < target) {
				return SearchProcess(Arr, l, mid - 1, target);
			} else {
				return SearchProcess(Arr, mid + 1, r, target);
			}
		}else {
			return -1;//�׺���
		}
	}

	public static void main(String[] args) {
//		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7 };
		int[] arr = { 9,8,4,3,2,1 };

		System.out.println("BinarySearch=" + BinarySearch(arr, 8));
		System.out.println("(7-0)/2=" + (7-0)/2);
	}
}
