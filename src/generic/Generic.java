package generic;

/**
 * 泛型演示类
 * @author 杨元
 *
 */
public class Generic {
	/**
	 * 泛型方法
	 * @param <T> 声明一个泛型T
	 * @param c 用来创建泛型对象
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException{
		//创建泛型对象
		T t = c.newInstance();
		return t;
	}
}
