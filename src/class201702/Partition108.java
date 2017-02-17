package class201702;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Partition108 {
	public static List<List<String>> partition(String s) {
		if(s == null || s.length() == 0) return new ArrayList<List<String>>();
		int nLen = s.length();
		boolean[][] dp = new boolean[nLen][nLen];
		for(int i = 0; i< nLen; i++) dp[i][i] = true;
		for(int i = nLen - 1; i >= 0; i--)
			for(int j = i + 1; j < nLen ; j++) {
				if(s.charAt(i) == s.charAt(j)) {
					if(j - i < 2 || dp[i + 1][j - 1]) dp[i][j] = true;
				}
			}
		//dp数组赋值完毕，
		List<List<String>>[] ret =(List<List<String>>[]) Array.newInstance
				(List.class, nLen + 1);
		ret[nLen] = new ArrayList<List<String>>();
		ret[nLen].add(new ArrayList<String>());
		for(int i = nLen - 1; i >= 0; i--) {
			ret[i] = new ArrayList<List<String>>();
			for(int j = i; j < nLen; j++) {
				if(!dp[i][j]) continue;
				
				String str = s.substring(i, j + 1);
				List<List<String>> lists = ret[j + 1];
	            for(List cur : lists) {
					List<String> temp = new ArrayList<String>(cur);
					temp.add(str);//C++版的是往头插，而add是往尾追加,此处需要改动。
					ret[i].add(temp);
				}
			}
		}
		return ret[0];
	}

	public static void main(String[] args) {
		String str="abbacc";
		int count=0;
		List<List<String>> res= partition(str);
		for (int i = 0; i < res.size(); i++) {
			System.out.println((count++)+","+res.get(i));
		}
		System.out.println("end");
	}
}
