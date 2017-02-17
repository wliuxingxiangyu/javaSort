package class201702;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
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
						isPalindrome[i][j] = true;//����i~j�ǻ��ģ�
				}
			}
		}
		List<List<String>>[] palindromes = (List<List<String>>[]) Array
				.newInstance(List.class, n + 1);

		palindromes[n] = (List) (new LinkedList<List<String>>());
		List<String> emptyList = new LinkedList<>();
		palindromes[n].add(emptyList);
		for (int i = n - 1; i >= 0; i--) {
			palindromes[i] = (List) (new LinkedList<List<String>>());
			for (int j = i; j < n; j++) {
				if (isPalindrome[i][j]) {
					List<List<String>> palinLists = palindromes[j + 1];
					String substring = s.substring(i, j + 1);//ԭʼ�ַ���[i]~[j+1]
					for (List<String> palinList : palinLists) {
						List<String> newPalinList = new LinkedList<>();
						newPalinList.add(substring);//substring=��c��
						newPalinList.addAll(palinList);
						//�˲�Ϊ�˽���һ��ѭ���Ļ�����[c,c]�����ں�������µĻ��Ĵ�newPalinList[a,c,c],
						//addAll��add���ܴ�,����β�Ӽ�������¼���.
						//newList=[a],addAll([c,c])��ΪnewList=[a,c,c];
						palindromes[i].add(newPalinList);
					}
				}
			}
		}
		return palindromes[0];
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
