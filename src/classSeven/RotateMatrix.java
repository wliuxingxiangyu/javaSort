package classSeven;

public class RotateMatrix {

	public static void rotateMatrix(int[][] matrix) {
		if (matrix.length != matrix[0].length) {
			return;
		}
		int topLeftRow = 0;
		int topLeftCol = 0;
		int bottomRightRow = matrix.length - 1;
		int bottomRightCol = matrix[0].length - 1;
		while (topLeftRow < bottomRightRow) {
			rotateLevel(matrix, topLeftRow++, topLeftCol++, bottomRightRow--, bottomRightCol--);
		}
	}

	public static void rotateLevel(int[][] matrix, int tLRow, int tLCol,
			int bRRow, int bRCol) {
		int rotateTimes = bRCol - tLCol;
		for (int i = 0; i != rotateTimes; i++) {
			int tmp = matrix[tLRow][tLCol + i];
			matrix[tLRow][tLCol + i] = matrix[bRRow - i][tLCol];
			matrix[bRRow - i][tLCol] = matrix[bRRow][bRCol - i];
			matrix[bRRow][bRCol - i] = matrix[tLRow + i][bRCol];
			matrix[tLRow + i][bRCol] = tmp;
		}
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
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printMatrix(matrix);
		rotateMatrix(matrix);
		System.out.println("======");
		printMatrix(matrix);

	}

}
