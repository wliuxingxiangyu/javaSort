package classFour;

import java.util.HashMap;

public class LeastRecentlyUsedCache{//������ݽṹ

	public static class Node<V>{//������ת��ΪNode����
		public V value;
		public Node<V> last;
		public Node<V> next;

		public Node(V value){
			this.value=value;
		}
	}

	public static class NodeDoubleLinkedList<V>{
		private Node<V> head;
		private Node<V> tail;

		public NodeDoubleLinkedList(){
			this.head=null;
			this.tail=null;
		}

		public void addNode(Node<V> newNode){
			if(newNode==null){
				return;
			}
			if(this.head==null){//˫�˶���һ���ڵ㶼û��
				this.head=newNode;
				this.tail=newNode;
			}else{
				this.tail.next=newNode;
				newNode.last=this.tail;
				this.tail=newNode;
			}
		}

		public void moveNodeToTail(Node<V> node){//�����ȼ�
			if(this.tail==node){//����ı�����β
				return;
			}
			if(this.head==node){
				this.head=node.next;
				this.head.last=null;
			}else{
				node.last.next=node.next;
				node.next.last=node.last;
			}
			node.last=this.tail;//���֧��ͬ����
			node.next=null;
			this.tail.next=node;
			this.tail=node;
		}

		public Node<V> removeAndChangeHead(){
			if(this.head==null){
				return null;
			}
			Node<V> res=this.head;
			if(this.head==this.tail){//ֻ��һ���ڵ�
				this.head=null;
				this.tail=null;
			}else{
				this.head=res.next;
				res.next=null;//����
				this.head.last=null;//����
			}
			return res;//������ͷ
		}

	}

	public static class MyCache<K,V>{
		private HashMap<K,Node<V>> keyNodeMap;//K��Node<V>ӳ��
		private HashMap<Node<V>,K> nodeKeyMap;
		private NodeDoubleLinkedList<V> nodeList;
		private int capacity;

		public MyCache(int capacity){
			if(capacity<1){
				throw new RuntimeException(
						"The capacity should be larger than 0.");
			}
			this.keyNodeMap=new HashMap<K,Node<V>>();
			this.nodeKeyMap=new HashMap<Node<V>,K>();
			this.nodeList=new NodeDoubleLinkedList<V>();
			this.capacity=capacity;
		}

		public V get(K key){
			if(this.keyNodeMap.containsKey(key)){//��
				Node<V> res=this.keyNodeMap.get(key);
				this.nodeList.moveNodeToTail(res);
				return res.value;//����������
			}
			return null;//��
		}

		public void set(K key,V value){
			if(this.keyNodeMap.containsKey(key)){//����z=6,Ϊ,z=8
				Node<V> node=this.keyNodeMap.get(key);
				node.value=value;
				this.nodeList.moveNodeToTail(node);//�Ƶ�β
			}else{//�¼�z=9
				Node<V> newNode=new Node<V>(value);
				this.keyNodeMap.put(key,newNode);
				this.nodeKeyMap.put(newNode,key);
				this.nodeList.addNode(newNode);
				if(this.keyNodeMap.size()==this.capacity+1){
					this.removeMostUnusedCache();
				}
			}
		}

		private void removeMostUnusedCache(){//�Ƴ�������ʹ�õ�
			Node<V> removeNode=this.nodeList.removeAndChangeHead();
			K removeKey=this.nodeKeyMap.get(removeNode);
			this.nodeKeyMap.remove(removeNode);//ɾ����
			this.keyNodeMap.remove(removeKey);
		}

	}

	public static void main(String[] args){

	}

}
