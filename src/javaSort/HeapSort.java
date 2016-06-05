package javaSort;

public class HeapSort {
	public static void main(String[] args) {
		int[] sort = new int[] { 1, 0, 2, 3, 5, 6, 4, 9, 8 };
		printArray(sort);
		System.out.println("���������򡣡�");
		buildMaxHeapify(sort);// 1.�������
		heapSort(sort);// 2.������
		printHeap(sort);
		System.out.println("���������飺");
		printArray(sort);
	}

	private static void buildMaxHeapify(int[] data) {
		// û���ӽڵ�Ĳ���Ҫ�������ѣ������һ���ĸ��ڵ㿪ʼ//data.length=14
		int startIndex = getParentIndex(data.length - 1);
		// ��β�˿�ʼ�������ѣ�ÿ�ζ�����ȷ�Ķ�//startIndex=6
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(data, data.length, i);
		}
	}

	/**
	 * �������� heapSize��Ҫ�������ѵĴ�С,һ����sort��ʱ���õ�,��Ϊ���ֵ����ĩβ,ĩβ�Ͳ��ٹ���������
	 * index��ǰ��Ҫ�������ѵ�λ��
	 */
	private static void maxHeapify(int[] data, int heapSize, int index) {
		// ��ǰ���������ӽڵ�Ƚ�
		int left = getChildLeftIndex(index);
		int right = getChildRightIndex(index);

		int largest = index;
		if (left < heapSize && data[index] < data[left]) {
			largest = left;
		}
		if (right < heapSize && data[largest] < data[right]) {
			largest = right;
		}
		// �õ����ֵ�������Ҫ��������������ˣ����ӽڵ���ܾͲ��������ˣ���Ҫ���µ���
		if (largest != index) {
			swap(data,largest,index);
			maxHeapify(data, heapSize, largest);
		}
	}

	// �������ֵ����ĩβ��data��Ȼ�����ѣ��������ͳ��˵�����
	private static void heapSort(int[] data) {
		// ĩβ��ͷ�������������������
		for (int i = data.length - 1; i > 0; i--) {
			swap(data,0,i);
			maxHeapify(data, i, 0);
		}
	}

	public static void swap(int[] a, int i, int j) {
		int tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	// ���ڵ�λ��
	private static int getParentIndex(int current) {// (13-1)/2=6
		return (current - 1) >> 1;// i�ĸ���Ϊ(i-1)/2.
	}

	// ���ӽڵ�position
	private static int getChildLeftIndex(int current) {
		return (current << 1) + 1;// i������Ϊ(2*i+1).������2i,��Ϊ���ʱ��1��ʼ��.
	}

	// ���ӽڵ�position
	private static int getChildRightIndex(int current) {
		return (current << 1) + 2;// i���Һ���Ϊ(2*i+2).
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	private static void printHeap(int[] data) {
		int pre = -2;
		for (int i = 0; i < data.length; i++) {
			if (pre < (int) getLog(i + 1)) {
				pre = (int) getLog(i + 1);
				System.out.println();
			}
			System.out.print(data[i] + "|");
		}
	}

	// ��2Ϊ�׵Ķ���
	private static double getLog(double param) {
		return Math.log(param) / Math.log(2);
	}

}
