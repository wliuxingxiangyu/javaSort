package javaSort;

//�����߳�ʱһ��Ҫstart()��������run().
public class Threadrun {
	public static void main(String[] args){
		Thread t=new Thread(){
			public void run() {
				pong();
			}
		};
		
//		t.run();//�ȴ�ӡpongһ��,��ʱʧȥ�߳�����,runΪ��ͨ����.
		t.start();//�ȴ�ӡpong��һ��
		System.out.println("ping");
	}
	
	public static void pong() {
		System.out.println("pong");
	}
}
