package Lesson0;

public class TestYinYong{

	public static class A{
		public int value;
	}
	
	
	public static void f(){
		A[] arr = new A[1];//对象数组
	
		arr[0] = new A();
		arr[0].value = 1;
		f2(arr);
		System.out.println(arr[0]);
		
	}
	
	public static void f2(A[] arr){
		arr[0] = null;
	}
	
	
	public static void main(String[] args){
		f();

	}

}
