package leetCode;
/**排列组合
 * 1.交换数组第一个元素buf[start]与后续的元素buf[i],2.进入全排列，3.再换回来buf[start]与buf[i]。
 */
public class L020113PermutationSequenceKth {
	public static int count = 0;// 计数
	public static StringBuffer sBuf = new StringBuffer();

	public static String getPermutationMy(int n, int k) {
		if (Factorial(n) < k)
			 return null;

		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;// 初始化数组
		}

		sBuf = perm(arr, 0, arr.length - 1, k);
		return sBuf.toString();
	}

	public static StringBuffer perm(int[] buf, int start, int end, int k) {// permutation排列
		if (start == end) {// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
			++count;// 计数1.2.3..
			if (k == count ) {
				for (int i = 0; i < buf.length; i++) {
					sBuf = sBuf.append(buf[i]);
				}
				return sBuf;
			}
		} else {// 多个字母全排列
			for (int i = start; i <= end; i++) {
				int temp = buf[start];// 交换数组第一个元素buf[start]与后续的元素buf[i]
				buf[start] = buf[i];
				buf[i] = temp;

				perm(buf, start + 1, end, k);// 后续元素递归全排列

				temp = buf[start];// 将交换后的数组还原
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
		System.out.println(n + "的第" + k + "个my阶乘为:" + getPermutationMy(n, k));
		System.out.println(n + "的第" + k + "个阶乘为:" + getPermutation(n, k));
		System.out.println(n + "的阶乘为：" + Factorial(n));
	}

	//参考解法///////////////////////////////////////////////
	public static String getPermutation(int n, int k) {
		if (n <= 0 || k < 0)
			return "";
		int base = Factorial(n-1);
		if (base*n < k)
			return "";
		String originalStr="123456789".substring(0, n);//可以为任何字符串如:“1268”
		StringBuffer source = new StringBuffer(originalStr);
		StringBuffer res = new StringBuffer();
		k--;//题中给的k从1开始，代码中给的k从0开始，
		int index;
		for (int i = n-1; i > 0;k = k % base, base = base / i,i--) {
			index= k / base;
			res.append(source.charAt(index));
			source.deleteCharAt(index);
		}
		res.append(source.charAt(0));
		return res.toString();
	}
	
	public static int Factorial(int n) {// 阶乘函数
		int result = 1;
		for (int i = 2; i <= n; i++) {
			result = result * i;
		}
		return result;
	}

}
