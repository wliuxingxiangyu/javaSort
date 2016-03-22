package leetCode;

public class L020112NextPermutationLintCode {
	public static int[] nextPermutation(int[] num) {
		if (num == null)
			return null;
		if (num.length == 1)
			return num;
		if (num.length == 2) {
			swap(num, 0, 1);
			return num;
		}

		int index = num.length - 2;// num[index]为倒数第二个元素,下面有index+1.
		while (index >= 0 && num[index] >= num[index + 1])
			index--;// 从后往前,找第一个破坏递增趋势的元素下标index,若没有则index=-1.

		if (index >= 0) {// 若没有则index=-1,不进此与第一个num[index]大于交换.合理.
			int firstMore = num.length - 1;
			while (firstMore > index && num[firstMore] <= num[index])
				firstMore--;// 从后往前找"第一个比num[index]大的"元素下标。
			swap(num, index, firstMore);// 交换num[index]和num[firstMore]
		}

		// 当index=-1,表明原数组递减,则按递增顺序返回.合理.
		reverse(num, index + 1, num.length - 1);
		return num;
	}

	private static void reverse(int[] arr, int start, int end) {
		while (start < end)
			// 逆置“start到end”元素部分
			swap(arr, start++, end--);
	}

	private static void swap(int[] num, int start, int end) {
		int temp = num[start];
		num[start] = num[end];
		num[end] = temp;
	}
}