package iterviewBook;

public abstract class TypeChange {

	double func3() {
		float i = 3;
		return i;//Сת����ǿ��ת��,���Զ����澫��,
	}

	float func4() {
		double i = 4;
		return (float) i;//��תС����ǿ��ת��,ǿ�ƺ�ᶪʧ����,����ͨ��.
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}
	
	protected abstract void f1();
//	public static final int a = 8;
	public final int a = 8;
//	a=9;//a�����Ա�

}
