package Lesson0;
//���������ķ�����֮��(1)�ݹ�ĳ�������disk��Ϊһ��ʱ��
//(2)����ڱƽ����������һ����n ����������Ų������n-1��disk��������Ų�꣬���ݹ鷵�ص�ʱ������Ų������µ�disk.
//������ˣ�һ��ò��ʮ�ָ��ӵ�����ͽ���ˣ���ΪŲ����n-1��disk��ʱ�򣬻�������ϼ��٣�ֱ��disk������ΪһΪֹ���������java�������(�Ѳ��Թ�����������)��
import javax.swing.JOptionPane;
public class classHanoi {
     private static final String DISK_B = "diskB";
     private static final String DISK_C = "diskC";
     private static final String DISK_A = "diskA";
     static String from=DISK_A;
     static String to=DISK_C;
     static String mid=DISK_B;
	 
     public static void main(String[] args) {
		 String input=JOptionPane.showInputDialog("input the number of the disks.");
		 int num=Integer.parseInt(input);
		 move(num,from,mid,to);
     }
	 
     private static void move(int num,String from,String mid,String to) {
		 if(num==1){
		 System.out.println("move disk 1 from "+from+" to "+to);
		 }
     else{
		 move(num-1,from,to,mid);
		 System.out.println("move disk "+num+" from "+from+" to "+to);
		 move(num-1,mid,from,to);
     	}
     }
}