package classFour;

public class FindMinValuePathInMatrix {

	public static int[] getMinimumPath(int[][] matrix) {
		return getPathFromDPRecord(matrix, computeDPRecord(matrix));
	}

	public static int[][] computeDPRecord(int[][] matrix) {
		int[][] DPMap = new int[matrix.length][matrix[0].length];
		DPMap[0][0] = matrix[0][0];
		for (int i = 1; i != DPMap.length; i++) {
			DPMap[i][0] = matrix[i][0] + DPMap[i - 1][0];
		}
		for (int i = 1; i != DPMap[0].length; i++) {
			DPMap[0][i] = matrix[0][i] + DPMap[0][i - 1];
		}
		for (int i = 1; i != DPMap.length; i++) {
			for (int j = 1; j != DPMap[0].length; j++) {
				DPMap[i][j] = Math.min(DPMap[i - 1][j], DPMap[i][j - 1])
						+ matrix[i][j];
			}
		}
		return DPMap;
	}

	public static int[] getPathFromDPRecord(int[][] matrix, int[][] DPMap) {
		int[] path = new int[matrix.length + matrix[0].length - 1];
		int row = matrix.length - 1;
		int col = matrix[0].length - 1;
		int index = path.length - 1;
		while (row != 0 && col != 0) {
			path[index--] = matrix[row][col];
			if (DPMap[row][col] == DPMap[row - 1][col] + matrix[row][col]) {
				row--;
			} else {
				col--;
			}
		}
		path[index--] = matrix[row][col];
		if (row == 0) {
			for (int i = col - 1; i != -1; i--) {
				path[index--] = matrix[0][i];
			}
		} else {
			for (int i = row - 1; i != -1; i--) {
				path[index--] = matrix[i][0];
			}
		}
		return path;
	}

	public static int getMinPathSum(int[][] matrix) {
		if (matrix.length > matrix[0].length) {
			return getMinPathSumLessCol(matrix);
		} else {
			return getMinPathSumLessRow(matrix);
		}
	}

	public static int getMinPathSumLessCol(int[][] matrix) {
		int[] record = new int[matrix[0].length];
		record[0] = matrix[0][0];
		for (int i = 1; i != matrix[0].length; i++) {
			record[i] = record[i - 1] + matrix[0][i];
		}
		for (int i = 1; i != matrix.length; i++) {
			record[0] = record[0] + matrix[i][0];
			for (int j = 1; j != matrix[0].length; j++) {
				record[j] = Math.min(record[j - 1], record[j]) + matrix[i][j];
			}
		}
		return record[record.length - 1];
	}

	public static int getMinPathSumLessRow(int[][] matrix) {
		int[] record = new int[matrix.length];
		record[0] = matrix[0][0];
		for (int i = 1; i != matrix.length; i++) {
			record[i] = record[i - 1] + matrix[i][0];
		}
		for (int j = 1; j != matrix[0].length; j++) {
			record[0] = record[0] + matrix[0][j];
			for (int i = 1; i != matrix.length; i++) {
				record[i] = Math.min(record[i - 1], record[i]) + matrix[i][j];
			}
		}
		return record[record.length - 1];
	}

	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
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

	public static void printMatrix(int[][] matrix) {
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
		int[][] matrix = generateRandomMatrix(4, 4);
		printMatrix(matrix);
		System.out.println("=====================");

		System.out.print("Min Path: ");
		printPathArray(getMinimumPath(matrix));

		System.out.print("Min Sum: ");
		System.out.println(getMinPathSum(matrix));

	}
}
