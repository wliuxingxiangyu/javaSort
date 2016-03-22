package classFive;

public class AToBInsertDeleteReplace {

	public static int getMinCost(String str1, String str2, int insertCost,
			int deleteCost, int replaceCost) {
		str1 = str1 == null ? "" : str1;
		str2 = str2 == null ? "" : str2;
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		int[][] dpMap = new int[charArr1.length + 1][charArr2.length + 1];
		for (int i = 1; i != dpMap.length; i++) {
			dpMap[i][0] = deleteCost * i;
		}
		for (int i = 1; i != dpMap[0].length; i++) {
			dpMap[0][i] = insertCost * i;
		}
		for (int i = 1; i != dpMap.length; i++) {
			for (int j = 1; j != dpMap[0].length; j++) {
				int insert = dpMap[i][j - 1] + insertCost;
				int delete = dpMap[i - 1][j] + deleteCost;
				int replace = 0;
				if (charArr1[i - 1] == charArr2[j - 1]) {
					replace = dpMap[i - 1][j - 1];
				} else {
					replace = dpMap[i - 1][j - 1] + replaceCost;
				}
				dpMap[i][j] = Math.min(Math.min(insert, delete), replace);
			}
		}
		printMatrix(dpMap);
		return dpMap[dpMap.length - 1][dpMap[0].length - 1];
	}

	public static int getMinCostBetter(String str1, String str2,
			int insertCost, int deleteCost, int replaceCost) {
		str1 = str1 == null ? "" : str1;
		str2 = str2 == null ? "" : str2;
		if (str1.length() < str2.length()) {
			int tmp = insertCost;
			insertCost = deleteCost;
			deleteCost = tmp;
		}
		char[] charArrLong = str1.length() >= str2.length() ? str1
				.toCharArray() : str2.toCharArray();
		char[] charArrShort = str1.length() < str2.length() ? str1
				.toCharArray() : str2.toCharArray();
		int[] record = new int[charArrShort.length + 1];
		for (int i = 1; i != record.length; i++) {
			record[i] = insertCost * i;
		}
		for (int i = 1; i != charArrLong.length + 1; i++) {
			int previous = record[0];
			record[0] = deleteCost * i;
			for (int j = 1; j != record.length; j++) {
				int insert = record[j - 1] + insertCost;
				int delete = record[j] + deleteCost;
				int replace = 0;
				if (charArrLong[i - 1] == charArrShort[j - 1]) {
					replace = previous;
				} else {
					replace = previous + replaceCost;
				}
				previous = record[j];
				record[j] = Math.min(Math.min(insert, delete), replace);
			}
		}
		return record[record.length - 1];
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
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(getMinCost(str1, str2, 5, 3, 2));
		System.out.println(getMinCostBetter(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(getMinCost(str1, str2, 5, 3, 2));
		System.out.println(getMinCostBetter(str1, str2, 5, 3, 2));

	}

}
