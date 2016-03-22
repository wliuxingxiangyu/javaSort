package javaSort;

import classOne.IsPalindromeLinkedList;

public class Y3Palindrome {//回文数 最好的方法:先模10后除10,得到翻转数
	public static Boolean IsPalindrome(int n){
		if(n/10==0) return true;
		String strN = String.valueOf(n);
		int first = 0,last = strN.length()-1;
		while (first<last) {
			char firstC=strN.charAt(first);
			char lastC=strN.charAt(last);
			if (firstC!=lastC) {
				return false;
			}
			first++;last--;
		}
		return true;
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 10000; i++) {
			if (IsPalindrome(i)) {
				System.out.print(" "+i);
			}
			
		}
	}

}
