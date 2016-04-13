package iterviewBook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
	private static Map<Integer, String> map = new HashMap<Integer, String>();

	/**
	 * 1.HashMap ��ӳ�䲻��֤˳��ĳЩӳ�����ȷ��֤��˳��: TreeMap ��
	 * 2.�ڱ���Map������,������map.put(key,newVal),map.remove(key)���޸ĺ�ɾ��Ԫ�أ� ������
	 * �����޸��쳣,����ͨ����������remove()�� �ӵ�����ָ��� collection ���Ƴ���ǰ����Ԫ�� ���ﵽɾ�������е�Ԫ�ص�Ŀ�ġ�
	 * */
	
	public static void MapTestFun(){
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");
		map.put(8, "eight");
		map.put(5, "five");
		map.put(9, "nine");
		map.put(10, "ten");
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, String> entry = it.next();
			int key = entry.getKey();
			if (key % 2 == 1) {
				System.out.println("delete this: " + key + " = " + key);
				// map.put(key, "����"); //ConcurrentModificationException
				// map.remove(key); //ConcurrentModificationException
				it.remove(); // OK
			}
		}
		// ������ǰ��map�������µ�forѭ���޷��޸�map���ݣ���Ϊ��ͨ����������
		System.out.println("-------\n\t���յ�map��Ԫ�ر�����");
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			int k = entry.getKey();
			String v = entry.getValue();
			System.out.println(k + " = " + v);
		}
	}
	public static void main(String[] args) {
		// MapTestFun();
	}
}
