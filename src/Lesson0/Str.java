package Lesson0;

class Str {
	public static void main(String[] args) {
		int i = 900;
		System.out.println("changeInt(i)前i="+i);//打印900
		changeInt(i);
		System.out.println("changeInt(i)后i="+i);//打印900
	}

	public static void changeInt(int s){
		s = 34234;
	}
}
