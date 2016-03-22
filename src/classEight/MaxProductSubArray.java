package classEight;

public class MaxProductSubArray {

	public static double getProductSubsArrayValue(double[] arr) {
		// max continuous subarray product contains end
		double max = arr[0];
		// min continuous subarray product contains end
		double min = arr[0];
		double res = arr[0];
		for (int i = 1; i < arr.length; ++i) {
			double maxContainsEnd = max * arr[i];
			double minContainsEnd = min * arr[i];
			max = Math.max(Math.max(maxContainsEnd, minContainsEnd), arr[i]);
			min = Math.min(Math.min(maxContainsEnd, minContainsEnd), arr[i]);
			res = Math.max(res, max);
		}
		return res;
	}

	public static void main(String[] args) {
		double[] arr = { -2.5, 4, 0, 3, 0.5, 8, -1 };
		System.out.println(getProductSubsArrayValue(arr));
	}

}
