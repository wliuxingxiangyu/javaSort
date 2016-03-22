package classFour;

public class DyeAreaProbleam{

	public static void dyeArea(int[][] matrix,int ri,int ci,int toValue){
		boolean[][] map=new boolean[matrix.length][matrix[0].length];
		dyeProcess(matrix,map,ri,ci,toValue);
	}

	public static void dyeProcess(int[][] matrix,boolean[][] map,int ri,int ci,
			int toValue){
		if(ri==-1||ri==matrix.length||ci==-1||ci==matrix[0].length
				||map[ri][ci]==true){
			return;
		}
		map[ri][ci]=true;
		if(matrix[ri][ci]!=toValue){
			matrix[ri][ci]=toValue;//保证  不走回头路
			dyeProcess(matrix,map,ri-1,ci,toValue);
			dyeProcess(matrix,map,ri+1,ci,toValue);
			dyeProcess(matrix,map,ri,ci-1,toValue);
			dyeProcess(matrix,map,ri,ci+1,toValue);
		}
		return;
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
		int[][] matrix={{2, 3, 1, 1, 1, 4}, {5, 1, 3, 6, 3, 1},
				{7, 1, 6, 3, 1, 1}, {6, 1, 5, 3, 1, 2}, {7, 5, 1, 1, 2, 7},};

		dyeArea(matrix,2,2,1);
		printMatrix(matrix);

	}

}
