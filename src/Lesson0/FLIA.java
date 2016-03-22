package Lesson0;

import java.util.HashSet;//1.setû�õ���
// ������������飨��Ԫ�ز�ͬʱ,���ֵ-��Сֵ=С��Ԫ�ظ���-1��
public class FLIA { //Find_Longest_Integrated_Array 
    public static int[] findLongestIntegratedArray(int[] array) {
        int begin = 0;
        int longestValue = 0;//��ʼ�����=0
        for (int i = 0; i != array.length; i++) {
            int max = array[i];
            int min = array[i];
            HashSet<Integer> set = new HashSet<Integer>();
            for (int j = i; j != array.length; j++) {
                if (set.contains(array[j])) {
                    break;//set��array[j]������for(j),i++����
                } else {
                    set.add(array[j]);//set��array[j],�ͼ���set
                }
                min = Math.min(min, array[j]);//ÿ����[i]��[j]��,
                max = Math.max(max, array[j]);
                if (max - min == j - i) {//������������:���ֵ����Сֵ֮���������±�֮��,
                	                     //max��һ��Ϊ[j],min��һ��Ϊ[i]
                    int currentLongest = j - i + 1;//Ԫ�ظ���=ĩ-��+1
                    if(currentLongest>longestValue){//��ǰ����>��ǰ�
                        longestValue = currentLongest;//�����
                        begin = i;
                        System.out.print("i="+i+",");//����
                        System.out.print("j="+j+",");//����
                        System.out.print("\n");//����
                    }
                }
            }
        }//�ҵ�longestValue��begin��,��֪��Ҫ��������飬
        int[] result = new int[longestValue];
        for (int i = 0; i != result.length; i++) {
            result[i] = array[begin + i];
        }
        return result;
    }
 
    public static void main(String[] args) {
//        int[] test = { 4, 7, 9, 3, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 11 };
        //��ӡ���LongestIntegratedArray={1,2,3,4,5,6,7,8} 
    	int[] test = { 4, 7, 9, 3, 2, 1, 3, 4, 5, 6, 7, 8, 10, 12, 11 };
    	//��ӡ���LongestIntegratedArray={2,1,3,4,5,6,7,8} 
//    	int[] test = { 3, 1, 2, 4};
//    	//��ӡ���LongestIntegratedArray={3,1,2,4} 
        int[] result = findLongestIntegratedArray(test);
        for (int i = 0; i != result.length; i++) {
            System.out.print(result[i] + ",");
        }
    }
}