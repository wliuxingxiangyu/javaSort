package Lesson0;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpMatchMonth {
	
	public static void printArray(String[] Array) {
		for (int i = 0; i < Array.length; i++) {
			System.out.print(Array[i]+"  ");
		}	
	}
	
    public static void main(String args[]){//java中正则表达式没有exec,split方法，matches只返回Boolean。
		String date="2014年11月17日";
		String[] token=new String[4];
		Pattern p = Pattern.compile("(\\d{2})");//匹配两位数,只有一个左括号,只有一个分组,即group(0)=group(1)
//		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");//匹配汉字
		Matcher m = p.matcher(date);
		for(int i=0;m.find()&&i<4;i++){
			token[i]=m.group(0);//符合条件的子字符串group(0)为20,14,11,17，
		}
		printArray(token);
		System.out.println( );
		System.out.println(token[2]+"月");
    }
}
