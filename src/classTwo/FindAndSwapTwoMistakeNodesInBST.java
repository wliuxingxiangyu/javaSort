package classTwo;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class FindAndSwapTwoMistakeNodesInBST{//L2P12

	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;

		public BinaryTreeNode(int data) {
			this.value = data;
		}
	}

	public static BinaryTreeNode restoreBSTtree(BinaryTreeNode head)
			throws Exception {
		if (head == null) {
			throw new java.lang.NullPointerException(
					"The head of BinaryTree is null");
		}
		if (head.left == null && head.right == null) {// �������������ֻ��һ��ͷ�ڵ㣬���Բ���ʧ��
			return head;
		}
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		HashMap<BinaryTreeNode, BinaryTreeNode> parentMap = new HashMap<BinaryTreeNode, BinaryTreeNode>();
		generateOrderQueueAndParentMap(head, queue, parentMap);// �и����������������ɽڵ��List������ÿһ���ڵ��Ӧ�ĸ��ڵ�Map��

		/*
		 * �������ҵ�����ڵ�취��
		 * ͨ���������������и������������Ѿ��õ����и������Ľڵ��б������ڵ��ֵ���б����ǲ������ε����ģ�
		 * 		����ǣ�˵������������û��ʧ��ֱ�ӷ���ͷ�ڵ㣻
		 * 		������ǣ�������Ҫ���������ж�һ��ʧ����и������б���ֻ��Ҫ���������ڵ���ܱ������ġ�
		 * ���ڵ�������������ж�һ�������е�Ԫ���Ƿ�һֱ������ģ� 
		 * 		����ǣ�û���⣻
		 * 		������ǣ���������ܷ�������Ԫ�ؼ����һ�ν�������ʹ����������
		 * 			 �ܣ����Ǵ���
		 * 			���ܣ����Ǳ����֪�����ĵ����ߣ�����ڵ㳬�����������ǵĺ���������
		 */
		BinaryTreeNode firstErr = null;
		BinaryTreeNode firstErrPrevious = null;
		BinaryTreeNode firstErrNext = null;
		BinaryTreeNode firstErrNextNext = null;
		BinaryTreeNode secondErr = null;
		BinaryTreeNode secondErrPrevious = null;
		BinaryTreeNode secondErrNext = null;
		BinaryTreeNode previous = queue.poll();
		BinaryTreeNode previousPervious = null;
		while (!queue.isEmpty()) {
			BinaryTreeNode current = queue.poll();
			if (previous.value > current.value) {
				if (firstErr == null) {
					firstErr = previous;
					firstErrPrevious = previousPervious;
					firstErrNext = current;
					firstErrNextNext = queue.peek();
				} else if (secondErr == null) {
					secondErr = current;
					secondErrPrevious = previous;
					secondErrNext = queue.peek();
				} else {
					throw new java.lang.IllegalArgumentException("More than 2 err nodes.");
				}
			}
			previousPervious = previous;
			previous = current;
		}
		if (firstErr == null) {
			System.out.println("This BinaryTree is fine.");
			return head;
		} else if (secondErr == null) {
			int firstErrPreviousNodeValue = firstErrPrevious == null ? Integer.MIN_VALUE
					: firstErrPrevious.value;
			int firstErrNodeValue = firstErr.value;
			int secondErrNodeValue = firstErrNext.value;
			int secondErrNextNodeValue = firstErrNextNext == null ? Integer.MAX_VALUE
					: firstErrNextNext.value;
			if (firstErrPreviousNodeValue <= secondErrNodeValue
					&& secondErrNodeValue <= firstErrNodeValue
					&& firstErrNodeValue <= secondErrNextNodeValue) {
				secondErr = firstErrNext;
			} else {
				throw new java.lang.IllegalArgumentException(
						"This binary tree can not be sorted by only exchage tow nodes.");
			}
		} else {
			int firstErrPreviousNodeValue = firstErrPrevious == null ? Integer.MIN_VALUE
					: firstErrPrevious.value;
			int firstErrNodeValue = firstErr.value;
			int firstErrNextNodeValue = firstErrNext.value;
			int secondErrPreviousNodeValue = secondErrPrevious.value;
			int secondErrNodeValue = secondErr.value;
			int secondErrNextNodeValue = secondErrNext == null ? Integer.MAX_VALUE
					: secondErrNext.value;
			if (!(firstErrPreviousNodeValue <= secondErrNodeValue
					&& secondErrNodeValue <= firstErrNextNodeValue
					&& firstErrNextNodeValue <= secondErrPreviousNodeValue
					&& secondErrPreviousNodeValue <= firstErrNodeValue && firstErrNodeValue <= secondErrNextNodeValue)) {
				throw new java.lang.IllegalArgumentException(
						"This binary tree can not be sorted by only exchage tow nodes.");
			}
		}
		System.out.println("Err Nodes found: " + firstErr.value + ","
				+ secondErr.value);
		BinaryTreeNode firstErrParent = parentMap.get(firstErr);
		BinaryTreeNode firstErrLeftChild = firstErr.left;
		BinaryTreeNode firstErrRightChild = firstErr.right;
		BinaryTreeNode secondErrParent = parentMap.get(secondErr);
		BinaryTreeNode secondErrLeftChild = secondErr.left;
		BinaryTreeNode secondErrRightChild = secondErr.right;

		/*
		 * ���������ڵ���������ô�����أ���ʵ�������Ƚϼ򵥣����ǰ������ڵ�Ӹ��Եġ���Χ���������ڳ�������λ�ú��ٻָ��롰��Χ����������ϵ���������Χ����
		 * �� ָ��������ڵ���Ը��ڵ���ӽڵ㣬�ٸ����ӣ�
		 * 
		 * Χ����firstErr�Ļ���Ϊ�� 
		 * 				firstErrParent 
		 * 					| 
		 * 			  ___firstErr___ 
		 * 			 /			 	\
		 * firstErrLeftChild firstErrRightChild
		 * 
		 * 
		 * Χ����secondErr�Ļ���Ϊ�� 
		 * 				secondErrParent 
		 * 						| 
		 * 				___secondErr___ 
		 * 			   /		 	   \
		 * secondErrLeftChild secondErrRightChild
		 * 
		 * 
		 * ����ͼ��ʾ��������Ҫ��firstErrParentָ��secondErr��secondErrParentָ��firstErr��
		 * ͬʱfirstErrָ��secondErr�����Һ��ӣ�secondErrָ��firstErr�����Һ��ӡ� 
		 * �����Ͻ����������ģ�������ϸһ��ʵ��ϸ�ڣ����Ƿ������Ǳ������������������⣺
		 * ����һ�� �Ƿ�������һ����ͷ��
		 * ����У�˭��ͷ�� 
		 * ��������Ƿ������ڵ㰤�ţ�������ţ�˭��˭�ĸ��ڵ㣬�ӽڵ��Ǹ��ڵ�����ӻ����Һ��ӣ�����������أ� 
		 * ��������
		 * �����ڵ�ֱ��Ǹ��Ը��ڵ�����ӻ����Һ���
		 * ע��㣺���и����������У�firstErr���ȳ��ֵģ�secondErr�Ǻ���ֵġ�
		 * ����firstErrһ������secondErr���Һ��ӣ� secondErrҲһ������firstErr�����ӡ�
		 * 
		 * ��3�������2��ע��㡱�໥Ӱ�죬ʮ�ָ��ӣ�����ǳ����ӣ�������Ϊ�޷��򻯡��������firstErr��ͷ�ڵ㣬
		 * ��ô�Ͳ�����׷��firstErr��firstErrParent�����ӻ����Һ��ӣ� ��ΪfirstErrParentΪnull��
		 * ���գ�����ϸϸ�����������14�֣� 
		 * 1.firstErr��ͷ������firstErr��secondErr�ĸ��ڵ㡣
		 * 2.firstErr��ͷ����firstErr����secondErr�ĸ��ڵ㣬second��secondParent������
		 * 3.firstErr��ͷ����firstErr����secondErr�ĸ��ڵ㣬second��secondParent���Һ���
		 * 4.secondErr��ͷ������secondErr��firstErr�ĸ��ڵ㡣
		 * 5.secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�,first��firstParent������
		 * 6.secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�,first��firstParent���Һ���
		 * 7.û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ�,first��firstParent������
		 * 8.û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ�,first��firstParent���Һ���
		 * 9.û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ㣬second��secondParent������
		 * 10.û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ㣬second��secondParent���Һ���
		 * 11.û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������,second���丸������
		 * 12.û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������,second���丸���Һ���
		 * 13.û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���,second���丸������
		 * 14.û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���,second���丸���Һ��� 
		 */
		if (firstErr == head) {// firstErr��ͷ
			if (firstErr == secondErrParent) {// firstErr��ͷ������firstErr��secondErr�ĸ��ڵ�
				System.out.println("situation 1");
				firstErr.left = secondErrLeftChild;
				firstErr.right = secondErrRightChild;
				secondErr.right = firstErr;
				secondErr.left = firstErrLeftChild;
			} else {// firstErr��ͷ����firstErr����secondErr�ĸ��ڵ�
				if (secondErrParent.left == secondErr) {// firstErr��ͷ����firstErr����secondErr�ĸ��ڵ㣬second��secondParent������
					System.out.println("situation 2");
					secondErrParent.left = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;

				} else {// firstErr��ͷ����firstErr����secondErr�ĸ��ڵ㣬second��secondParent���Һ���
					System.out.println("situation 3");
					secondErrParent.right = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
				}
			}
			return secondErr;
		} else if (secondErr == head) {// second�ڵ���ͷ
			if (secondErr == firstErrParent) {// secondErr��ͷ������secondErr��firstErr�ĸ��ڵ�
				System.out.println("situation 4");
				secondErr.left = firstErrLeftChild;
				secondErr.right = firstErrRightChild;
				firstErr.left = secondErr;
				firstErr.right = secondErrRightChild;

			} else {// secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�
				if (firstErrParent.left == firstErr) {// secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�,first��firstParent������
					System.out.println("situation 5");
					firstErrParent.left = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;

				} else {// secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�,first��firstParent���Һ���
					System.out.println("situation 6");
					firstErrParent.right = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
				}
			}
			return firstErr;
		} else {// û���κ�һ���ڵ���ͷ�ڵ�
			if (firstErr == secondErrParent) {// û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ�
				if (firstErrParent.left == firstErr) {// û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ㣬first��firstParent������
					System.out.println("situation 7");
					firstErrParent.left = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErr;

				} else {// û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ㣬first��firstParent���Һ���
					System.out.println("situation 8");
					firstErrParent.right = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErr;
				}
			} else if (secondErr == firstErrParent) {// û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ�
				if (secondErrParent.left == secondErr) {// û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ�,��second��secondParent������
					System.out.println("situation 9");
					secondErrParent.left = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErr;
					firstErr.right = secondErrRightChild;
				} else {// û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ�,��second��secondParent���Һ���
					System.out.println("situation 10");
					secondErrParent.right = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErr;
					firstErr.right = secondErrRightChild;
				}
			} else {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�
				if (firstErrParent.left == firstErr) {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������
					if (secondErrParent.left == secondErr) {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������,second���丸������
						System.out.println("situation 11");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.left = secondErr;
						secondErrParent.left = firstErr;

					} else {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������,second���丸���Һ���
						System.out.println("situation 12");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.left = secondErr;
						secondErrParent.right = firstErr;
					}

				} else {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���
					if (secondErrParent.left == secondErr) {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���,second���丸������
						System.out.println("situation 13");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.right = secondErr;
						secondErrParent.left = firstErr;
					} else {// û���κ�һ���ڵ�ʱͷ�ڵ㣬ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���,second���丸���Һ���
						System.out.println("situation 14");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.right = secondErr;
						secondErrParent.right = firstErr;
					}
				}

			}
			return head;
		}

	}

	public static void generateOrderQueueAndParentMap(BinaryTreeNode head,
			Queue<BinaryTreeNode> orderQueue,
			HashMap<BinaryTreeNode, BinaryTreeNode> parentMap) { // �и��������������������������Ͷ�Ӧ���ڵ��map
		if (head == null) {
			return;
		}
		if (head.left != null) {
			parentMap.put(head.left, head);
			generateOrderQueueAndParentMap(head.left, orderQueue, parentMap);
		}
		orderQueue.add(head);
		if (head.right != null) {
			parentMap.put(head.right, head);
			generateOrderQueueAndParentMap(head.right, orderQueue, parentMap);
		}
	}

	public static void printInOrder(BinaryTreeNode head) {
		traverseInOrder(head);
		System.out.println();
	}

	public static void traverseInOrder(BinaryTreeNode head) {
		if (head == null) {
			return;
		}
		traverseInOrder(head.left);
		System.out.print(head.value + " ");
		traverseInOrder(head.right);
	}

	public static void main(String[] args) throws Exception {
		// ������û����������
		BinaryTreeNode head = new BinaryTreeNode(5);
		head.left = new BinaryTreeNode(3);
		head.right = new BinaryTreeNode(7);
		head.left.left = new BinaryTreeNode(2);
		head.left.right = new BinaryTreeNode(4);
		head.right.left = new BinaryTreeNode(6);
		head.right.right = new BinaryTreeNode(8);
		head.left.left.left = new BinaryTreeNode(1);
		printInOrder(head);
		BinaryTreeNode result = restoreBSTtree(head);
		printInOrder(result);
		System.out.println("===========================================");

		// ���1��firstErr��ͷ������firstErr��secondErr�ĸ��ڵ㡣
		BinaryTreeNode head1 = new BinaryTreeNode(7);// should be 5
		head1.left = new BinaryTreeNode(3);
		head1.right = new BinaryTreeNode(5);// should be 7
		head1.left.left = new BinaryTreeNode(2);
		head1.left.right = new BinaryTreeNode(4);
		head1.right.left = new BinaryTreeNode(6);
		head1.right.right = new BinaryTreeNode(8);
		head1.left.left.left = new BinaryTreeNode(1);
		printInOrder(head1);
		BinaryTreeNode result1 = restoreBSTtree(head1);
		printInOrder(result1);
		System.out.println("===========================================");

		// ���2��firstErr��ͷ������firstErr��secondErr�ĸ��ڵ㣬second��firstErr����
		BinaryTreeNode head2 = new BinaryTreeNode(6);// should be 5
		head2.left = new BinaryTreeNode(3);
		head2.right = new BinaryTreeNode(7);
		head2.left.left = new BinaryTreeNode(2);
		head2.left.right = new BinaryTreeNode(4);
		head2.right.left = new BinaryTreeNode(5); // should be 6
		head2.right.right = new BinaryTreeNode(8);
		head2.left.left.left = new BinaryTreeNode(1);
		printInOrder(head2);
		BinaryTreeNode result2 = restoreBSTtree(head2);
		printInOrder(result2);
		System.out.println("===========================================");

		// ���3��firstErr��ͷ����firstErr����secondErr�ĸ��ڵ㣬second��secondParent���Һ���
		BinaryTreeNode head3 = new BinaryTreeNode(8); // should be 5
		head3.left = new BinaryTreeNode(3);
		head3.right = new BinaryTreeNode(7);
		head3.left.left = new BinaryTreeNode(2);
		head3.left.right = new BinaryTreeNode(4);
		head3.right.left = new BinaryTreeNode(6);
		head3.right.right = new BinaryTreeNode(5); // should be 8
		head3.left.left.left = new BinaryTreeNode(1);
		printInOrder(head3);
		BinaryTreeNode result3 = restoreBSTtree(head3);
		printInOrder(result3);
		System.out.println("===========================================");

		// ���4��secondErr��ͷ������secondErr��firstErr�ĸ��ڵ㡣
		BinaryTreeNode head4 = new BinaryTreeNode(3); // should be 5
		head4.left = new BinaryTreeNode(5); // should be 3
		head4.right = new BinaryTreeNode(7);
		head4.left.left = new BinaryTreeNode(2);
		head4.left.right = new BinaryTreeNode(4);
		head4.right.left = new BinaryTreeNode(6);
		head4.right.right = new BinaryTreeNode(8);
		head4.left.left.left = new BinaryTreeNode(1);
		printInOrder(head4);
		BinaryTreeNode result4 = restoreBSTtree(head4);
		printInOrder(result4);
		System.out.println("===========================================");

		// ���5��secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�,first��firstParent������
		BinaryTreeNode head5 = new BinaryTreeNode(2); // should be 5
		head5.left = new BinaryTreeNode(3);
		head5.right = new BinaryTreeNode(7);
		head5.left.left = new BinaryTreeNode(5);// should be 2
		head5.left.right = new BinaryTreeNode(4);
		head5.right.left = new BinaryTreeNode(6);
		head5.right.right = new BinaryTreeNode(8);
		head5.left.left.left = new BinaryTreeNode(1);
		printInOrder(head5);
		BinaryTreeNode result5 = restoreBSTtree(head5);
		printInOrder(result5);
		System.out.println("===========================================");

		// ���6��secondErr��ͷ����secondErr����firstErr�ĸ��ڵ�,first��firstParent���Һ���
		BinaryTreeNode head6 = new BinaryTreeNode(4); // should be 5
		head6.left = new BinaryTreeNode(3);
		head6.right = new BinaryTreeNode(7);
		head6.left.left = new BinaryTreeNode(2);
		head6.left.right = new BinaryTreeNode(5); // should be 4
		head6.right.left = new BinaryTreeNode(6);
		head6.right.right = new BinaryTreeNode(8);
		head6.left.left.left = new BinaryTreeNode(1);
		printInOrder(head6);
		BinaryTreeNode result6 = restoreBSTtree(head6);
		printInOrder(result6);
		System.out.println("===========================================");

		// ���7��û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ�,first��firstParent������
		BinaryTreeNode head7 = new BinaryTreeNode(5);
		head7.left = new BinaryTreeNode(4); // should be 3
		head7.right = new BinaryTreeNode(7);
		head7.left.left = new BinaryTreeNode(2);
		head7.left.right = new BinaryTreeNode(3); // should be 4
		head7.right.left = new BinaryTreeNode(6);
		head7.right.right = new BinaryTreeNode(8);
		head7.left.left.left = new BinaryTreeNode(1);
		printInOrder(head7);
		BinaryTreeNode result7 = restoreBSTtree(head7);
		printInOrder(result7);
		System.out.println("===========================================");

		// ���8��û���κ�һ���ڵ���ͷ��firstErr��secondErr�ĸ��ڵ�,first��firstParent���Һ���
		BinaryTreeNode head8 = new BinaryTreeNode(5);
		head8.left = new BinaryTreeNode(3);
		head8.right = new BinaryTreeNode(8); // should be 7
		head8.left.left = new BinaryTreeNode(2);
		head8.left.right = new BinaryTreeNode(4);
		head8.right.left = new BinaryTreeNode(6);
		head8.right.right = new BinaryTreeNode(7); // should be 8
		head8.left.left.left = new BinaryTreeNode(1);
		printInOrder(head8);
		BinaryTreeNode result8 = restoreBSTtree(head8);
		printInOrder(result8);
		System.out.println("===========================================");

		// ���9��û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ㣬second��secondParent������
		BinaryTreeNode head9 = new BinaryTreeNode(5);
		head9.left = new BinaryTreeNode(2); // should be 3
		head9.right = new BinaryTreeNode(7);
		head9.left.left = new BinaryTreeNode(3); // should be 2
		head9.left.right = new BinaryTreeNode(4);
		head9.right.left = new BinaryTreeNode(6);
		head9.right.right = new BinaryTreeNode(8);
		head9.left.left.left = new BinaryTreeNode(1);
		printInOrder(head9);
		BinaryTreeNode result9 = restoreBSTtree(head9);
		printInOrder(result9);
		System.out.println("===========================================");

		// ���10��û���κ�һ���ڵ���ͷ��secondErr��firstErr�ĸ��ڵ㣬second��secondParent���Һ���
		BinaryTreeNode head10 = new BinaryTreeNode(5);
		head10.left = new BinaryTreeNode(3);
		head10.right = new BinaryTreeNode(6); // should be 7
		head10.left.left = new BinaryTreeNode(2);
		head10.left.right = new BinaryTreeNode(4);
		head10.right.left = new BinaryTreeNode(7); // should be 6
		head10.right.right = new BinaryTreeNode(8);
		head10.left.left.left = new BinaryTreeNode(1);
		printInOrder(head10);
		BinaryTreeNode result10 = restoreBSTtree(head10);
		printInOrder(result10);
		System.out.println("===========================================");

		// ���11��û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������,second���丸������
		BinaryTreeNode head11 = new BinaryTreeNode(5);
		head11.left = new BinaryTreeNode(3);
		head11.right = new BinaryTreeNode(7);
		head11.left.left = new BinaryTreeNode(6); // should be 2
		head11.left.right = new BinaryTreeNode(4);
		head11.right.left = new BinaryTreeNode(2); // should be 6
		head11.right.right = new BinaryTreeNode(8);
		head11.left.left.left = new BinaryTreeNode(1);
		printInOrder(head11);
		BinaryTreeNode result11 = restoreBSTtree(head11);
		printInOrder(result11);
		System.out.println("===========================================");

		// ���12��û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸������,second���丸���Һ���
		BinaryTreeNode head12 = new BinaryTreeNode(5);
		head12.left = new BinaryTreeNode(3);
		head12.right = new BinaryTreeNode(7);
		head12.left.left = new BinaryTreeNode(8); // should be 2
		head12.left.right = new BinaryTreeNode(4);
		head12.right.left = new BinaryTreeNode(6);
		head12.right.right = new BinaryTreeNode(2); // should be 8
		head12.left.left.left = new BinaryTreeNode(1);
		printInOrder(head12);
		BinaryTreeNode result12 = restoreBSTtree(head12);
		printInOrder(result12);
		System.out.println("===========================================");

		// ���13��û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���,second���丸������
		BinaryTreeNode head13 = new BinaryTreeNode(5);
		head13.left = new BinaryTreeNode(3);
		head13.right = new BinaryTreeNode(7);
		head13.left.left = new BinaryTreeNode(2);
		head13.left.right = new BinaryTreeNode(6); // should be 4
		head13.right.left = new BinaryTreeNode(4); // should be 6
		head13.right.right = new BinaryTreeNode(8);
		head13.left.left.left = new BinaryTreeNode(1);
		printInOrder(head13);
		BinaryTreeNode result13 = restoreBSTtree(head13);
		printInOrder(result13);
		System.out.println("===========================================");

		// ���14��û���κ�һ���ڵ���ͷ��ͬʱ˭Ҳ����˭�ĸ��ڵ�,first���丸���Һ���,second���丸���Һ���
		BinaryTreeNode head14 = new BinaryTreeNode(5);
		head14.left = new BinaryTreeNode(3);
		head14.right = new BinaryTreeNode(7);
		head14.left.left = new BinaryTreeNode(2);
		head14.left.right = new BinaryTreeNode(8); // should be 4
		head14.right.left = new BinaryTreeNode(6);
		head14.right.right = new BinaryTreeNode(4); // should be 8
		head14.left.left.left = new BinaryTreeNode(1);
		printInOrder(head14);
		BinaryTreeNode result14 = restoreBSTtree(head14);
		printInOrder(result14);
		System.out.println("===========================================");

		// ����������ԣ�����100000��������
		for (int times = 0; times != 100000; times++) {
			int[] test = { 5, 3, 7, 2, 4, 6, 8, 1 };
			int index1 = (int) (Math.random() * 8);
			int index2 = 0;
			do {
				index2 = (int) (Math.random() * 8);
			} while (index1 == index2);
			int tmp = test[index1];
			test[index1] = test[index2];
			test[index2] = tmp;
			int index = 0;
			BinaryTreeNode headAny = new BinaryTreeNode(test[index++]);
			headAny.left = new BinaryTreeNode(test[index++]);
			headAny.right = new BinaryTreeNode(test[index++]);
			headAny.left.left = new BinaryTreeNode(test[index++]);
			headAny.left.right = new BinaryTreeNode(test[index++]);
			headAny.right.left = new BinaryTreeNode(test[index++]);
			headAny.right.right = new BinaryTreeNode(test[index++]);
			headAny.left.left.left = new BinaryTreeNode(test[index++]);
			printInOrder(headAny);
			BinaryTreeNode resultAny = restoreBSTtree(headAny);
			printInOrder(resultAny);
			System.out.println("===========================================");
		}

	}

}
