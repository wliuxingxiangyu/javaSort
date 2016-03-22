package classOne;
import java.util.Stack;
public class StackSortByOtherStack {//L1p5�ø���ջ���ջ������

	public static void sortStack(Stack<Integer> stack) {
		Stack<Integer> helpStack = new Stack<Integer>();
		while (!stack.isEmpty()) {//ԭջ�ǿգ�������ջ
			int curMin = stack.pop();//����ԭջջ��  ����   ��ǰ��С
			while (!helpStack.isEmpty() && helpStack.peek() > curMin) {
				 //����ջ�ǿգ����Ҹ���ջջ��Ԫ�� ���� ԭջ��ǰ��С  ʱ
				stack.push(helpStack.pop());//���� ����ջ ջ����ѹ��ԭջ
			}//��while����:�� ����ջ ���     ѹ��ԭջ
			System.out.print("curMin="+curMin+",");//����
			helpStack.push(curMin);//ʹ��ǰ��С ��ѹ�븨��ջ����֤����ջ ����
		}                          //���ɸ���ջ����ԭջʱ��ԭջ ����
		while (!helpStack.isEmpty()) {//�ص�ԭջ
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
