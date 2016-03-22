package javaSort;

public class OneForPrint2DArr {
	public static void main(String[] args) throws Exception {
		int[][] a = { { 1, 2, 3 }, { 4, 5} };
		for (int i = 0, j = 0; i < a.length;) {  //i控制行//j控制列
			System.out.print(a[i][j]);
			j++;
			if (j >= a[i].length) {//打印1个元素,不让循环变量i++,走出思维定式(执行一次循环体,就i++)。
				i++;//i控制行,换行
				j = 0;//j控制列,拉回到第0列
				System.out.println();
			}
		}
	}
}
