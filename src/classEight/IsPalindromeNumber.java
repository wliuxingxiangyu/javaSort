package classEight;

public class IsPalindromeNumber {

    public static boolean isPalindrome(int x){
		if (x < 0){
			return false;
		}
    	int orderOfMagnitude = 1;
		while (x / orderOfMagnitude >= 10) {
			orderOfMagnitude *= 10;
		}
		while (x != 0) {
			int left = x / orderOfMagnitude;
			int right = x % 10;
			if (left != right){
				return false;
			}
			x = (x % orderOfMagnitude) / 10;
			orderOfMagnitude /= 100;
		} 
		return true;
    }
    
	public static void main(String[] args) {
		int test = 2137447312;
		System.out.println(isPalindrome(test));
	}
	
}
