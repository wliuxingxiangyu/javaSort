package class201702;

import java.util.ArrayList;
import java.util.List;

public class H106RestoreIPAddresses {
	/**
	 * ��һ����������ɵ��ַ������������ָܻ�Ϊ������IP��ַ�� ����:�����ַ��� "25525511135"�����п��ܵ�IP��ַΪ�� [
	 * "255.255.11.135", "255.255.111.35" ]
	 * ��˳���޹ؽ�Ҫ�����⣺������ȱ���=ѭ���еݹ飬1.�ݹ�ʵ�εĸı䣬2.�ݹ����������
	 * 
	 * @return All possible valid IP addresses ���ܰ���01 001�����ĸ�ʽ
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
		if ((total == 1) && isValid(s)) {
			res.add(path + s);//��ʣ���һ����������Ч��IP�����롣
			return;
		} else {
			for (int i = 1, nLen = Math.min(4, s.length()); i < nLen; i++) {
				String midPart = s.substring(0, i);// ȡ���沿�ֵ�ǰi����s.substring(k).substring(0,i)
				if (isValid(midPart)) {

					System.out.println((line++) + "��,i=" + i + ",path=" + path
							+ ",midPart=" + midPart + ",(total - 1)="
							+ (total - 1));

					dfs(res, s.substring(i), path + midPart + ".", total - 1);
				}
			}// end for
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
		System.out.println("���н���");
	}
}