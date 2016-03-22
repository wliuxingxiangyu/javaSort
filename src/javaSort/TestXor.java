package javaSort;

public class TestXor{

	public static void main(String[] args){
		int x=3;
		int y=4;
		System.out.print("交换前x="+x);		
		System.out.print("交换前y="+y);
		System.out.println(" ");
		
		x=x^y;
		y=x^y;//抵消掉原y,剩下原x,赋给y
		System.out.print("交换中x="+x);
		System.out.print("交换中y="+y);
		x=x^y;//抵消掉原x,剩下原y,赋给x
		//3步：左边为x，y，x,右边相同为x^y。
		
		System.out.println(" ");
		System.out.print("交换后x="+x);
		System.out.print("交换后y="+y);
	
		
		
		
		
		
		
		
	}	
	
	
}
