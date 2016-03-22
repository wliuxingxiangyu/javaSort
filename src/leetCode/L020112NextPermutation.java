package leetCode;

public class L020112NextPermutation {
	public static void nextPermutation(int[] num) {
		if (num == null || num.length < 2) //0��null,1������.
			return;
		
		int index = num.length - 2;//num[index]Ϊ�����ڶ���Ԫ��,������index+1.
		while (index >= 0 && num[index] >= num[index + 1])
			index--;//�Ӻ���ǰ,�ҵ�һ���ƻ��������Ƶ�Ԫ���±�index,��û����index=-1.
		
		if (index >= 0) {//��û����index=-1,���������һ��num[index]���ڽ���.����.
			int firstMore = num.length - 1;
			while (firstMore >index && num[firstMore] <= num[index])
				firstMore--;//�Ӻ���ǰ��"��һ����num[index]���"Ԫ���±ꡣ
			swap(num, index, firstMore);//����num[index]��num[firstMore]
		}
		
		//��index=-1,����ԭ����ݼ�,�򰴵���˳�򷵻�.����.
		reverse(num, index + 1,num.length - 1);
	}

	private static void reverse(int[] arr, int start,int end) {
		while (start < end)//���á�start��end��Ԫ�ز���
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
		System.out.println("ǰ");
		PrintArr(arr);//5 3 2 4 1 
		nextPermutation(arr);
		System.out.println("��");
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
