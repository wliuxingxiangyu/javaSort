package classSeven;

public class PrintingMatrixInSpiralOrder {

	public static void printMatrixBySpiralOrder(int[][] test) {
		int topLeftRow = 0;
		int topLeftCol = 0;
		int bottomRightRow = test.length - 1;
		int bottomRightCol = test[0].length - 1;
		while (topLeftRow <= bottomRightRow && topLeftCol <= bottomRightCol) {
			if (topLeftRow == bottomRightRow) {
				for (int i = topLeftCol; i <= bottomRightCol; i++) {
					System.out.print(test[topLeftRow][i] + " ");
				}
			} else if (topLeftCol == bottomRightCol) {
				for (int i = topLeftRow; i <= bottomRightRow; i++) {
					System.out.print(test[i][topLeftCol] + " ");
				}
			} else {
				int curCol = topLeftCol;
				while (curCol != bottomRightCol) {
					System.out.print(test[topLeftRow][curCol] + " ");
					curCol++;
				}
				int curRow = topLeftRow;
				while (curRow != bottomRightRow) {
					System.out.print(test[curRow][bottomRightCol] + " ");
					curRow++;
				}
				while (curCol != topLeftCol) {
					System.out.print(test[bottomRightRow][curCol] + " ");
					curCol--;
				}
				while (curRow != topLeftRow) {
					System.out.print(test[curRow][topLeftCol] + " ");
					curRow--;
				}
			}
			topLeftRow++;
			topLeftCol++;
			bottomRightRow--;
			bottomRightCol--;
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printMatrixBySpiralOrder(matrix);
	}

}
