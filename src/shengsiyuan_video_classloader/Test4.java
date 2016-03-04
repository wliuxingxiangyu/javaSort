package shengsiyuan_video_classloader;

class Parent{
	static int a=3;
	static{
		System.out.println("Parent static block");
	}
}

class Child extends Parent{
	static int b=4;
	static{
		System.out.println("Child static block");
	}
}

public class Test4 {
	static{
		System.out.println("Test4 static block");
	}//程序的启动类，先加载
	
	public static void main(String[] args) {
		System.out.println(Child.b);
	}
}
