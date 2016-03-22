package Lesson0;

//����һ��n*n�ľ���A��ÿһ�к�ÿһ�ж��������,�ھ�����������һ�������������򷵻�-1��
//Ҫ���㷨���Ӷ�O(n)
public class SeaEle { //Searching_Element_In_2D_Sorted_Matrix
    public static void findAimInMatrix(int[][] test, int aim) {
        int indexRow = 0;
        int indexCol = test[0].length - 1;//��test������������������Ͻǿ�ʼ�Ƚϡ�
        while (indexRow < test.length && indexCol >= 0) {
            if (test[indexRow][indexCol] == aim) {
                break;
            }
            if (test[indexRow][indexCol] > aim) {//��Ϊ�������������о�����
                indexCol--;//aim��ÿһ�е�δ�Ƚϵ�������Ƚ�,��Ԫ�ش��б������
            } else {
                indexRow++;//��Ԫ��С���б�Ӽӣ�
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