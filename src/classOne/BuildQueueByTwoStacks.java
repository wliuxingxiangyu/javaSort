package classOne;
import java.util.Stack;
public class BuildQueueByTwoStacks {//L1p7������ջʵ�ֶ���
	public static class TwoStacksImplementQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksImplementQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		public void add(int pushInt) {//ѹջ:ֻѹ  ѹ��ջ
			stackPush.push(pushInt);
		}

		public int poll() {//ѹ��ջ�򵯳�ջ�����塱���ݺ��ٵ���  ����ջ
			if (stackPop.empty() && stackPush.empty()) {////��ջͬʱΪ��ʱ�����п�
				throw new RuntimeException("Queue is empty!");//�û����Ķ���
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {//����ջΪ��  ��  ѹ��ջ�ǿ�ʱ,�򵯳�ջ��������
					stackPop.push(stackPush.pop());
				}//ѹ��ջ�򵯳�ջ�������ݺ�,����������,��������,�����ȳ�,�ö���˳��
			}
			return stackPop.pop();
		}

		public int peek() {//ѹ��ջ�򵯳�ջ�����塱���ݺ��ټ�ⵯ��ջ
			if (stackPop.empty() && stackPush.empty()){//��ջͬʱΪ��ʱ�����п�
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while(!stackPush.empty()){//����ջΪ��  ��  ѹ��ջ�ǿ�ʱ,�򵯳�ջ��������
				 stackPop.push(stackPush.pop());
				}//ѹ��ջ�򵯳�ջ�������ݺ�,����������,��������,�����ȳ�,�ö���˳��
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
