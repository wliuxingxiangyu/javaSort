package shengsiyuan_video_classloader;
class Parent3{
	static int a=3;
	static{
		System.out.println("Parent3 static block");
	}
	static void doSomething() {
		System.out.println("do something");
	}
}

class Child3 extends Parent3{//若Parent2已被加载了,就不再重复加载.
	static{
		System.out.println("Child3 static block");
	}
}

public class Test6 {
	public static void main(String[] args) {
		System.out.println(Child3.a);//不会打印出Child3,因为a为Parent3中定义的.
		Child3.doSomething();
	}

}
