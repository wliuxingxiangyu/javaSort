package javaSort;
public class SortBubble{
	public static void swap(int[] Array,int i,int j){
		int temp=Array[i];
		Array[i]=Array[j];
		Array[j]=temp;
	}

	public static void sort(int[] Array){// 冒泡排序
		for(int i=0;i<Array.length;i++){
			for(int j=Array.length-1;j>i;j--){
				if(Array[j]<Array[j-1]){// 相邻的两个比较，后面的小，就交换
					swap(Array,j,j-1);
				}
			}
		}
	}

	public static int[] randomToArray(){
		int[] Array=new int[8];// 构造数组存放浮点数
		for(int i=0;i<Array.length;i++){// 随即产生浮点数，填充数组
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
		System.out.print("排序前的数组");
		printArray(Array);

		System.out.println();
		sort(Array);
		System.out.print("冒泡排序后的数组");
		printArray(Array);
	}

}
