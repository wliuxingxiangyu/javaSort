package nowcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RevsesWordsInLine {

	public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		String str =sc.nextLine();
		String str = "Hi, I come from china!";//Êä³öchina! from come I Hi,
		
		if (str==null||str.equals("")) {
			return ;
		}
		// ·Ö¸î·û
		char[] splits = " ".toCharArray();
		Set<Character> splitCharSet = new HashSet<Character>();
//		for (char split : splits) {
//			splitCharSet.add(split);
//		}
		for (int i = 0; i < splits.length; i++) {
			splitCharSet.add(splits[i]);
			
		}
//		Set.Entry<Character> entry:s
		StringBuilder reverseStr = new StringBuilder();
		StringBuilder rsIndex = new StringBuilder();
		Character indexChar;
		for (int i = str.length() - 1; i > -1; i--) {
			indexChar = Character.valueOf(str.charAt(i));
			if (!splitCharSet.contains(indexChar)) {
				rsIndex.append(indexChar);
			} else {
				reverseStr.append(rsIndex.reverse()).append(indexChar);
				rsIndex.delete(0, rsIndex.length());
			}
		}
		reverseStr.append(rsIndex.reverse());
		System.out.println(reverseStr);

	}

}
