package classTwo;

public class IsProOrderArray{// L2P15
	// ������һ���������飬�жϸ������ǲ���һ�Ŷ�����������"���"�����Ľ����

	public static boolean isProOrder(int[] arr){//�ؼ���lastLess,firstMore���ݹ�
		if(arr==null||arr.length==0){
			return false;
		}
		return isProOrderArray(arr,0,arr.length-1);
	}

	public static boolean isProOrderArray(int[] arr,int begin,int end){
		// ������һ���������飬�жϸ������ǲ���һ�Ŷ�����������"���"�����Ľ����
		if(begin==end){// ֻ��һ���ڵ�ʱ���϶���:������������"���"�����Ľ����
			return true;
		}
		int lastLess=-1;// ���һ��С�ڸ�ֵ���±�
		int firstMore=Integer.MAX_VALUE;// ��һ�����ڸ�ֵ���±�
		for(int i=begin;i!=end;i++){
			if(arr[i]<arr[end]){// �ȸ��ڵ�СΪ����
				lastLess=i;
			}else{// �ȸ��ڵ��Ϊ�Һ���
				firstMore=Math.min(firstMore,i);// ��һ�����ڸ�ֵ,ȡmin
			}
		}
		if(firstMore==Integer.MAX_VALUE){// ֻ��������
			return isProOrderArray(arr,begin,lastLess);
		}
		if(lastLess==-1){// ֻ��������
			return isProOrderArray(arr,firstMore,end-1);
		}

		if(lastLess!=firstMore-1){// ��"��,������"���±��1
			return false;
		}
		return isProOrderArray(arr,begin,lastLess)
				&&isProOrderArray(arr,firstMore,end-1);
	}

	public static void main(String[] args){
		// int[] arr={5, 7, 6, 9, 11, 10, 8};//��"��,������"���±��1
		int[] arr={5, 7, 6, 9, 11, 10, 8, 16};// �����Һ���,������������"��,�Һ���"��
		System.out.println(isProOrder(arr));
	}

}
