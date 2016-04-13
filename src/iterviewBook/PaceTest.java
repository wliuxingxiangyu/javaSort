package iterviewBook;

import java.util.Random;

public class PaceTest {
	static Random rand = new Random();

	public static int pace() {
		return (int) (rand.nextDouble() * 2 + 1);
	}

	public static void main(String[] args) {
		double sum = 0;
		int times = 10000;// 实验10000次
		for (int i = 0; i < times; i++) {
			int index = 1;
			int pace = 0;
			while (index % 4 != 0) {
				index += pace();
				pace++;
			}
			sum += pace;
		}
		System.out.println("期望值为: " + sum / times);//3.5927
	   
		Integer i1=127,i2=127,i3=128,i4=128;
		System.out.println(i1==i2);
		System.out.println(i1.equals(i2));
		System.out.println(i3==i4);
		System.out.println(i3.equals(i4));
	
	}

}
