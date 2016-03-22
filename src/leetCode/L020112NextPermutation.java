package leetCode;

public class L020112NextPermutation {
	public static void nextPermutation(int[] num) {
		if (num == null || num.length < 2) //0返null,1返本身.
			return;
		
		int index = num.length - 2;//num[index]为倒数第二个元素,下面有index+1.
		while (index >= 0 && num[index] >= num[index + 1])
			index--;//从后往前,找第一个破坏递增趋势的元素下标index,若没有则index=-1.
		
		if (index >= 0) {//若没有则index=-1,不进此与第一个num[index]大于交换.合理.
			int firstMore = num.length - 1;
			while (firstMore >index && num[firstMore] <= num[index])
				firstMore--;//从后往前找"第一个比num[index]大的"元素下标。
			swap(num, index, firstMore);//交换num[index]和num[firstMore]
		}
		
		//当index=-1,表明原数组递减,则按递增顺序返回.合理.
		reverse(num, index + 1,num.length - 1);
	}

	private static void reverse(int[] arr, int start,int end) {
		while (start < end)//逆置“start到end”元素部分
			swap(arr, start++, end--);
	}

	private static void swap(int[] num, int start, int end) {
		int temp = num[start];
		num[start] = num[end];
		num[end] = temp;
	}

	public static void main(String[] args) {
//		int[] arr={6,8,7,4,3,2};
//		int[] arr={1,1,5};
//		int[] arr={1,2,3};
//		int[] arr={3,2,1};
		int[] arr={5,3,2,4,1};
		System.out.println("前");
		PrintArr(arr);//5 3 2 4 1 
		nextPermutation(arr);
		System.out.println("后");
		PrintArr(arr);//5 3 4 1 2 
	}
	
	public static void PrintArr (int[] arr) {
		if(arr==null) return;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

}
