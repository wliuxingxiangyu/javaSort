package classFive;

public class LongestCommonSubSequence {

	public static String getLCSubSequence(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chars1 = str1.toCharArray();
		char[] chars2 = str2.toCharArray();
		return generateLCSequenceFromDPMap(generateDPMap(chars1, chars2),
				chars1, chars2);
	}

	public static int[][] generateDPMap(char[] chars1, char[] chars2) {
		int[][] dpMap = new int[chars1.length][chars2.length];
		dpMap[0][0] = chars1[0] == chars2[0] ? 1 : 0;//dpMap[0][0]无上无左,单独赋值
		for (int i = 1; i != chars1.length; i++) {//对dpMap[i][0]第0列赋值
			dpMap[i][0]=Math.max(dpMap[i-1][0],chars1[i]==chars2[0] ? 1: 0);
		}
		for (int j = 1; j != chars2.length; j++) {//对dpMap[0][j]第0行赋值
			dpMap[0][j]=Math.max(dpMap[0][j-1],chars2[j]==chars1[0] ? 1: 0);
		}
		for (int i = 1; i != chars1.length; i++) {
			for (int j = 1; j != chars2.length; j++) {
				int tmp = Math.max(dpMap[i - 1][j], dpMap[i][j - 1]);
				if (chars1[i] != chars2[j]) {//不同时,max(从左边,从上边)
					dpMap[i][j] =tmp;
				}else{//相同时,max(从 左上角+1,tmp)。。加1是因为两串都要算上相同的元素.
					dpMap[i][j] = Math.max(dpMap[i - 1][j - 1] + 1, tmp);
				}
				
			}
		}
		return dpMap;
	}

	public static String generateLCSequenceFromDPMap(int[][] dpMap,
			char[] chars1, char[] chars2) {
		int row = dpMap.length - 1;
		int col = dpMap[0].length - 1;
		char[] result = new char[dpMap[row][col]];
		while (row != 0 && col != 0) {
			int cur = dpMap[row][col];
			if (cur > dpMap[row - 1][col] && cur > dpMap[row][col - 1]) {
				result[cur - 1] = chars1[row];
				row--;
				col--;
			} else {
				if (dpMap[row - 1][col] > dpMap[row][col - 1]) {
					row--;
				} else {
					col--;
				}
			}
		}
		if (dpMap[row][col] == 1) {
			result[0] = row == 0 ? chars1[0] : chars2[0];
		}
		return String.valueOf(result);
	}

	public static int getLCSubSequenceLength(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return 0;
		}
		char[] chars1 = str1.length() >= str2.length() ? str1.toCharArray()
				: str2.toCharArray();
		char[] chars2 = str1.length() < str2.length() ? str1.toCharArray()
				: str2.toCharArray();
		int[] record = new int[chars2.length];
		record[0] = chars1[0] == chars2[0] ? 1 : 0;
		for (int i = 1; i != chars2.length; i++) {
			record[i] = Math.max(record[i - 1], chars1[0] == chars2[i] ? 1 : 0);
		}
		for (int i = 1; i != chars1.length; i++) {
			int pre = record[0];
			record[0] = Math.max(pre, chars1[i] == chars2[0] ? 1 : 0);
			for (int j = 1; j != chars2.length; j++) {
				int nextPre = record[j];
				int tmp = Math.max(nextPre, record[j - 1]);
				record[j] = chars1[i] == chars2[j] ? Math.max(pre + 1, tmp) : tmp;
				pre = nextPre;
			}
		}
		return record[record.length - 1];
	}

	public static void main(String[] args) {
		String A = "cabbeaf";
		char[] cArr=A.toCharArray();
		String B = null ;
		for (int i = A.length()-1; i >=0 ; i--) {
			B += cArr[i];
		}
		
//		String A = "1A2C3D4B56";
//		String B = "B1D23CA45B6A";
		System.out.println("LCSubsequence: " + getLCSubSequence(A, B));
		System.out.println("Length: " + getLCSubSequenceLength(A, B));

	}
}