package shengsiyuan_video_classloader;//ʥ˼԰java��Ƶ

import java.util.Random;

class FinalTest{
//	public static final int x=6/3;//����ʱ��ĳ���,�������ʼ��,
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
