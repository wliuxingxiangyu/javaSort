package class201702;

import java.util.ArrayList;

public class H106RestoreIPAddresses {
	/**
	 * 给一个由数字组成的字符串。求出其可能恢复为的所有IP地址。 样例:给出字符串 "25525511135"，所有可能的IP地址为： [
	 * "255.255.11.135", "255.255.111.35" ]
	 * （顺序无关紧要）解题：深度优先遍历=循环中递归，1.递归实参的改变，2.递归结束条件。
	 * 
	 * @return All possible valid IP addresses 不能包括01 001这样的格式
	 */
	static int line = 0;

	public static boolean isValid(String s) {
		if (s.charAt(0) == '0')
			return s.equals("0");// 不能包括01 001这样的格式，但可以0.0.1
		int num = Integer.parseInt(s);
		return num <= 255 && num > 0;
	}

	public static void dfs(String s, String prePart, ArrayList<String> res,
			int subIPsize) {
		if (subIPsize > 4) {
			return;
		}
		if (subIPsize == 3 && isValid(s)) {// 前面已经有3部分,如果这个满足即4个subIP成为有效IP,可以返回.
			res.add(prePart + s);// 前面部分（包括中间部分）+后面部分
			return;
		}
		for (int i = 1; i < 4 && i < s.length(); i++) {// s.substring(i)至少倒数后面有4个字符串，
			String midPart = s.substring(0, i);// 取后面部分的前i个，s.substring(k).substring(0,i)
												// 下标为0～(i-1)的子串
			if (isValid(midPart)) {
				System.out.println((line++) + "行,i=" + i + "-s.substring(i)="
						+ s.substring(i) + "-prePart=" + prePart + "-midPart="
						+ midPart + "-res=" + res + "-(subIPsize+1) ="
						+ (subIPsize + 1));
				dfs(s.substring(i), prePart + midPart + '.', res, subIPsize + 1);
			}// 下标i到最后的字符子串，
		}// end for
	}

	public static ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> res = new ArrayList<String>();
		if (s.length() < 4 || s.length() > 12)
			return res;
		dfs(s, "", res, 0);
		return res;
	}

	public static void main(String[] args) {
		String str = "25525511135";
		restoreIpAddresses(str);
		System.out.println("运行结束");
	}
}