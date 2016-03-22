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

		int index = num.length - 2;// num[index]Ϊ�����ڶ���Ԫ��,������index+1.
		while (index >= 0 && num[index] >= num[index + 1])
			index--;// �Ӻ���ǰ,�ҵ�һ���ƻ��������Ƶ�Ԫ���±�index,��û����index=-1.

		if (index >= 0) {// ��û����index=-1,���������һ��num[index]���ڽ���.����.
			int firstMore = num.length - 1;
			while (firstMore > index && num[firstMore] <= num[index])
				firstMore--;// �Ӻ���ǰ��"��һ����num[index]���"Ԫ���±ꡣ
			swap(num, index, firstMore);// ����num[index]��num[firstMore]
		}

		// ��index=-1,����ԭ����ݼ�,�򰴵���˳�򷵻�.����.
		reverse(num, index + 1, num.length - 1);
		return num;
	}

	private static void reverse(int[] arr, int start, int end) {
		while (start < end)
			// ���á�start��end��Ԫ�ز���
			swap(arr, start++, end--);
	}

	private static void swap(int[] num, int start, int end) {
		int temp = num[start];
		num[start] = num[end];
		num[end] = temp;
	}
}