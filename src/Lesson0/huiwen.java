package Lesson0;

//��һ���ַ�����������λ��������ٵ��ַ���ʹ����Ӻ���ַ��������ǻ����ַ���
class Person {
	// lps��ʾlongest� Palindrome���� substring�Ӵ�
	public String generateASPByLPS(String str, String strLPS) {
		if (str == null || str.equals("")) {
			return "";
		}
		char[] charArr = str.toCharArray();// strת��Ϊ�ַ�����
		char[] charLPSArr = strLPS.toCharArray();// strLPSת��Ϊ�ַ�����
		char[] result = new char[2 * charArr.length - charLPSArr.length];// ��Ϊ�ѻ���strLPS�����ظ���
		int lpsLeft = 0;// ������Ӵ� ���±�
		int lpsRight = charLPSArr.length - 1;// ������Ӵ� ���±�
		int charLeft = 0;// �� ���±�,
		int charRight = charArr.length - 1;// �� ���±�,
		int resultLeft = 0;// ��� ���±�
		int resultRight = result.length - 1;// ��� ���±�
		while (lpsLeft <= lpsRight) { // �����Ӵ�
			int tmpCharLeft = charLeft;// �� ���±�,�����������±�,�����β�leftPartStart
			int tmpCharRight = charRight;// �� ���±�,���Ҳ�������±�,�����β�rightPartEnd
			while (charArr[charLeft] != charLPSArr[lpsLeft]) {// �Ҵ�����ַ�=�����Ӵ�����ַ������±�
				charLeft++;// �� ���±� �Ӽ�,while�б�Ǵ�С����ͬ����λ��,�����������±�
			}
			while (charArr[charRight] != charLPSArr[lpsRight]) {// �Ҵ��ұ��ַ�=�����Ӵ�����ַ������±�
				charRight--;// �� ���±� �Ӽ�,while�б�Ǵ�С����ͬ����λ��,���Ҳ�������±�
			}
			System.out.println("tmpCharLeft=" + tmpCharLeft);// ����
			System.out.println("charLeft=" + charLeft);// ����
			System.out.println("charRight=" + charRight);// ����
			System.out.println("tmpCharRight=" + tmpCharRight);// ����
			setTwoUnPalindromePartToResult(result, resultLeft, resultRight,
					charArr, tmpCharLeft, charLeft, charRight, tmpCharRight);
			// �β�charArr,leftPartStart,leftPartEnd,rightPartStart,rightPartEnd

			resultLeft += charLeft - tmpCharLeft + tmpCharRight - charRight;// ��ֵresult��,�ı�λ���±�
			resultRight -= charLeft - tmpCharLeft + tmpCharRight - charRight;
			result[resultLeft++] = charArr[charLeft++];// �����Ӵ���ͬʱ��result���߸���ͬ��ֵ
			result[resultRight--] = charArr[charRight--];//
			lpsLeft++;
			lpsRight--;
		}
		return String.valueOf(result);// ��resultת�����ַ���
	}

	public void setTwoUnPalindromePartToResult(char[] result, int resultLeft,
			int resultRight, char[] charArr, int leftPartStart,
			int leftPartEnd, int rightPartStart, int rightPartEnd) {
		// �������ߵķǻ��Ĳ��ֵ�Result��
		for (int i = leftPartStart; i != leftPartEnd; i++) {
			result[resultLeft++] = charArr[i];// ����result���Ҷ���charArr[i]��
			result[resultRight--] = charArr[i];
			System.out.println("charArr[i]��=" + charArr[i]);// ����
		}
		for (int i = rightPartEnd; i != rightPartStart; i--) {
			result[resultLeft++] = charArr[i];
			result[resultRight--] = charArr[i];
			System.out.println("charArr[i]��=" + charArr[i]);// ����
		}
	}
}

public class huiwen {
	// ��������
	public static void main(String[] args) {
		Person p = new Person();
		String str = "B1G2TY34I3OPX2S1";
		String strLPS = "123I321";
		String result = p.generateASPByLPS(str, strLPS);
		System.out.println(str + " shortest all palindrome string:result="
				+ result);
	}
}