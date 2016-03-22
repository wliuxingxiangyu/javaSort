package classThree;

import java.util.HashMap;
import java.util.Map.Entry;

public class GetMinDistanceBetweenTwoNum{//12

	public static int getMinDistOfTwoWords(String[] strArr,String str1,
			String str2){
		if(str1==null||str2==null){
			return -1;
		}
		if(str1.equals(str2)){
			return 0;
		}
		int last1=-1;
		int last2=-1;
		int minDist=Integer.MAX_VALUE;
		for(int i=0;i!=strArr.length;i++){
			if(strArr[i].equals(str1)){
				minDist=Math.min(minDist,last2==-1?minDist:i-last2);
				last1=i;
			}
			if(strArr[i].equals(str2)){
				minDist=Math.min(minDist,last1==-1?minDist:i-last1);
				last2=i;
			}
		}
		return minDist==Integer.MAX_VALUE?-1:minDist;
	}

	public static class Record{
		private HashMap<String,HashMap<String,Integer>> record;

		public Record(String[] strArr){
			record=new HashMap<String,HashMap<String,Integer>>();
			HashMap<String,Integer> lastIndexMap=new HashMap<String,Integer>();
			//lastIndexMap之前所有的字符串出现的最近位置,
			for(int i=0;i!=strArr.length;i++){
				String curStr=strArr[i];
				updateRecord(record,lastIndexMap,curStr,i);
				lastIndexMap.put(curStr,i);
			}
		}

		private void updateRecord(
				HashMap<String,HashMap<String,Integer>> record,
				HashMap<String,Integer> lastIndexMap,String str,int index){
			if(!record.containsKey(str)){
				record.put(str,new HashMap<String,Integer>());
			}
			HashMap<String,Integer> strMap=record.get(str);
			for(Entry<String,Integer> lastEntry:lastIndexMap.entrySet()){
				String lastKey=lastEntry.getKey();
				int lastIndex=lastEntry.getValue();
				if(!lastKey.equals(str)){
					HashMap<String,Integer> lastMap=record.get(lastKey);
					int curMin=index-lastIndex;
					if(strMap.containsKey(lastKey)){
						int previousMin=strMap.get(lastKey);
						if(curMin<previousMin){//更新距离
							strMap.put(lastKey,curMin);
							lastMap.put(str,curMin);
						}
					}else{
						strMap.put(lastKey,curMin);
						lastMap.put(str,curMin);
					}
				}
			}
		}

		public int getMinDistOfTwoWords(String str1,String str2){
			if(str1==null||str2==null){
				return -1;
			}
			if(str1.equals(str2)){
				return 0;
			}
			if(record.containsKey(str1)&&record.get(str1).containsKey(str2)){
				return record.get(str1).get(str2);//record.get(str1)小map
			}
			return -1;
		}

	}

	public static void main(String[] args){
		String[] strArr=new String[]{"4", "2", "2", "3", "2", "2", "3", "1",
				"1", "3"};
		System.out.println(getMinDistOfTwoWords(strArr,"4","3"));
		System.out.println(getMinDistOfTwoWords(strArr,"2","3"));
		System.out.println(getMinDistOfTwoWords(strArr,"2","1"));

		System.out.println("==================================");

		Record record=new Record(strArr);
		System.out.println(record.getMinDistOfTwoWords("4","3"));
		System.out.println(record.getMinDistOfTwoWords("2","3"));
		System.out.println(record.getMinDistOfTwoWords("2","1"));
	}
}
