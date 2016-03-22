package shengsiyuan_video_classloader;//圣思园java视频

import java.util.Random;

class FinalTest{
//	public static final int x=6/3;//编译时候的常量,不对类初始化,
	public static final int x=new Random().nextInt(100);
	static{
		System.out.println("FinalTest static block");
	}
}
public class StaticFinalTest {
	public static void main(String[] args) {
		System.out.println(FinalTest.x);
		
	}
}
