package Lesson0;

public class Ex{
      String str=new String("good");//全局变量
      char[]ch={'a','b','c'};
      public static void main(String args[]){
             Ex ex=new Ex();
             ex.change(ex.str,ex.ch);
             System.out.println(ex.str);//打印good
             System.out.println(ex.ch);//打印gbc
      }
      public void change(String str,char ch[]){
//8基本数据类型   (4个整型byte；short；int；long.
//    	    2个浮点型float;double .
//    	    1个字符型char.
//    	    1个布尔型boolean )   传值
             str="test ok";
             ch[0]='g';
      }
}
