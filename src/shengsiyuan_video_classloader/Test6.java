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

class Child3 extends Parent3{//��Parent2�ѱ�������,�Ͳ����ظ�����.
	static{
		System.out.println("Child3 static block");
	}
}

public class Test6 {
	public static void main(String[] args) {
		System.out.println(Child3.a);//�����ӡ��Child3,��ΪaΪParent3�ж����.
		Child3.doSomething();
	}

}
