package classFive;

public class FindLongestIncreaseListInMatrix {

	public static int[] getLongestIncreasingList(int[][] matrix) {
		int[][] record = new int[matrix.length][matrix[0].length];
		int max = -1;
		int rowIndex = -1;
		int colIndex = -1;
		// 步骤一，求出以每一个元素为结尾的最长递增链长度并记录下最大长度和位置。
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				int curMax = findProcess(matrix, i, j, record);
				if (max < curMax) {
					max = curMax;
					rowIndex = i;
					colIndex = j;
				}
			}
		}
		// 步骤二，生成最长递增链
		return generateLIL(matrix, record, max, rowIndex, colIndex);
	}

	public static int findProcess(int[][] matrix, int row, int col, int[][] map) {
		int length = 1;
		// 如果当前元素有上边的元素并且上边的元素比自己小。
		if (row > 0 && matrix[row - 1][col] < matrix[row][col]) {
			if (map[row - 1][col] == 0) { // 上元素的record值之前没算过，要递归进行计算
				length = Math.max(length,
						1 + findProcess(matrix, row - 1, col, map));
			} else { // 上元素的record值之前算过，可以直接拿来用
				length = Math.max(length, 1 + map[row - 1][col]);
			}
		}
		// 如果当前元素有左边的元素并且左边的元素比自己小。
		if (col > 0 && matrix[row][col - 1] < matrix[row][col]) {
			if (map[row][col - 1] == 0) { // 左元素的record值之前没算过，要递归进行计算
				length = Math.max(length,
						1 + findProcess(matrix, row, col - 1, map));
			} else { // 左元素的record值之前算过，可以直接拿来用
				length = Math.max(length, 1 + map[row][col - 1]);
			}
		}
		// 如果当前元素有下边的元素并且下边的元素比自己小。
		if (row < matrix.length - 1 && matrix[row + 1][col] < matrix[row][col]) {
			if (map[row + 1][col] == 0) { // 下元素的record值之前没算过，要递归进行计算
				length = Math.max(length,
						1 + findProcess(matrix, row + 1, col, map));
			} else { // 下元素的record值之前算过，可以直接拿来用
				length = Math.max(length, 1 + map[row + 1][col]);
			}
		}
		// 如果当前元素有右边的元素并且左边的元素比自己小。
		if (col < matrix[0].length - 1
				&& matrix[row][col + 1] < matrix[row][col]) {
			if (map[row][col + 1] == 0) { // 右元素的record值之前没算过，要递归进行计算
				length = Math.max(length,
						1 + findProcess(matrix, row, col + 1, map));
			} else { // 右元素的record值之前算过，可以直接拿来用
				length = Math.max(length, 1 + map[row][col + 1]);
			}
		}
		map[row][col] = length; // 记录下当前位置的record
		return length;
	}

	public static int[] generateLIL(int[][] matrix, int[][] record,
			int maxLength, int theRowIndex, int theColIndex) {
		int[] res = new int[maxLength];
		int index = maxLength - 1;
		while (index != -1) {
			res[index--] = matrix[theRowIndex][theColIndex];
			// 有上元素并且上元素满足条件表达式
			if (theRowIndex > 0
					&& matrix[theRowIndex - 1][theColIndex] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex - 1][theColIndex] == record[theRowIndex][theColIndex] - 1) {
				theRowIndex--;
				continue;
			}
			// 有左元素并且左元素满足条件表达式
			if (theColIndex > 0
					&& matrix[theRowIndex][theColIndex - 1] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex][theColIndex - 1] == record[theRowIndex][theColIndex] - 1) {
				theColIndex--;
				continue;
			}
			// 有下元素并且下元素满足条件表达式
			if (theRowIndex < matrix.length - 1
					&& matrix[theRowIndex + 1][theColIndex] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex + 1][theColIndex] == record[theRowIndex][theColIndex] - 1) {
				theRowIndex++;
				continue;
			}
			// 有右元素并且右元素满足条件表达式
			if (theColIndex < matrix[0].length - 1
					&& matrix[theRowIndex][theColIndex + 1] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex][theColIndex + 1] == record[theRowIndex][theColIndex] - 1) {
				theColIndex++;
				continue;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 11, 4, 5 }, { 13, 12, 10, 11 },
				{ 20, 6, 9, 12 }, { 21, 7, 8, 16 }, };
		int[] result = getLongestIncreasingList(matrix);
		for (int i = 0; i != result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

}
