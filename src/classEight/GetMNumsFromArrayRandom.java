package classEight;

public class GetMNumsFromArrayRandom {

	public static int[] getRandomMNumsFromArray(int[] arr, int m) {
		m = Math.min(arr.length, m);
		int[] res = new int[m];
		int randomIndex = -1;
		for (int i = 0; i != res.length; i++) {
			randomIndex = (int) (Math.random() * (arr.length - i));
			res[i] = arr[randomIndex];
			swap(arr, randomIndex, arr.length - i - 1);
		}
		return res;
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		int m = 4;
		printArray(getRandomMNumsFromArray(arr, m));

	}

}
