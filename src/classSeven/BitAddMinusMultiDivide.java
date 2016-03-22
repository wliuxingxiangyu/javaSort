package classSeven;

public class BitAddMinusMultiDivide{

	public static int add(int a,int b){
		int sum=a;
		while(b!=0){
			sum=a^b;
			b=(a&b)<<1;
			a=sum;
		}
		return sum;
	}

	public static int negNum(int n){
		return add(~n,1);
	}

	public static int minus(int a,int b){
		return add(a,negNum(b));
	}

	public static int flip(int n){
		return n^1;
	}

	public static int sign(int n){
		return flip((n>>31)&1);
	}

	public static int multi(int a,int b){
		int sb=sign(b);
		b=sb==1?b:negNum(b);
		int res=0;
		while(b!=0){
			if((b&1)!=0){
				res=add(res,a);
			}
			a<<=1;
			b>>>=1;
		}
		return sb==1?res:negNum(res);
	}

	public static int divide(int a,int b){//
		if(b==0){
			throw new RuntimeException("Divisor should not be 0");
		}
		if(a==Integer.MIN_VALUE&&b==Integer.MIN_VALUE){
			return 1;
		}else if(b==Integer.MIN_VALUE){//最小的负数为除数
			return 0;
		}else if(a==Integer.MIN_VALUE){//最小的负数为被除数,不能转化为正数
			int res=divideProcess(add(a,1),b);
			return add(res, divideProcess(minus(a,multi(res,b)), b) );
		}else{
			return divideProcess(a,b);
		}
	}

	public static int divideProcess(int a,int b){
		int sa=sign(a);
		int sb=sign(b);
		a=sa==1?a:negNum(a);
		b=sb==1?b:negNum(b);
		int bit=1;
		while(a>=b&&b>0){
			b<<=1;
			bit<<=1;
		}
		b>>>=1;
		bit>>>=1;
		int res=0;
		while(bit!=0){
			int tmp=minus(a,b);
			if(tmp>=0){
				res|=bit;
				a=tmp;
			}
			b>>>=1;
			bit>>>=1;
		}
		return sa==sb?res:negNum(res);
	}

	public static void main(String[] args){
		int a=(int)(Math.random()*100000)-50000;
		int b=(int)(Math.random()*100000)-50000;
		System.out.println("a = "+a+", b = "+b);
		System.out.println(add(a,b));
		System.out.println(a+b);
		System.out.println("=========");
		System.out.println(minus(a,b));
		System.out.println(a-b);
		System.out.println("=========");
		System.out.println(multi(a,b));
		System.out.println(a*b);
		System.out.println("=========");
		System.out.println(divide(a,b));
		System.out.println(a/b);
		System.out.println("=========");

	}

}
