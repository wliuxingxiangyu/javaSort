package class201702;

import java.util.ArrayList;
import java.util.List;

public class H106RestoreIPAddresses {
	/**
	 * 给一个由数字组成的字符串。求出其可能恢复为的所有IP地址。 样例:给出字符串 "25525511135"，所有可能的IP地址为： [
	 * "255.255.11.135", "255.255.111.35" ]
	 * （顺序无关紧要）解题：深度优先遍历=循环中递归，1.递归实参的改变，2.递归结束条件。
	 * 
	 * @return All possible valid IP addresses 不能包括01 001这样的格式
	 */
	static int line = 0;

	public static List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<String>();
		if (s == null || s.length() < 4)
			return res;
		dfs(res, s, "", 4);
		return res;
	}

	private static void dfs(List<String> res, String s, String path, int total) {
		if (total == 1) {// 终止条件，到了叶子节点
			if (isValid(s))// 收敛条件，找到了一个可行解
				res.add(path + s);
		} else {//total=4,3,2进else;所以if不能这样写“终止条件&&收敛条件”。因为会让isValid(s)==false进else.
			for (int i = 1, nLen = Math.min(4, s.length()); i < nLen; i++) {//4是因为s.substring(0,i)最大3位的255.
				String midPart = s.substring(0, i);// 取后面部分的前i个，s.substring(k).substring(0,i)
				if (isValid(midPart)) {
					System.out.println((line++) + "行,i=" + i + ",path=" + path
							+ ",midPart=" + midPart + ",(total - 1)="
							+ (total - 1)+",s.substring(i)="+s.substring(i));

					dfs(res, s.substring(i), path + midPart + ".", total - 1);
				}
			}
		}
	}

	private static boolean isValid(String s) {
		if (s.length() == 1)
			return true;
		if (s.length() >= 4)
			return false;
		int num = Integer.parseInt(s);
		return s.charAt(0) != '0' && num > 0 && num <= 255;
	}

	public static void main(String[] args) {
		String str = "25525511135";
		restoreIpAddresses(str);
		System.out.println("运行结束");
	}
}