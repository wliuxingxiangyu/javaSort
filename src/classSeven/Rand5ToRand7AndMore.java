package classSeven;

public class Rand5ToRand7AndMore {
	public static int getRandom1To5() {
		return (int) (Math.random() * 5) + 1;// 1,2,3,4,5
	}

	public static int getRandom1To7() {
		int num = 0;
		do {
			num = (getRandom1To5() - 1) * 5 + getRandom1To5() - 1;// 0,1,2,3,...,23,24
		} while (num > 20);
		return num % 7 + 1;
	}

	public static int getRandom01P() {
		// you can change p to what you like, but it must be (0,1)
		double p = 0.83;
		return Math.random() < p ? 0 : 1;
	}

	public static int getRandom01() {
		int randomNum;
		do {
			randomNum = getRandom01P();
		} while (randomNum == getRandom01P());
		return randomNum == 1 ? 1 : 0;
	}

	public static int getRandom0To3() {
		return getRandom01() * 2 + getRandom01();
	}

	public static int getRandom1To6() {
		int num = 0;
		do {
			num = getRandom0To3() * 4 + getRandom0To3();// 0,1,2,3,..,13,14,15
		} while (num > 11);
		return num % 6 + 1;
	}

	public static int getRandom1ToM(int m) {
		return (int) (Math.random() * m) + 1;
	}

	public static int getRandom1ToNFromM(int n, int m) {
		int[] nMSysNum = getMSysNumFromNum(n - 1, m);
		int[] getRandomNum = getRanMSysNumLessN(nMSysNum, m);
		return getNumFromMSysNum(getRandomNum, m) + 1;
	}

	public static int[] getMSysNumFromNum(int value, int m) {
		int[] res = new int[32];
		int index = res.length - 1;
		while (value != 0) {
			res[index--] = value % m;
			value = value / m;
		}
		return res;
	}

	public static int[] getRanMSysNumLessN(int[] nMSysNum, int m) {
		int[] res = new int[nMSysNum.length];
		int start = 0;
		while (nMSysNum[start] == 0) {
			start++;
		}
		do {
			for (int i = start; i != nMSysNum.length; i++) {
				res[i] = getRandom1ToM(m) - 1;
			}
		} while (!isNBiggerM(nMSysNum, res, start));
		return res;
	}

	public static boolean isNBiggerM(int[] n, int[] m, int start) {
		for (; start != n.length; start++) {
			if (n[start] > m[start]) {
				return true;
			}
			if (n[start] < m[start]) {
				return false;
			}
		}
		return true;
	}

	public static int getNumFromMSysNum(int[] mSysNum, int m) {
		int result = 0;
		for (int i = 0; i != mSysNum.length; i++) {
			result = result * m + mSysNum[i];
		}
		return result;
	}

	public static void printCountArray(int[] countArr) {
		for (int i = 0; i != countArr.length; i++) {
			System.out.println(i + " appears " + countArr[i] + " times");
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int testTimes = 1000000;
		int[] countArr1 = new int[8];
		for (int i = 0; i != testTimes; i++) {
			countArr1[getRandom1To7()]++;
		}
		printCountArray(countArr1);

		System.out.println("=====================");

		int[] countArr2 = new int[7];
		for (int i = 0; i != testTimes; i++) {
			countArr2[getRandom1To6()]++;
		}
		printCountArray(countArr2);

		System.out.println("=====================");

		int n = 17;
		int m = 3;
		int[] countArr3 = new int[n + 1];
		for (int i = 0; i != 2000000; i++) {
			countArr3[getRandom1ToNFromM(n, m)]++;
		}
		printCountArray(countArr3);

		System.out.println("=====================");

	}

}
