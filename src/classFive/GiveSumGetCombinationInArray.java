package classFive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class GiveSumGetCombinationInArray{
	public static void printCombinationForSum(int[] arr,int given){
		int[] cloneArr=cloneArray(arr); // ��ѡ��Ѵ����������鿽��һ�ݴ��������Ϳ��Ա���ı�ԭʼ����
		Arrays.sort(cloneArr); // �ѿ���������������Ϊ���봦��Ķ���
		LinkedList<Integer> sumList=new LinkedList<Integer>(); // һ�����ڵݹ麯����˵��ȫ�ֱ��������Ը����ҵ�ÿһ�ξ��ߡ�
		HashSet<String> result=new HashSet<String>(); // ����ÿһ�ַ�������Ϊ��HashSet�����Կ��Խ���ͬ�����ȥ��
		findAllResult(0,given,cloneArr,sumList,result); // �ݹ麯�������������
		printResult(result); // ��ӡ����ȥ�صĽ��
	}

	/*
	 * ��Ҫ�ĵݹ麯�� ������ currentIndex : ��0��arr.length-1,��ʾĿǰ�������˵ڼ���Ԫ�� given : ����Ҫ�ҵ��ĺ�
	 * arr : ��С�������������� sumList : �����������Ԫ���ۼӵĽ��������������ѡ����2,2,3,4�ĸ�Ԫ��.
	 * ��ô��sumList�е�ֵΪ,2->4->7->11.
	 * �����Ҳ�������֪��Ŀǰ����ѡ��Ԫ���ܹ��ĺ��Ƕ��٣����ҿ���ͨ���򵥵ļ���������õ�����ЩԪ�ء� result :
	 * ���ҵõ�given��ʱ���Ұ���һ�ַ�����¼��result�С�
	 */
	public static void findAllResult(int currentIndex,int given,int[] arr,
			LinkedList<Integer> sumList,HashSet<String> result){
		if(currentIndex==arr.length){ // ��ʾ�Ѿ����������е�Ԫ�أ�ֱ�ӷ��أ�//currentIndexΪ�ӵ��˵ڼ���
			return;
		}
		int currentSum=(sumList.isEmpty()?0:sumList.getLast())
				+arr[currentIndex]; // ֮ǰ��ѡԪ�ص��ܺͣ����ϱ�������ǰԪ�ص�ֵ��

		/*
		 * �����if����Ҫ��һ�μ�֦����Ϊ�ҵ�arr�Ǵ�С����������ģ�ͬʱgiven��һ����С��0����
		 * ���������֮ǰ��ѡԪ�ص��ܺ�+��ǰԪ�ص�ֵ������given����ô��֮����κ�Ԫ��+֮ǰ��ѡԪ�ص��ܺͣ��������given��
		 * �������ǰѽ������ķ�֧ȫ��ɱ������Ϊ���������߼�չ���������ܵõ�given�ˡ�
		 */
		if(currentSum>given){
			return;
		}

		sumList.add(currentSum);// ������ǰԪ��+֮ǰ��ѡԪ�ص��ܺͣ���ֵ֮ǰ����sumList����ʾ��ǰ�������ǰԪ�ء�
		if(sumList.getLast()==given){ // ���������ˣ����ǵõ���given
			updateResult(sumList,result); // ��¼��ǰ��
		}
		findAllResult(currentIndex+1,given,arr,sumList,result);// ��ǰ�������ǰԪ��,�����߼�չ��
		sumList.removeLast();// ����ǰԪ�ز���Ϊ��ǰ���һ����
		findAllResult(currentIndex+1,given,arr,sumList,result);// ��ǰ�ⲻ������ǰԪ��,�����߼�չ��
	}

	/*
	 * �򵥵Ŀ�������ĺ��� ΪʲôҪ������һ�ݴ����أ� Ϊ�˼�֦����������������Ҫһ������������飬�������ֱ�Ӱ�ԭʼ�������򲢲����ʡ�
	 * �ٸ����ӣ�Ҳ����������뿴���Լ��������ж����ַ����������givenֵ�� Ȼ����������ҵĺ����� Ȼ���㷢�����ԭʼ���鱻��������...
	 * ��᲻�������أ� ��Ϊ��ֻ���뿴��Ҫ�Ľ���������������������򰡡�
	 */
	public static int[] cloneArray(int[] arr){
		int[] result=new int[arr.length];
		for(int i=0;i!=arr.length;i++){
			result[i]=arr[i];
		}
		return result;
	}

	/*
	 * ��sumList�е�״̬����ԭ���������״̬�� ����sumListĿǰ��״̬��2->4->7->11 ���״̬���ǣ�2 2 3 4
	 * ע��sumList�е�ÿһ��Ԫ�صĺ����ǣ�֮ǰ��ѡԪ�ص��ۼ�ֵ
	 */
	public static void updateResult(LinkedList<Integer> sumList,
			HashSet<String> result){
		int previousSum=0;
		String currentResult="";
		for(Integer currentSum:sumList){
			currentResult+=String.valueOf(currentSum-previousSum)+" ";
			previousSum=currentSum;
		}
		result.add(currentResult);// result����һ���µļ�¼����Ϊ��HashSet��������Ȼ��ȥ���ظ���
	}

	public static void printResult(HashSet<String> result){
		for(String record:result){
			System.out.println(record);
		}
	}

	public static void main(String[] args){
		int[] test={5, 2, 6, 3, 4, 2, -1};
		int given=10;
		printCombinationForSum(test,given);
	}
}
