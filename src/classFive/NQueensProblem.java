package classFive;

public class NQueensProblem{

	public static int getNQueensNum(int numbers){
		if(numbers<1){
			return 0;
		}
		int[] record=new int[numbers];
		return computeProcess(0,record,numbers);
	}

	public static int computeProcess(int row,int[] record,int nQueenNum){
		if(row==nQueenNum){//nQueenNum皇后问题
			return 1;
		}
		int result=0;
		for(int col=0;col<nQueenNum;col++){
			if(isValid(record,row,col)){
				record[row]=col;
				result+=computeProcess(row+1,record,nQueenNum);//按行递归,1的个数代表总个数
			}
		}
		return result;
	}

	public static boolean isValid(int[] columns,int row1,int column1){
		for(int row2=0;row2<row1;row2++){//遍历row1之前的行
			int column2=columns[row2];
			if(column1==column2||Math.abs(column2-column1)==Math.abs(row1-row2)){
				return false;
			}
		}
		return true;
	}

	public static int getNQueensNumBetter(int numbers){
		// 因为本方法中位运算的载体是int型变量，所以该方法只能算1~32皇后问题
		// 如果想计算更多的皇后问题，需使用包含更多位的变量
		if(numbers<1||numbers>32){
			return 0;
		}
		int upperLim=numbers==32?-1:(1<<numbers)-1;//-1表示全1，都可放皇后，//9不能放
		return computeProcessBetter(upperLim,0,0,0);//初始时0为无限制
	}

	public static int computeProcessBetter(int upperLim,int colLim,//upperLim能放哪些位置
			int leftDiaLim,int rightDiaLim){//0010000表示放了皇后
		if(colLim==upperLim){
			return 1;
		}
		int pos=0;
		int mostRightOne=0;
		pos=upperLim&(~(colLim|leftDiaLim|rightDiaLim));//列限制,左对角线限制,右对角线限制
		int result=0;
		while(pos!=0){//所有为0退出
			mostRightOne=pos&(~pos+1);//找末尾的1
			pos=pos-mostRightOne;//消末尾的1
			result+=computeProcessBetter(upperLim,colLim|mostRightOne,
					(leftDiaLim|mostRightOne)<<1,(rightDiaLim|mostRightOne)>>>1);//>>>无符号右移
		}//左移，负数，1移出矩阵，
		return result;
	}

	public static void main(String[] args){
		int queensNumber=14;

		long start=System.currentTimeMillis();
		System.out.println(getNQueensNumBetter(queensNumber));
		long end=System.currentTimeMillis();
		System.out.println("Run time1: "+(end-start)+"ms");

		start=System.currentTimeMillis();
		System.out.println(getNQueensNum(queensNumber));
		end=System.currentTimeMillis();
		System.out.println("Run time2: "+(end-start)+"ms");

	}
}
