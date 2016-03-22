package leetCode;

import java.util.LinkedHashMap;

public class L020107TwoSum {
	//有序数组的写法,不能通过。题中默认为无序数组。
    public static int[] twoSumSortedArray(int[] nums, int target) {
        if(nums==null||nums.length==0){return null;}
    	int[] res=new int[2];
    	int l=0,r=nums.length-1;
        while (l<r) {
			if (nums[l]+nums[r]<target) {
				l++;
			} else if(nums[l]+nums[r]>target){
				r--;
			}else {
				res[0]=l;res[1]=r; return res;
			}
			
		}
        return null;
    }
    
    public static int[] twoSum(int[] nums, int target) {//无序数组
    	if(nums==null||nums.length==0){return null;}
    	int[] res=new int[2];
    	LinkedHashMap<Integer, Integer> hMap=new LinkedHashMap<>();
    	for (int i = 0; i < nums.length; i++) {
    		hMap.put(nums[i],i);
		}
    	for (int i = 0; i < nums.length; i++) {
			int gap=target-nums[i];
			if(hMap.containsKey(gap)){
				int gapV=hMap.get(gap);
				if(gapV>i){//此处不能等号
					res[0]=i+1;  res[1]=gapV+1;break;
				}else if(gapV<i){
					res[0]=gapV+1;  res[1]=i+1;break;
				}
			}
		}
    	return res;	
    }
	
	public static void main(String[] args) {
		int[] arr = { 3,2,4 };
//		int[] arr = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
		if (twoSum(arr, 6)!=null) {
			System.out.println("二元组如下："+twoSum(arr, 6)[0]+
					","+twoSum(arr, 6)[1]);
		}else {
			System.out.println("二元组没找到");
		}
	}
}
