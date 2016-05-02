package generic;

import generic.Generic;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
//有个泛型generic-归并排序方法
public class ExpressSort {
	public static void ExpressSort(InfoNode[] arr) {
		if (arr == null)
			return;
		HashMap<Timestamp, Long> hm = new HashMap<Timestamp, Long>();
		HashSet<Timestamp> hs = new HashSet<Timestamp>();
		int len = arr.length;
		InfoNode[] tempArrHm = new InfoNode[len];
		InfoNode[] tempArrHs = new InfoNode[len];
		for (int i = 0; i < len; i++) {//用HashMap
			Timestamp ts = arr[i].ts;
			if (!hm.containsKey(ts)) {
				hm.put(ts, arr[i].id);
				tempArrHm[i] = arr[i];
			}
		}
		for (int i = 0; i < len; i++) {//用HashSet
			Timestamp ts = arr[i].ts;
			if (!hs.contains(ts)) {
				hs.add(ts);
				tempArrHs[i] = arr[i];
			}
		}

		// HashMap放回arr
//		InfoNode us=new InfoNode(111,111,"111");
		// ExpressSort.<InfoNode> mergeSortNonRecursive(tempArr);
		//int[] arrTest={1,4,3,6,4,6};
		//Generic generic = new Generic();		
		//调用泛型方法
		//Object[]  obj = generic.getObject(arrTest.getClass());
		// mergeSortNonRecursive( obj);

	}

	public static void main(String[] args) {// 测试用例。NULL；

	}

	public Boolean CompareDate(Timestamp ts1, Timestamp ts2) {
		int i = ts1.compareTo(ts2);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static <T extends Comparable> boolean mergeSortNonRecursive(T[] t) {
		if (t == null || t.length <= 1)
			return true;

		int len = 1;
		// 程序边界的处理非常重要
		while (len <= t.length) {
			for (int i = 0; i + len <= t.length - 1; i += len * 2) {
				int low = i, mid = i + len - 1, high = i + len * 2 - 1;
				if (high > t.length - 1)
					high = t.length - 1;
				merge(t, i, mid, high);
			}

			len *= 2;
		}
		return true;
	}

	/**
	 * 将两个归并段合并成一个归并段
	 * 
	 * @param <T>
	 * @param t
	 * @param low
	 * @param mid
	 * @param high
	 * @return
	 */
	private static <T extends Comparable> boolean merge(T[] t, int low,
			int mid, int high) {
		T[] s = t.clone();// 先复制一个辅助数组

		int i, j, k;// 三个指示器，i指示t[low...mid]，j指示t[mid+1...high]，k指示s[low...high]
		for (i = low, j = mid + 1, k = low; i <= mid && j <= high; k++) {
			if (t[i].compareTo(t[j]) <= 0) {
				s[k] = t[i++];
			} else {
				s[k] = t[j++];
			}
		}

		// 将剩下的元素复制到s中
		if (i <= mid) {
			for (; k <= high; k++) {
				s[k] = t[i++];
			}
		} else {
			for (; k <= high; k++) {
				s[k] = s[j++];
			}
		}
		for (int m = low; m <= high; m++) {// 将辅助数组中的排序好的元素复制回原数组
			t[m] = s[m];
		}

		return true;
	}

}
