package classFour;

import java.util.Map.Entry;
import java.util.TreeMap;

public class CoinsProblem4 {

	public static int solution(int[] coins, int aim) {
		TreeMap<Integer, Integer> sortMap = new TreeMap<Integer, Integer>();
		for (int i = 0; i != coins.length; i++) {
			if (sortMap.containsKey(coins[i])) {
				sortMap.put(coins[i], sortMap.get(coins[i]) + 1);
			} else {
				sortMap.put(coins[i], 1);
			}
		}
		int[] coinArr = new int[sortMap.size()];
		int[] valueArr = new int[sortMap.size()];
		int index = 0;
		while (!sortMap.isEmpty()) {
			Entry<Integer, Integer> entry = sortMap.pollFirstEntry();
			coinArr[index] = entry.getKey();
			valueArr[index++] = entry.getValue();
		}
		int[][] map = new int[aim + 1][coins.length];
		int result = process(coinArr, valueArr, aim, coinArr.length - 1, map);
		return result;
	}

	public static int process(int[] coinArr, int[] valueArr, int aim,
			int curIndex, int[][] map) {
		if (aim == 0) {
			if (curIndex >= 0) {
				map[aim][curIndex] = 1;
			}
			return 1;
		}
		if (curIndex < 0) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i <= valueArr[curIndex] && coinArr[curIndex] * i <= aim; i++) {
			int curCoins = coinArr[curIndex] * i;
			if (curIndex - 1 >= 0 && map[aim - curCoins][curIndex - 1] != 0) {
				res += map[aim - curCoins][curIndex - 1] == -1 ? 0 : map[aim - curCoins][curIndex - 1];
			} else {
				res += process(coinArr, valueArr, aim - curCoins, curIndex - 1, map);
			}
		}
		map[aim][curIndex] = res == 0 ? -1 : 0;
		return res;

	}

	public static void main(String[] args) {
		int[] coins = { 1, 1, 1, 2, 2, 3, 5, 5, 5, 10, 10 };
		int aim = 10;
		int ways = solution(coins, aim);
		System.out.println("Solutions : " + ways);

	}
}
