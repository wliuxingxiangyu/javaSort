package classEight;

public class MaxSizeBorder {

	public static int getMaxSize(int[][] matrix) {
		int[][] rightMap = new int[matrix.length][matrix[0].length];
		int[][] downMap = new int[matrix.length][matrix[0].length];
		setBorderMap(matrix, rightMap, downMap);
		for (int size = Math.min(matrix.length, matrix[0].length); size != 0; size--) {
			if (hasSizeOfBorder(size, rightMap, downMap)) {
				return size;
			}
		}
		return 0;
	}

	public static void setBorderMap(int[][] matrix, int[][] rightMap,
			int[][] downMap) {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		if (matrix[rowSize - 1][colSize - 1] == 1) {
			rightMap[rowSize - 1][colSize - 1] = 1;
			downMap[rowSize - 1][colSize - 1] = 1;
		}
		for (int i = rowSize - 2; i != -1; i--) {
			if (matrix[i][colSize - 1] == 1) {
				rightMap[i][colSize - 1] = 1;
				downMap[i][colSize - 1] = downMap[i + 1][colSize - 1] + 1;
			}
		}
		for (int i = colSize - 2; i != -1; i--) {
			if (matrix[rowSize - 1][i] == 1) {
				rightMap[rowSize - 1][i] = rightMap[rowSize - 1][i + 1] + 1;
				downMap[rowSize - 1][i] = 1;
			}
		}
		for (int i = rowSize - 2; i != -1; i--) {
			for (int j = colSize - 2; j != -1; j--) {
				if (matrix[i][j] == 1) {
					rightMap[i][j] = rightMap[i][j + 1] + 1;
					downMap[i][j] = downMap[i + 1][j] + 1;
				}
			}
		}
	}

	public static boolean hasSizeOfBorder(int size, int[][] rightMap,
			int[][] downMap) {
		for (int i = 0; i != rightMap.length - size + 1; i++) {
			for (int j = 0; j != rightMap[0].length - size + 1; j++) {
				if (rightMap[i][j] >= size 
					&& downMap[i][j] >= size
					&& rightMap[i + size - 1][j] >= size
					&& downMap[i][j + size - 1] >= size) {
					return true;
				}
			}
		}
		return false;
	}

	public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
		int[][] res = new int[rowSize][colSize];
		for (int i = 0; i != rowSize; i++) {
			for (int j = 0; j != colSize; j++) {
				res[i][j] = (int) (Math.random() * 2);
			}
		}
		return res;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = generateRandom01Matrix(7, 8);
		printMatrix(matrix);
		System.out.println();
		System.out.println(getMaxSize(matrix));
	}
}
