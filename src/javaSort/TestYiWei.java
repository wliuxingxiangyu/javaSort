package javaSort;

public class TestYiWei {
	
	public static String ToBinStr(int a){
		return Integer.toBinaryString(a);//整数转换为二进制字符
	}

	public static void main(String[] args){
		int a=2;
		System.out.println("原数如下：");
		System.out.println(ToBinStr(a)+"长度"+ToBinStr(a).length());//打印10   
		System.out.println(ToBinStr(-a)+"长度"+ToBinStr(-a).length());//打印1.110
		
		System.out.println("有符号右移如下：");//如果值为正，则在高位补0，如果值为负，则在高位补1.
		System.out.println(ToBinStr(a>>1)+"长度"+ToBinStr(a>>1).length());//打印1,前补零不展现    
		System.out.println(ToBinStr(-a>>1)+"长度"+ToBinStr(-a>>1).length());//打印1..1,32位,前补1展现
		
		System.out.println("无符号右移如下：");//无论值的正负，都在高位补0.
		System.out.println(ToBinStr(a>>>1)+"长度"+ToBinStr(a>>>1).length());//打印1,前补零不展现    
		System.out.println(ToBinStr(-a>>>1)+"长度"+ToBinStr(-a>>>1).length());//打印1..1,31位,前补零不展
		
		System.out.println("左移如下：");//无论值的正负，都在低位补0.
		System.out.println(ToBinStr(a<<1)+"长度"+ToBinStr(a<<1).length());//打印100   
		System.out.println(ToBinStr(-a<<1)+"长度"+Integer.toBinaryString(-a<<1).length());//打印1..100,32位,

	}

}
