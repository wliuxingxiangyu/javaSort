package shengsiyuan_video_classloader;
class CL{
	static {
		System.out.println("Class CL");
	}
}

public class Test7 {
	public static void main(String[] args) throws Exception{
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		//调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
		Class<?> clazz=loader.loadClass("shengsiyuan_video_classloader.CL");
		System.out.println("-------");
		clazz=Class.forName("shengsiyuan_video_classloader.CL");
	}
}
