package javaSort;

//启动线程时一定要start()，而不是run().
public class Threadrun {
	public static void main(String[] args){
		Thread t=new Thread(){
			public void run() {
				pong();
			}
		};
		
//		t.run();//先打印pong一定,此时失去线程特性,run为普通方法.
		t.start();//先打印pong不一定
		System.out.println("ping");
	}
	
	public static void pong() {
		System.out.println("pong");
	}
}
