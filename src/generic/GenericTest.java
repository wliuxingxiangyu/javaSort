package generic;

/**
 * 泛型调用演示
 * @author 杨元
 *
 */
public class GenericTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		Generic generic = new Generic();		
		//调用泛型方法
		Object obj = generic.getObject(Class.forName("generic.User"));
		//判断obj的类型是否是指定的User类型
		System.out.println(obj instanceof User);
	}
}
