package leetCode;

import java.util.Arrays;
import java.util.List;

public class L020110_4Sum {
	public static List<List<Integer>> fourSum(int[] num, int target) {
		if (num == null || num.length < 0)
			return null;
		Arrays.sort(num);
		for (int a = 0; a < num.length; a++) {
			int j = num[a];
			
		}
		
		return null;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 0, -1, 0, -2, 2 };
		List<List<Integer>> testll = fourSum(arr, 0);
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
