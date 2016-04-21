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
		LinkedList<String> path=new LinkedList<String>();//链表
		char[] fromChars=from.toCharArray();
		char[] toChars=to.toCharArray();
		path.add(from);
		if(findProcess(fromChars,toChars,path,wordMap)){//找到了一条路径。
			String[] result=new String[path.size()];
			int index=0;
			while(!path.isEmpty()){
				result[index++]=path.pollFirst();//path的第一个移出后给result[index++]
			}
			return result;
		}
		return null;
	}

	public static boolean findProcess(char[] fromChars,char[] toChars,
			LinkedList<String> path,HashSet<String> wordMap){
		boolean isEquelFromAndEnd=true;
		for(int i=0;i!=fromChars.length;i++){//逐次改变from的每个位置.往to上.看是否在book中。
			if(fromChars[i]!=toChars[i]){//不等时,要将from[i]变成to[i].
				isEquelFromAndEnd=false;
				char tmp=fromChars[i];//取出fromChars[i]备份
				fromChars[i]=toChars[i];
				String from=String.valueOf(fromChars);
				System.out.println("wordMap是否含="+ from);
				if(wordMap.contains(from)){
					path.addLast(from);//addLast为LinkedList方法.先将from加入path进行往深度探索.
					if(findProcess(fromChars,toChars,path,wordMap)){//fromChars变了进递归
						return true;//变成功了
					}else{
						path.removeLast();//递归未成功时在path中删除,removeLast为LinkedList方法。
					}
				}
				fromChars[i]=tmp;//备份的换回来
				System.out.println("备份的换回来"+ tmp);
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
