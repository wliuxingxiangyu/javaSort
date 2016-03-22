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
		System.out.println("交换后被调swap中x="+x+",y="+y);
	}
}

public class ChuanZhiZhenFuBen {//传指针副本
	public static void main(String[] args) {
		Point2D pnt1 = new Point2D(1,2);
		System.out.println("调用前"+pnt1.hashCode());
		System.out.println("调用swap前x="+pnt1.x+",y="+pnt1.y);
		ChuanZhiZhenFuBen c=new ChuanZhiZhenFuBen();
		c.TestFun(pnt1);
		System.out.println("调用后"+pnt1.hashCode());
		pnt1.swap();
		System.out.println("交换后main中x="+pnt1.x+",y="+pnt1.y);
	}
	
	public void TestFun(Point2D  arg1) {
		System.out.println("调用中"+arg1.hashCode());
	}
	
	
	

}
