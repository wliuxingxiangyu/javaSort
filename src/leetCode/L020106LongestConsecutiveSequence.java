package leetCode;

import java.util.HashSet;

public class L020106LongestConsecutiveSequence {
	public static int longestConsecutive(int[] num) {
		if (num.length == 0)
			return 0;
		if (num.length == 1)
			return 1;
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (int i = 0; i < num.length; i++) {//�����е����ּ���set
			hashSet.add(num[i]);
		}
		int res = 0;
		
		for (int i = 0; i < num.length; i++) {
			int temLen = 1, nearNum = num[i] + 1;//num[i]�ұߵ���
			while (hashSet.contains(nearNum)) {
				hashSet.remove(nearNum);//����forǶ��while,����O(n*n),������4ʱ123��ɾ����
				temLen++;
				nearNum++;//nearNum�����Ӽӣ��ٽ���while
			}
			nearNum = num[i] - 1;//num[i]�ұߵ���
			while (hashSet.contains(nearNum)) {
				hashSet.remove(nearNum);
				temLen++;
				nearNum--;//nearNum�������ٽ���while
			}
			if (temLen > res)
				res = temLen;//������󷵻صġ���󳤶ȡ�
			if (res >= num.length)
				break;//����������󳤶ȣ����ˡ����ҵ������˳�for
		}
		
		return res;
	}

	public static void main(String[] args) {
		int[] A = { 100, 4, 200, 1, 3, 2};
		System.out.println("������ִ����ȣ�" + longestConsecutive(A));
	}

}
