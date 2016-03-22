package classTwo;

public class IsProOrderArray{// L2P15
	// 输入入一个整数数组，判断该数组是不是一颗二叉搜索树的"后根"遍历的结果。

	public static boolean isProOrder(int[] arr){//关键找lastLess,firstMore进递归
		if(arr==null||arr.length==0){
			return false;
		}
		return isProOrderArray(arr,0,arr.length-1);
	}

	public static boolean isProOrderArray(int[] arr,int begin,int end){
		// 输入入一个整数数组，判断该数组是不是一颗二叉搜索树的"后根"遍历的结果。
		if(begin==end){// 只有一个节点时，肯定是:二叉搜索树的"后根"遍历的结果。
			return true;
		}
		int lastLess=-1;// 最后一个小于根值的下标
		int firstMore=Integer.MAX_VALUE;// 第一个大于根值的下标
		for(int i=begin;i!=end;i++){
			if(arr[i]<arr[end]){// 比根节点小为左孩子
				lastLess=i;
			}else{// 比根节点大为右孩子
				firstMore=Math.min(firstMore,i);// 第一个大于根值,取min
			}
		}
		if(firstMore==Integer.MAX_VALUE){// 只有左子树
			return isProOrderArray(arr,begin,lastLess);
		}
		if(lastLess==-1){// 只有右子树
			return isProOrderArray(arr,firstMore,end-1);
		}

		if(lastLess!=firstMore-1){// 有"左,右子树"且下标差1
			return false;
		}
		return isProOrderArray(arr,begin,lastLess)
				&&isProOrderArray(arr,firstMore,end-1);
	}

	public static void main(String[] args){
		// int[] arr={5, 7, 6, 9, 11, 10, 8};//有"左,右子树"且下标差1
		int[] arr={5, 7, 6, 9, 11, 10, 8, 16};// 此无右孩子,搜索树必须有"左,右孩子"。
		System.out.println(isProOrder(arr));
	}

}
