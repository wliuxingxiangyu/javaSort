package Lesson0;

//�Ӿ�������Ͻ��ߵ���������½ǣ���;���ۼ����о��������֣�����С�ĺ�
public class MinPathValue {

	public static int getMinPathValueSum(int[][] matrix) { // �����С·����
		int[] DPArr = new int[matrix[0].length];// ������matrixͬ������һά����DPArr
		DPArr[0] = matrix[0][0];// ���������Ͻǵ�һ��Ԫ�����
		for (int i = 1; i != matrix[0].length; i++) {
			DPArr[i] = DPArr[i - 1] + matrix[0][i];// ֻ����0���ߵ�·����
		}
		for (int i = 1; i != matrix.length; i++) {
			DPArr[0] = DPArr[0] + matrix[i][0];// ��0�е��ۼӺͣ���Ϊ���������ʼֵ
			for (int j = 1; j != matrix[0].length; j++) {
				DPArr[j] = Math.min(DPArr[j - 1], DPArr[j]) + matrix[i][j];
			}// һά����DPArr��·����=�Ƚ�С���������ֵ����������ֵ��+���ո�ֵ
		}
		return DPArr[DPArr.length - 1];
	}

	public static int[] findMinimumPath(int[][] matrix) {// ������С·��
		return generatePathFromDPRecord(matrix, computeDPRecord(matrix));
	}

	public static int[][] computeDPRecord(int[][] matrix) {
		int[][] DPMap = new int[matrix.length][matrix[0].length];// DPMap��matrixͬά��
		// matrix.length�м���(Row)��������Ԫ������
		DPMap[0][0] = matrix[0][0];
		for (int i = 1; i != DPMap.length; i++) {
			DPMap[i][0] = matrix[i][0] + DPMap[i - 1][0];// ��0�е��ۼӺͣ�
		}
		for (int i = 1; i != DPMap[0].length; i++) {// ֻ����0���ߵ�·����
			DPMap[0][i] = matrix[0][i] + DPMap[0][i - 1];
		}
		for (int i = 1; i != DPMap.length; i++) {
			for (int j = 1; j != DPMap[0].length; j++) {
				DPMap[i][j] = Math.min(DPMap[i - 1][j], DPMap[i][j - 1])
						+ matrix[i][j];
			}// ��ά����DPMap��·����=�Ƚ�С���������ֵ����������ֵ��+���ո�ֵ
		}
		return DPMap;
	}

	public static int[] generatePathFromDPRecord(int[][] matrix, int[][] DPMap) {
		int[] path = new int[matrix.length + matrix[0].length - 1];// matrix����+����-1
		int row = matrix.length - 1; // matrix����-1=3:Ϊ�ߵ����½ǵ����Ҳ���
		int col = matrix[0].length - 1;// matrix����-1=3:Ϊ�ߵ����½ǵ����²���
		int index = path.length - 1;// matrix����+����-2=6
		while (row != 0 && col != 0) { // ��0�У�0�н���while
			path[index--] = matrix[row][col];
			if (DPMap[row][col] == DPMap[row - 1][col] + matrix[row][col]) {
				row--;// ���ϡ��߹����ģ��м�һ
			} else {
				col--;// �����߹����ģ��м�һ
			}
		}
		path[index--] = matrix[row][col];
		if (row == 0) { // ��0��
			for (int i = col - 1; i != -1; i--) {
				path[index--] = matrix[0][i];
			}
		} else {// ��0��
			for (int i = row - 1; i != -1; i--) {
				path[index--] = matrix[i][0];
			}
		}
		return path;
	}

	public static int[][] generateMatrix(int rowSize, int colSize) { // �����������
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 10);
			}
		}
		return result;
	}


	public static void printMatrix(int[][] matrix) { // ��ӡ����
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printPathArray(int[] array) {
		for (int i = 0; i != array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[][] matrix2 = { { 1, 3, 2, 1 }, { 0, 5, 1, 9 },
				{ 8, 0, 0, 7 }, { 6, 7, 1, 3 } };
		// int[][] matrix = generateMatrix(4, 4);
		printMatrix(matrix2);
		System.out.println("getMinPathValueSum="+ getMinPathValueSum(matrix2));
		System.out.println("==============");
		printPathArray(findMinimumPath(matrix2));
	}
}
