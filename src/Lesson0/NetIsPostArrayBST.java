package Lesson0;
import java.util.Arrays;
//��������Ҫ�������������������������ص㣺
//1.�������һ��Ԫ��Ϊ���ڵ� 
//2.��ȥ���ڵ�ǰ�沿�ֱַ�Ϊ�������������������ȸ��ڵ�С���������ͱȸ��ڵ�����������
//Ҳ����˵Υ�����������ص�Ķ����Ƕ���������ĺ��������������ж��Ե��ص�ֻ�е�2���ص㣬�����������ȸ��ڵ�С�����������ȸ��ڵ��
public class NetIsPostArrayBST{//������ϵ��жϣ���������ķ���,L2P15
//˼·��������flag=true;����������һ��λ��firstMore;for�ж������������ȸ�С,flag=false;
	public static boolean searchTree(int a[],int length){
		boolean leftFlag=true,rightFlag=true,flag=true;//�ֲ�����
		if(length<=0||a==null){
			return false;// ���鲻���ڣ����ؼ�
		}
		int root=a[length-1];//�ݹ鵽ֻʣһ��Ԫ��ʱ����Ϊroot=a[0];
		int firstMore=0;

		while(a[firstMore]<root){//������"�ȸ�"ȫС
			firstMore++;// �õ����������������ķֽ��ߣ�a[firstMore]Ϊ��������һ��
		}
		
		for(int j=firstMore;j<length-1;++j){//������"�ȸ�"ȫ�� 
			if(a[j]<root){//��֤������"�ȸ�"ȫ��
				flag=false;
				break;
			}
		}
		if(firstMore>0){//��ΪfirstMore��ʼ��0,��0��firstMore-1��ΧΪ��������,��if.
			leftFlag=searchTree(a,firstMore);
		}
		if(firstMore<length-1){
			rightFlag=searchTree(Arrays.copyOfRange(a,firstMore,length-1),length-firstMore-1);
		}//����ֵ�����Ƶõ��������顱����Ϊ�ݹ����searchTree(int a[],int length),û�÷�Χ��ʾ��
		//copyOfRange������length-1����Ԫ�أ������������������ڵ㡣����=(length-2)ĩ��-(firstMore)���+1.
		return flag&&leftFlag&&rightFlag;
	}

	public static void main(String args[]){
//		int a[]={5, 7, 6, 9, 11, 10, 8}; //��������ȫ�� true
//		int a[]={5, 7, 6, 9}; //ֻ�������� true
//		int a[]={9, 11, 10, 8}; //ֻ�������� true
		int a[]={7, 4, 6, 5} ;// false
		System.out.println(searchTree(a,a.length));
	}

}
