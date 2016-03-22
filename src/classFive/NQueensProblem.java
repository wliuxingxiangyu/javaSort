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
		if(row==nQueenNum){//nQueenNum�ʺ�����
			return 1;
		}
		int result=0;
		for(int col=0;col<nQueenNum;col++){
			if(isValid(record,row,col)){
				record[row]=col;
				result+=computeProcess(row+1,record,nQueenNum);//���еݹ�,1�ĸ��������ܸ���
			}
		}
		return result;
	}

	public static boolean isValid(int[] columns,int row1,int column1){
		for(int row2=0;row2<row1;row2++){//����row1֮ǰ����
			int column2=columns[row2];
			if(column1==column2||Math.abs(column2-column1)==Math.abs(row1-row2)){
				return false;
			}
		}
		return true;
	}

	public static int getNQueensNumBetter(int numbers){
		// ��Ϊ��������λ�����������int�ͱ��������Ը÷���ֻ����1~32�ʺ�����
		// �����������Ļʺ����⣬��ʹ�ð�������λ�ı���
		if(numbers<1||numbers>32){
			return 0;
		}
		int upperLim=numbers==32?-1:(1<<numbers)-1;//-1��ʾȫ1�����ɷŻʺ�//9���ܷ�
		return computeProcessBetter(upperLim,0,0,0);//��ʼʱ0Ϊ������
	}

	public static int computeProcessBetter(int upperLim,int colLim,//upperLim�ܷ���Щλ��
			int leftDiaLim,int rightDiaLim){//0010000��ʾ���˻ʺ�
		if(colLim==upperLim){
			return 1;
		}
		int pos=0;
		int mostRightOne=0;
		pos=upperLim&(~(colLim|leftDiaLim|rightDiaLim));//������,��Խ�������,�ҶԽ�������
		int result=0;
		while(pos!=0){//����Ϊ0�˳�
			mostRightOne=pos&(~pos+1);//��ĩβ��1
			pos=pos-mostRightOne;//��ĩβ��1
			result+=computeProcessBetter(upperLim,colLim|mostRightOne,
					(leftDiaLim|mostRightOne)<<1,(rightDiaLim|mostRightOne)>>>1);//>>>�޷�������
		}//���ƣ�������1�Ƴ�����
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
