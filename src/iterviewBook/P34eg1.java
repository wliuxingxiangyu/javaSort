package iterviewBook;
public class P34eg1 {
	static {
//		 x=5;
		int x=5;
		System.out.println( "static x="+ x);
	}
	static int x,y;
	public static void main(String[] args) {
		System.out.println( "main x="+ x);
		x--;
		System.out.println( "x--ºó  x="+ x);
		myMethod();
//		System.out.println("Êä³ö£º"+ x + y++ + x);
		System.out.println( x + y++ + x);
	}
	
	public static void myMethod() {
		
		y=x++ + ++x;
		System.out.println( "myMethod x="+ x);
		System.out.println( "myMethod y="+ y);
	}
	
}
