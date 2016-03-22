package Lesson0;

//1~M�ȸ� ���� 1~N�ȸ�
public class MProbabilityToN {
	public static int getRandom1ToM(int m) { // ��M=5,����ȸŲ���1..5�ĺ���
		return (int) (Math.random() * m) + 1;
	}

	public static int getRandom1ToNFromM(int n, int m) { // ��n=7,m=5
		int[] nMSysNum = getMSysNumFromNum(n - 1, m);
		int[] getRandomNum = getRanMSysNumLessN(nMSysNum, m);
		return getNumFromMSysNum(getRandomNum, m) + 1; // (0~m-1)+1=��1~m��
	}

	public static int[] getMSysNumFromNum(int value, int m) { // ��value=6,m=5
		// ����N-1= value��M���Ʊ��
		int[] res = new int[32]; // ��n-1=value���m����
		int index = res.length - 1; // ��index=32-1=31
		while (value != 0) {// ��valueת��Ϊm����,��д˳����λ����res[32]������
			res[index--] = value % m; // ��6%5=1
			value = value / m;
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]+" ");
		}
		return res; // res={000...11}
	}

	public static int[] getRanMSysNumLessN(int[] nMSysNum, int m) {
		// ��������MͬʱM<N, ���� ɸ��
		int[] res = new int[nMSysNum.length]; // ����res[32]����nMSysNum
		int start = 0;
		while (nMSysNum[start] == 0) {// �����е�һλ��0������������ա�
			start++;
		}
		do {
			for (int i = start; i != nMSysNum.length; i++) {
				res[i] = getRandom1ToM(m) - 1; // ����k�������������k��m���Ϊm^k,
				// �ȸŲ���0..m-1(m����),������res[i],��0~4
			}
		} while (!isNBiggerM(nMSysNum, res, start));
		return res; // ����res=mС,������, ����ѭ��,���� ɸ��
	}

	public static boolean isNBiggerM(int[] n, int[] m, int start) {// �ж�N>M
		for (; start != n.length; start++) {// n= nMSysNumwΪn-1��m������ʽ
			// m= resΪ������У�
			if (n[start] > m[start]) {// ����res=mС,������, ����ѭ��,
				return true;
			}
			if (n[start] < m[start]) {// ����res=m��,���ؼ�,ѭ���ظ�,n-1<n<=m^kɸ
				return false;
			}
		}
		return true;
	}
	
	public static void printArray(int[] Array) {
		System.out.println( );
		System.out.println("�������£�");
		for (int i = 0; i < Array.length; i++) {
			System.out.print(Array[i] + "  ");
		}
	}

	public static int getNumFromMSysNum(int[] mSysNum, int m) {//
		printArray(mSysNum);
		System.out.println("m = " + m);
		int result = 0;
		for (int i = 0; i != mSysNum.length; i++) {
			result = result * m + mSysNum[i];// m���Ʊ��ʮ����
		}
		return result;
	}

	public static void main(String[] args) {

		System.out.println("result=" + getRandom1ToNFromM(7, 5));
	}
}