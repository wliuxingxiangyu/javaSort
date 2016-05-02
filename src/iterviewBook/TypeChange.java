package iterviewBook;

public abstract class TypeChange {

	double func3() {
		float i = 3;
		return i;//小转大需强制转换,会自动保存精度,
	}

	float func4() {
		double i = 4;
		return (float) i;//大转小无需强制转换,强制后会丢失精度,编译通过.
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}
	
	protected abstract void f1();
//	public static final int a = 8;
	public final int a = 8;
//	a=9;//a不可以变

}
