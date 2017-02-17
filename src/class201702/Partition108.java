package class201702;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Partition108 {
	public static List<List<String>> partition(String s) {// partition
		int n = s.length();
		boolean[][] isPalindrome = new boolean[n][n];
		for (int i = 0; i < n; i++)
			isPalindrome[i][i] = true;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					if (j - i < 2 || isPalindrome[i + 1][j - 1])
						isPalindrome[i][j] = true;//表明i~j是回文，
				}
			}
		}
		List<List<String>>[] palinListLists = (List<List<String>>[]) Array
				.newInstance(List.class, n + 1);
		//定义如同int[] arr;这里palinListLists每个元素类型是List<List<String>>。

		palinListLists[n] = (List) (new ArrayList<List<String>>());
		List<String> emptyList = new ArrayList<>();
		palinListLists[n].add(emptyList);//定义时:在最后多定义一个空list,供n-1下标的palinLists尾加上加回文串.
		for (int i = n - 1; i >= 0; i--) {
			palinListLists[i] = (List) (new ArrayList<List<String>>());
			for (int j = i; j < n; j++) {
				if (isPalindrome[i][j]) {//原始字符串s[i]~s[j]为回文
					List<List<String>> palinLists = palinListLists[j + 1];//j+1下标的palinLists供j下标尾加.
					String palinStr = s.substring(i, j + 1);//原始字符串s[i]~s[j]为回文,for中搭配后面的回文,
					for (List<String> palinList : palinLists) {
						List<String> newPalinList = new ArrayList<>();
						newPalinList.add(palinStr);//palinStr=‘a’
						newPalinList.addAll(palinList);//addAll是将旧list尾加在后面,组成一个list.
						//此步为了将上一次循环的回文字[c,c]串加在后面组成新的回文串newPalinList[a,c,c],
						//"addAll(list)"比"add(字符串)"功能大,可以尾加集合组成新集合;
						//palinStr=[a],addAll([c,c])后为newPalinList=[a,c,c];
						palinListLists[i].add(newPalinList);//因为有下标i控制,所以[a,c,c]总在[a,cc]前面.
					}
				}
			}
		}
		return palinListLists[0];
	}

	public static void main(String[] args) {
		String str = "abbacc";
		int count = 0;
		List<List<String>> res = partition(str);
		for (int i = 0; i < res.size(); i++) {
			System.out.println((count++) + "," + res.get(i));
		}
		System.out.println("end");
	}
}
