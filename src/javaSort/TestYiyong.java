package javaSort;

public class TestYiyong {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("hello ");
		getString(sb);
		System.out.println(sb);
	}

	public static void getString(StringBuffer s) {
		// s = new StringBuffer("ha");//指向了另外的空间
		s.append("world");
	}
}
