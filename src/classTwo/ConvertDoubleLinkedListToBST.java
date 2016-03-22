package classTwo;

public class ConvertDoubleLinkedListToBST{// L2P18
	//S1:双向链表节点“存入”节点数组 (获得双向链表节点的个数);//S2:节点数组“转为”BST

	public static class Node{
		public int value;
		public Node last;
		public Node next;

		public Node(int data){
			this.value=data;
		}
	}

	public static Node convertDoubleLinkedListToBST(Node head){
		if(head==null){
			return head;
		}
		Node[] nodeArr=generateNodesArr(head);//双向链表节点“存入”节点数组
		return generateBSTFromNodeArray(nodeArr,0,nodeArr.length-1);//节点数组“转为”BST
	}

	public static Node[] generateNodesArr(Node head){//双向链表节点“存入”节点数组
		Node[] nodeArr=new Node[getDoubleLinkedListLength(head)];//获得双向链表节点的个数
		int index=0;
		while(head!=null){
			nodeArr[index++]=head;
			head=head.next;
		}
		return nodeArr;
	}

	public static int getDoubleLinkedListLength(Node head){//获得双向链表节点的个数
		int res=0;
		while(head!=null){
			res++;
			head=head.next;
		}
		return res;
	}

	public static Node generateBSTFromNodeArray(Node[] nodeArr,int start,int end){
		//S1:双向链表节点“存入”节点数组 (获得双向链表节点的个数);//S2:节点数组“转为”BST
		if(start==end){//只有一个节点
			return nodeArr[start];
		}
		if(start==end-1){//只有2个节点
			nodeArr[end].last=null;//断反向链
			return nodeArr[start];//nodeArr[start]-->nodeArr[end]
		}
		int mid=(start+end)/2;//节点数3.4..例如9个,mid=(0+8)/2=4;
		nodeArr[mid-1].next=null;//例如nodeArr[3].next即根的左孩子“指向根”断链,
		nodeArr[mid+1].last=null;//断链,例如nodeArr[5].last即根的右孩子“指向根”断链,
		nodeArr[mid].last=generateBSTFromNodeArray(nodeArr,start,mid-1);//例如nodeArr[4].next即根的左子树=数组的左边
		nodeArr[mid].next=generateBSTFromNodeArray(nodeArr,mid+1,end);//例如nodeArr[4].next即根的右子树=数组的右边
		return nodeArr[mid];//返回根节点
	}

	public static void printDoubleLinkedList(Node head){//打印双向链表
		if(head==null){
			return;
		}
		Node previous=null;
		System.out.println("正向打印双向链表： ");
		while(head!=null){
			System.out.print(head.value+" ");
			previous=head;
			head=head.next;
		}
		
		System.out.println();
		System.out.println("逆向打印双向链表：");
		while(previous!=null){
			System.out.print(previous.value+" ");
			head=previous;
			previous=previous.last;
		}
		System.out.println();
	}

	public static String generateBSTString(Node head){//将二叉树转化为字符串
		String str="";
		return generateProcess(head,str);//String 类型的传递 是引用传递
	}

	public static String generateProcess(Node head,String str){
		if(head==null){
			return str+"#";//String 类型的传递 是引用传递
		}
		str+=head.value;
		str=generateProcess(head.last,str);//将二叉树“左孩子”转化为字符串
		return generateProcess(head.next,str);//将二叉树“右孩子”转化为字符串
	}

	public static void main(String[] args){
		Node head=new Node(1);//建立双向链表
		head.next=new Node(2);
		head.next.last=head;
		head.next.next=new Node(3);//新建节点且挂链
		head.next.next.last=head.next;//逆向挂链
		head.next.next.next=new Node(4);
		head.next.next.next.last=head.next.next;
		head.next.next.next.next=new Node(5);
		head.next.next.next.next.last=head.next.next.next;
		head.next.next.next.next.next=new Node(6);
		head.next.next.next.next.next.last=head.next.next.next.next;
		head.next.next.next.next.next.next=new Node(7);
		head.next.next.next.next.next.next.last=head.next.next.next.next.next;
		head.next.next.next.next.next.next.next=new Node(8);
		head.next.next.next.next.next.next.next.last=head.next.next.next.next.next.next;
		head.next.next.next.next.next.next.next.next=new Node(9);
		head.next.next.next.next.next.next.next.next.last=head.next.next.next.next.next.next.next;//逆向挂链
		printDoubleLinkedList(head);
		Node bstHead=convertDoubleLinkedListToBST(head);
		System.out.println(generateBSTString(bstHead));

	}

}
