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
		// t.join();//����join(),main�̡߳��ȴ���t�߳�ִ����ϲ�����,����ִ�д�ӡ5.
		System.out.println(a);//����join(),�п���t��ûִ����,main�̴߳�ӡ��a��һ���Ƕ���.
	}
}
