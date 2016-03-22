package shengsiyuan_video_classloader;
class Parent2{
	static int a=3;
	static{
		System.out.println("Parent2 static block");
	}
}

class Child2 extends Parent2{//若Parent2已被加载了,就不再重复加载.
	static int b=4;
	static{
		System.out.println("Child2 static block");
	}
}

public class Test5 {
	static{
		System.out.println("Test5 static block");
	}//程序的启动类，先加载
	
	public static void main(String[] args) {
		Parent2 parent2;
		System.out.println("---------");
		parent2=new Parent2();
		System.out.println(Parent2.a);
		System.out.println(Child2.b);
	}
}