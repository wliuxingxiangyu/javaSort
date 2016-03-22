package classOne;
import java.util.Stack;
public class GetMinStack {//pop,push,getMin����ʱ�临�Ӷȶ���O��n��,
	public static class MyStack1 {//MyStack1��һ�ַ���,��Сջֻѹ��С��
		private Stack<Integer> baseStack;//����ջ
		private Stack<Integer> minStack;

		public MyStack1() {//���캯��
			this.baseStack = new Stack<Integer>();//ջΪʵ��
			this.minStack = new Stack<Integer>();
		}

		public void push(int newItem) {
			if (this.minStack.isEmpty()) {//��СջΪ��ʱ��ֱ�Ӽ���
				this.minStack.push(newItem);
			}else if(newItem<=this.getmin()){//��СջΪ�ǿ�ʱ,ѹ��С��,getmin()�����ж���
				this.minStack.push(newItem);//ֻ����ѹ���Сʱ,��ѹ�룬�ڶ�����
			}
			this.baseStack.push(newItem);//����ջҲѹ��������
		}

		public int pop() {
			if (this.baseStack.isEmpty()) {
				System.out.println("Your stack is empty!");
			}
			int value = this.baseStack.pop();
			if (value == this.getmin()) {//����ջ����ʱ,��СջҲҪ����
				this.minStack.pop();
			}
			return value;
		}

		public int getmin() {
			if (this.minStack.isEmpty()) {
				System.out.println("Your stack is empty!");
			}
			return this.minStack.peek();//��Сջջ�������˻���ջ����СԪ��
		}
	}

	public static class MyStack2 {//MyStack2�ڶ��ַ���,����ջ��ͬ������
		private Stack<Integer> baseStack;
		private Stack<Integer> minStack;

		public MyStack2() {//���캯��
			this.baseStack = new Stack<Integer>();//ʵ����
			this.minStack = new Stack<Integer>();
		}

		public void push(int newItem) {
			if (this.minStack.isEmpty()) {//��СջΪ��ʱ��ֱ�Ӽ���
				this.minStack.push(newItem);
			} else if (newItem < this.getmin()){//��СջΪ�ǿ�ʱ,ѹ��С��,getmin()�����ж���
				this.minStack.push(newItem);
			} else {
				int newMin = this.minStack.peek();//ͬ��ѹ��minStack����С�ġ�
				this.minStack.push(newMin);//ͬ�����ظ�ѹ��minStack����С�ġ�
			}
			this.baseStack.push(newItem);//����ջҲѹ��������
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
			return this.minStack.peek();//��Сջջ��������  ����ջ����СԪ��
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
