package classFive;

public class FindLongestIncreaseListInMatrix {

	public static int[] getLongestIncreasingList(int[][] matrix) {
		int[][] record = new int[matrix.length][matrix[0].length];
		int max = -1;
		int rowIndex = -1;
		int colIndex = -1;
		// ����һ�������ÿһ��Ԫ��Ϊ��β������������Ȳ���¼����󳤶Ⱥ�λ�á�
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
		// ������������������
		return generateLIL(matrix, record, max, rowIndex, colIndex);
	}

	public static int findProcess(int[][] matrix, int row, int col, int[][] map) {
		int length = 1;
		// �����ǰԪ�����ϱߵ�Ԫ�ز����ϱߵ�Ԫ�ر��Լ�С��
		if (row > 0 && matrix[row - 1][col] < matrix[row][col]) {
			if (map[row - 1][col] == 0) { // ��Ԫ�ص�recordֵ֮ǰû�����Ҫ�ݹ���м���
				length = Math.max(length,
						1 + findProcess(matrix, row - 1, col, map));
			} else { // ��Ԫ�ص�recordֵ֮ǰ���������ֱ��������
				length = Math.max(length, 1 + map[row - 1][col]);
			}
		}
		// �����ǰԪ������ߵ�Ԫ�ز�����ߵ�Ԫ�ر��Լ�С��
		if (col > 0 && matrix[row][col - 1] < matrix[row][col]) {
			if (map[row][col - 1] == 0) { // ��Ԫ�ص�recordֵ֮ǰû�����Ҫ�ݹ���м���
				length = Math.max(length,
						1 + findProcess(matrix, row, col - 1, map));
			} else { // ��Ԫ�ص�recordֵ֮ǰ���������ֱ��������
				length = Math.max(length, 1 + map[row][col - 1]);
			}
		}
		// �����ǰԪ�����±ߵ�Ԫ�ز����±ߵ�Ԫ�ر��Լ�С��
		if (row < matrix.length - 1 && matrix[row + 1][col] < matrix[row][col]) {
			if (map[row + 1][col] == 0) { // ��Ԫ�ص�recordֵ֮ǰû�����Ҫ�ݹ���м���
				length = Math.max(length,
						1 + findProcess(matrix, row + 1, col, map));
			} else { // ��Ԫ�ص�recordֵ֮ǰ���������ֱ��������
				length = Math.max(length, 1 + map[row + 1][col]);
			}
		}
		// �����ǰԪ�����ұߵ�Ԫ�ز�����ߵ�Ԫ�ر��Լ�С��
		if (col < matrix[0].length - 1
				&& matrix[row][col + 1] < matrix[row][col]) {
			if (map[row][col + 1] == 0) { // ��Ԫ�ص�recordֵ֮ǰû�����Ҫ�ݹ���м���
				length = Math.max(length,
						1 + findProcess(matrix, row, col + 1, map));
			} else { // ��Ԫ�ص�recordֵ֮ǰ���������ֱ��������
				length = Math.max(length, 1 + map[row][col + 1]);
			}
		}
		map[row][col] = length; // ��¼�µ�ǰλ�õ�record
		return length;
	}

	public static int[] generateLIL(int[][] matrix, int[][] record,
			int maxLength, int theRowIndex, int theColIndex) {
		int[] res = new int[maxLength];
		int index = maxLength - 1;
		while (index != -1) {
			res[index--] = matrix[theRowIndex][theColIndex];
			// ����Ԫ�ز�����Ԫ�������������ʽ
			if (theRowIndex > 0
					&& matrix[theRowIndex - 1][theColIndex] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex - 1][theColIndex] == record[theRowIndex][theColIndex] - 1) {
				theRowIndex--;
				continue;
			}
			// ����Ԫ�ز�����Ԫ�������������ʽ
			if (theColIndex > 0
					&& matrix[theRowIndex][theColIndex - 1] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex][theColIndex - 1] == record[theRowIndex][theColIndex] - 1) {
				theColIndex--;
				continue;
			}
			// ����Ԫ�ز�����Ԫ�������������ʽ
			if (theRowIndex < matrix.length - 1
					&& matrix[theRowIndex + 1][theColIndex] < matrix[theRowIndex][theColIndex]
					&& record[theRowIndex + 1][theColIndex] == record[theRowIndex][theColIndex] - 1) {
				theRowIndex++;
				continue;
			}
			// ����Ԫ�ز�����Ԫ�������������ʽ
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
