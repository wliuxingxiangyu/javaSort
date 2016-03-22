package leetCode;

import java.util.HashSet;

public class L020106LongestConsecutiveSequence {
	public static int longestConsecutive(int[] num) {
		if (num.length == 0)
			return 0;
		if (num.length == 1)
			return 1;
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (int i = 0; i < num.length; i++) {//将所有的数字加入set
			hashSet.add(num[i]);
		}
		int res = 0;
		
		for (int i = 0; i < num.length; i++) {
			int temLen = 1, nearNum = num[i] + 1;//num[i]右边的数
			while (hashSet.contains(nearNum)) {
				hashSet.remove(nearNum);//别看有for嵌套while,看似O(n*n),但考虑4时123已删除了
				temLen++;
				nearNum++;//nearNum减减加加，再进入while
			}
			nearNum = num[i] - 1;//num[i]右边的数
			while (hashSet.contains(nearNum)) {
				hashSet.remove(nearNum);
				temLen++;
				nearNum--;//nearNum减减，再进入while
			}
			if (temLen > res)
				res = temLen;//更新最后返回的“最大长度”
			if (res >= num.length)
				break;//超过数组最大长度，满了“已找到”，退出for
		}
		
		return res;
	}

	public static void main(String[] args) {
		int[] A = { 100, 4, 200, 1, 3, 2};
		System.out.println("最长连续字串长度：" + longestConsecutive(A));
	}

}
