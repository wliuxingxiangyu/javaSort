package javaSort;

public class AllArrangement{// 全排列
	public static int count=0;// 计数
	public static void main(String[] args){
		char buf[]={'a', 'b', 'c'};

		perm(buf,0,buf.length-1);
	}

	public static void perm(char[] buf,int start,int end){// permutation排列
		if(start==end){// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
			System.out.print(++count+".");// 计数1.2.3..
			System.out.print("buf.toString():"+buf.toString()+"--");
			for(int i=0;i<=end;i++){
				System.out.print(buf[i]);
			}
			System.out.println();//一个全排列,一行
		}else{// 多个字母全排列
			for(int i=start;i<=end;i++){
				char temp=buf[start];// 交换数组第一个元素buf[start]与后续的元素buf[i]
				buf[start]=buf[i];
				buf[i]=temp;

				perm(buf,start+1,end);// 后续元素递归全排列

				temp=buf[start];// 将交换后的数组还原
				buf[start]=buf[i];
				buf[i]=temp;
			}
		}
	}

}
