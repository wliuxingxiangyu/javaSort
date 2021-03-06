package leetCode;

public class L020104SearchInRotatedSortedArray2 {
	public static Boolean search(int[] A, int target) {
//		public static int search(int[] A, int target) {
		int l = 0, r = A.length-1, mid;
		while (l <= r) {
			mid = l + (r - l) / 2;
			if (A[mid] == target) {
				return true;
//				return mid;
			} else if (A[l] < A[mid]) {//mid左边有序
				if (A[l] <= target && target <= A[mid]) {
					r = mid-1;// target在l~(mid-1)间
				} else {// 右边有序
					l = mid + 1;
				}
			} else if(A[l] > A[mid]){//mid右边有序
				if (A[mid] <= target && target <= A[r]) {
					l = mid+ 1;// target在mid~r间
				} else {// 外nei层if什么都不满足时，执行此，必须改变l或r，否则死循环。
					r = mid -1;
				}
			}else{
				l++;//最左端的左指针往后加加
			}
		}
		return false;
//		return -1;
	}

	public static void main(String[] args) {
		int[] A={1,2,3,1,1,1,1 };
//		int[] A={1};
//		int[] A={5,1,3};
//		int[] A={4,5,6,7,0,1,2};
		System.out.println("结果为"+search(A,3));
	}

}
