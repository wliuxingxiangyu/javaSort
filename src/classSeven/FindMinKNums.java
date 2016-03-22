package classSeven;

public class FindMinKNums {

	// O(N*logK)
	public static int[] getMinKNumsByHeap(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int[] kHeap = new int[k];
		for (int i = 0; i != k; i++) {
			heapInsert(kHeap, arr[i], i);
		}
		for (int i = k; i != arr.length; i++) {
			if (arr[i] < kHeap[0]) {
				kHeap[0] = arr[i];
				heapify(kHeap, 0, k);
			}
		}
		return kHeap;
	}

	public static void heapInsert(int[] arr, int value, int index) {
		arr[index] = value;
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (arr[parent] < arr[index]) {
				swap(arr, parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}

	public static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int largest = index;
		while (left < heapSize) {
			if (arr[left] > arr[index]) {
				largest = left;
			}
			if (right < heapSize && arr[right] > arr[largest]) {
				largest = right;
			}
			if (largest != index) {
				swap(arr, largest, index);
			} else {
				break;
			}
			index = largest;
			left = index * 2 + 1;
			right = index * 2 + 2;
		}
	}

	// O(N)
	public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int minKth = getMinKthByBFPRT(arr, k);
		int[] res = new int[k];
		int index = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] < minKth) {
				res[index++] = arr[i];
			}
		}
		for (; index != res.length; index++) {
			res[index] = minKth;
		}
		return res;
	}

	public static int getMinKthByBFPRT(int[] arr, int K) {
		int[] copyArr = copyArray(arr);
		return bfprtProcess(copyArr, 0, copyArr.length - 1, K - 1);
	}

	public static int[] copyArray(int[] arr) {
		int[] result = new int[arr.length];
		for (int i = 0; i != result.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}

	public static int bfprtProcess(int[] arr, int begin, int end, int findIndex) {
		if (begin == end) {
			return arr[begin];
		}
		int pivotValue = medianOfMedians(arr, begin, end);
		int[] pivotStartAndEndIndexs = partition(arr, begin, end, pivotValue);
		if (findIndex >= pivotStartAndEndIndexs[0]
				&& findIndex <= pivotStartAndEndIndexs[1]) {
			return arr[findIndex];
		} else if (findIndex < pivotStartAndEndIndexs[0]) {
			return bfprtProcess(arr, begin, pivotStartAndEndIndexs[0] - 1,
					findIndex);
		} else {
			return bfprtProcess(arr, pivotStartAndEndIndexs[1] + 1, end,
					findIndex);
		}
	}

	public static int medianOfMedians(int[] arr, int begin, int end) {
		int elementNum = end - begin + 1;
		int offset = elementNum % 5 == 0 ? 0 : 1;
		int[] mediansArr = new int[elementNum / 5 + offset];
		for (int i = 0; i < mediansArr.length; i++) {
			int beginIndex = begin + i * 5;
			int endIndex = beginIndex + 4;
			mediansArr[i] = getMedian(arr, beginIndex, Math.min(end, endIndex));
		}
		return bfprtProcess(mediansArr, 0, mediansArr.length - 1,
				mediansArr.length / 2);
	}

	public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int smallerIndex = begin - 1;
		int currentIndex = begin;
		int biggerIndex = end + 1;
		while (currentIndex != biggerIndex) {
			if (arr[currentIndex] < pivotValue) {
				swap(arr, ++smallerIndex, currentIndex++);
			} else if (arr[currentIndex] > pivotValue) {
				swap(arr, currentIndex, --biggerIndex);
			} else {
				currentIndex++;
			}
		}
		int[] result = new int[2];
		result[0] = smallerIndex + 1;
		result[1] = biggerIndex - 1;
		return result;
	}

	public static int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int midIndex = (sum / 2) + (sum % 2);
		return arr[midIndex];
	}

	public static void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
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
		int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
		// sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
		printArray(getMinKNumsByHeap(arr, 10));
		printArray(getMinKNumsByBFPRT(arr, 10));

	}

}
