package Lesson0;

//给定一个n*n的矩阵A，每一行和每一列都是有序的,在矩阵中找任意一个数，不存在则返回-1。
//要求算法复杂度O(n)
public class SeaEle { //Searching_Element_In_2D_Sorted_Matrix
    public static void findAimInMatrix(int[][] test, int aim) {
        int indexRow = 0;
        int indexCol = test[0].length - 1;//即test列数，将从数组的右上角开始比较。
        while (indexRow < test.length && indexCol >= 0) {
            if (test[indexRow][indexCol] == aim) {
                break;
            }
            if (test[indexRow][indexCol] > aim) {//因为测试用例是行列均升序，
                indexCol--;//aim与每一行的未比较的最大数比较,若元素大，列标减减，
            } else {
                indexRow++;//若元素小，行标加加，
            }
        }
        if (indexRow == test.length || indexCol == -1) {
            System.out.println("No exist!");
        } else {
            System.out.println("Position, Row: " + indexRow + " Col:"
                    + indexCol);
        }
    }
 
    public static void main(String[] args) {
        int[][] test = { { 1, 4, 7, 11, 15 }, 
                         { 2, 5, 8, 12, 19 },
                         { 3, 6, 9, 16, 22 }, 
                         { 10, 13, 14, 17, 24 },
                         { 18, 21, 23, 26, 30 } 
                        };
        int aim = 14;
        findAimInMatrix(test, aim);
 
    }
 }