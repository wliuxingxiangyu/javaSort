package javaSort;

import java.util.Arrays;

public class MergeSortTest {
	
	public static void PrintArr(int[] arr){
		if (arr!=null && arr.length!=0) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]+" ");
			}
		}
	}
	
	public static void merge_sort_recursive(int[] arr, int[] reg, int start, int end) {
		if (start >= end)
			return;
		int len = end - start, mid = (len >> 1) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge_sort_recursive(arr, reg, start1, end1);
		merge_sort_recursive(arr, reg, start2, end2);
		int k = start;
		// 拆开的原数组arr 合并入 临时数组reg
		while (start1 <= end1 && start2 <= end2)
			reg[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
		while (start1 <= end1)
			reg[k++] = arr[start1++];
		while (start2 <= end2)
			reg[k++] = arr[start2++];
		for (k = start; k <= end; k++)//临时数组reg 返回给  原数组arr
			arr[k] = reg[k];
	}

	public static void main(String[] args) {
		int[] arr={2,9,6,8,5,4};
		System.out.println("前");
		PrintArr(arr);
		int[] reg=new int[arr.length];
		merge_sort_recursive(arr,reg,0,arr.length-1);
		System.out.println("hou");
		PrintArr(arr);
	}

}
