//��������arr[]��ֻ����0��1����һ�����0��1������ȵ��Ӵ�
package Lesson0;
import java.util.HashMap;
public class ZeroEqualOne{
	public static void printLongestSubArrayRangeContainSameOand1(int[] arr) {
	//�������ã���arrֻ��0��1ӳ���tmpArr[](ֻ��-1��1)
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
 
    public static void printTargetSumMaxSubArrRange(int[] tmpArr, int target){//��ӡ ��Ϊtarget ����������鷶Χ
        HashMap<Integer, Integer> valueMinIndexMap = new HashMap<Integer, Integer>();
        valueMinIndexMap.put(0, -1);//put(key, value)��(sum=0, i=-1)��ʼ��ʱ  Ԫ�غ�Ϊ0��  i��ʱ��Ϊ-1,
        int beginIndex = 0;//��Χ����ʼ�±� ��ʼ�� Ϊ0  
        int endIndex = -1; //��Χ�ҽ����±� ��ʼ�� Ϊ-1 
        int sum = 0;
        for (int i = 0; i != tmpArr.length; i++) {
			System.out.println("------------------------");//���Խ�ÿ��i�ֿ�
            sum += tmpArr[i];//0��i��tmpArr[i]�ۼӺ�
			System.out.println(i+"sum="+sum);//����
            if(valueMinIndexMap.containsKey(sum-target)){//��ϣ�����sum-target,����minIndex=ԭvalue,��֤�������,
			    // System.out.println(i+"containsKey(sum - target)="+valueMinIndexMap.containsKey(sum - target));//����
                int minIndex = valueMinIndexMap.get(sum - target);//get(key):����ָ������ӳ���ֵvalue��
				System.out.println(i+"minIndex="+minIndex);//����
				if (i - minIndex > endIndex - beginIndex + 1){//��1����ΪminIndex��ʼ��Ϊ-1//ĩ��-����+1=Ԫ�ظ���>0
                    beginIndex = minIndex + 1;//��1����Ϊsum(i)+target=sum(j),��i+1��jԪ�����
                    endIndex = i;
					System.out.println(i+"beginIndex="+beginIndex);//����
					System.out.println(i+"endIndex="+endIndex);//����
					//if((sum==target)&&(i-minIndex>endIndex-beginIndex+1))//�Լ�д��sum==targetһ���Ǵ�i=0��ʼ,����,��Ϊ������6~19��
					// {beginIndex = minIndex+1;
					// endIndex = i;
					// System.out.println(i+"endIndex="+endIndex);//����	
					// System.out.println(i+"beginIndex="+beginIndex);//����
					// }				
				}
            }
            if (!valueMinIndexMap.containsKey(sum - target)){//��ϣ������sum-target,��(sum,i)��ӵ���ϣ���С�
			// System.out.println(i+"containsKey(sum - target)="+valueMinIndexMap.containsKey(sum - target));
                valueMinIndexMap.put(sum, i);//HashMap��key�������ظ�ֵ������key=sum
            }
        }
		
        if (endIndex - beginIndex + 1 > 0) {//ĩ��-����+1=Ԫ�ظ���>0
            System.out.println("Max range [" + beginIndex + "," + endIndex
                    + "]");
        } else {
            System.out.println("Range is not exist.");
        }
 
    }
 
/*     public static int[] generateArray(int size) { //��������
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
		int[] arr = {0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,0,0,1,1}; //�����ϳ�
		// int[] arr = {0,1,0,0,0,0,1,1,1,1};
        printArray(arr);
        printLongestSubArrayRangeContainSameOand1(arr);
 
    }
 
}

