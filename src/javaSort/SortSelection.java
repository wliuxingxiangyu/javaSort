package javaSort;

public class SortSelection{

	public static void swap(int[] Array,int i,int j){
		int temp=Array[i];
		Array[i]=Array[j];
		Array[j]=temp;
	}

	public static void sort(int[] Array){// 选择排序：找最小数，次小数。。
		for(int i=0;i<Array.length;i++){
			int lowIndex=i;// 进入内层for前，假定当前Array[j]为后面无序区得到最小数
			for(int j=Array.length-1;j>i;j--){
				if(Array[j]<Array[lowIndex]){// 比最小数小时，改lowIndex
					lowIndex=j;
				}
			}
			swap(Array,i,lowIndex);// 第i小值归到Array(i)位置
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
		System.out.print("排序前的数组");
		printArray(Array);

		System.out.println();
		sort(Array);
		System.out.print("排序后的数组");
		printArray(Array);

	}
}
