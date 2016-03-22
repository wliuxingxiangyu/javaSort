package classFour;

public class FibonacciNumberProblem{

	public static int getFibNumSolution1(int n){//暴力递归
		if(n<1){
			return 0;
		}
		if(n==1||n==2){
			return 1;
		}
		return getFibNumSolution1(n-1)+getFibNumSolution1(n-2);
	}

	public static int getFibNumSolution2(int n){//只需两个变量
		if(n<1){
			return 0;
		}
		if(n==1||n==2){
			return 1;
		}
		int previous=1;//只需两个变量
		int previousPrevious=1;
		for(int i=3;i!=n;i++){
			int tmp=previous;
			previous=previous+previousPrevious;
			previousPrevious=tmp;
		}
		return previous+previousPrevious;
	}

	public static int getFibNumSolution3(int n){//矩阵乘法:固定的状态迁移
		if(n<1){
			return 0;
		}
		if(n==1||n==2){
			return 1;
		}
		int[][] matrix={{1,1},{1,0}};
		int[][] resultMatrix=matrixPow(matrix,n-2);//矩阵幂次,将n-2拆二进制
		return resultMatrix[0][0]+resultMatrix[1][0];//fn=f(n-1)+f(n-2)
	}

	public static int[][] matrixPow(int[][] matrix,int times){
		int[][] result=new int[matrix.length][matrix[0].length];
		for(int i=0;i!=result.length;i++){
			result[i][i]=1;
		}
		int[][] tmp=matrix;
		for(;times!=0;times>>=1){//右移,n-2=times不可能为负，
			if((times&1)!=0){//末尾不为1
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
		for(int i=0;i!=23;i++){
			System.out.println(getFibNumSolution1(i));
			System.out.println(getFibNumSolution2(i));
			System.out.println(getFibNumSolution3(i));
			System.out.println("==========================");
		}
	}

}
