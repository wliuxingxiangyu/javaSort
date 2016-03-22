package classFour;

public class HowManyPath {

	public static long solution1(int downT, int rightT) {
		return process1(downT, rightT, 0, 0);
	}

	public static long process1(int row, int col, int ri, int ci) {
		if (row == ri || col == ci) {
			return 1;
		}
		return process1(row, col, ri + 1, ci) + process1(row, col, ri, ci + 1);
	}

	public static long solution2(int downT, int rightT) {
		long[][] dpMap = new long[downT + 1][rightT + 1];
		for (int j = 0; j != rightT + 1; j++) {
			dpMap[0][j] = 1;
		}
		for (int i = 1; i != downT + 1; i++) {
			dpMap[i][0] = 1;
		}
		for (int i = 1; i != downT + 1; i++) {
			for (int j = 1; j != rightT + 1; j++) {
				dpMap[i][j] = dpMap[i - 1][j] + dpMap[i][j - 1];
			}
		}
		return dpMap[downT][rightT];
	}

	public static long solution3(int downT, int rightT) {
		long[] dpArr = new long[Math.min(downT, rightT) + 1];
		for (int i = 0; i != dpArr.length; i++) {
			dpArr[i] = 1;
		}
		int computeTimes = Math.max(downT, rightT) + 1;
		for (int i = 1; i != computeTimes; i++) {
			for (int j = 1; j != dpArr.length; j++) {
				dpArr[j] += dpArr[j - 1];
			}
		}
		return dpArr[dpArr.length - 1];
	}

	public static long solution4(int downT, int rightT) {
		int less = Math.min(downT, rightT);
		int more = Math.max(downT, rightT);
		int i = 1;
		long mP = 1;
		long mA = 1;
		for (; i != less + 1; i++) {
			mP *= i;
		}
		for (; i != more + 1; i++) {
			mP *= i;
			mA *= i;
		}
		for (; i != less + more + 1; i++) {
			mA *= i;
			if (mA % mP == 0) {
				i++;
				break;
			}
		}
		mA /= mP;
		for (; i != less + more + 1; i++) {
			mA *= i;
		}
		return mA;
	}

	public static void main(String[] args) {
		int downT = 13;
		int rightT = 10;
		System.out.println("Path Number:" + solution1(downT, rightT));
		System.out.println("Path Number:" + solution2(downT, rightT));
		System.out.println("Path Number:" + solution3(downT, rightT));
		System.out.println("Path Number:" + solution4(downT, rightT));

	}
}
