package leetCode;

public class L020103SearchInRotatedSortedArray {
	public static int search(int[] A, int target) {
		int l = 0, r = A.length-1, mid;
		while (l <= r) {
			mid = l + (r - l) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[l] <= A[mid]) {//mid�������
				if (A[l] <= target && target <= A[mid]) {
					r = mid-1;// target��l~(mid-1)��
				} else {// �ұ�����
					l = mid + 1;
				}
			} else {//mid�ұ�����
				if (A[mid] <= target && target <= A[r]) {
					l = mid+ 1;// target��mid~r��
				} else {// ��nei��ifʲô��������ʱ��ִ�дˣ�����ı�l��r��������ѭ����
					r = mid -1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
//		int[] A={ };
		int[] A={5,1,3};
//		int[] A={4,5,6,7,0,1,2};
		System.out.println("���Ϊ"+search(A,0));
	}

}
