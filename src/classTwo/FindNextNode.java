package classTwo;

public class FindNextNode {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getNextNode(Node node){//L2P4找后继节点(中序遍历排列)
		if (node == null) {
			return null;
		}
		if (node.right != null){//若右孩子非空，据中序遍历：左中右。
			return getLeftMost(node.right);//返回node右孩子  最左端的节点:即后继
		} else {//若右孩子空,回溯找(最靠近的,作为左孩子的)祖先节点,
			Node parent = node.parent;
			while(parent!=null&&parent.left!=node){
				//根节点的parent为空,此时不再往上找了,
				node = parent;
				parent = node.parent;
			}
			return parent;//左节点的后继为父节点,
		}
	}

	public static Node getLeftMost(Node node){//找最左节点
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;//节点值为1
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.left.left.right;//节点值为2
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.left;//节点值为3
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getNextNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getNextNode(test));
	}

}
