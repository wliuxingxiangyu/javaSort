package classFour;

public class IslandProblem{

	public static int getIslandNum(int[][] map){
		if(map==null||map.length<3||map[0].length<3){
			return 0;
		}
		int islandNum=0;
		boolean[][] stat=new boolean[map.length][map[0].length];//标记感染
		for(int i=1;i!=map.length-1;i++){//从第1排，就不用考虑越界
			for(int j=1;j!=map[0].length-1;j++){
				if((!stat[i][j])&&isIslandCenter(map,i,j)){
					islandNum++;
					infectProcess(map,stat,i,j);
				}
			}
		}
		return islandNum;
	}

	public static boolean isIslandCenter(int[][] map,int r,int c){
		if(map[r][c]==1&&map[r-1][c-1]==1&&map[r-1][c]==1&&map[r-1][c+1]==1
				&&map[r][c-1]==1&&map[r][c+1]==1&&map[r+1][c-1]==1
				&&map[r+1][c]==1&&map[r+1][c+1]==1){
			return true;
		}else{
			return false;
		}
	}

	public static void infectProcess(int[][] map,boolean[][] stat,int r,int c){//感染过程
		stat[r][c]=true;
		if(r!=0&&(!stat[r-1][c])&&map[r-1][c]==1){//!stat[r-1][c]可防止走回头路
			infectProcess(map,stat,r-1,c);
		}
		if(c!=0&&(!stat[r][c-1])&&map[r][c-1]==1){
			infectProcess(map,stat,r,c-1);
		}
		if(r!=map.length-1&&(!stat[r+1][c])&&map[r+1][c]==1){
			infectProcess(map,stat,r+1,c);
		}
		if(c!=map[0].length-1&&(!stat[r][c+1])&&map[r][c+1]==1){
			infectProcess(map,stat,r,c+1);
		}

	}

	public static int[][] generateIslandMap(int rowSize,int colSize){
		if(rowSize<2||colSize<2){
			return null;
		}
		int[][] map=new int[rowSize][colSize];
		int setTimes=(int)(Math.random()*10)+1;
		while(setTimes--!=0){
			int row=(int)(Math.random()*(rowSize-2))+1;
			int col=(int)(Math.random()*(colSize-2))+1;
			setIsland(map,row,col);
		}
		return map;
	}

	public static void setIsland(int[][] map,int row,int col){
		map[row][col]=1;
		map[row-1][col-1]=1;
		map[row-1][col]=1;
		map[row-1][col+1]=1;
		map[row][col-1]=1;
		map[row][col+1]=1;
		map[row+1][col-1]=1;
		map[row+1][col]=1;
		map[row+1][col+1]=1;
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
		int[][] map=generateIslandMap(10,10);
		printMatrix(map);
		System.out.println(getIslandNum(map));

	}

}
