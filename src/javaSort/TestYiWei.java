package javaSort;

public class TestYiWei {
	
	public static String ToBinStr(int a){
		return Integer.toBinaryString(a);//����ת��Ϊ�������ַ�
	}

	public static void main(String[] args){
		int a=2;
		System.out.println("ԭ�����£�");
		System.out.println(ToBinStr(a)+"����"+ToBinStr(a).length());//��ӡ10   
		System.out.println(ToBinStr(-a)+"����"+ToBinStr(-a).length());//��ӡ1.110
		
		System.out.println("�з����������£�");//���ֵΪ�������ڸ�λ��0�����ֵΪ�������ڸ�λ��1.
		System.out.println(ToBinStr(a>>1)+"����"+ToBinStr(a>>1).length());//��ӡ1,ǰ���㲻չ��    
		System.out.println(ToBinStr(-a>>1)+"����"+ToBinStr(-a>>1).length());//��ӡ1..1,32λ,ǰ��1չ��
		
		System.out.println("�޷����������£�");//����ֵ�����������ڸ�λ��0.
		System.out.println(ToBinStr(a>>>1)+"����"+ToBinStr(a>>>1).length());//��ӡ1,ǰ���㲻չ��    
		System.out.println(ToBinStr(-a>>>1)+"����"+ToBinStr(-a>>>1).length());//��ӡ1..1,31λ,ǰ���㲻չ
		
		System.out.println("�������£�");//����ֵ�����������ڵ�λ��0.
		System.out.println(ToBinStr(a<<1)+"����"+ToBinStr(a<<1).length());//��ӡ100   
		System.out.println(ToBinStr(-a<<1)+"����"+Integer.toBinaryString(-a<<1).length());//��ӡ1..100,32λ,

	}

}
