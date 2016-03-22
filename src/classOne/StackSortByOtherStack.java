package classOne;
import java.util.Stack;
public class StackSortByOtherStack {//L1p5用辅助栈完成栈的排序

	public static void sortStack(Stack<Integer> stack) {
		Stack<Integer> helpStack = new Stack<Integer>();
		while (!stack.isEmpty()) {//原栈非空，往辅助栈
			int curMin = stack.pop();//弹出原栈栈顶  保存   当前最小
			while (!helpStack.isEmpty() && helpStack.peek() > curMin) {
				 //辅助栈非空，并且辅助栈栈顶元素 大于 原栈当前最小  时
				stack.push(helpStack.pop());//弹出 辅助栈 栈顶，压入原栈
			}//此while作用:把 辅助栈 大的     压入原栈
			System.out.print("curMin="+curMin+",");//调试
			helpStack.push(curMin);//使当前最小 的压入辅助栈，保证辅助栈 升序，
		}                          //则由辅助栈倒入原栈时，原栈 降序
		while (!helpStack.isEmpty()) {//回到原栈
			stack.push(helpStack.pop());
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(6);
		stack.push(2);
		stack.push(5);
		stack.push(4);
		sortStack(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
