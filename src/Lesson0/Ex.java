package Lesson0;

public class Ex{
      String str=new String("good");//ȫ�ֱ���
      char[]ch={'a','b','c'};
      public static void main(String args[]){
             Ex ex=new Ex();
             ex.change(ex.str,ex.ch);
             System.out.println(ex.str);//��ӡgood
             System.out.println(ex.ch);//��ӡgbc
      }
      public void change(String str,char ch[]){
//8������������   (4������byte��short��int��long.
//    	    2��������float;double .
//    	    1���ַ���char.
//    	    1��������boolean )   ��ֵ
             str="test ok";
             ch[0]='g';
      }
}
