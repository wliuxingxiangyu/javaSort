package Lesson0;

//1~M等概 产生 1~N等概
public class MProbabilityToN {
	public static int getRandom1ToM(int m) { // 如M=5,定义等概产生1..5的函数
		return (int) (Math.random() * m) + 1;
	}

	public static int getRandom1ToNFromM(int n, int m) { // 如n=7,m=5
		int[] nMSysNum = getMSysNumFromNum(n - 1, m);
		int[] getRandomNum = getRanMSysNumLessN(nMSysNum, m);
		return getNumFromMSysNum(getRandomNum, m) + 1; // (0~m-1)+1=（1~m）
	}

	public static int[] getMSysNumFromNum(int value, int m) { // 如value=6,m=5
		// 返回N-1= value的M进制表达
		int[] res = new int[32]; // 将n-1=value变成m进制
		int index = res.length - 1; // 即index=32-1=31
		while (value != 0) {// 将value转化为m进制,书写顺序逐位存入res[32]数组中
			res[index--] = value % m; // 如6%5=1
			value = value / m;
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]+" ");
		}
		return res; // res={000...11}
	}

	public static int[] getRanMSysNumLessN(int[] nMSysNum, int m) {
		// 获得随机数M同时M<N, 即” 筛”
		int[] res = new int[nMSysNum.length]; // 传入res[32]数组nMSysNum
		int start = 0;
		while (nMSysNum[start] == 0) {// 数组中第一位非0跳出，即”插空”
			start++;
		}
		do {
			for (int i = start; i != nMSysNum.length; i++) {
				res[i] = getRandom1ToM(m) - 1; // 调用k次随机函数，即k个m相乘为m^k,
				// 等概产生0..m-1(m进制),并存入res[i],如0~4
			}
		} while (!isNBiggerM(nMSysNum, res, start));
		return res; // 新数res=m小,返回真, 跳出循环,即” 筛”
	}

	public static boolean isNBiggerM(int[] n, int[] m, int start) {// 判断N>M
		for (; start != n.length; start++) {// n= nMSysNumw为n-1的m进制形式
			// m= res为随机序列，
			if (n[start] > m[start]) {// 新数res=m小,返回真, 跳出循环,
				return true;
			}
			if (n[start] < m[start]) {// 新数res=m大,返回假,循环重复,n-1<n<=m^k筛
				return false;
			}
		}
		return true;
	}
	
	public static void printArray(int[] Array) {
		System.out.println( );
		System.out.println("数组如下：");
		for (int i = 0; i < Array.length; i++) {
			System.out.print(Array[i] + "  ");
		}
	}

	public static int getNumFromMSysNum(int[] mSysNum, int m) {//
		printArray(mSysNum);
		System.out.println("m = " + m);
		int result = 0;
		for (int i = 0; i != mSysNum.length; i++) {
			result = result * m + mSysNum[i];// m进制变回十进制
		}
		return result;
	}

	public static void main(String[] args) {

		System.out.println("result=" + getRandom1ToNFromM(7, 5));
	}
}