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
			PrintArr(buf);
		}else{// �����ĸȫ����,ѭ���н���,�ݹ齻��,
			for(int i=start;i<=end;i++){//��forѭ��Ϊ�˽���start��i
				swap(buf,start,i);//��һ��i=start����û��һ��.
				perm(buf,start+1,end);//start+1����Ԫ�صݹ�ȫ����.
				swap(buf,start,i);// ������������黹ԭ.
			}
		}
	}
	
	public static void PrintArr(char[] buf){
		for(int i=0;i<buf.length;i++){//һ��ȫ�������
			System.out.print(buf[i]);
		}
		System.out.println();//һ��ȫ����,һ��
	}
	
	
	public static void swap(char[] buf,int a,int b){
		char temp=buf[a];// ���������һ��Ԫ��buf[start]�������Ԫ��buf[i]
		buf[a]=buf[b];
		buf[b]=temp;
	}

}
