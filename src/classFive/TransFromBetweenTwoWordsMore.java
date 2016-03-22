package classFive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TransFromBetweenTwoWordsMore {

	// 一个简单的单链表节点结构
	public static class myListNode {
		public String data;
		public myListNode next;

		public myListNode(String data) {
			this.data = data;
		}
	}

	// 主函数
	public static String[] findTransPathByBFS(String[] book, String from,
			String to) {
		// 如果以下情况有一种成立，说明不存在变换路径
		if (from == null || to == null || from.length() != to.length()) {
			return null;
		}

		// 把所有的book中的字符串都放到这个链表中
		myListNode head = new myListNode("");
		myListNode previous = head;

		// 这个HashSet用来保证去重的把book中的字符串放到上面的链表中
		HashSet<String> hasVisited = new HashSet<String>();

		for (String word : book) {
			// 我们只想把那些和from、to长度相等的字符串，去重的加入链表中
			if (word.length() == from.length() && !hasVisited.contains(word)) {
				hasVisited.add(word);
				// 因为宽度优先遍历是从from开始的，所以不要把from再次加入到链表中
				if (!word.equals(from)) {
					myListNode currentNode = new myListNode(word);
					previous.next = currentNode;
					previous = currentNode;
				}
			}
		}

		// 如果上面加入链表的过程中没有发现from或to在book中存在，说明路径不可能存在
		if (!hasVisited.contains(from) || !hasVisited.contains(to)) {
			return null;
		}

		// 用队列结构来完成宽度优先搜索是非常常见的方式
		Queue<String> queue = new LinkedList<String>();
		queue.add(from);

		// 这个map的key和value的含义是：
		// key字符串是在宽度搜索value字符串的时候发现的，也就是说value是key的父节点
		// 用这个map就可以保存每一次字符串的父字符串信息，那么当我们找到to字符串的时候，
		// 就可以通过这个map逆向找到from，从而得到整个路径
		HashMap<String, String> childParentMap = new HashMap<String, String>();
		// from字符串是开始搜索的起点，所以父字符串为null
		childParentMap.put(from, null);

		while (!queue.isEmpty()) {
			String currentStr = queue.poll();
			previous = head;
			myListNode currentNode = head.next;
			// 在链表中开始查找与当前字符串--currentStr仅有一个位置不一样的字符串有哪些
			// 并把它们加入到宽度优先遍历的队列中
			// 同时，每找到一个这样的字符串，就把它从链表中删除，这样既可以保证宽度搜索不出现环；
			// 也可以让在链表中的字符串越来越少，查找仅有一个位置不一样的字符串过程会越来越快
			while (currentNode != null) {
				if (isDifferentOnlyOneSet(currentNode.data, currentStr)) {
					queue.add(currentNode.data);
					// 更新childParentMap，记录每一个字符串都是由哪个父字符串得到的
					childParentMap.put(currentNode.data, currentStr);
					// 在链表中删除找到字符串的过程
					previous.next = currentNode.next;
				} else {
					previous = currentNode;
				}
				currentNode = currentNode.next;
			}

			// 在找完所有与当前字符串仅有一个位置不一样的字符串后，如果我们发现to，说明路径存在
			if (childParentMap.containsKey(to)) {
				LinkedList<String> resultList = new LinkedList<String>();
				resultList.add(to);
				String parentStr = childParentMap.get(to);
				// 在childParentMap中记录了每一个字符串都是怎么来的，所以逆过程就可以得到整个path了
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

	// 判断字符串a和b是否只有一个位置上的字符不一样的简单函数
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
