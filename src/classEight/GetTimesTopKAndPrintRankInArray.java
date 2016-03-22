package classEight;

import java.util.HashMap;
import java.util.Map.Entry;

public class GetTimesTopKAndPrintRankInArray {

	public static class Node {
		public String str;
		public int times;

		public Node(String str, int times) {
			this.str = str;
			this.times = times;
		}
	}

	public static void printTopKAndRank(String[] arr, int topK) {
		if (arr == null || topK < 1) {
			return;
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i != arr.length; i++) {
			String cur = arr[i];
			if (!map.containsKey(cur)) {
				map.put(cur, 1);
			} else {
				map.put(cur, map.get(cur) + 1);
			}
		}
		Node[] nodeArr = new Node[topK];
		int index = 0;
		for (Entry<String, Integer> entry : map.entrySet()) {
			String str = entry.getKey();
			int times = entry.getValue();
			Node node = new Node(str, times);
			if (index != topK) {
				nodeArr[index] = node;
				heapInsert(nodeArr, index++);
			} else {
				if (nodeArr[0].times < node.times) {
					nodeArr[0] = node;
					heapify(nodeArr, 0, topK);
				}
			}
		}
		for (int i = index - 1; i != 0; i--) {
			swap(nodeArr, 0, i);
			heapify(nodeArr, 0, i);
		}
		for (int i = 0; i != nodeArr.length; i++) {
			if (nodeArr[i] == null) {
				break;
			} else {
				System.out.print("No." + (i + 1) + ": ");
				System.out.print(nodeArr[i].str + ", times: ");
				System.out.println(nodeArr[i].times);
			}
		}
	}

	public static void heapInsert(Node[] arr, int index) {
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (arr[index].times < arr[parent].times) {
				swap(arr, parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}

	public static void heapify(Node[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int smallest = index;
		while (left < heapSize) {
			if (arr[left].times < arr[index].times) {
				smallest = left;
			}
			if (right < heapSize && arr[right].times < arr[smallest].times) {
				smallest = right;
			}
			if (smallest != index) {
				swap(arr, smallest, index);
			} else {
				break;
			}
			index = smallest;
			left = index * 2 + 1;
			right = index * 2 + 2;
		}
	}

	public static void swap(Node[] arr, int index1, int index2) {
		Node tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static String[] generateRandomArray(int len, int max) {
		String[] res = new String[len];
		for (int i = 0; i != len; i++) {
			res[i] = String.valueOf((int) (Math.random() * (max + 1)));
		}
		return res;
	}

	public static void printArray(String[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] arr = generateRandomArray(50, 10);
		int topK = 3;
		printArray(arr);
		printTopKAndRank(arr, topK);

	}
}