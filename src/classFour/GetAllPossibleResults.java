package classFour;

import java.util.HashMap;
import java.util.Map.Entry;

public class GetAllPossibleResults{//P10
	public static HashMap<Integer,String> findAllPossibleValue(int[] arr){
		// ���Ǽ�¼���������鷽����map
		// key�����������ʼλ�úͽ���λ�ã�����"+"�����ӣ�
		// value�������������������Щ������
		// ��valueMap�У�key�Ƿ����õ���ֵ(Integer)��value�Ƿ����ľ������(String)
		HashMap<String,HashMap<Integer,String>> map=new HashMap<String,HashMap<Integer,String>>();

		computeAllPossibleValue(arr,0,arr.length-1,map);
		return map.get(String.valueOf(0)+"+"+String.valueOf(arr.length-1));//���Ļ���0~N-1
	}

	public static void computeAllPossibleValue(int[] arr,int beginIndex,
			int endIndex,HashMap<String,HashMap<Integer,String>> map){
		// ���ɵ�ǰ������arr(beginIndex,endIndex)�ķ���map
		HashMap<Integer,String> valueExpressionMap=new HashMap<Integer,String>();
		if(beginIndex==endIndex){ // ����ֻ��һ��Ԫ�ص����
			valueExpressionMap.put(arr[beginIndex],
					String.valueOf(arr[beginIndex]));
			map.put(String.valueOf(beginIndex)+"+"+String.valueOf(endIndex),
					valueExpressionMap);
			return;
		}

		// ��ǰ�����鿪ʼ���ֹ���
		for(int i=beginIndex;i!=endIndex;i++){
			// ������������������ʼλ�ú���ֹλ����Ϣ
			String leftKey=String.valueOf(beginIndex)+"+"+String.valueOf(i);
			// ������������������ʼλ�ú���ֹλ����Ϣ
			String rightKey=String.valueOf(i+1)+"+"+String.valueOf(endIndex);
			if(!map.containsKey(leftKey)){ // ���֮ǰ��������û�м��������Ҫ�ݹ����
				computeAllPossibleValue(arr,beginIndex,i,map);
			}
			if(!map.containsKey(rightKey)){// ���֮ǰ��������û�м��������Ҫ�ݹ����
				computeAllPossibleValue(arr,i+1,endIndex,map);
			}
			// ��������ķ���
			HashMap<Integer,String> leftMap=map.get(leftKey);
			// ��������ķ���
			HashMap<Integer,String> rightMap=map.get(rightKey);

			// ����������������������ǰ���ֵķ�������
			for(Entry<Integer,String> leftEntry:leftMap.entrySet()){
				Integer leftValue=leftEntry.getKey();
				String leftExpression=leftEntry.getValue();
				for(Entry<Integer,String> rightEntry:rightMap.entrySet()){
					Integer rightValue=rightEntry.getKey();
					String rightExpression=rightEntry.getValue();

					Integer addValue=leftValue+rightValue;
					// �����ӵ�ֵû�г��ֹ����ͼ��룬�������
					if(!valueExpressionMap.containsKey(addValue)){
						String addExpression="("+leftExpression+"+"
								+rightExpression+")";
						valueExpressionMap.put(addValue,addExpression);
					}

					Integer subValue=leftValue-rightValue;
					// ��������ֵû�г��ֹ����ͼ��룬�������
					if(!valueExpressionMap.containsKey(subValue)){
						String subExpression="("+leftExpression+"-"
								+rightExpression+")";
						valueExpressionMap.put(subValue,subExpression);
					}

					Integer mulValue=leftValue*rightValue;
					// �����˵�ֵû�г��ֹ����ͼ��룬�������
					if(!valueExpressionMap.containsKey(mulValue)){
						String mulExpression="("+leftExpression+"*"
								+rightExpression+")";
						valueExpressionMap.put(mulValue,mulExpression);
					}

					// ��������Ľ�������뱣֤������Ϊ0����������
					if(rightValue!=0){
						Integer devValue=leftValue/rightValue;
						// ��������ֵû�г��ֹ����ͼ��룬�������
						if(!valueExpressionMap.containsKey(devValue)){
							String devExpression="("+leftExpression+"/"
									+rightExpression+")";
							valueExpressionMap.put(devValue,devExpression);
						}
					}
				}
			}
		}
		// �����л����еõ������в�ͬ����ķ�������¼����
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
