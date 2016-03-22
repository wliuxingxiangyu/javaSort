package classFive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TransFromBetweenTwoWordsMore {

	// һ���򵥵ĵ�����ڵ�ṹ
	public static class myListNode {
		public String data;
		public myListNode next;

		public myListNode(String data) {
			this.data = data;
		}
	}

	// ������
	public static String[] findTransPathByBFS(String[] book, String from,
			String to) {
		// ������������һ�ֳ�����˵�������ڱ任·��
		if (from == null || to == null || from.length() != to.length()) {
			return null;
		}

		// �����е�book�е��ַ������ŵ����������
		myListNode head = new myListNode("");
		myListNode previous = head;

		// ���HashSet������֤ȥ�صİ�book�е��ַ����ŵ������������
		HashSet<String> hasVisited = new HashSet<String>();

		for (String word : book) {
			// ����ֻ�����Щ��from��to������ȵ��ַ�����ȥ�صļ���������
			if (word.length() == from.length() && !hasVisited.contains(word)) {
				hasVisited.add(word);
				// ��Ϊ������ȱ����Ǵ�from��ʼ�ģ����Բ�Ҫ��from�ٴμ��뵽������
				if (!word.equals(from)) {
					myListNode currentNode = new myListNode(word);
					previous.next = currentNode;
					previous = currentNode;
				}
			}
		}

		// ��������������Ĺ�����û�з���from��to��book�д��ڣ�˵��·�������ܴ���
		if (!hasVisited.contains(from) || !hasVisited.contains(to)) {
			return null;
		}

		// �ö��нṹ����ɿ�����������Ƿǳ������ķ�ʽ
		Queue<String> queue = new LinkedList<String>();
		queue.add(from);

		// ���map��key��value�ĺ����ǣ�
		// key�ַ������ڿ������value�ַ�����ʱ���ֵģ�Ҳ����˵value��key�ĸ��ڵ�
		// �����map�Ϳ��Ա���ÿһ���ַ����ĸ��ַ�����Ϣ����ô�������ҵ�to�ַ�����ʱ��
		// �Ϳ���ͨ�����map�����ҵ�from���Ӷ��õ�����·��
		HashMap<String, String> childParentMap = new HashMap<String, String>();
		// from�ַ����ǿ�ʼ��������㣬���Ը��ַ���Ϊnull
		childParentMap.put(from, null);

		while (!queue.isEmpty()) {
			String currentStr = queue.poll();
			previous = head;
			myListNode currentNode = head.next;
			// �������п�ʼ�����뵱ǰ�ַ���--currentStr����һ��λ�ò�һ�����ַ�������Щ
			// �������Ǽ��뵽������ȱ����Ķ�����
			// ͬʱ��ÿ�ҵ�һ���������ַ������Ͱ�����������ɾ���������ȿ��Ա�֤������������ֻ���
			// Ҳ�������������е��ַ���Խ��Խ�٣����ҽ���һ��λ�ò�һ�����ַ������̻�Խ��Խ��
			while (currentNode != null) {
				if (isDifferentOnlyOneSet(currentNode.data, currentStr)) {
					queue.add(currentNode.data);
					// ����childParentMap����¼ÿһ���ַ����������ĸ����ַ����õ���
					childParentMap.put(currentNode.data, currentStr);
					// ��������ɾ���ҵ��ַ����Ĺ���
					previous.next = currentNode.next;
				} else {
					previous = currentNode;
				}
				currentNode = currentNode.next;
			}

			// �����������뵱ǰ�ַ�������һ��λ�ò�һ�����ַ�����������Ƿ���to��˵��·������
			if (childParentMap.containsKey(to)) {
				LinkedList<String> resultList = new LinkedList<String>();
				resultList.add(to);
				String parentStr = childParentMap.get(to);
				// ��childParentMap�м�¼��ÿһ���ַ���������ô���ģ���������̾Ϳ��Եõ�����path��
				while (parentStr != null) {
					resultList.addLast(parentStr);
					parentStr = childParentMap.get(parentStr);
				}
				String[] result = new String[resultList.size()];
				int indexResult = result.length - 1;
				for (String str : resultList) {
					result[indexResult--] = str;
				}
				return result;
			}
		}
		return null;
	}

	// �ж��ַ���a��b�Ƿ�ֻ��һ��λ���ϵ��ַ���һ���ļ򵥺���
	public static boolean isDifferentOnlyOneSet(String a, String b) {
		char[] aChars = a.toCharArray();
		char[] bChars = b.toCharArray();
		int differentNum = 0;
		for (int i = 0; i != aChars.length; i++) {
			if (aChars[i] != bChars[i]) {
				differentNum++;
			}
		}
		return differentNum == 1;
	}

	public static void main(String[] args) {
		String[] book = new String[] { "AAA", "AAC", "CAC", "CCC", "DCC",
				"DDC", "DDD" };
		String from = "AAA";
		String to = "DDD";
		String[] result = findTransPathByBFS(book, from, to);
		if (result != null) {
			for (String str : result) {
				System.out.println(str);
			}
		} else {
			System.out.println("No path!");
		}
	}
}
