package leetCode;

import java.util.HashSet;
import java.util.Iterator;

public class L020101RemoveDuplicatesFromSortedArray {
	public class Solution {
		public Solution() {
		}

		public int removeDuplicates1(int[] nums) {
			if (nums.length==0) {
				return 0;
			}
			HashSet<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < nums.length; i++) {
				if (!set.contains(nums[i])) {
					set.add(nums[i]);
				}
			}
			int numsLen = set.size();
			System.out.println("��������newǰ:numsnums.hashCode"+nums.hashCode());
			nums = new int[numsLen];// ���غ�nums���� ���ͷš�
			System.out.println("��������new��:numsnums.hashCode"+nums.hashCode());
			int cnt = 0;
			Iterator<Integer> iterator = set.iterator();
			while (iterator.hasNext()) {
				nums[cnt++] = iterator.next();
			}
			System.out.println("����������:");
		    for (int i = 0; i < nums.length; i++) {
				System.out.print(nums[i]+"");
			}
		    System.out.println();
			return numsLen;
		}
		
		public int removeDuplicates2(int[] A) {  
		    if(A.length ==0){  
		        return 0;  
		    }  
		    int count=1;  
		    for(int i=1; i<A.length; ++i){  
		        if(A[count-1] != A[i]){ //ע�����д���  
		            A[count]=A[i];  
		            count++;  
		        }  
		    }
		    System.out.println("����������:");
		    for (int i = 0; i < A.length; i++) {
				System.out.print(A[i]+"");
			}
		    System.out.println();
		    return count;  
		}  
		
		    
		    public int removeDuplicates(int[] nums) {
		        if(nums==null){
		            return 0;
		        }
		        int q=0;
		        for(int i=1;i<nums.length;i++){
		            if(nums[q]!=nums[i]){
		                nums[q+1]=nums[i];
		                q++;
		            }
		        }
		        return q+1;
		    }
	}

	public static void main(String[] args) {
		L020101RemoveDuplicatesFromSortedArray L0 = new L020101RemoveDuplicatesFromSortedArray();
		Solution so = L0.new Solution();
		int[] Arr = { 1,1,2,2,3,4 };
//		int[] Arr = { };
		System.out.println("ɾ��ǰ������");
		System.out.println("ɾ����ĳ���"+so.removeDuplicates(Arr));
		System.out.println("ɾ���󡣡���");
		for (int i = 0; i < Arr.length; i++) {
			System.out.print(Arr[i]+"");
		}
	}
}
