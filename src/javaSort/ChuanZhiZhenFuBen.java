package javaSort;
class Point2D{
	int x,y;
	public Point2D(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void swap() {
		int temp=x;
		x=y;
		y=temp;
		System.out.println("�����󱻵�swap��x="+x+",y="+y);
	}
}

public class ChuanZhiZhenFuBen {//��ָ�븱��
	public static void main(String[] args) {
		Point2D pnt1 = new Point2D(1,2);
		System.out.println("����ǰ"+pnt1.hashCode());
		System.out.println("����swapǰx="+pnt1.x+",y="+pnt1.y);
		ChuanZhiZhenFuBen c=new ChuanZhiZhenFuBen();
		c.TestFun(pnt1);
		System.out.println("���ú�"+pnt1.hashCode());
		pnt1.swap();
		System.out.println("������main��x="+pnt1.x+",y="+pnt1.y);
	}
	
	public void TestFun(Point2D  arg1) {
		System.out.println("������"+arg1.hashCode());
	}
	
	
	

}
