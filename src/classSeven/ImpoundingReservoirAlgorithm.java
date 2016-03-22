package classSeven;

public class ImpoundingReservoirAlgorithm {

	public static int getRandomNum(int max) {
		return (int) (Math.random() * max) + 1;
	}

	public static int[] getRandomKNums(int k, int max) {
		if (max < 1 || k < 1) {
			return null;
		}
		int[] res = new int[Math.min(k, max)];
		for (int i = 0; i != res.length; i++) {
			res[i] = i + 1;
		}
		for (int i = k + 1; i < max + 1; i++) {
			if (getRandomNum(i) <= k) {
				res[getRandomNum(k) - 1] = i;
			}
		}
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] result = getRandomKNums(10, 10000);
		printArray(result);
	}

}
