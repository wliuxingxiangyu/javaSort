package leetCode;

public class L020116RotateImage {
    public static void rotate(int[][] matrix) {
        int row = matrix.length;//一层一层的转
        int layers = row/2;//,,3/2=1,4/2=2;
        for(int i=0;i<layers; i++){
            for(int j=i;j<row-1-i;j++){//
                int temp = matrix[i][j];
                matrix[i][j] = matrix[row-1-j][i];
                matrix[row-1-j][i] = matrix[row-1-i][row-1-j];
                matrix[row-1-i][row-1-j] = matrix[j][row-1-i];
                matrix[j][row-1-i] = temp;
            }
        }
    }

	public static void main(String[] args) {
		int[][] arr={{1,2,3},{4,5,6}};
		System.out.println("arr.length="+arr.length);
		rotate(arr);
//		int[][] arr=new int[2][3];
//		arr={{1,2,3},{4,5,6}};
	}

}
