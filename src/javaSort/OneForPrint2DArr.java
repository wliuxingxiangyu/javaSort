package javaSort;

public class OneForPrint2DArr {
	public static void main(String[] args) throws Exception {
		int[][] a = { { 1, 2, 3 }, { 4, 5} };
		for (int i = 0, j = 0; i < a.length;) {  //i������//j������
			System.out.print(a[i][j]);
			j++;
			if (j >= a[i].length) {//��ӡ1��Ԫ��,����ѭ������i++,�߳�˼ά��ʽ(ִ��һ��ѭ����,��i++)��
				i++;//i������,����
				j = 0;//j������,���ص���0��
				System.out.println();
			}
		}
	}
}
