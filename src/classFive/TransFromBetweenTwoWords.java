package classFive;

import java.util.HashSet;
import java.util.LinkedList;

public class TransFromBetweenTwoWords{//L5P6

	public static String[] getTransPath(String[] book,String from,String to){
		if(from==null||to==null||from.length()!=to.length()){
			return null;
		}
		HashSet<String> wordMap=new HashSet<String>();
		for(String word:book){
			wordMap.add(word);
		}
		LinkedList<String> path=new LinkedList<String>();//����
		char[] fromChars=from.toCharArray();
		char[] toChars=to.toCharArray();
		path.add(from);
		if(findProcess(fromChars,toChars,path,wordMap)){//�ҵ���һ��·����
			String[] result=new String[path.size()];
			int index=0;
			while(!path.isEmpty()){
				result[index++]=path.pollFirst();//path�ĵ�һ���Ƴ����result[index++]
			}
			return result;
		}
		return null;
	}

	public static boolean findProcess(char[] fromChars,char[] toChars,
			LinkedList<String> path,HashSet<String> wordMap){
		boolean isEquelFromAndEnd=true;
		for(int i=0;i!=fromChars.length;i++){//��θı�from��ÿ��λ��.��to��.���Ƿ���book�С�
			if(fromChars[i]!=toChars[i]){//����ʱ,Ҫ��from[i]���to[i].
				isEquelFromAndEnd=false;
				char tmp=fromChars[i];//ȡ��fromChars[i]����
				fromChars[i]=toChars[i];
				String from=String.valueOf(fromChars);
				System.out.println("wordMap�Ƿ�="+ from);
				if(wordMap.contains(from)){
					path.addLast(from);//addLastΪLinkedList����.�Ƚ�from����path���������̽��.
					if(findProcess(fromChars,toChars,path,wordMap)){//fromChars���˽��ݹ�
						return true;//��ɹ���
					}else{
						path.removeLast();//�ݹ�δ�ɹ�ʱ��path��ɾ��,removeLastΪLinkedList������
					}
				}
				fromChars[i]=tmp;//���ݵĻ�����
				System.out.println("���ݵĻ�����"+ tmp);
			}
		}
		return isEquelFromAndEnd;
	}

	public static void main(String[] args){
		String[] book=new String[]{"hot","dot","dog","lot","log"};
		String from="hit";
		String to="cog";
		//---------------
//		String[] book=new String[]{"AAA", "AAB", "ACB", "BAB", "CCB","BBB", "CCC"};
//		String from="AAA";
//		String to="BBB";
//		//---------------
//		String[] book=new String[]{"DAMP", "LAMP", "LIMP", "LIME", "DAME",
//				"DAKE", "DIKE", "DIMP", "DIKP", "LIKP", "LIKE"};
//		String from="DAMP";
//		String to="LIKE";
		String[] result=getTransPath(book,from,to);
		if(result!=null){
			for(String str:result){
				System.out.println(str);
			}
		}else{
			System.out.println("No path!");
		}
	}
}
