package leetCode;

public class strstr {
	public static int strStrMy(String source, String target) {
		if (source == null || target == null) {
			return -1;
		} else if (target.equals("")) {//target�մ�""!=null.�ַ���Ҫ��equals�е�
			return 0;
		}
		int i = 0, j = 0;
		while(i < source.length()) {
			if (j < target.length() && source.charAt(i) == target.charAt(j)) {
				i++;//���ʱ��������Ӽӡ�
				j++;
			} else if (j == target.length()) {
				break;//�Ӽӵ�С����βʱ������
			} else {//ƥ����;��һ������ʱ,����i��(��j��ȵ���һ��)��j-1��
				i = i - (j - 1);
				j = 0;
			}
		}

		if (j == target.length()) {//�ж������ѭ����������
			return i - target.length();//��ȫƥ���Ӵ�
		} else {//û�ҵ��Ӵ�
			return -1;
		}
	}

	public static int strStr(String haystack, String needle) {
		if (needle.length() == 0)
			return 0;
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				else if (i + j >= haystack.length())
					return -1;
				else if (needle.charAt(j) != haystack.charAt(i + j))
					break;// ����ʱ������ ִ�����ѭ��i++
			}
		}
	}

	public static void main(String[] args) {
		// String source = "lintcode";
		// String target = "lintcode";
		String source = "mississippi";
		String target = "issip";
//		String source = "";
//		String target = "a";
		// String source = "a";
		// String target = "";
		// String source = "abcdabcdefg";
		// String target = "bcd";
		System.out.println("������:" + strStr(source, target));//4
		System.out.println("������--strStrMy" + strStrMy(source, target));//4
		System.out.println("�������մ�.equals(null) :" + "".equals(null));//false
	}
}
