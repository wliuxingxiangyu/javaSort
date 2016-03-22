package leetCode;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class L020102RemoveDuplicatesFromSortedArray2 {
	public class Solution {
		public Solution() {
		}

		public int removeDuplicates(int[] nums) {
			if (nums.length == 0) {
				return 0;
			}
			LinkedHashMap<Integer, Integer> map=new LinkedHashMap<Integer, Integer>();
			for (int i = 0; i < nums.length; i++) {
				if (!map.containsKey(nums[i])) {
					map.put(nums[i], 1);
				}else {
					map.put(nums[i], map.get(nums[i]) + 1); 
				}
			}
			//遍历map取出元素，存回数组
			int i=0;
			for (Iterator itkey=map.keySet().iterator();itkey.hasNext();) {
				int key = (int) itkey.next();
				if(map.get(key)>2){
					map.put(key, 2); 
				}
				int value=map.get(key);
				do {
					nums[i++]=key;
					value--;
				} while (value>0);
			}

			return i;
		}
	}

	public static void main(String[] args) {
		L020102RemoveDuplicatesFromSortedArray2 L0 = new 
				L020102RemoveDuplicatesFromSortedArray2();
		Solution so = L0.new Solution();
//		int[] Arr = { 1, 2, 2, 3, 4,4,4,4,5,6 };
		 int[] Arr = { -3,-1,0,0,0,3,3};
		System.out.println("删除前。。。");
		System.out.println("删除后的长度" + so.removeDuplicates(Arr));
		System.out.println("删除后。。。");
		for (int i = 0; i < Arr.length; i++) {
			System.out.print(Arr[i] + "");
		}
	}
}
