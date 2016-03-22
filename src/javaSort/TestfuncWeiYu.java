package javaSort;

public class TestfuncWeiYu {
	
	public static int funcWeiYu(int x){//funcWeiYu的作用是计算x"二进制"补码表示中1的个数
	    int countx = 0;
	    boolean boo=true;
		while(boo){//当x不为0时循环//while不能用x判断,因为Java中int不能转换为boolean,c可以转while(x)
	    	countx ++;//记录循环次数
	        x = x&(x-1);//&是按位"与"操作，即以二进制bit进行比较，只有全1时才是1，否则该位为0
	        if(x==0){//当x为0时跳出循环
	        	boo=false;
	        }
		}
	    return countx;//返回循环次数
	}

	
	public static void main(String[] args) {
		int countx1=funcWeiYu(99);//int为4字节.99的补码同,原码,前24位为全0.后8为 0110,0011
		System.out.println("99的countx1="+countx1);//打印99的countx1=4

		int countx2=funcWeiYu(-99);//int为4字节.-99的补码为29个1，
		System.out.println("-99的countx2="+countx2);//打印-99的countx2=29
		

	}

}
