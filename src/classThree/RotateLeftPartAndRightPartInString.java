package classThree;

public class RotateLeftPartAndRightPartInString {

	public static String rotateLeftAndRight1(String str, int apartIndex) {
		if (str == null || apartIndex <= 0 || apartIndex >= str.length()) {
			return str;
		}
		char[] charArr = str.toCharArray();
		reverseCharArr(charArr, 0, apartIndex - 1);
		reverseCharArr(charArr, apartIndex, charArr.length - 1);
		reverseCharArr(charArr, 0, charArr.length - 1);
		return String.valueOf(charArr);
	}

	public static void reverseCharArr(char[] charArr, int start, int end) {
		while (start < end) {
			char tmp = charArr[start];
			charArr[start] = charArr[end];
			charArr[end] = tmp;
			start++;
			end--;
		}
	}

	public static String rotateLeftAndRight2(String str, int apartIndex) {
		if (str == null || apartIndex <= 0 || apartIndex >= str.length()) {
			return str;
		}
		char[] charArr = str.toCharArray();
		int start = 0;
		int end = charArr.length - 1;
		int leftPartSize = apartIndex;
		int rightPartSize = charArr.length - apartIndex;
		int size = Math.min(leftPartSize, rightPartSize);
		int sizeDValue = leftPartSize - rightPartSize;
		while (true) {
			reverseWithSize(charArr, start, end, size);
			if (sizeDValue == 0) {
				break;
			} else if (sizeDValue > 0) {
				start += size;
				leftPartSize = sizeDValue;
			} else {
				end -= size;
				rightPartSize = -sizeDValue;
			}
			size = Math.min(leftPartSize, rightPartSize);
			sizeDValue = leftPartSize - rightPartSize;
		}
		return String.valueOf(charArr);
	}

	public static void reverseWithSize(char[] charArr, int start, int end,
			int size) {
		int rightStart = end - size + 1;
		while (size-- != 0) {
			char tmp = charArr[start];
			charArr[start] = charArr[rightStart];
			charArr[rightStart] = tmp;
			start++;
			rightStart++;
		}
	}

	public static void main(String[] args) {
		String str = "12345ABC";
		System.out.println(rotateLeftAndRight1(str, 5));
		System.out.println(rotateLeftAndRight2(str, 5));

	}

}
