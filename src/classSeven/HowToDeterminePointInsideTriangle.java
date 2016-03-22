package classSeven;

public class HowToDeterminePointInsideTriangle{

	public static boolean isInside1(double x1,double y1,double x2,double y2,
			double x3,double y3,double x,double y){
		double area1=getAreaOfTriangle(x1,y1,x2,y2,x,y);
		double area2=getAreaOfTriangle(x1,y1,x3,y3,x,y);
		double area3=getAreaOfTriangle(x2,y2,x3,y3,x,y);
		double allArea=getAreaOfTriangle(x1,y1,x2,y2,x3,y3);
		return area1+area2+area3<=allArea;
	}

	public static double getAreaOfTriangle(double x1,double y1,double x2,
			double y2,double x3,double y3){
		double side1Len=getLengthOfside(x1,y1,x2,y2);
		double side2Len=getLengthOfside(x1,y1,x3,y3);
		double side3Len=getLengthOfside(x2,y2,x3,y3);
		double p=(side1Len+side2Len+side3Len)/2;
		return Math.sqrt((p-side1Len)*(p-side2Len)*(p-side3Len)*p);
	}

	public static double getLengthOfside(double x1,double y1,double x2,double y2){
		double a=Math.abs(x1-x2);
		double b=Math.abs(y1-y2);
		return Math.sqrt(a*a+b*b);
	}

	public static boolean isInside2(double x1,double y1,double x2,double y2,
			double x3,double y3,double x,double y){
		if(getCrossProduct(x3-x1,y3-y1,x2-x1,y2-y1)>=0){//»»BºÍc
			double tmpX=x2;
			double tmpY=y2;
			x2=x3;
			y2=y3;
			x3=tmpX;
			y3=tmpY;
		}
		if(getCrossProduct(x2-x1,y2-y1,x-x1,y-y1)<0){//3¸ö²æ³Ë£¬ÅÐ¶Ï×ó²à£¬
			return false;
		}
		if(getCrossProduct(x3-x2,y3-y2,x-x2,y-y2)<0){
			return false;
		}
		if(getCrossProduct(x1-x3,y1-y3,x-x3,y-y3)<0){
			return false;
		}
		return true;
	}

	public static double getCrossProduct(double x1,double y1,double x2,double y2){//²æ³Ë,½«A×ø±êÖÃÁãÁË£¬
		return x1*y2-x2*y1;
	}

	public static void main(String[] args){
		double x1=-5;
		double y1=0;
		double x2=0;
		double y2=8;
		double x3=5;
		double y3=0;
		double x=0;
		double y=5;
		System.out.println(isInside1(x1,y1,x2,y2,x3,y3,x,y));
		System.out.println(isInside2(x1,y1,x2,y2,x3,y3,x,y));

	}

}
