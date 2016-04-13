package iterviewBook;

public class Base {
	int i;

	Base() {
		add(i);
	}

	void add(int v) {
		i += v;
	}

	void print() {
		System.out.println(i);
	}
}

class Extension extends Base {
	Extension() {
		add(2);
	}

	void add(int v) {
		i += v * 2;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
