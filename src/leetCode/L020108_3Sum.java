package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class L020108_3Sum {//�Լ�д�ģ�û��Ԥ����������leetcode��ʱ.
	public static List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		List<List<Integer>> resLL = new ArrayList<List<Integer>>();
		for (int i = 0; i < nums.length - 2; i++) {
			twoSum(nums, 0 - nums[i],i, nums[i], resLL);
		}
		return resLL;
	}

	public static void twoSum(int[] nums, int target, int firstIndex,
			int firstElem,List<List<Integer>> resLL) {// ��������
		if (nums == null || nums.length == 0) {
			return;
		}
		LinkedHashMap<Integer, Integer> hMap = new LinkedHashMap<>();
		for (int i = firstIndex+1; i < nums.length; i++) {
			hMap.put(nums[i], i);
		}
		for (int i = firstIndex+1; i < nums.length; i++) {
			int gap = target - nums[i];
			if (hMap.containsKey(gap)) {
				int gapV = hMap.get(gap);
				if (gapV > i) {// �˴����ܵȺ�
					//�����for�жϣ�-1,0,1���ͣ�0,1,-1���ظ�
					for (int j = 0; j < resLL.size(); j++) {
						List<Integer> subResL=resLL.get(j);
						if(subResL.contains(nums[i])&&subResL.contains(gap)){
							return;
						}
					}
					List<Integer> subResL2 = new ArrayList<Integer>();
					int sum=firstElem+nums[i]+gap;
					int min=Math.min(firstElem, Math.min(nums[i],gap));
					int max=Math.max(firstElem, Math.max(nums[i],gap));
					subResL2.add(min);
					subResL2.add(sum-min-max);
					subResL2.add(max);
					resLL.add(subResL2);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { -1,0,1,2,-1,-4 };
		Arrays.sort(arr);
		List<List<Integer>> testll=threeSum(arr);
		if(testll != null){
			System.out.println("�ҵ����£�");
			for (int i = 0; i < testll.size(); i ++) {
				System.out.println("��"+i +"��,���£�");
				List<Integer> subResLL=testll.get(i);
				for(int j=0;j<subResLL.size();j++)
				    System.out.print(subResLL.get(j)+",");
				System.out.println();
			}
		}else {
			System.out.println("δ �ҵ�");
		}
	}
}
