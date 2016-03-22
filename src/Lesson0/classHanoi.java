package Lesson0;
//以我上述的方法观之：(1)递归的出口在于disk数为一的时候
//(2)向出口逼近：如果不是一，是n ，则我们先挪动上面n-1块disk，等上面挪完，即递归返回的时候，我们挪动最底下的disk.
//仅仅如此，一个貌似十分复杂的问题就解决了，因为挪动那n-1块disk的时候，会继续向上减少，直到disk的数量为一为止。下面给出java程序编码(已测试过，运行正常)：
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