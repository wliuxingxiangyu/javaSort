package javaSort;

public class TestfuncWeiYu {
	
	public static int funcWeiYu(int x){//funcWeiYu�������Ǽ���x"������"�����ʾ��1�ĸ���
	    int countx = 0;
	    boolean boo=true;
		while(boo){//��x��Ϊ0ʱѭ��//while������x�ж�,��ΪJava��int����ת��Ϊboolean,c����תwhile(x)
	    	countx ++;//��¼ѭ������
	        x = x&(x-1);//&�ǰ�λ"��"���������Զ�����bit���бȽϣ�ֻ��ȫ1ʱ����1�������λΪ0
	        if(x==0){//��xΪ0ʱ����ѭ��
	        	boo=false;
	        }
		}
	    return countx;//����ѭ������
	}

	
	public static void main(String[] args) {
		int countx1=funcWeiYu(99);//intΪ4�ֽ�.99�Ĳ���ͬ,ԭ��,ǰ24λΪȫ0.��8Ϊ 0110,0011
		System.out.println("99��countx1="+countx1);//��ӡ99��countx1=4

		int countx2=funcWeiYu(-99);//intΪ4�ֽ�.-99�Ĳ���Ϊ29��1��
		System.out.println("-99��countx2="+countx2);//��ӡ-99��countx2=29
		

	}

}
