package classFour;

public class CowNumberProblem {

	public static int getCowNumSolution1(int nYear) {
		if (nYear < 1) {
			return 0;
		}
		if (nYear == 1 || nYear == 2 || nYear == 3) {
			return nYear;
		}
		return getCowNumSolution1(nYear - 1) + getCowNumSolution1(nYear - 3);
	}

	public static int getCowNumSolution2(int nYear) {
		if (nYear < 1) {
			return 0;
		}
		if (nYear == 1 || nYear == 2 || nYear == 3) {
			return nYear;
		}
		int p = 3;
		int pp = 2;
		int ppp = 1;
		for (int i = 4; i != nYear; i++) {
			int tmp1 = p;
			int tmp2 = pp;
			p = p + ppp;
			pp = tmp1;
			ppp = tmp2;
		}
		return p + ppp;
	}

	public static int getCowNumSolution3(int nYear) {
		if (nYear < 1) {
			return 0;
		}
		if (nYear == 1 || nYear == 2 || nYear == 3) {
			return nYear;
		}
		int[][] matrix = { { 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } };
		int[][] resultMatrix = matrixPow(matrix, nYear - 3);
		return 3 * resultMatrix[0][0] + 2 * resultMatrix[1][0]
				+ resultMatrix[2][0];
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
		for (int i = 0; i != 31; i++) {
			System.out.println(getCowNumSolution1(i));
			System.out.println(getCowNumSolution2(i));
			System.out.println(getCowNumSolution3(i));
			System.out.println("====================");
		}

	}

}
