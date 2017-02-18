package class201702;

import java.util.ArrayList;

public class H106RestoreIPAddresses {
	/**
	 * ��һ����������ɵ��ַ������������ָܻ�Ϊ������IP��ַ�� ����:�����ַ��� "25525511135"�����п��ܵ�IP��ַΪ�� [
	 * "255.255.11.135", "255.255.111.35" ]
	 * ��˳���޹ؽ�Ҫ�����⣺������ȱ���=ѭ���еݹ飬1.�ݹ�ʵ�εĸı䣬2.�ݹ����������
	 * 
	 * @return All possible valid IP addresses ���ܰ���01 001�����ĸ�ʽ
	 */
	static int line = 0;

	public static boolean isValid(String s) {
		if (s.charAt(0) == '0')
			return s.equals("0");// ���ܰ���01 001�����ĸ�ʽ��������0.0.1
		int num = Integer.parseInt(s);
		return num <= 255 && num > 0;
	}

	public static void dfs(String s, String prePart, ArrayList<String> res,
			int subIPsize) {
		if (subIPsize > 4) {
			return;
		}
		if (subIPsize == 3 && isValid(s)) {// ǰ���Ѿ���3����,���������㼴4��subIP��Ϊ��ЧIP,���Է���.
			res.add(prePart + s);// ǰ�沿�֣������м䲿�֣�+���沿��
			return;
		}
		for (int i = 1; i < 4 && i < s.length(); i++) {// s.substring(i)���ٵ���������4���ַ�����
			String midPart = s.substring(0, i);// ȡ���沿�ֵ�ǰi����s.substring(k).substring(0,i)
												// �±�Ϊ0��(i-1)���Ӵ�
			if (isValid(midPart)) {
				System.out.println((line++) + "��,i=" + i + "-s.substring(i)="
						+ s.substring(i) + "-prePart=" + prePart + "-midPart="
						+ midPart + "-res=" + res + "-(subIPsize+1) ="
						+ (subIPsize + 1));
				dfs(s.substring(i), prePart + midPart + '.', res, subIPsize + 1);
			}// �±�i�������ַ��Ӵ���
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
		System.out.println("���н���");
	}
}