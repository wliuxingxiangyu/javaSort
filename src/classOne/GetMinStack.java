package classOne;
import java.util.Stack;
public class GetMinStack {//pop,push,getMin操作时间复杂度都是O（n）,
	public static class MyStack1 {//MyStack1第一种方法,最小栈只压入小的
		private Stack<Integer> baseStack;//基本栈
		private Stack<Integer> minStack;

		public MyStack1() {//构造函数
			this.baseStack = new Stack<Integer>();//栈为实例
			this.minStack = new Stack<Integer>();
		}

		public void push(int newItem) {
			if (this.minStack.isEmpty()) {//最小栈为空时，直接加入
				this.minStack.push(newItem);
			}else if(newItem<=this.getmin()){//最小栈为非空时,压入小的,getmin()下面有定义
				this.minStack.push(newItem);//只有新压入的小时,才压入，第二方法
			}
			this.baseStack.push(newItem);//基本栈也压入新数据
		}

		public int pop() {
			if (this.baseStack.isEmpty()) {
				System.out.println("Your stack is empty!");
			}
			int value = this.baseStack.pop();
			if (value == this.getmin()) {//基本栈弹出时,最小栈也要弹出
				this.minStack.pop();
			}
			return value;
		}

		public int getmin() {
			if (this.minStack.isEmpty()) {
				System.out.println("Your stack is empty!");
			}
			return this.minStack.peek();//最小栈栈顶保存了基本栈的最小元素
		}
	}

	public static class MyStack2 {//MyStack2第二种方法,辅助栈，同步增加
		private Stack<Integer> baseStack;
		private Stack<Integer> minStack;

		public MyStack2() {//构造函数
			this.baseStack = new Stack<Integer>();//实例化
			this.minStack = new Stack<Integer>();
		}

		public void push(int newItem) {
			if (this.minStack.isEmpty()) {//最小栈为空时，直接加入
				this.minStack.push(newItem);
			} else if (newItem < this.getmin()){//最小栈为非空时,压入小的,getmin()下面有定义
				this.minStack.push(newItem);
			} else {
				int newMin = this.minStack.peek();//同步压入minStack中最小的。
				this.minStack.push(newMin);//同步即重复压入minStack中最小的。
			}
			this.baseStack.push(newItem);//基本栈也压入新数据
		}

		public int pop() {
			if (this.baseStack.isEmpty()) {
				System.out.println("Your stack is empty!");
			}
			this.minStack.pop();
			return this.baseStack.pop();
		}

		public int getmin() {
			if (this.minStack.isEmpty()) {
				System.out.println("Your stack is empty!");
			}
			return this.minStack.peek();//最小栈栈顶保存了  基本栈的最小元素
		}
	}

	public static void main(String[] args) {
		MyStack2 test = new MyStack2();
		test.push(6);
		System.out.println(test.getmin());
		test.push(3);
		test.push(7);
		System.out.println(test.getmin());
		System.out.println(test.pop());
	}

}
