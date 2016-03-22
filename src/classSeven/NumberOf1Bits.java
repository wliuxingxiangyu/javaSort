package classSeven;

public class NumberOf1Bits{

	public static int count1(int n){
		int res=0;
		while(n!=0){
			res+=n&1;
			n>>>=1;
		}
		return res;
	}

	public static int count2(int n){
		int res=0;
		while(n!=0){
			n&=(n-1);// xiao chu mo wei de 1,
			res++;
		}
		return res;
	}

	public static int count3(int n){
		int res=0;
		while(n!=0){
			n-=n&(~n+1);//
			res++;
		}
		return res;
	}

	public static int count4(int n){ //ֻ����32λ��
		n=(n&0x55555555)+((n>>>1)&0x55555555);//2λ�� ����ֵ�1����,5��ʾ��0101
		n=(n&0x33333333)+((n>>>2)&0x33333333);//4λ�� ����ֵ�1����,3��ʾ��0011
		n=(n&0x0f0f0f0f)+((n>>>4)&0x0f0f0f0f);//8λ�� ����ֵ�1����
		n=(n&0x00ff00ff)+((n>>>8)&0x00ff00ff);//16λ�� ����ֵ�1����
		n=(n&0x0000ffff)+((n>>>16)&0x0000ffff);//32λ�� ����ֵ�1����
		return n;
	}

	public static void printNumBit(int n){
		for(int i=31;i!=-1;i--){
			if((n&(1<<i))!=0){
				System.out.print(1);
			}else{
				System.out.print(0);
			}
		}
		System.out.println();
	}

	public static void main(String[] args){
		int num=-1;
		System.out.println(count1(num));
		System.out.println(count2(num));
		System.out.println(count3(num));
		System.out.println(count4(num));
		printNumBit(0x55555555);
		printNumBit(0x33333333);
		printNumBit(0x0f0f0f0f);
		printNumBit(0x00ff00ff);
		printNumBit(0x0000ffff);

	}

}
