package shengsiyuan_video_classloader;
class CL{
	static {
		System.out.println("Class CL");
	}
}

public class Test7 {
	public static void main(String[] args) throws Exception{
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		//����ClassLoader���loadClass��������һ���࣬�����Ƕ��������ʹ�ã����ᵼ����ĳ�ʼ����
		Class<?> clazz=loader.loadClass("shengsiyuan_video_classloader.CL");
		System.out.println("-------");
		clazz=Class.forName("shengsiyuan_video_classloader.CL");
	}
}
