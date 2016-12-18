package javaSort;

import Lesson0.TestYinYong.A;

public class HeapSortSimple {
	private static int leftChild(int i) {
		return i;
	}

	private static void percDown(
			int[] a, int i, int n) {
		int child = 0;
		int tmp;
		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child]<a[child + 1]) {
				child++;
			}

			if (tmp<a[child]) {
				a[i] = a[child];
			} else {
				break;
			}
		}
		a[i] = tmp;
	}

	public static void heapsort(
			int[] a) {
		for (int i = a.length / 2; i >= 0; i--) {
			percDown(a, i, a.length);
		}

		for (int i = a.length - 1; i > 0; i--) {
			swap(a, 0, i);
			percDown(a, 0, i);
		}
	}

	public static void swap(
			int[] a, int i, int j) {
		int tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}


	public static void main(String[] args) {
		int[] sort = new int[] { 1, 0, 10, 20, 3, 5, 6, 4, 9, 8, 12,
				17, 34, 11 };
		printArray(sort);
		System.out.println("¼´½«¶ÑÅÅÐò¡£¡£");
		heapsort(sort);
		printArray(sort);
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
