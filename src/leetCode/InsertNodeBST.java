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

	// �ݹ�
	public static TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			root = node;
			return root;//�˴�ʱ����root,������node,��Ϊ������Ǽ��root.
		} else if (node.val > root.val ) {//������ڵ�ֵ��root��,���������ϲ�.
			root.right = insertNode(root.right, node);//root.right==null�ж�,��������������
			return root;
		} else {//������ڵ�ֵ��rootС,���������ϲ�//root.left==null�ж�,��������������.���õݹ�ǰ�ж�.
			root.left = insertNode(root.left, node);
			return root;
		}
	}

	// ����������ڵ����ֻ����Ҷ�ӽڵ㴦
	// �ǵݹ�
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
		// TODO �Զ����ɵķ������

	}

}
