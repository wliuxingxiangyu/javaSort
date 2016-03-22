package classFive;

import java.util.HashSet;
import java.util.LinkedList;

public class TransFromBetweenTwoWords{

	public static String[] getTransPath(String[] book,String from,String to){
		if(from==null||to==null||from.length()!=to.length()){
			return null;
		}
		HashSet<String> wordMap=new HashSet<String>();
		for(String word:book){
			wordMap.add(word);
		}
		LinkedList<String> path=new LinkedList<String>();//链表
		char[] fromChars=from.toCharArray();
		char[] toChars=to.toCharArray();
		path.add(from);
		if(findProcess(fromChars,toChars,path,wordMap)){
			String[] result=new String[path.size()];
			int index=0;
			while(!path.isEmpty()){
				result[index++]=path.pollFirst();
			}
			return result;
		}
		return null;
	}

	public static boolean findProcess(char[] fromChars,char[] toChars,
			LinkedList<String> path,HashSet<String> wordMap){
		boolean isEquelFromAndEnd=true;
		for(int i=0;i!=fromChars.length;i++){
			if(fromChars[i]!=toChars[i]){
				isEquelFromAndEnd=false;
				char tmp=fromChars[i];
				fromChars[i]=toChars[i];
				String from=String.valueOf(fromChars);
				if(wordMap.contains(from)){
					path.addLast(from);
					if(findProcess(fromChars,toChars,path,wordMap)){
						return true;//变成了
					}else{
						path.removeLast();
					}
				}
				fromChars[i]=tmp;//换回来
			}
		}
		return isEquelFromAndEnd;
	}

	public static void main(String[] args){
		String[] book=new String[]{"DAMP", "LAMP", "LIMP", "LIME", "DAME",
				"DAKE", "DIKE", "DIMP", "DIKP", "LIKP", "LIKE"};
		String from="DAMP";
		String to="LIKE";
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
