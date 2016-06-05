package javaSort;

public class SortQuick{// 相当于以中轴tmp为中心的二叉树,时间复杂度是O(nlogn)
	public static int getMidIndex(int[] Array,int low,int high){// 返回中轴的位置
		int tmp=Array[low]; // 数组的第一个作为中轴
		while(low<high){
			while(low<high&&Array[high]>=tmp){// high大,就减,升序排列
				System.out.println(Array[high] +"比较"+tmp);
				high--;// 等时,不移动数组值,稳定
			}
			Array[low]=Array[high];// 比中轴小的元素移到低端,Array[high]不变

			while(low<high&&Array[low]<tmp){
				System.out.println(Array[low] +"比较"+tmp);
				low++;
			}
			Array[high]=Array[low]; // 比中轴大的元素移到高端,Array[low]不变
			
		}
		Array[low]=tmp;// 中轴元素归位,因为每次和 tmp比较,所以 tmp为大小中轴分界点。
		return low; // 返回中轴的位置
	}

	public static void fastSort(int[] Array,int low,int high){
		if(Array.length<=0){
			System.out.print("待排数组无效");
			return;
		}
		if(low<high){
			int middle=getMidIndex(Array,low,high);// 将Array数组进行一分为二
			fastSort(Array,low,middle);// 对低字表进行递归排序 //中轴分界点-1
			fastSort(Array,middle+1,high);// 对高字表进行递归排序//中轴分界点+1
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
		System.out.print("排序前的数组");
		printArray(Array);

		System.out.println();
		fastSort(Array,0,Array.length-1);
		System.out.print("排序后的数组");
		printArray(Array);
	}
}
