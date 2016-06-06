package javaSort;

public class HeapSort {
	public static void main(String[] args) {
		int[] sort = new int[] { 1, 0, 2, 8, 5, 6, 4, 9, 3 };
		printArray(sort);
		System.out.println("即将堆排序。。");
		buildMaxHeapify(sort);// 1.建大根堆
		heapSort(sort);// 2.堆排序
		printHeap(sort);
		System.out.println("排序后的数组：");
		printArray(sort);
	}

	private static void buildMaxHeapify(int[] arr) {
		// 没有子节点的才需要创建最大堆，从最后一个的父节点开始//arr.length=14
		int startIndex = arr.length/2 - 1;//从中间元素开始建堆，
		// 从尾端开始创建最大堆，每次都是正确的堆//startIndex=6
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(arr, arr.length, i);
		}
	}

	/**
	 * 创建最大堆 heapSize需要创建最大堆的大小,因为最多值放在末尾,末尾就不再归入最大堆了
	 * index当前需要创建最大堆的位置，最大堆排序后，是升序排列
	 */
	private static void maxHeapify(int[] arr, int heapSize, int index) {
		// 当前点与左右子节点比较// i的左孩子为(2*i+1).本来是2i,因为编程时从1开始数.
		int left = (index << 1) + 1;
		int right = (index << 1) + 2;

		int largest = index;
		if (left < heapSize && arr[index] < arr[left]) {
			largest = left;
		}
		if (right < heapSize && arr[largest] < arr[right]) {
			largest = right;
		}
		// 得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
		if (largest != index) {
			swap(arr,largest,index);
			maxHeapify(arr, heapSize, largest);
		}
	}

	// 排序，最大值放在末尾，arr虽然是最大堆，在排序后就成了递增的
	private static void heapSort(int[] arr) {
		// 末尾与头交换，交换后调整最大堆
		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr,0,i);
			maxHeapify(arr, i, 0);
		}
	}

	public static void swap(int[] a, int i, int j) {
		int tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	private static void printHeap(int[] arr) {
		int pre = -2;
		for (int i = 0; i < arr.length; i++) {
			if (pre < (int) getLog(i + 1)) {
				pre = (int) getLog(i + 1);
				System.out.println();
			}
			System.out.print(arr[i] + "|");
		}
	}

	// 以2为底的对数
	private static double getLog(double param) {
		return Math.log(param) / Math.log(2);
	}

}
