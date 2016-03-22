package classSeven;

public class ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int upRow = 0;
		int upCol = 0;
		int downRow = 0;
		int downCol = 0;
		int endRow = matrix.length - 1;
		int endCol = matrix[0].length - 1;
		boolean fromUp = false;
		while (upRow != endRow + 1) {
			printLevel(matrix, upRow, upCol, downRow, downCol, fromUp);
			upRow = upCol == endCol ? upRow + 1 : upRow;
			upCol = upCol == endCol ? upCol : upCol + 1;
			downCol = downRow == endRow ? downCol + 1 : downCol;
			downRow = downRow == endRow ? downRow : downRow + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	public static void printLevel(int[][] matrix, int upRow, int upCol,
			int downRow, int downCol, boolean fromUp) {
		if (fromUp) {
			while (upRow != downRow + 1) {
				System.out.print(matrix[upRow++][upCol--] + " ");
			}
		} else {
			while (downRow != upRow - 1) {
				System.out.print(matrix[downRow--][downCol++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printMatrixZigZag(matrix);

	}

}
