package classFour;

import java.util.HashMap;
import java.util.Map.Entry;

public class GetAllPossibleResults{//P10
	public static HashMap<Integer,String> findAllPossibleValue(int[] arr){
		// 这是记录所有子数组方案的map
		// key是子数组的起始位置和结束位置，并用"+"号连接；
		// value是描述子数组包含了哪些方案。
		// 在valueMap中，key是方案得到的值(Integer)，value是方案的具体组成(String)
		HashMap<String,HashMap<Integer,String>> map=new HashMap<String,HashMap<Integer,String>>();

		computeAllPossibleValue(arr,0,arr.length-1,map);
		return map.get(String.valueOf(0)+"+"+String.valueOf(arr.length-1));//最大的划分0~N-1
	}

	public static void computeAllPossibleValue(int[] arr,int beginIndex,
			int endIndex,HashMap<String,HashMap<Integer,String>> map){
		// 生成当前子数组arr(beginIndex,endIndex)的方案map
		HashMap<Integer,String> valueExpressionMap=new HashMap<Integer,String>();
		if(beginIndex==endIndex){ // 数组只有一个元素的情况
			valueExpressionMap.put(arr[beginIndex],
					String.valueOf(arr[beginIndex]));
			map.put(String.valueOf(beginIndex)+"+"+String.valueOf(endIndex),
					valueExpressionMap);
			return;
		}

		// 当前子数组开始划分过程
		for(int i=beginIndex;i!=endIndex;i++){
			// 子数组的左子数组的起始位置和终止位置信息
			String leftKey=String.valueOf(beginIndex)+"+"+String.valueOf(i);
			// 子数组的右子数组的起始位置和终止位置信息
			String rightKey=String.valueOf(i+1)+"+"+String.valueOf(endIndex);
			if(!map.containsKey(leftKey)){ // 如果之前左子数组没有计算过则需要递归计算
				computeAllPossibleValue(arr,beginIndex,i,map);
			}
			if(!map.containsKey(rightKey)){// 如果之前右子数组没有计算过则需要递归计算
				computeAllPossibleValue(arr,i+1,endIndex,map);
			}
			// 左子数组的方案
			HashMap<Integer,String> leftMap=map.get(leftKey);
			// 右子数组的方案
			HashMap<Integer,String> rightMap=map.get(rightKey);

			// 遍历两个方案，结合算出当前划分的方案集合
			for(Entry<Integer,String> leftEntry:leftMap.entrySet()){
				Integer leftValue=leftEntry.getKey();
				String leftExpression=leftEntry.getValue();
				for(Entry<Integer,String> rightEntry:rightMap.entrySet()){
					Integer rightValue=rightEntry.getKey();
					String rightExpression=rightEntry.getValue();

					Integer addValue=leftValue+rightValue;
					// 如果相加的值没有出现过，就加入，否则忽略
					if(!valueExpressionMap.containsKey(addValue)){
						String addExpression="("+leftExpression+"+"
								+rightExpression+")";
						valueExpressionMap.put(addValue,addExpression);
					}

					Integer subValue=leftValue-rightValue;
					// 如果相减的值没有出现过，就加入，否则忽略
					if(!valueExpressionMap.containsKey(subValue)){
						String subExpression="("+leftExpression+"-"
								+rightExpression+")";
						valueExpressionMap.put(subValue,subExpression);
					}

					Integer mulValue=leftValue*rightValue;
					// 如果相乘的值没有出现过，就加入，否则忽略
					if(!valueExpressionMap.containsKey(mulValue)){
						String mulExpression="("+leftExpression+"*"
								+rightExpression+")";
						valueExpressionMap.put(mulValue,mulExpression);
					}

					// 想算相除的结果，必须保证除数不为0，否则跳过
					if(rightValue!=0){
						Integer devValue=leftValue/rightValue;
						// 如果相除的值没有出现过，就加入，否则忽略
						if(!valueExpressionMap.containsKey(devValue)){
							String devExpression="("+leftExpression+"/"
									+rightExpression+")";
							valueExpressionMap.put(devValue,devExpression);
						}
					}
				}
			}
		}
		// 把所有划分中得到的所有不同结果的方案，记录下来
		map.put(String.valueOf(beginIndex)+"+"+String.valueOf(endIndex),
				valueExpressionMap);
	}

	public static void main(String[] args){
		int[] test=new int[]{1,2,3};
		HashMap<Integer,String> map=findAllPossibleValue(test);
		for(Entry<Integer,String> entry:map.entrySet()){
			System.out.println(entry.getValue()+"="+entry.getKey());
		}
	}
}
