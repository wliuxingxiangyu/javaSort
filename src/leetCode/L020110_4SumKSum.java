package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L020110_4SumKSum {
	//用KSum但超时，
	public static List<List<Integer>> fourSum(int[] num) {
		if (num == null || num.length == 0)
			return new ArrayList<List<Integer>>();
		Arrays.sort(num);
		return KSum(num, 0, 0, 4);//KSum可以求k个元素的Sum.将4换成k.
	}

	private static List<List<Integer>> KSum(int[] num, int start, int sum,
			int count) {
		if (count == 2)
			return TwoSum(num, start, sum);
		else {
			List<List<Integer>> ret = new ArrayList<List<Integer>>();
			for (int i = start; i <= num.length - count; i++) {
				if (i != start && num[i] == num[i - 1])
					continue;
				List<List<Integer>> sub = KSum(num, i + 1, sum - num[i],
						count - 1);
				if (sub != null) {
					for (List<Integer> cur : sub) {
						ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(num[i]);
						temp.addAll(cur);
						ret.add(temp);
					}
				}
			}
			return ret;
		}
	}
	
	private static List<List<Integer>> TwoSum(int[] num, int start, int sum) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		for (int left = start, right = num.length - 1; left < right;) {
			int total = num[left] + num[right];
			if (total == sum) {
				ArrayList<Integer> cur = new ArrayList<Integer>();
				cur.add(num[left++]);
				cur.add(num[right--]);
				ret.add(cur);
				while (left < right && num[left] == num[left - 1])
					left++;
				while (left < right && num[right] == num[right + 1])
					right--;
			} else if (total < sum) {
				left++;
				while (left < right && num[left] == num[left - 1])
					left++;
			} else {
				right--;
				while (left < right && num[right] == num[right + 1])
					right--;
			}
		}
		return ret;
	}


	public static void main(String[] args) {
		int[] arr = { 1, 0, -1, 0, -2, 2 };
		List<List<Integer>> testll = fourSum(arr);
		if (testll != null) {
			System.out.println("找到如下：");
			for (int i = 0; i < testll.size(); i++) {
				System.out.println("第" + i + "个,如下：");
				List<Integer> subResLL = testll.get(i);
				for (int j = 0; j < subResLL.size(); j++)
					System.out.print(subResLL.get(j) + ",");
				System.out.println();
			}
		} else {
			System.out.println("未 找到");
		}

	}
}
