package classSeven;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class FindKMajorityNumberInArray{

	public static void printHalfMajorNumber(int[] arr){
		int candNum=0;
		int sameTimes=0;
		for(int i=0;i!=arr.length;i++){
			if(sameTimes==0){
				candNum=arr[i];
				sameTimes=1;
			}else if(arr[i]==candNum){
				sameTimes++;
			}else{
				sameTimes--;
			}
		}
		sameTimes=0;
		for(int i=0;i!=arr.length;i++){
			if(arr[i]==candNum){
				sameTimes++;
			}
		}
		if(sameTimes>arr.length/2){
			System.out.println("Result: "+candNum);
		}else{
			System.out.println("Result: No such number");
		}
	}

	public static void printKMajorNumbers(int[] arr,int K){
		if(K<2){
			System.out.println("The value of K is invalid.");
			return;
		}
		HashMap<Integer,Integer> kMajorMap=new HashMap<Integer,Integer>();
		for(int i=0;i!=arr.length;i++){
			if(kMajorMap.containsKey(arr[i])){
				kMajorMap.put(arr[i],kMajorMap.get(arr[i])+1);
			}else{
				if(kMajorMap.size()==K-1){
					allCandsMinusOne(kMajorMap);
				}else{
					kMajorMap.put(arr[i],1);
				}
			}
		}
		HashMap<Integer,Integer> realTimesMap=getRealTimesMap(arr,kMajorMap);
		boolean hasPrint=false;
		System.out.print("Result: ");
		for(Entry<Integer,Integer> set:kMajorMap.entrySet()){
			Integer key=set.getKey();
			if(realTimesMap.get(key)>arr.length/K){
				hasPrint=true;
				System.out.print(key+" ");
			}
		}
		System.out.println(hasPrint?"":"No such Number");
	}

	public static void allCandsMinusOne(HashMap<Integer,Integer> map){
		List<Integer> removeList=new LinkedList<Integer>();
		for(Entry<Integer,Integer> set:map.entrySet()){
			Integer key=set.getKey();
			Integer value=set.getValue();
			if(value==1){
				removeList.add(key);
			}
			map.put(key,value-1);
		}
		for(Integer removeKey:removeList){//统一删,不能在迭代器for中增删，
			map.remove(removeKey);
		}
	}

	public static HashMap<Integer,Integer> getRealTimesMap(int[] arr,
			HashMap<Integer,Integer> kMajorMap){
		HashMap<Integer,Integer> realTimesMap=new HashMap<Integer,Integer>();
		for(int i=0;i!=arr.length;i++){
			int curNum=arr[i];
			if(kMajorMap.containsKey(curNum)){
				if(realTimesMap.containsKey(curNum)){
					realTimesMap.put(curNum,realTimesMap.get(curNum)+1);
				}else{
					realTimesMap.put(curNum,1);
				}
			}
		}
		return realTimesMap;
	}

	public static void main(String[] args){
		int[] arr={1, 2, 3, 1, 1, 2, 1};
		printHalfMajorNumber(arr);
		int K=4;
		printKMajorNumbers(arr,K);
	}

}
