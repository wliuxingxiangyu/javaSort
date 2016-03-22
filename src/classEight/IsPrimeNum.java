package classEight;

public class IsPrimeNum {

	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		int start = 2;
		int end = (int) Math.sqrt((double) (num));
		while (start < end) {
			if (num % start == 0) {
				return false;
			}
			start++;
		}
		return true;
	}

	public static void main(String[] args) {
		int num = 479001599;
		System.out.println(isPrime(num));
	}
}
