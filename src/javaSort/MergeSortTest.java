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
		int mid = ((end - start) >> 1) + start;//��(start+end)/2
		//�鲢�����3��,2���ݹ�,1���ϲ���
		merge_sort_recursive(arr, reg, start, mid);//start~mid
		merge_sort_recursive(arr, reg, mid + 1, end);//mid + 1~end
		merge(arr,reg,start,mid,end);
	}
	
	public static void merge(int[] arr, int[] reg, int start,int mid, int end) {
		int k = start;
		int i1 = start, end1 = mid;//ǰ���� �ϲ��� reg
		int i2 = mid + 1, end2 = end;//�󲿷� �ϲ��� reg
		// �𿪵�ԭ����arr �ϲ��� ��ʱ����reg
		while (i1 <= end1 && i2 <= end2)
			reg[k++] = arr[i1] < arr[i2] ? arr[i1++] : arr[i2++];
		while (i1 <= end1)//ǰ�벿�ֵ�ʣ��
			reg[k++] = arr[i1++];
		while (i2 <= end2)//��벿�ֵ�ʣ��
			reg[k++] = arr[i2++];
		for (k = start; k <= end; k++)//��ʱ����reg ���ظ�  ԭ����arr
			arr[k] = reg[k];
	}

	public static void main(String[] args) {
		int[] arr={2,9,6,8,5,4};
		System.out.println("ǰ");
		PrintArr(arr);
		int[] reg=new int[arr.length];//��ʱ����reg
		merge_sort_recursive(arr,reg,0,arr.length-1);
		System.out.println("hou");
		PrintArr(arr);
	}

}
