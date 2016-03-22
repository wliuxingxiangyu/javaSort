package leetCode;

import java.util.ArrayList;
import java.util.List;

public class L020105MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==0&&nums2.length==0) {
			return 0;
		}
        List<Integer> arrls = new ArrayList<Integer>();
    	for (int i = 0,j=0; i < nums1.length||j < nums2.length;) {
			if (i < nums1.length&&j < nums2.length&&nums1[i]<=nums2[j]) {
				arrls.add(nums1[i]);
				if (i<nums1.length) {
					i++;
				}
			} else if(i < nums1.length&&j < nums2.length&&nums1[i]>nums2[j]) {
				arrls.add(nums2[j]);
				if (j<nums2.length) {
					j++;
				}
			}else if (i==nums1.length&&j < nums2.length) {
				arrls.add(nums2[j++]);
			}else if (i<nums1.length&&j==nums2.length) {
				arrls.add(nums1[i++]);
			}
		}
    	int arrlsSize=arrls.size();
    	Double d;//中位数
    	if(arrlsSize%2!=0){//总个数是奇数
    		Object o=arrls.get(arrlsSize/2);
        	d=Double.parseDouble(o==null?"":o.toString() ) ;
    	}else {//总个数是偶数
    		Object o1=arrls.get((arrlsSize-1)/2);
    		Object o2=arrls.get((arrlsSize+1)/2);
    		Double d1=Double.parseDouble(o1==null?"":o1.toString() ) ;
    		Double d2=Double.parseDouble(o2==null?"":o2.toString() ) ;
    		d=(d1+d2)/2;
		}
    	return d; 
    }
	
	public static void main(String[] args) {
//		int[] nums1={ };
//		int[] nums2={1};
		int[] nums1={ };
		int[] nums2={2,3};
//		int[] nums1={1,3,5,7,9};
//		int[] nums2={2,3,4,6};
		
		System.out.println("中位数："+findMedianSortedArrays(nums1,nums2));
	}

}
