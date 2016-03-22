package javaSort;

public class TestWeiFei {
	public static void main(String[] args){
		int a=2;//正数要转化为补码=原码，再取非
		System.out.println("正数a：非a的结果是："+(~a));//输出-3
		
		int b=-2;//负数要转化为补码再取非
		System.out.println("负数b：非b的结果是："+(~b));//输出1
		
		int c=0;//0存储为0..0
		System.out.println("零c：非零c的结果是："+(~c));//输出-1
		
	}
}
//快速运算：
//0和正数任意进制 -|x+1|，0和正数加一，添负号
//负数任意进制     |-x|-1，负数变正，减一。

// 2的原码 	0..0 0..0 0..0  0000 0010
//~2的原码	1..1 1..1 1..1  1111 1101设为计算机存的b补码，
//b补码反求b原码1..0 0..0 0..0  0000 0011即b=-3.

//输出"非a的结果是：-3"
