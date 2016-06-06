package javaSort;

public class HeapSort {
	public static void main(String[] args) {
		int[] sort = new int[] { 1, 0, 2, 8, 5, 6, 4, 9, 3 };
		printArray(sort);
		System.out.println("���������򡣡�");
		buildMaxHeapify(sort);// 1.�������
		heapSort(sort);// 2.������
		printHeap(sort);
		System.out.println("���������飺");
		printArray(sort);
	}

	private static void buildMaxHeapify(int[] arr) {
		// û���ӽڵ�Ĳ���Ҫ�������ѣ������һ���ĸ��ڵ㿪ʼ//arr.length=14
		int startIndex = arr.length/2 - 1;//���м�Ԫ�ؿ�ʼ���ѣ�
		// ��β�˿�ʼ�������ѣ�ÿ�ζ�����ȷ�Ķ�//startIndex=6
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(arr, arr.length, i);
		}
	}

	/**
	 * �������� heapSize��Ҫ�������ѵĴ�С,��Ϊ���ֵ����ĩβ,ĩβ�Ͳ��ٹ���������
	 * index��ǰ��Ҫ�������ѵ�λ�ã��������������������
	 */
	private static void maxHeapify(int[] arr, int heapSize, int index) {
		// ��ǰ���������ӽڵ�Ƚ�// i������Ϊ(2*i+1).������2i,��Ϊ���ʱ��1��ʼ��.
		int left = (index << 1) + 1;
		int right = (index << 1) + 2;

		int largest = index;
		if (left < heapSize && arr[index] < arr[left]) {
			largest = left;
		}
		if (right < heapSize && arr[largest] < arr[right]) {
			largest = right;
		}
		// �õ����ֵ�������Ҫ��������������ˣ����ӽڵ���ܾͲ��������ˣ���Ҫ���µ���
		if (largest != index) {
			swap(arr,largest,index);
			maxHeapify(arr, heapSize, largest);
		}
	}

	// �������ֵ����ĩβ��arr��Ȼ�����ѣ��������ͳ��˵�����
	private static void heapSort(int[] arr) {
		// ĩβ��ͷ�������������������
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

	// ��2Ϊ�׵Ķ���
	private static double getLog(double param) {
		return Math.log(param) / Math.log(2);
	}

}
