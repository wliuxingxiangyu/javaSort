package classOne;
import java.util.Stack;
public class BuildQueueByTwoStacks {//L1p7用两个栈实现队列
	public static class TwoStacksImplementQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksImplementQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		public void add(int pushInt) {//压栈:只压  压入栈
			stackPush.push(pushInt);
		}

		public int poll() {//压入栈向弹出栈“倒清”数据后，再弹出  弹出栈
			if (stackPop.empty() && stackPush.empty()) {////两栈同时为空时，队列空
				throw new RuntimeException("Queue is empty!");//用户关心队列
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {//弹出栈为空  且  压入栈非空时,向弹出栈倒清数据
					stackPop.push(stackPush.pop());
				}//压入栈向弹出栈倒清数据后,两次先入后出,负负得正,先入先出,得队列顺序
			}
			return stackPop.pop();
		}

		public int peek() {//压入栈向弹出栈“倒清”数据后，再检测弹出栈
			if (stackPop.empty() && stackPush.empty()){//两栈同时为空时，队列空
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while(!stackPush.empty()){//弹出栈为空  且  压入栈非空时,向弹出栈倒清数据
				 stackPop.push(stackPush.pop());
				}//压入栈向弹出栈倒清数据后,两次先入后出,负负得正,先入先出,得队列顺序
			}
			return stackPop.peek();
		}
	}

	public static void main(String[] args) {
		TwoStacksImplementQueue test = new TwoStacksImplementQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
