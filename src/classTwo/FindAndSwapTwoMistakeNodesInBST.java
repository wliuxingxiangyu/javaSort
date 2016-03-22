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
		if (head.left == null && head.right == null) {// 这颗搜索二叉树只有一个头节点，所以不会失序
			return head;
		}
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		HashMap<BinaryTreeNode, BinaryTreeNode> parentMap = new HashMap<BinaryTreeNode, BinaryTreeNode>();
		generateOrderQueueAndParentMap(head, queue, parentMap);// 中根遍历二叉树，生成节点的List，还有每一个节点对应的父节点Map。

		/*
		 * 下面是找到错误节点办法：
		 * 通过搜索二叉树的中根遍历，我们已经得到了中根遍历的节点列表，看看节点的值在列表中是不是依次递增的，
		 * 		如果是，说明搜索二叉树没有失序，直接返回头节点；
		 * 		如果不是，我们需要考虑怎样判断一个失序的中根遍历列表是只需要调整两个节点就能变的有序的。
		 * 现在的问题变成了如何判断一个数组中的元素是否一直是升序的： 
		 * 		如果是，没问题；
		 * 		如果不是，这个数组能否在两个元素间仅做一次交互就能使得整体有序。
		 * 			 能，我们处理
		 * 			不能，我们报错告知函数的调用者，错误节点超过两个，我们的函数做不了
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
		 * 具体两个节点在树中怎么调换呢？其实粗起来比较简单，就是把两个节点从各自的“周围环境”中挖出，调换位置后，再恢复与“周围环境”的联系。这个“周围环境
		 * ” 指两个错误节点各自父节点和子节点，举个例子：
		 * 
		 * 围绕着firstErr的环境为： 
		 * 				firstErrParent 
		 * 					| 
		 * 			  ___firstErr___ 
		 * 			 /			 	\
		 * firstErrLeftChild firstErrRightChild
		 * 
		 * 
		 * 围绕着secondErr的环境为： 
		 * 				secondErrParent 
		 * 						| 
		 * 				___secondErr___ 
		 * 			   /		 	   \
		 * secondErrLeftChild secondErrRightChild
		 * 
		 * 
		 * 如上图所示，我们需要将firstErrParent指向secondErr，secondErrParent指向firstErr，
		 * 同时firstErr指向secondErr的左右孩子，secondErr指向firstErr的左右孩子。 
		 * 大体上讲就是这样的，但是仔细一想实现细节，我们发现我们必须讨论如下三个问题：
		 * 问题一： 是否其中有一个是头？
		 * 如果有，谁是头？ 
		 * 问题二：是否两个节点挨着？如果挨着，谁是谁的父节点，子节点是父节点的左孩子还是右孩子；如果不挨着呢？ 
		 * 问题三：
		 * 两个节点分别是各自父节点的左孩子还是右孩子
		 * 注意点：在中根遍历序列中，firstErr是先出现的，secondErr是后出现的。
		 * 所以firstErr一定不是secondErr的右孩子； secondErr也一定不是firstErr的左孩子。
		 * 
		 * “3个问题和2个注意点”相互影响，十分复杂，情况非常复杂，个人认为无法简化。比如如果firstErr是头节点，
		 * 那么就不用在追求firstErr是firstErrParent的左孩子还是右孩子， 因为firstErrParent为null。
		 * 最终，经过细细的整理，情况有14种： 
		 * 1.firstErr是头，并且firstErr是secondErr的父节点。
		 * 2.firstErr是头，但firstErr不是secondErr的父节点，second是secondParent的左孩子
		 * 3.firstErr是头，但firstErr不是secondErr的父节点，second是secondParent的右孩子
		 * 4.secondErr是头，并且secondErr是firstErr的父节点。
		 * 5.secondErr是头，但secondErr不是firstErr的父节点,first是firstParent的左孩子
		 * 6.secondErr是头，但secondErr不是firstErr的父节点,first是firstParent的右孩子
		 * 7.没有任何一个节点是头，firstErr是secondErr的父节点,first是firstParent的左孩子
		 * 8.没有任何一个节点是头，firstErr是secondErr的父节点,first是firstParent的右孩子
		 * 9.没有任何一个节点是头，secondErr是firstErr的父节点，second是secondParent的左孩子
		 * 10.没有任何一个节点是头，secondErr是firstErr的父节点，second是secondParent的右孩子
		 * 11.没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的左孩子,second是其父的左孩子
		 * 12.没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的左孩子,second是其父的右孩子
		 * 13.没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的右孩子,second是其父的左孩子
		 * 14.没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的右孩子,second是其父的右孩子 
		 */
		if (firstErr == head) {// firstErr是头
			if (firstErr == secondErrParent) {// firstErr是头，并且firstErr是secondErr的父节点
				System.out.println("situation 1");
				firstErr.left = secondErrLeftChild;
				firstErr.right = secondErrRightChild;
				secondErr.right = firstErr;
				secondErr.left = firstErrLeftChild;
			} else {// firstErr是头，但firstErr不是secondErr的父节点
				if (secondErrParent.left == secondErr) {// firstErr是头，但firstErr不是secondErr的父节点，second是secondParent的左孩子
					System.out.println("situation 2");
					secondErrParent.left = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;

				} else {// firstErr是头，但firstErr不是secondErr的父节点，second是secondParent的右孩子
					System.out.println("situation 3");
					secondErrParent.right = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
				}
			}
			return secondErr;
		} else if (secondErr == head) {// second节点是头
			if (secondErr == firstErrParent) {// secondErr是头，并且secondErr是firstErr的父节点
				System.out.println("situation 4");
				secondErr.left = firstErrLeftChild;
				secondErr.right = firstErrRightChild;
				firstErr.left = secondErr;
				firstErr.right = secondErrRightChild;

			} else {// secondErr是头，但secondErr不是firstErr的父节点
				if (firstErrParent.left == firstErr) {// secondErr是头，但secondErr不是firstErr的父节点,first是firstParent的左孩子
					System.out.println("situation 5");
					firstErrParent.left = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;

				} else {// secondErr是头，但secondErr不是firstErr的父节点,first是firstParent的右孩子
					System.out.println("situation 6");
					firstErrParent.right = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
				}
			}
			return firstErr;
		} else {// 没有任何一个节点是头节点
			if (firstErr == secondErrParent) {// 没有任何一个节点是头，firstErr是secondErr的父节点
				if (firstErrParent.left == firstErr) {// 没有任何一个节点是头，firstErr是secondErr的父节点，first是firstParent的左孩子
					System.out.println("situation 7");
					firstErrParent.left = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErr;

				} else {// 没有任何一个节点是头，firstErr是secondErr的父节点，first是firstParent的右孩子
					System.out.println("situation 8");
					firstErrParent.right = secondErr;
					firstErr.left = secondErrLeftChild;
					firstErr.right = secondErrRightChild;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErr;
				}
			} else if (secondErr == firstErrParent) {// 没有任何一个节点是头，secondErr是firstErr的父节点
				if (secondErrParent.left == secondErr) {// 没有任何一个节点是头，secondErr是firstErr的父节点,且second是secondParent的左孩子
					System.out.println("situation 9");
					secondErrParent.left = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErr;
					firstErr.right = secondErrRightChild;
				} else {// 没有任何一个节点是头，secondErr是firstErr的父节点,且second是secondParent的右孩子
					System.out.println("situation 10");
					secondErrParent.right = firstErr;
					secondErr.left = firstErrLeftChild;
					secondErr.right = firstErrRightChild;
					firstErr.left = secondErr;
					firstErr.right = secondErrRightChild;
				}
			} else {// 没有任何一个节点时头节点，同时谁也不是谁的父节点
				if (firstErrParent.left == firstErr) {// 没有任何一个节点时头节点，同时谁也不是谁的父节点,first是其父的左孩子
					if (secondErrParent.left == secondErr) {// 没有任何一个节点时头节点，同时谁也不是谁的父节点,first是其父的左孩子,second是其父的左孩子
						System.out.println("situation 11");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.left = secondErr;
						secondErrParent.left = firstErr;

					} else {// 没有任何一个节点时头节点，同时谁也不是谁的父节点,first是其父的左孩子,second是其父的右孩子
						System.out.println("situation 12");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.left = secondErr;
						secondErrParent.right = firstErr;
					}

				} else {// 没有任何一个节点时头节点，同时谁也不是谁的父节点,first是其父的右孩子
					if (secondErrParent.left == secondErr) {// 没有任何一个节点时头节点，同时谁也不是谁的父节点,first是其父的右孩子,second是其父的左孩子
						System.out.println("situation 13");
						firstErr.left = secondErrLeftChild;
						firstErr.right = secondErrRightChild;
						secondErr.left = firstErrLeftChild;
						secondErr.right = firstErrRightChild;
						firstErrParent.right = secondErr;
						secondErrParent.left = firstErr;
					} else {// 没有任何一个节点时头节点，同时谁也不是谁的父节点,first是其父的右孩子,second是其父的右孩子
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
			HashMap<BinaryTreeNode, BinaryTreeNode> parentMap) { // 中根遍历二叉树并生成有序链表，和对应父节点的map
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
		// 二叉树没问题的情况：
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

		// 情况1：firstErr是头，并且firstErr是secondErr的父节点。
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

		// 情况2：firstErr是头，并且firstErr是secondErr的父节点，second是firstErr左孩子
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

		// 情况3：firstErr是头，但firstErr不是secondErr的父节点，second是secondParent的右孩子
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

		// 情况4：secondErr是头，并且secondErr是firstErr的父节点。
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

		// 情况5：secondErr是头，但secondErr不是firstErr的父节点,first是firstParent的左孩子
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

		// 情况6：secondErr是头，但secondErr不是firstErr的父节点,first是firstParent的右孩子
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

		// 情况7：没有任何一个节点是头，firstErr是secondErr的父节点,first是firstParent的左孩子
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

		// 情况8：没有任何一个节点是头，firstErr是secondErr的父节点,first是firstParent的右孩子
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

		// 情况9：没有任何一个节点是头，secondErr是firstErr的父节点，second是secondParent的左孩子
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

		// 情况10：没有任何一个节点是头，secondErr是firstErr的父节点，second是secondParent的右孩子
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

		// 情况11：没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的左孩子,second是其父的左孩子
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

		// 情况12：没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的左孩子,second是其父的右孩子
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

		// 情况13：没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的右孩子,second是其父的左孩子
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

		// 情况14：没有任何一个节点是头，同时谁也不是谁的父节点,first是其父的右孩子,second是其父的右孩子
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

		// 任意情况测试，测试100000组随机情况
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
