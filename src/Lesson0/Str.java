package Lesson0;

class Str {
	public static void main(String[] args) {
		int i = 900;
		System.out.println("changeInt(i)ǰi="+i);//��ӡ900
		changeInt(i);
		System.out.println("changeInt(i)��i="+i);//��ӡ900
	}

	public static void changeInt(int s){
		s = 34234;
	}
}
