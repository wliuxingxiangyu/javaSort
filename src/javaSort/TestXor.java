package javaSort;

public class TestXor{

	public static void main(String[] args){
		int x=3;
		int y=4;
		System.out.print("����ǰx="+x);		
		System.out.print("����ǰy="+y);
		System.out.println(" ");
		
		x=x^y;
		y=x^y;//������ԭy,ʣ��ԭx,����y
		System.out.print("������x="+x);
		System.out.print("������y="+y);
		x=x^y;//������ԭx,ʣ��ԭy,����x
		//3�������Ϊx��y��x,�ұ���ͬΪx^y��
		
		System.out.println(" ");
		System.out.print("������x="+x);
		System.out.print("������y="+y);
	
		
		
		
		
		
		
		
	}	
	
	
}
