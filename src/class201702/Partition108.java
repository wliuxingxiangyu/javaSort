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
						isPalindrome[i][j] = true;//����i~j�ǻ��ģ�
				}
			}
		}
		List<List<String>>[] palinListLists = (List<List<String>>[]) Array
				.newInstance(List.class, n + 1);
		//������ͬint[] arr;����palinListListsÿ��Ԫ��������List<List<String>>��

		palinListLists[n] = (List) (new ArrayList<List<String>>());
		List<String> emptyList = new ArrayList<>();
		palinListLists[n].add(emptyList);//����ʱ:�����ඨ��һ����list,��n-1�±��palinListsβ���ϼӻ��Ĵ�.
		for (int i = n - 1; i >= 0; i--) {
			palinListLists[i] = (List) (new ArrayList<List<String>>());
			for (int j = i; j < n; j++) {
				if (isPalindrome[i][j]) {//ԭʼ�ַ���s[i]~s[j]Ϊ����
					List<List<String>> palinLists = palinListLists[j + 1];//j+1�±��palinLists��j�±�β��.
					String palinStr = s.substring(i, j + 1);//ԭʼ�ַ���s[i]~s[j]Ϊ����,for�д������Ļ���,
					for (List<String> palinList : palinLists) {
						List<String> newPalinList = new ArrayList<>();
						newPalinList.add(palinStr);//palinStr=��a��
						newPalinList.addAll(palinList);//addAll�ǽ���listβ���ں���,���һ��list.
						//�˲�Ϊ�˽���һ��ѭ���Ļ�����[c,c]�����ں�������µĻ��Ĵ�newPalinList[a,c,c],
						//"addAll(list)"��"add(�ַ���)"���ܴ�,����β�Ӽ�������¼���;
						//palinStr=[a],addAll([c,c])��ΪnewPalinList=[a,c,c];
						palinListLists[i].add(newPalinList);//��Ϊ���±�i����,����[a,c,c]����[a,cc]ǰ��.
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
