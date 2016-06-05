package javaSort;

import Lesson0.TestYinYong.A;

public class HeapSortSimple {
	private static int leftChild(int i) {
		return i;
	}

	private static <AnyType extends Comparable<? super AnyType>> void percDown(
			AnyType[] a, int i, int n) {
		int child = 0;
		AnyType tmp;
		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) {
				child++;
			}

			if (tmp.compareTo(a[child]) < 0) {
				a[i] = a[child];
			} else {
				break;
			}
		}
		a[i] = tmp;
	}

	public static <AnyType extends Comparable<? super AnyType>> void heapsort(
			AnyType[] a) {
		for (int i = a.length / 2; i >= 0; i--) {
			percDown(a, i, a.length);
		}

		for (int i = a.length - 1; i > 0; i--) {
			swap(a, 0, i);
			percDown(a, 0, i);
		}
	}

	public static <AnyType extends Comparable<? super AnyType>> void swap(
			AnyType[] a, int i, int j) {
		AnyType tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static int[] sort = new int[] { 1, 0, 10, 20, 3, 5, 6, 4, 9, 8, 12,
			17, 34, 11 };

	public static void main(String[] args) {
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
