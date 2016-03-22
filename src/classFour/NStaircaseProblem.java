package classFour;

public class NStaircaseProblem {

	public static int getNStairNum1(int nStair) {
		if (nStair < 1) {
			return 0;
		}
		if (nStair == 1 || nStair == 2) {
			return nStair;
		}
		return getNStairNum1(nStair - 1) + getNStairNum1(nStair - 2);
	}

	public static int getNStairNum2(int nStair) {
		if (nStair < 1) {
			return 0;
		}
		if (nStair == 1 || nStair == 2) {
			return nStair;
		}
		int previous = 1;
		int previousPrevious = 1;
		for (int i = 3; i != nStair + 1; i++) {
			int tmp = previous;
			previous = previous + previousPrevious;
			previousPrevious = tmp;
		}
		return previous + previousPrevious;
	}

	public static int getNStairNum3(int nStair) {
		if (nStair < 1) {
			return 0;
		}
		if (nStair == 1 || nStair == 2) {
			return nStair;
		}
		int[][] matrix = { { 1, 1 }, { 1, 0 } };
		int[][] resultMatrix = matrixPow(matrix, nStair - 2);
		return 2 * resultMatrix[0][0] + resultMatrix[1][0];
	}

	public static int[][] matrixPow(int[][] matrix, int times) {
		int[][] result = new int[matrix.length][matrix[0].length];
		for (int i = 0; i != result.length; i++) {
			result[i][i] = 1;
		}
		int[][] tmp = matrix;
		for (; times != 0; times >>= 1) {
			if ((times & 1) != 0) {
				result = matrixMuliMatrix(result, tmp);
			}
			tmp = matrixMuliMatrix(tmp, tmp);
		}
		return result;
	}

	public static int[][] matrixMuliMatrix(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[matrix1.length][matrix2[0].length];
		for (int i = 0; i != matrix2[0].length; i++) {
			for (int j = 0; j != matrix1.length; j++) {
				int resultIJ = 0;
				for (int k = 0; k != matrix2.length; k++) {
					resultIJ += matrix1[i][k] * matrix2[k][j];
				}
				result[i][j] = resultIJ;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		for (int i = 0; i != 30; i++) {
			System.out.println(getNStairNum1(i));
			System.out.println(getNStairNum2(i));
			System.out.println(getNStairNum3(i));
			System.out.println("==============");
		}

	}
}
