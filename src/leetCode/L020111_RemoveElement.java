package leetCode;

import java.util.HashMap;
import java.util.Map;

public class L020111_RemoveElement {
	public static int removeElement(int[] nums, int val) {
		if (nums == null)
			return 0;
		int cnt,arrIndex=0;
		HashMap<Integer, Integer> timesMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (timesMap.containsKey(nums[i])) {
				cnt = timesMap.get(nums[i]);
				timesMap.put(nums[i], cnt + 1);
			} else {
				timesMap.put(nums[i], 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : timesMap.entrySet()) {
			if (entry.getKey() != val) {// 不往数组里加入
				cnt=entry.getValue();
				while (cnt!=0) {
					nums[arrIndex++]=entry.getKey() ;
					cnt--;
				}
			}
		}

		return arrIndex;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 4, 4, 0, 0, 2, 4, 4 };
		int v = 4;
		// int[] arr={2,1,2,3,1,1};
		// int v=1;
		System.out.println("删除后的长度为" + removeElement(arr, v));
		printArr(arr);
	}

	public static void printArr(int[] arr) {
		System.out.println("删除后的数组为：");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "");
		}
	}

}
