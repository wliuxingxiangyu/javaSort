package classSeven;

public class GetMaxSumSubMatrix {

	public static int findMaxSumSubMatrix(int[][] test) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != test.length; i++) {
			int[] sumArr = new int[test[0].length];
			for (int j = i; j != test.length; j++) {
				int curMax = 0;
				for (int k = 0; k != sumArr.length; k++) {
					sumArr[k] += test[j][k];
					curMax += sumArr[k];
					if (curMax < 0) {
						curMax = 0;
					}
					if (curMax > max) {
						max = curMax;
					}
				}
			}
		}
		return max;
	}

	public static int[][] getRandomMatrix(int row, int col) {
		int[][] matrix = new int[row][col];
		for (int i = 0; i != row; i++) {
			for (int j = 0; j != col; j++) {
				matrix[i][j] = (int) (Math.random() * 201) - 100;
			}
		}
		return matrix;
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
		int rowNumber = 3;
		int colNumber = 3;
		int[][] matrix = getRandomMatrix(rowNumber, colNumber);
		printMatrix(matrix);
		System.out.println(findMaxSumSubMatrix(matrix));
	}

}
