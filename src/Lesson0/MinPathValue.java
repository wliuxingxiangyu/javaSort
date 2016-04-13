package Lesson0;

//从矩阵的左上角走到矩阵的右下角，沿途将累加所有经过的数字，求最小的和
public class MinPathValue {

	public static int getMinPathValueSum(int[][] matrix) { // 获得最小路径和
		int[] DPArr = new int[matrix[0].length];// 创建与matrix同列数的一维数组DPArr
		DPArr[0] = matrix[0][0];// 两矩阵左上角第一个元素相等
		for (int i = 1; i != matrix[0].length; i++) {
			DPArr[i] = DPArr[i - 1] + matrix[0][i];// 只按第0行走的路径和
		}
		for (int i = 1; i != matrix.length; i++) {
			DPArr[0] = DPArr[0] + matrix[i][0];// 第0列的累加和，作为本行求和起始值
			for (int j = 1; j != matrix[0].length; j++) {
				DPArr[j] = Math.min(DPArr[j - 1], DPArr[j]) + matrix[i][j];
			}// 一维数组DPArr中路径和=比较小（从左边走值，从上面走值）+本空格值
		}
		return DPArr[DPArr.length - 1];
	}

	public static int[] findMinimumPath(int[][] matrix) {// 查找最小路径
		return generatePathFromDPRecord(matrix, computeDPRecord(matrix));
	}

	public static int[][] computeDPRecord(int[][] matrix) {
		int[][] DPMap = new int[matrix.length][matrix[0].length];// DPMap与matrix同维数
		// matrix.length有几行(Row)，不是总元素数。
		DPMap[0][0] = matrix[0][0];
		for (int i = 1; i != DPMap.length; i++) {
			DPMap[i][0] = matrix[i][0] + DPMap[i - 1][0];// 第0列的累加和，
		}
		for (int i = 1; i != DPMap[0].length; i++) {// 只按第0行走的路径和
			DPMap[0][i] = matrix[0][i] + DPMap[0][i - 1];
		}
		for (int i = 1; i != DPMap.length; i++) {
			for (int j = 1; j != DPMap[0].length; j++) {
				DPMap[i][j] = Math.min(DPMap[i - 1][j], DPMap[i][j - 1])
						+ matrix[i][j];
			}// 二维数组DPMap中路径和=比较小（从左边走值，从上面走值）+本空格值
		}
		return DPMap;
	}

	public static int[] generatePathFromDPRecord(int[][] matrix, int[][] DPMap) {
		int[] path = new int[matrix.length + matrix[0].length - 1];// matrix行数+列数-1
		int row = matrix.length - 1; // matrix行数-1=3:为走到右下角的向右步数
		int col = matrix[0].length - 1;// matrix列数-1=3:为走到右下角的向下步数
		int index = path.length - 1;// matrix行数+列数-2=6
		while (row != 0 && col != 0) { // 非0行，0列进入while
			path[index--] = matrix[row][col];
			if (DPMap[row][col] == DPMap[row - 1][col] + matrix[row][col]) {
				row--;// “上”走过来的，行减一
			} else {
				col--;// “左”走过来的，列减一
			}
		}
		path[index--] = matrix[row][col];
		if (row == 0) { // 第0列
			for (int i = col - 1; i != -1; i--) {
				path[index--] = matrix[0][i];
			}
		} else {// 第0列
			for (int i = row - 1; i != -1; i--) {
				path[index--] = matrix[i][0];
			}
		}
		return path;
	}

	public static int[][] generateMatrix(int rowSize, int colSize) { // 产生随机矩阵
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


	public static void printMatrix(int[][] matrix) { // 打印矩阵
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
