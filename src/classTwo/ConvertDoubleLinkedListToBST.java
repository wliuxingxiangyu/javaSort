package classTwo;

public class ConvertDoubleLinkedListToBST{// L2P18
	//S1:˫������ڵ㡰���롱�ڵ����� (���˫������ڵ�ĸ���);//S2:�ڵ����顰תΪ��BST

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
		Node[] nodeArr=generateNodesArr(head);//˫������ڵ㡰���롱�ڵ�����
		return generateBSTFromNodeArray(nodeArr,0,nodeArr.length-1);//�ڵ����顰תΪ��BST
	}

	public static Node[] generateNodesArr(Node head){//˫������ڵ㡰���롱�ڵ�����
		Node[] nodeArr=new Node[getDoubleLinkedListLength(head)];//���˫������ڵ�ĸ���
		int index=0;
		while(head!=null){
			nodeArr[index++]=head;
			head=head.next;
		}
		return nodeArr;
	}

	public static int getDoubleLinkedListLength(Node head){//���˫������ڵ�ĸ���
		int res=0;
		while(head!=null){
			res++;
			head=head.next;
		}
		return res;
	}

	public static Node generateBSTFromNodeArray(Node[] nodeArr,int start,int end){
		//S1:˫������ڵ㡰���롱�ڵ����� (���˫������ڵ�ĸ���);//S2:�ڵ����顰תΪ��BST
		if(start==end){//ֻ��һ���ڵ�
			return nodeArr[start];
		}
		if(start==end-1){//ֻ��2���ڵ�
			nodeArr[end].last=null;//�Ϸ�����
			return nodeArr[start];//nodeArr[start]-->nodeArr[end]
		}
		int mid=(start+end)/2;//�ڵ���3.4..����9��,mid=(0+8)/2=4;
		nodeArr[mid-1].next=null;//����nodeArr[3].next���������ӡ�ָ���������,
		nodeArr[mid+1].last=null;//����,����nodeArr[5].last�������Һ��ӡ�ָ���������,
		nodeArr[mid].last=generateBSTFromNodeArray(nodeArr,start,mid-1);//����nodeArr[4].next������������=��������
		nodeArr[mid].next=generateBSTFromNodeArray(nodeArr,mid+1,end);//����nodeArr[4].next������������=������ұ�
		return nodeArr[mid];//���ظ��ڵ�
	}

	public static void printDoubleLinkedList(Node head){//��ӡ˫������
		if(head==null){
			return;
		}
		Node previous=null;
		System.out.println("�����ӡ˫������ ");
		while(head!=null){
			System.out.print(head.value+" ");
			previous=head;
			head=head.next;
		}
		
		System.out.println();
		System.out.println("�����ӡ˫������");
		while(previous!=null){
			System.out.print(previous.value+" ");
			head=previous;
			previous=previous.last;
		}
		System.out.println();
	}

	public static String generateBSTString(Node head){//��������ת��Ϊ�ַ���
		String str="";
		return generateProcess(head,str);//String ���͵Ĵ��� �����ô���
	}

	public static String generateProcess(Node head,String str){
		if(head==null){
			return str+"#";//String ���͵Ĵ��� �����ô���
		}
		str+=head.value;
		str=generateProcess(head.last,str);//�������������ӡ�ת��Ϊ�ַ���
		return generateProcess(head.next,str);//�����������Һ��ӡ�ת��Ϊ�ַ���
	}

	public static void main(String[] args){
		Node head=new Node(1);//����˫������
		head.next=new Node(2);
		head.next.last=head;
		head.next.next=new Node(3);//�½��ڵ��ҹ���
		head.next.next.last=head.next;//�������
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
		head.next.next.next.next.next.next.next.next.last=head.next.next.next.next.next.next.next;//�������
		printDoubleLinkedList(head);
		Node bstHead=convertDoubleLinkedListToBST(head);
		System.out.println(generateBSTString(bstHead));

	}

}
