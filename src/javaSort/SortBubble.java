package javaSort;
public class SortBubble{
	public static void swap(int[] Array,int i,int j){
		int temp=Array[i];
		Array[i]=Array[j];
		Array[j]=temp;
	}

	public static void sort(int[] Array){// ð������
		for(int i=0;i<Array.length;i++){
			for(int j=Array.length-1;j>i;j--){
				if(Array[j]<Array[j-1]){// ���ڵ������Ƚϣ������С���ͽ���
					swap(Array,j,j-1);
				}
			}
		}
	}

	public static int[] randomToArray(){
		int[] Array=new int[8];// ���������Ÿ�����
		for(int i=0;i<Array.length;i++){// �漴�������������������
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
		int[] Array=randomToArray();
		System.out.print("����ǰ������");
		printArray(Array);

		System.out.println();
		sort(Array);
		System.out.print("ð������������");
		printArray(Array);
	}

}
