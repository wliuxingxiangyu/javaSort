package classOne;
import java.util.Stack;
public class SortStackByRecursiveFunc {//L1p8ͨ��"�ݹ�"ʵ��'����ջ'������
	public static void reverse(Stack<Integer> stack){//static����,���ö��󼴿ɵ���
		if (stack.isEmpty()){//ջΪ�գ�����
			return;
		}
		int i = getAndRemoveLastElement(stack);//�õ������Ƴ����һ��Ԫ��
		reverse(stack);
		stack.push(i);//�����Ķ���ѹ��
	}

	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		//�õ������Ƴ����һ��Ԫ��
		int result = stack.pop();
		if (stack.isEmpty()) {//��ʱջ�գ���ԭջֻ��һ��Ԫ��result��
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);//ÿ�ζ���5
			stack.push(result);
			return last;
		}
	}
//	if(!stack.isEmpty()){//����
//	System.out.println(i+" reverse��stack.search(1)="+stack.search(1));
//	}//����

	
	
//	if(!stack.isEmpty()){//����
//		System.out.println(result+" getEle��stack.search(2)="+stack.search(2));
//		}//����			
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
//		test.push(4);
//		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
