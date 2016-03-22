package classTwo;

public class TraversalTreeByMorris{//L2P7�ȸ����и�������һ��,ֻ�Ǵ�ӡʱ�䲻ͬ,ʱ�临�Ӷ�O(N),����ռ临�Ӷ�O(1)

	public static class BinaryTreeNode{
		public int value;
		BinaryTreeNode left;
		BinaryTreeNode right;

		public BinaryTreeNode(int data){
			this.value=data;
		}
	}

	public static void traversalPreOrderMorris(BinaryTreeNode head){//�ȸ�������
		if(head==null){
			return;
		}
		BinaryTreeNode cur1Node=head;
		while(cur1Node!=null){// ��ǰ��
			BinaryTreeNode cur2Node=cur1Node.left;
			if(cur2Node!=null){
				while(cur2Node.right!=null&&cur2Node.right!=cur1Node){
					  //cur2�������ǿ� �� ��������Ϊcur1
					cur2Node=cur2Node.right;//cur2������������
				}//���ϱ�ʾ:һ���ڵ������������ǰ���ڵ����������������ұߵĽڵ�
				if(cur2Node.right==null){
					cur2Node.right=cur1Node;
					System.out.print(cur1Node.value+" ");
					cur1Node=cur1Node.left;
					continue;
				}else{
					cur2Node.right=null;//�����ָ�ԭ��
				}
			}else{
				System.out.print(cur1Node.value+" ");// ĳһ������ڵ�
			}
			cur1Node=cur1Node.right;
		}
		System.out.println();
	}

	public static void traversalInOrderMorris(BinaryTreeNode head){//�������
		if(head==null){
			return;
		}
		BinaryTreeNode cur1Node=head;
		while(cur1Node!=null){
			BinaryTreeNode cur2Node=cur1Node.left;
			if(cur2Node!=null){//��Ϊcur1Node.left��cur2Node,����ǿ�,����Ӧ������cur2Node
				while(cur2Node.right!=null&&cur2Node.right!=cur1Node){
					cur2Node=cur2Node.right; 
				}//���ϱ�ʾ:һ���ڵ������������ǰ���ڵ�   ���������������ұߵĽڵ�
				if(cur2Node.right==null){
					cur2Node.right=cur1Node;//�Ѹ��ڵ�ǿ����Ϊ���ӵ��Һ���
					cur1Node=cur1Node.left;//������,�ƶ���������
					continue;
				}else{
					cur2Node.right=null;//�����ָ�ԭ��
				}
			}
			System.out.print(cur1Node.value+" ");
			cur1Node=cur1Node.right;//��ӡ�����ٴ�ӡ�Һ���
		}
		System.out.println();
	}

	public static void traversalProOrderMorris(BinaryTreeNode head){//�������
		if(head==null){
			return;
		}
		BinaryTreeNode helpNode=new BinaryTreeNode(Integer.MIN_VALUE);
		helpNode.left=head;
		BinaryTreeNode curNode1=helpNode;
		while(curNode1!=null){
			BinaryTreeNode curNode2=curNode1.left;
			if(curNode2!=null){
				while(curNode2.right!=null&&curNode2.right!=curNode1){
					curNode2=curNode2.right;
				}
				if(curNode2.right==null){
					curNode2.right=curNode1;
					curNode1=curNode1.left;
					continue;
				}else{
					printProPath(curNode1.left,curNode2);
					curNode2.right=null;
				}
			}
			curNode1=curNode1.right;
		}
		helpNode.left=null;
		System.out.println();
	}

	public static void printProPath(BinaryTreeNode from,BinaryTreeNode to){
		reversePath(from,to);
		BinaryTreeNode cur=to;
		while(true){
			System.out.print(cur.value+" ");
			if(cur==from){
				break;
			}
			cur=cur.right;
		}
		reversePath(to,from);
	}

	public static void reversePath(BinaryTreeNode from,BinaryTreeNode to){
		if(from==to){// ����
			return;
		}
		BinaryTreeNode cur=from;
		BinaryTreeNode curNext=from.right;
		while(cur!=to){
			BinaryTreeNode tmp=curNext.right;
			curNext.right=cur;
			cur=curNext;
			curNext=tmp;
		}
	}

	public static void main(String[] args){
		BinaryTreeNode head=new BinaryTreeNode(1);
		head.left=new BinaryTreeNode(2);
		head.right=new BinaryTreeNode(3);
		head.left.left=new BinaryTreeNode(4);//2����
		head.left.right=new BinaryTreeNode(5);//2����
		head.right.left=new BinaryTreeNode(6);//3����
		head.right.right=new BinaryTreeNode(7);//3����

		traversalPreOrderMorris(head);
		traversalInOrderMorris(head);
		traversalProOrderMorris(head);

	}

}
