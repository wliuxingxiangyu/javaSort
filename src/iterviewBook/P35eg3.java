package iterviewBook;

public class P35eg3 {

	public static void main(String[] args) {
		int j=0,k=0,a=0;
//		for (int i = 0; i < 100; i++) {
//			j++;
//			j=(j++);
			j=j++;
//			j= ++ j;
//		}
		System.out.println("j="+j);
		
		k= ++ k;
		System.out.println("k="+k);
		
		a=a++ + ++a;
		System.out.println("a="+a);
		
		int i2="abc".length();
		System.out.println("i2="+i2);
		
		short s=1;
//		s=s+1;// 不能从int转成short
		s+=1;
		System.out.println("s="+s);
	}

}
