package classSeven;

public class ReturnMaxNumberUsingBitOperation{

	public static int flip(int n){
		return n^1;
	}

	public static int sign(int n){
		return flip((n>>31)&1);
	}

	public static int getMax(int a,int b){
		int c=a-b;
		int scA=sign(c);
		int scB=flip(scA);
		return a*scA+b*scB;
	}

	public static int getMaxBetter(int a,int b){
		int c=a-b;
		int sa=sign(a);
		int sb=sign(b);
		int sc=sign(c);//xiang  tong  wei   1
		int difSab=sa^sb;
		int sameSab=flip(difSab);//cha zhi shi 符号相同，不会溢出
		int returnA=difSab*sa+sameSab*sc;//difSab   符号bu同shi，会溢出,mei yong  c
		int returnB=flip(returnA);
		return a*returnA+b*returnB;//returnA,returnB  bu keng neng tongshi  wei 1
	}

	public static void main(String[] args){
		int a=-16;
		int b=1;
		System.out.println(getMax(a,b));
		System.out.println(getMaxBetter(a,b));
		a=2147483647;
		b=-2147480000;
		System.out.println(getMax(a,b));
		System.out.println(getMaxBetter(a,b));

	}

}
