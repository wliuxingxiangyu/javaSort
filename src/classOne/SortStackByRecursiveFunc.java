package classOne;
import java.util.Stack;
public class SortStackByRecursiveFunc {//L1p8通过"递归"实现'有序栈'的逆序
	public static void reverse(Stack<Integer> stack){//static函数,不用对象即可调用
		if (stack.isEmpty()){//栈为空，返回
			return;
		}
		int i = getAndRemoveLastElement(stack);//得到并且移除最后一个元素
		reverse(stack);
		stack.push(i);//弹出的东西压回
	}

	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		//得到并且移除最后一个元素
		int result = stack.pop();
		if (stack.isEmpty()) {//此时栈空，即原栈只含一个元素result，
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);//每次都接5
			stack.push(result);
			return last;
		}
	}
//	if(!stack.isEmpty()){//调试
//	System.out.println(i+" reverse—stack.search(1)="+stack.search(1));
//	}//调试

	
	
//	if(!stack.isEmpty()){//调试
//		System.out.println(result+" getEle—stack.search(2)="+stack.search(2));
//		}//调试			
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
