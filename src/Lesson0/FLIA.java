package Lesson0;

import java.util.HashSet;//1.set没用到，
// 找最长整合子数组（当元素不同时,最大值-最小值=小组元素个数-1）
public class FLIA { //Find_Longest_Integrated_Array 
    public static int[] findLongestIntegratedArray(int[] array) {
        int begin = 0;
        int longestValue = 0;//初始化最长度=0
        for (int i = 0; i != array.length; i++) {
            int max = array[i];
            int min = array[i];
            HashSet<Integer> set = new HashSet<Integer>();
            for (int j = i; j != array.length; j++) {
                if (set.contains(array[j])) {
                    break;//set有array[j]就跳出for(j),i++后移
                } else {
                    set.add(array[j]);//set无array[j],就加入set
                }
                min = Math.min(min, array[j]);//每次用[i]和[j]比,
                max = Math.max(max, array[j]);
                if (max - min == j - i) {//最长整合数组概念:最大值与最小值之差正好是下标之差,
                	                     //max不一定为[j],min不一定为[i]
                    int currentLongest = j - i + 1;//元素个数=末-起+1
                    if(currentLongest>longestValue){//当前长度>以前最长
                        longestValue = currentLongest;//更新最长
                        begin = i;
                        System.out.print("i="+i+",");//调试
                        System.out.print("j="+j+",");//调试
                        System.out.print("\n");//调试
                    }
                }
            }
        }//找到longestValue和begin后,就知道要求的子数组，
        int[] result = new int[longestValue];
        for (int i = 0; i != result.length; i++) {
            result[i] = array[begin + i];
        }
        return result;
    }
 
    public static void main(String[] args) {
//        int[] test = { 4, 7, 9, 3, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 11 };
        //打印结果LongestIntegratedArray={1,2,3,4,5,6,7,8} 
    	int[] test = { 4, 7, 9, 3, 2, 1, 3, 4, 5, 6, 7, 8, 10, 12, 11 };
    	//打印结果LongestIntegratedArray={2,1,3,4,5,6,7,8} 
//    	int[] test = { 3, 1, 2, 4};
//    	//打印结果LongestIntegratedArray={3,1,2,4} 
        int[] result = findLongestIntegratedArray(test);
        for (int i = 0; i != result.length; i++) {
            System.out.print(result[i] + ",");
        }
    }
}