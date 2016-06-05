package javaSort;

public class SwitchString {

	public static void main(String[] args) {
		testSwitchChar();
	}

	public static void testSwitchString() {
		System.out.println("--testSwitchString--");
		String str = "abc";
		switch (str) {
		case "abc":
			System.out.println("abc");
			break;
		case "123":
			System.out.println("123");
			break;

		default:
			break;
		}
	}
	
	public static void testSwitchChar() {
		System.out.println("--testSwitchChar--");
		char c = 'a';
		switch (c) {// ±¨´í
		case 'a':
			System.out.println("a");
			break;
		case 'b':
			System.out.println("b");
			break;

		default:
			break;
		}
	}
	
	// public static void testSwitchFloat() {
	// float f = 1.0f;
	// switch (f) {//±¨´í
	// case 1.0:
	// System.out.println("1.0");
	// break;
	// case 2.0:
	// System.out.println("2.0");
	// break;
	//
	// default:
	// break;
	// }
	// }
	
	// public static void testSwitchBoolean() {
	// Boolean b = true;
	// switch (b) {//±¨´í
	// case true:
	// System.out.println("true");
	// break;
	// case flase:
	// System.out.println("flase");
	// break;
	//
	// default:
	// break;
	// }
	// }

}
