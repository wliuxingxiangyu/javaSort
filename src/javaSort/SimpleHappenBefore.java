package javaSort;

public class SimpleHappenBefore {//���з���ԭ��
	/** ����һ����֤����ı��� */
	private static int a = 0;
	/** ����һ����־λ */
	private static boolean flag = false;

	public static void main(String[] args) throws InterruptedException {
		// ���ڶ��߳������δ�ػ��Գ�������Ľ���,���Զ���һЩ��
		for (int i = 0; i < 1000; i++) {
			ThreadA threadA = new ThreadA();
			ThreadB threadB = new ThreadB();
			threadA.start();
			threadB.start();

			// ����ȴ��߳̽�����,���ù������,��ʹ��֤����Ĺ�����ü�Щ.
			threadA.join();//����join(),main�̡߳��ȴ���threadA�߳�ִ����ϲ�����,
			threadB.join();//����join(),main�̡߳��ȴ���threadB�߳�ִ����ϲ�����,
			a = 0;
			flag = false;
		}
	}

	static class ThreadA extends Thread {
		public void run() {
			a = 1;
			flag = true;
		}
	}

	static class ThreadB extends Thread {
		public void run() {
			if (flag) {
				a = a * 1;
			}
			if (a == 0) {
				System.out.println("ha,a==0");
			}
		}
	}
}
