package classSeven;

public class FactorialProblem {

	public static int getZerosNum(int num) {
		if (num < 0) {
			return 0;
		}
		int result = 0;
		for (int i = 5; i < num + 1; i = i + 5) {
			int cur = i;
			while (cur % 5 == 0) {
				result++;
				cur /= 5;
			}
		}
		return result;
	}

	public static int getZerosNumBetter(int num) {
		if (num < 0) {
			return 0;
		}
		int result = 0;
		while (num != 0) {
			result += num / 5;
			num /= 5;
		}
		return result;
	}

	public static int getMostRightOneIndex1(int num) {
		if (num < 1) {
			return -1;
		}
		int result = 0;
		while (num != 0) {
			num >>>= 1;
			result += num;
		}
		return result;
	}

	public static int getMostRightOneIndex2(int num) {
		if (num < 1) {
			return -1;
		}
		int oneNum = 0;
		int tmp = num;
		while (tmp != 0) {
			if ((tmp & 1) != 0) {
				oneNum++;
			}
			tmp >>>= 1;
		}
		return num - oneNum;
	}

	public static void main(String[] args) {
		int num = 1000000000;

		System.out.println(getZerosNumBetter(num));
		System.out.println(getZerosNum(num));

		System.out.println(getMostRightOneIndex1(num));
		System.out.println(getMostRightOneIndex2(num));

	}

}
