package javaSort;

public class SortSelection{

	public static void swap(int[] Array,int i,int j){
		int temp=Array[i];
		Array[i]=Array[j];
		Array[j]=temp;
	}

	public static void sort(int[] Array){// ѡ����������С������С������
		for(int i=0;i<Array.length;i++){
			int lowIndex=i;// �����ڲ�forǰ���ٶ���ǰArray[j]Ϊ�����������õ���С��
			for(int j=Array.length-1;j>i;j--){
				if(Array[j]<Array[lowIndex]){// ����С��Сʱ����lowIndex
					lowIndex=j;
				}
			}
			swap(Array,i,lowIndex);// ��iСֵ�鵽Array(i)λ��
		}
	}

	public static int[] randomToArray(){
		int[] Array=new int[8];
		for(int i=0;i<Array.length;i++){
			Array[i]=(int)(Math.random()*10);
		}
		return Array;
	}

	public static void printArray(int[] Array){
		for(int i=0;i<Array.length;i++){
			System.out.print(Array[i]+"  ");
		}
	}

	public static void main(String[] args){
		// int[] Array = randomToArray();
		int[] Array={1, 3, 5, 7, 9, 2, 4, 6, 8};
		System.out.print("����ǰ������");
		printArray(Array);

		System.out.println();
		sort(Array);
		System.out.print("����������");
		printArray(Array);

	}
}
