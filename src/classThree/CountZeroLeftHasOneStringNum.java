package classThree;

public class CountZeroLeftHasOneStringNum{//L3P1。。0左边右1

	public static int getValidStatusNum1(int length){
		if(length<1){
			return 0;
		}
		char[] charArr=new char[length];
		charArr[0]='1';
		for(int i=1;i!=charArr.length;i++){
			charArr[i]='0';
		}
		int result=isValid(charArr)?1:0;
		int addTime=(1<<(length-1))-1;
		for(int i=0;i!=addTime;i++){
			addOneToCharArr(charArr);
			if(isValid(charArr)){
				result++;
			}
		}
		return result;
	}

	public static void addOneToCharArr(char[] charArr){
		for(int i=charArr.length-1;i!=0;i--){
			if(charArr[i]=='0'){
				charArr[i]='1';
				break;
			}else{
				charArr[i]='0';
			}
		}
	}

	public static boolean isValid(char[] charArr){
		for(int i=1;i!=charArr.length;i++){
			if(charArr[i]=='0'&&charArr[i-1]=='0'){
				return false;
			}
		}
		return true;
	}

	public static int getValidStatusNum2(int length){
		if(length<1){
			return 0;
		}
		return computeProcess(1,length);
	}

	public static int computeProcess(int index,int length){
		if(index==length-1){//2个终止条件
			return 2;
		}
		if(index==length){
			return 1;
		}
		return computeProcess(index+1,length)+computeProcess(index+2,length);
	}

	public static int getValidStatusNum3(int length){//动态规划
		if(length<1){
			return 0;
		}
		if(length==1){
			return 1;
		}
		int previous=1;
		int current=1;
		for(int i=2;i!=length+1;i++){
			int tmp=current;
			current+=previous;
			previous=tmp;
		}
		return current;
	}

	public static int getValidStatusNum4(int length){//矩阵乘法
		if(length<1){
			return 0;
		}
		if(length==1||length==2){
			return length;
		}
		int[][] matrix={{1,1},{1,0}};
		int[][] resultMatrix=matrixPow(matrix,length-2);
		return 2*resultMatrix[0][0]+resultMatrix[1][0];
	}

	public static int[][] matrixPow(int[][] matrix,int times){
		int[][] result=new int[matrix.length][matrix[0].length];
		for(int i=0;i!=result.length;i++){
			result[i][i]=1;
		}
		int[][] tmp=matrix;
		for(;times!=0;times>>=1){//逐渐升阶，O(logn),java只有三种移位运算>>（是否包含符号位）,>>>,<<
			if((times&1)!=0){
				result=matrixMuliMatrix(result,tmp);
			}
			tmp=matrixMuliMatrix(tmp,tmp);
		}
		return result;
	}

	public static int[][] matrixMuliMatrix(int[][] matrix1,int[][] matrix2){
		int[][] result=new int[matrix1.length][matrix2[0].length];
		for(int i=0;i!=matrix2[0].length;i++){
			for(int j=0;j!=matrix1.length;j++){
				int resultIJ=0;
				for(int k=0;k!=matrix2.length;k++){
					resultIJ+=matrix1[i][k]*matrix2[k][j];
				}
				result[i][j]=resultIJ;
			}
		}
		return result;
	}

	public static void main(String[] args){
		for(int i=0;i!=20;i++){
			System.out.println(getValidStatusNum1(i));
			System.out.println(getValidStatusNum2(i));
			System.out.println(getValidStatusNum3(i));
			System.out.println(getValidStatusNum4(i));
			System.out.println("===================");

		}

	}
}
