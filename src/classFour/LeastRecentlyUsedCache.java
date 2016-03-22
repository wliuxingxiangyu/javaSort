package classFour;

import java.util.HashMap;

public class LeastRecentlyUsedCache{//设计数据结构

	public static class Node<V>{//将数据转化为Node类型
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
			if(this.head==null){//双端队列一个节点都没有
				this.head=newNode;
				this.tail=newNode;
			}else{
				this.tail.next=newNode;
				newNode.last=this.tail;
				this.tail=newNode;
			}
		}

		public void moveNodeToTail(Node<V> node){//调优先级
			if(this.tail==node){//插入的本身是尾
				return;
			}
			if(this.head==node){
				this.head=node.next;
				this.head.last=null;
			}else{
				node.last.next=node.next;
				node.next.last=node.last;
			}
			node.last=this.tail;//多分支共同部分
			node.next=null;
			this.tail.next=node;
			this.tail=node;
		}

		public Node<V> removeAndChangeHead(){
			if(this.head==null){
				return null;
			}
			Node<V> res=this.head;
			if(this.head==this.tail){//只有一个节点
				this.head=null;
				this.tail=null;
			}else{
				this.head=res.next;
				res.next=null;//断链
				this.head.last=null;//断链
			}
			return res;//返回老头
		}

	}

	public static class MyCache<K,V>{
		private HashMap<K,Node<V>> keyNodeMap;//K到Node<V>映射
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
			if(this.keyNodeMap.containsKey(key)){//有
				Node<V> res=this.keyNodeMap.get(key);
				this.nodeList.moveNodeToTail(res);
				return res.value;//返回数据项
			}
			return null;//无
		}

		public void set(K key,V value){
			if(this.keyNodeMap.containsKey(key)){//更新z=6,为,z=8
				Node<V> node=this.keyNodeMap.get(key);
				node.value=value;
				this.nodeList.moveNodeToTail(node);//移到尾
			}else{//新加z=9
				Node<V> newNode=new Node<V>(value);
				this.keyNodeMap.put(key,newNode);
				this.nodeKeyMap.put(newNode,key);
				this.nodeList.addNode(newNode);
				if(this.keyNodeMap.size()==this.capacity+1){
					this.removeMostUnusedCache();
				}
			}
		}

		private void removeMostUnusedCache(){//移除经常不使用的
			Node<V> removeNode=this.nodeList.removeAndChangeHead();
			K removeKey=this.nodeKeyMap.get(removeNode);
			this.nodeKeyMap.remove(removeNode);//删除表
			this.keyNodeMap.remove(removeKey);
		}

	}

	public static void main(String[] args){

	}

}
