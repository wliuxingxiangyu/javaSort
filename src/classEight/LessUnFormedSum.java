package classEight;

import java.util.Arrays;
import java.util.HashSet;

public class LessUnFormedSum{

	public static int getFirstUnFormedNum(int[] arr){
		HashSet<Integer> sumSet=new HashSet<Integer>();
		setSumSetProcess(arr,0,0,sumSet);
		int min=Integer.MAX_VALUE;
		for(int i=0;i!=arr.length;i++){
			min=Math.min(min,arr[i]);
		}
		for(int i=min+1;i!=Integer.MIN_VALUE;i++){//Integer.MAX_VALUE+1=Integer.MIN_VALUE
			if(!sumSet.contains(i)){
				return i;
			}
		}
		return 0;
	}

	public static void setSumSetProcess(int[] arr,int index,int preSum,
			HashSet<Integer> set){
		if(index==arr.length){
			set.add(preSum);
			return;
		}
		setSumSetProcess(arr,index+1,preSum,set);
		setSumSetProcess(arr,index+1,preSum+arr[index],set);
	}

	public static int getFirstUnFormedNumDP(int[] arr){
		HashSet<Integer> sumSet=new HashSet<Integer>();
		HashSet<String> isCompute=new HashSet<String>();
		setSumSetProcessDP(arr,0,0,isCompute,sumSet);
		int min=Integer.MAX_VALUE;
		for(int i=0;i!=arr.length;i++){
			min=Math.min(min,arr[i]);
		}
		for(int i=min;i!=Integer.MIN_VALUE;i++){
			if(!sumSet.contains(i)){
				return i;
			}
		}
		return 0;
	}

	public static void setSumSetProcessDP(int[] arr,int index,int preSum,
			HashSet<String> isCompute,HashSet<Integer> sumSet){
		String curKey=String.valueOf(index+"+"+preSum);
		if(isCompute.contains(curKey)){
			return;
		}
		if(index==arr.length){
			sumSet.add(preSum);
			isCompute.add(curKey);
			return;
		}
		setSumSetProcessDP(arr,index+1,preSum,isCompute,sumSet);
		setSumSetProcessDP(arr,index+1,preSum+arr[index],isCompute,sumSet);
		isCompute.add(curKey);
	}

	public static int getFirstUnFormedNumHasOne(int[] arr){
		if(arr==null||arr.length==0){
			return 0;
		}
		int[] newArr=getSortedNewArray(arr);
		if(newArr[0]!=1){
			throw new RuntimeException("err, your arr should has 1");
		}
		int rangeMax=0;
		for(int i=0;i!=newArr.length;i++){
			if(newArr[i]>rangeMax+1){
				return rangeMax+1;
			}else{
				rangeMax+=newArr[i];
			}
		}
		return rangeMax+1;
	}

	public static int[] getSortedNewArray(int[] arr){
		if(arr==null){
			return null;
		}
		int[] res=new int[arr.length];
		for(int i=0;i!=arr.length;i++){
			res[i]=arr[i];
		}
		Arrays.sort(res);
		return res;
	}

	public static int getFirstUnFormedNumAdditional(int[] arr){
		if(arr==null||arr.length==0){
			return 0;
		}
		int[] newArr=getSortedNewArray(arr);
		if(newArr[0]!=1){
			return 1;
		}
		int rangeMax=0;
		for(int i=0;i!=newArr.length;i++){
			if(newArr[i]>rangeMax+1){
				return rangeMax+1;
			}else{
				rangeMax+=newArr[i];
			}
		}
		return rangeMax+1;
	}

	public static int[] generateRandomPositiveArray(int len,int maxValue){
		int[] res=new int[len];
		for(int i=0;i!=res.length;i++){
			res[i]=(int)(Math.random()*maxValue)+1;
		}
		return res;
	}

	public static void printArray(int[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		int arrLength=27;
		int maxValue=30;
		int[] arr=generateRandomPositiveArray(arrLength,maxValue);
		printArray(arr);
		long start=System.currentTimeMillis();
		System.out.println(getFirstUnFormedNum(arr));
		long end=System.currentTimeMillis();
		System.out.println("cost time: "+(end-start)+" ms");
		System.out.println("======================================");
		start=System.currentTimeMillis();
		System.out.println(getFirstUnFormedNumDP(arr));
		end=System.currentTimeMillis();
		System.out.println("cost time: "+(end-start)+" ms");
		System.out.println("======================================");
		System.out.println("set arr[0] to 1");
		arr[0]=1;
		start=System.currentTimeMillis();
		System.out.println(getFirstUnFormedNumHasOne(arr));
		end=System.currentTimeMillis();
		System.out.println("cost time: "+(end-start)+" ms");

	}
}
