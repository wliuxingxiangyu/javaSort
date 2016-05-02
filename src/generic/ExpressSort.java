package generic;

import generic.Generic;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
//�и�����generic-�鲢���򷽷�
public class ExpressSort {
	public static void ExpressSort(InfoNode[] arr) {
		if (arr == null)
			return;
		HashMap<Timestamp, Long> hm = new HashMap<Timestamp, Long>();
		HashSet<Timestamp> hs = new HashSet<Timestamp>();
		int len = arr.length;
		InfoNode[] tempArrHm = new InfoNode[len];
		InfoNode[] tempArrHs = new InfoNode[len];
		for (int i = 0; i < len; i++) {//��HashMap
			Timestamp ts = arr[i].ts;
			if (!hm.containsKey(ts)) {
				hm.put(ts, arr[i].id);
				tempArrHm[i] = arr[i];
			}
		}
		for (int i = 0; i < len; i++) {//��HashSet
			Timestamp ts = arr[i].ts;
			if (!hs.contains(ts)) {
				hs.add(ts);
				tempArrHs[i] = arr[i];
			}
		}

		// HashMap�Ż�arr
//		InfoNode us=new InfoNode(111,111,"111");
		// ExpressSort.<InfoNode> mergeSortNonRecursive(tempArr);
		//int[] arrTest={1,4,3,6,4,6};
		//Generic generic = new Generic();		
		//���÷��ͷ���
		//Object[]  obj = generic.getObject(arrTest.getClass());
		// mergeSortNonRecursive( obj);

	}

	public static void main(String[] args) {// ����������NULL��

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
		// ����߽�Ĵ���ǳ���Ҫ
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
	 * �������鲢�κϲ���һ���鲢��
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
		T[] s = t.clone();// �ȸ���һ����������

		int i, j, k;// ����ָʾ����iָʾt[low...mid]��jָʾt[mid+1...high]��kָʾs[low...high]
		for (i = low, j = mid + 1, k = low; i <= mid && j <= high; k++) {
			if (t[i].compareTo(t[j]) <= 0) {
				s[k] = t[i++];
			} else {
				s[k] = t[j++];
			}
		}

		// ��ʣ�µ�Ԫ�ظ��Ƶ�s��
		if (i <= mid) {
			for (; k <= high; k++) {
				s[k] = t[i++];
			}
		} else {
			for (; k <= high; k++) {
				s[k] = s[j++];
			}
		}
		for (int m = low; m <= high; m++) {// �����������е�����õ�Ԫ�ظ��ƻ�ԭ����
			t[m] = s[m];
		}

		return true;
	}

}
