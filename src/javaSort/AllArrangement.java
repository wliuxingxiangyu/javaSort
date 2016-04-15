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
			PrintArr(buf);
		}else{// 多个字母全排列,循环中交换,递归交换,
			for(int i=start;i<=end;i++){//该for循环为了交换start和i
				swap(buf,start,i);//第一次i=start换与没换一样.
				perm(buf,start+1,end);//start+1后续元素递归全排列.
				swap(buf,start,i);// 将交换后的数组还原.
			}
		}
	}
	
	public static void PrintArr(char[] buf){
		for(int i=0;i<buf.length;i++){//一个全排列输出
			System.out.print(buf[i]);
		}
		System.out.println();//一个全排列,一行
	}
	
	
	public static void swap(char[] buf,int a,int b){
		char temp=buf[a];// 交换数组第一个元素buf[start]与后续的元素buf[i]
		buf[a]=buf[b];
		buf[b]=temp;
	}

}
