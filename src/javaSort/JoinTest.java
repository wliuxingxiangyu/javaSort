package javaSort;

public class JoinTest implements Runnable {

	public static int a = 0;

	public void run() {
		for (int k = 0; k < 5; k++) {
			a = a + 1;
		}
	}

	public static void main(String[] args) throws Exception {
		Runnable r = new JoinTest();
		Thread t = new Thread(r);
		t.start();
		// t.join();//加了join(),main线程“等待”t线程执行完毕才往下,所以执行打印5.
		System.out.println(a);//不加join(),有可能t还没执行完,main线程打印了a不一定是多少.
	}
}
