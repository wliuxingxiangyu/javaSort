package iterviewBook;

public class TypeChange {

	float func3() {
		long i = 3;
		return i;//大转小 无需强制转换,丢失精度,
	}

	float func4() {
		double i = 4;
		return (float) i;//小转大需强制转换,保存精度,
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
