package Lesson0;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpMatchMonth {
	
	public static void printArray(String[] Array) {
		for (int i = 0; i < Array.length; i++) {
			System.out.print(Array[i]+"  ");
		}	
	}
	
    public static void main(String args[]){//java��������ʽû��exec,split������matchesֻ����Boolean��
		String date="2014��11��17��";
		String[] token=new String[4];
		Pattern p = Pattern.compile("(\\d{2})");//ƥ����λ��,ֻ��һ��������,ֻ��һ������,��group(0)=group(1)
//		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");//ƥ�人��
		Matcher m = p.matcher(date);
		for(int i=0;m.find()&&i<4;i++){
			token[i]=m.group(0);//�������������ַ���group(0)Ϊ20,14,11,17��
		}
		printArray(token);
		System.out.println( );
		System.out.println(token[2]+"��");
    }
}
