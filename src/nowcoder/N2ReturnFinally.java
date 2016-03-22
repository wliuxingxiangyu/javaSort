package nowcoder;

public class N2ReturnFinally {
	public int add(int a, int b) {
		try {
			return a + b+0;
		} catch (Exception e) {
			System.out.println("catchÓï¾ä¿é");
		} finally {
			System.out.println("finallyÓï¾ä¿é");
//			System.out.println("finallyÓï¾ä¿é");
//			return 99;
		}
		return 0;
	}

	public static void main(String argv[]) {
		N2ReturnFinally test = new N2ReturnFinally();
		System.out.println("ºÍÊÇ£º" + test.add(9, 34));
	}
}
