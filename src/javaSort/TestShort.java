package javaSort;

public class TestShort {
	
	public static String IntToBinStr(int a){
		return Integer.toBinaryString(a);//整数转换为二进制字符
	}
	
	public static String ByteToBinStr(Byte a){
		return Byte.toString(a);//整数转换为二进制字符
	}
	
	public static byte[] intToByteArray(int num){//int形式-17二进制补码1..1 1..1 1..1 11101110
		   byte[] by = new byte[4];  
		   by[0] = (byte)(num >>> 24);//by[0]=1..1  
		   by[1] = (byte)(num >>> 16);//by[1]=1..1  
		   by[2] = (byte)(num >>> 8); //by[2]=1..1 
		   by[3] = (byte)(num );      //by[3]=11101110 
		   for (int i = 0; i < by.length; i++) {
				System.out.println(by[i]+"  ");//打印按十进制打印
		   }
		   for (int i = 0; i < by.length; i++) {
				System.out.println("by["+i+"]"+ByteToBinStr(by[i])+"  ");//打印按十进制打印
		   }		   
		   return by;
	}  
	
    public static short byteToShort(byte[] by) {  
        short s0 = (short) (by[0] & 0xff);//低  
        short s1 = (short) (by[1] & 0xff);  
        s1 <<= 8;  
        short s= (short) (s0 | s1);  
        return s;  
    }  
    
	public static void main(String[] args) {//int形式17二进制原码1..0 0..0 0..0 00010001
		byte[]  by=intToByteArray(-17);     //int形式-17二进制补码1..1 1..1 1..1 11101110
		short sh = byteToShort(by);
		System.out.print("sh"+sh+ "  ");
	}
}
