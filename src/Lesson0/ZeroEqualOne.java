//输入数组arr[]，只包含0，1，求一个最长且0和1个数相等的子串
package Lesson0;
import java.util.HashMap;
public class ZeroEqualOne{
	public static void printLongestSubArrayRangeContainSameOand1(int[] arr) {
	//函数作用：将arr只含0或1映射成tmpArr[](只含-1或1)
        int[] tmpArr = new int[arr.length];
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] == 0) {
                tmpArr[i] = -1;
            } else if (arr[i] == 1) {
                tmpArr[i] = 1;
            } else {
                System.out.println("Your array should only contains 0 or 1.");
            }
        }
        printTargetSumMaxSubArrRange(tmpArr, 0);
    }
 
    public static void printTargetSumMaxSubArrRange(int[] tmpArr, int target){//打印 和为target 的最大子数组范围
        HashMap<Integer, Integer> valueMinIndexMap = new HashMap<Integer, Integer>();
        valueMinIndexMap.put(0, -1);//put(key, value)即(sum=0, i=-1)初始化时  元素和为0的  i暂时赋为-1,
        int beginIndex = 0;//范围左起始下标 初始化 为0  
        int endIndex = -1; //范围右结束下标 初始化 为-1 
        int sum = 0;
        for (int i = 0; i != tmpArr.length; i++) {
			System.out.println("------------------------");//调试将每个i分开
            sum += tmpArr[i];//0到i的tmpArr[i]累加和
			System.out.println(i+"sum="+sum);//调试
            if(valueMinIndexMap.containsKey(sum-target)){//哈希表包含sum-target,赋予minIndex=原value,保证最长子数组,
			    // System.out.println(i+"containsKey(sum - target)="+valueMinIndexMap.containsKey(sum - target));//调试
                int minIndex = valueMinIndexMap.get(sum - target);//get(key):返回指定键所映射的值value；
				System.out.println(i+"minIndex="+minIndex);//调试
				if (i - minIndex > endIndex - beginIndex + 1){//加1，因为minIndex初始化为-1//末标-初标+1=元素个数>0
                    beginIndex = minIndex + 1;//加1，因为sum(i)+target=sum(j),是i+1到j元素求和
                    endIndex = i;
					System.out.println(i+"beginIndex="+beginIndex);//调试
					System.out.println(i+"endIndex="+endIndex);//调试
					//if((sum==target)&&(i-minIndex>endIndex-beginIndex+1))//自己写的sum==target一定是从i=0开始,不对,因为可以是6~19，
					// {beginIndex = minIndex+1;
					// endIndex = i;
					// System.out.println(i+"endIndex="+endIndex);//调试	
					// System.out.println(i+"beginIndex="+beginIndex);//调试
					// }				
				}
            }
            if (!valueMinIndexMap.containsKey(sum - target)){//哈希表不包含sum-target,将(sum,i)添加到哈希表中。
			// System.out.println(i+"containsKey(sum - target)="+valueMinIndexMap.containsKey(sum - target));
                valueMinIndexMap.put(sum, i);//HashMap用key控制无重复值，本例key=sum
            }
        }
		
        if (endIndex - beginIndex + 1 > 0) {//末标-初标+1=元素个数>0
            System.out.println("Max range [" + beginIndex + "," + endIndex
                    + "]");
        } else {
            System.out.println("Range is not exist.");
        }
 
    }
 
/*     public static int[] generateArray(int size) { //产生数组
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 2);
        }
        return result;
    } */
 
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
 
    public static void main(String[] args) {
         //int[] arr = generateArray(20);
		int[] arr = {0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,0,0,1,1}; //用例较长
		// int[] arr = {0,1,0,0,0,0,1,1,1,1};
        printArray(arr);
        printLongestSubArrayRangeContainSameOand1(arr);
 
    }
 
}

