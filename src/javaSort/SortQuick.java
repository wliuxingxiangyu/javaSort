package javaSort;

public class SortQuick{// �൱��������tmpΪ���ĵĶ�����,ʱ�临�Ӷ���O(nlogn)
	public static int getMidIndex(int[] Array,int low,int high){// ���������λ��
		int tmp=Array[low]; // ����ĵ�һ����Ϊ����
		while(low<high){
			while(low<high&&Array[high]>=tmp){// high��,�ͼ�,��������
				System.out.println(Array[high] +"�Ƚ�"+tmp);
				high--;// ��ʱ,���ƶ�����ֵ,�ȶ�
			}
			Array[low]=Array[high];// ������С��Ԫ���Ƶ��Ͷ�,Array[high]����

			while(low<high&&Array[low]<tmp){
				System.out.println(Array[low] +"�Ƚ�"+tmp);
				low++;
			}
			Array[high]=Array[low]; // ��������Ԫ���Ƶ��߶�,Array[low]����
			
		}
		Array[low]=tmp;// ����Ԫ�ع�λ,��Ϊÿ�κ� tmp�Ƚ�,���� tmpΪ��С����ֽ�㡣
		return low; // ���������λ��
	}

	public static void fastSort(int[] Array,int low,int high){
		if(Array.length<=0){
			System.out.print("����������Ч");
			return;
		}
		if(low<high){
			int middle=getMidIndex(Array,low,high);// ��Array�������һ��Ϊ��
			fastSort(Array,low,middle);// �Ե��ֱ���еݹ����� //����ֽ��-1
			fastSort(Array,middle+1,high);// �Ը��ֱ���еݹ�����//����ֽ��+1
		}
	}

	public static void printArray(int[] Array){
		for(int i=0;i<Array.length;i++){
			System.out.print(Array[i]+"  ");
		}

	}

	public static void main(String[] args){
//		int[] Array={3, 2, 5, 7, 6, 8};
		int[] Array={4,5,1,2,3};
		// int[] Array={1,8,5,7,9,2,4,6,8};
		System.out.print("����ǰ������");
		printArray(Array);

		System.out.println();
		fastSort(Array,0,Array.length-1);
		System.out.print("����������");
		printArray(Array);
	}
}
