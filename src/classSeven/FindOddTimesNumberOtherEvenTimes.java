package classSeven;

public class FindOddTimesNumberOtherEvenTimes {

	public static int findOddTimesNumberOtherEvenTimes(int[] array) {
		int eORes = 0;
		for (int curNum : array) {
			eORes ^= curNum;
		}
		return eORes;
	}

	public static void printOddTimesTwoNumbersOtherEvenTimes(int[] array) {
		int eORes = 0;
		for (int curNum : array) {
			eORes ^= curNum;
		}
		if (eORes == 0) {
			System.out.println("The array is invalid.");
			return;
		}
		int mostRightOne = eORes & (~eORes + 1);
		int eoResHas1 = 0;
		for (int curNum : array) {
			if ((curNum & mostRightOne) != 0) {
				eoResHas1 ^= curNum;
			}
		}
		System.out.println(eoResHas1 + " , " + (eORes ^ eoResHas1));
	}

	public static void main(String[] args) {
		int[] test1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		System.out.println(findOddTimesNumberOtherEvenTimes(test1));

		int[] test2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesTwoNumbersOtherEvenTimes(test2);

	}

}
