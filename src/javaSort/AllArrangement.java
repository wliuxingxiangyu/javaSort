package javaSort;

public class AllArrangement{// ȫ����
	public static int count=0;// ����
	public static void main(String[] args){
		char buf[]={'a', 'b', 'c'};

		perm(buf,0,buf.length-1);
	}

	public static void perm(char[] buf,int start,int end){// permutation����
		if(start==end){// ��ֻҪ���������һ����ĸ����ȫ����ʱ��ֻҪ�Ͱ��������������
			System.out.print(++count+".");// ����1.2.3..
			System.out.print("buf.toString():"+buf.toString()+"--");
			for(int i=0;i<=end;i++){
				System.out.print(buf[i]);
			}
			System.out.println();//һ��ȫ����,һ��
		}else{// �����ĸȫ����
			for(int i=start;i<=end;i++){
				char temp=buf[start];// ���������һ��Ԫ��buf[start]�������Ԫ��buf[i]
				buf[start]=buf[i];
				buf[i]=temp;

				perm(buf,start+1,end);// ����Ԫ�صݹ�ȫ����

				temp=buf[start];// ������������黹ԭ
				buf[start]=buf[i];
				buf[i]=temp;
			}
		}
	}

}
