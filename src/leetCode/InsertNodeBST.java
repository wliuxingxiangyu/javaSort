package leetCode;

public class InsertNodeBST {
	public class TreeNode {
		public int val;
		public TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}

	// 递归
	public static TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			root = node;
			return root;//此处时返回root,而不是node,因为检查结果是检查root.
		} else if (node.val > root.val ) {//待插入节点值比root大,往右子树上插.
			root.right = insertNode(root.right, node);//root.right==null判断,交给结束处理了
			return root;
		} else {//待插入节点值比root小,往左子树上插//root.left==null判断,交给结束处理了.不用递归前判断.
			root.left = insertNode(root.left, node);
			return root;
		}
	}

	// 二叉查找树节点插入只插在叶子节点处
	// 非递归
	public static TreeNode insertNodeNoRecursion(TreeNode root, TreeNode node) {
		if (root == null) {
			root = node;
			return root;
		}
		TreeNode p = root;
		while (p != null) {
			if (p.val < node.val) {
				if (p.right == null) {
					p.right = node;
					return root;
				}
				p = p.right;
			} else {
				if (p.left == null) {
					p.left = node;
					return root;
				}
				p = p.left;
			}
		}
		return root;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
