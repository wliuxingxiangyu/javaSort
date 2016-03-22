package classFour;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PaintFillFunction{//p7

	public static void transChangeValue(int[][] matrix,int row,int col,
			int newValue){
		if(row<0||row>=matrix.length||col<0||col>=matrix[0].length){
			return;
		}
		if(matrix[row][col]!=newValue){
			transProcess(matrix,row,col,matrix[row][col],newValue);
		}
	}

	public static void transProcess(int[][] matrix,int row,int col,
			int oldValue,int newValue){
		if(matrix[row][col]==oldValue){//不重复感染,
			matrix[row][col]=newValue;
			if(row!=0){//不是第一行。有上邻居
				transProcess(matrix,row-1,col,oldValue,newValue);
			}
			if(row!=matrix.length-1){//不是最后一行。有下邻居
				transProcess(matrix,row+1,col,oldValue,newValue);
			}
			if(col!=0){
				transProcess(matrix,row,col-1,oldValue,newValue);
			}
			if(col!=matrix[0].length-1){
				transProcess(matrix,row,col+1,oldValue,newValue);
			}
		}
	}

	public static void transChangeValueUnRecursive(int[][] matrix,int row,
			int col,int newValue){
		if(row<0||row>=matrix.length||col<0||col>=matrix[0].length){
			return;
		}
		int oldValue=matrix[row][col];
		if(oldValue==newValue){
			return;
		}
		HashSet<String> hasVisited=new HashSet<String>();
		Queue<Integer> rowQ=new LinkedList<Integer>();
		Queue<Integer> colQ=new LinkedList<Integer>();
		hasVisited.add(String.valueOf(row+"+"+col));
		rowQ.add(row);
		colQ.add(col);
		while(!rowQ.isEmpty()){
			int curR=rowQ.poll();
			int curC=colQ.poll();
			matrix[curR][curC]=newValue;
			if(curR!=0&&matrix[curR-1][curC]==oldValue){
				String upPosition=String.valueOf((curR-1)+"+"+curC);
				if(!hasVisited.contains(upPosition)){
					hasVisited.add(upPosition);
					rowQ.add(curR-1);
					colQ.add(curC);
				}
			}
			if(curR!=matrix.length-1&&matrix[curR+1][curC]==oldValue){
				String downPosition=String.valueOf((curR+1)+"+"+curC);
				if(!hasVisited.contains(downPosition)){
					hasVisited.add(downPosition);
					rowQ.add(curR+1);
					colQ.add(curC);
				}
			}
			if(curC!=0&&matrix[curR][curC-1]==oldValue){
				String leftPosition=String.valueOf(curR+"+"+(curC-1));
				if(!hasVisited.contains(leftPosition)){
					hasVisited.add(leftPosition);
					rowQ.add(curR);
					colQ.add(curC-1);
				}
			}
			if(curC!=matrix[0].length-1&&matrix[curR][curC+1]==oldValue){
				String rightPosition=String.valueOf(curR+"+"+(curC+1));
				if(!hasVisited.contains(rightPosition)){
					hasVisited.add(rightPosition);
					rowQ.add(curR);
					colQ.add(curC+1);
				}
			}
		}
	}

	public static int[][] generateRandomMatrix(int rowSize,int colSize,
			int maxValue){
		int[][] matrix=new int[rowSize][colSize];
		for(int i=0;i!=matrix.length;i++){
			for(int j=0;j!=matrix[0].length;j++){
				matrix[i][j]=(int)(Math.random()*(maxValue+1));
			}
		}
		return matrix;
	}

	public static void printMatrix(int[][] matrix){
		for(int i=0;i!=matrix.length;i++){
			for(int j=0;j!=matrix[0].length;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		int[][] matrix=generateRandomMatrix(4,4,2);
		int originalRow=0;
		int originalCol=1;

		System.out.println("Original matrix:");
		printMatrix(matrix);

		transChangeValue(matrix,originalRow,originalCol,3);
		System.out.println("Change matrix by recursive:");
		printMatrix(matrix);

		transChangeValueUnRecursive(matrix,originalRow,originalCol,5);
		System.out.println("Change matrix by unrecursive:");
		printMatrix(matrix);

	}
}
