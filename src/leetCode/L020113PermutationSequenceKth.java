package leetCode;
/**�������
 * 1.���������һ��Ԫ��buf[start]�������Ԫ��buf[i],2.����ȫ���У�3.�ٻ�����buf[start]��buf[i]��
 */
public class L020113PermutationSequenceKth {
	public static int count = 0;// ����
	public static StringBuffer sBuf = new StringBuffer();

	public static String getPermutationMy(int n, int k) {
		if (Factorial(n) < k)
			 return null;

		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;// ��ʼ������
		}

		sBuf = perm(arr, 0, arr.length - 1, k);
		return sBuf.toString();
	}

	public static StringBuffer perm(int[] buf, int start, int end, int k) {// permutation����
		if (start == end) {// ��ֻҪ���������һ����ĸ����ȫ����ʱ��ֻҪ�Ͱ��������������
			++count;// ����1.2.3..
			if (k == count ) {
				for (int i = 0; i < buf.length; i++) {
					sBuf = sBuf.append(buf[i]);
				}
				return sBuf;
			}
		} else {// �����ĸȫ����
			for (int i = start; i <= end; i++) {
				int temp = buf[start];// ���������һ��Ԫ��buf[start]�������Ԫ��buf[i]
				buf[start] = buf[i];
				buf[i] = temp;

				perm(buf, start + 1, end, k);// ����Ԫ�صݹ�ȫ����

				temp = buf[start];// ������������黹ԭ
				buf[start] = buf[i];
				buf[i] = temp;
			}
		}
		return sBuf;
	}



	public static void main(String[] args) {
//		int n = 2, k = 2;
		int n = 3, k = 4;
//		int n = 8, k = 8590;
		// int n = 1,k=1;
		// int n = 3,k=4;
		System.out.println(n + "�ĵ�" + k + "��my�׳�Ϊ:" + getPermutationMy(n, k));
		System.out.println(n + "�ĵ�" + k + "���׳�Ϊ:" + getPermutation(n, k));
		System.out.println(n + "�Ľ׳�Ϊ��" + Factorial(n));
	}

	//�ο��ⷨ///////////////////////////////////////////////
	public static String getPermutation(int n, int k) {
		if (n <= 0 || k < 0)
			return "";
		int base = Factorial(n-1);
		if (base*n < k)
			return "";
		String originalStr="123456789".substring(0, n);//����Ϊ�κ��ַ�����:��1268��
		StringBuffer source = new StringBuffer(originalStr);
		StringBuffer res = new StringBuffer();
		k--;//���и���k��1��ʼ�������и���k��0��ʼ��
		int index;
		for (int i = n-1; i > 0;k = k % base, base = base / i,i--) {
			index= k / base;
			res.append(source.charAt(index));
			source.deleteCharAt(index);
		}
		res.append(source.charAt(0));
		return res.toString();
	}
	
	public static int Factorial(int n) {// �׳˺���
		int result = 1;
		for (int i = 2; i <= n; i++) {
			result = result * i;
		}
		return result;
	}

}
