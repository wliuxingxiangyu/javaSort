package classEight;

import java.util.HashMap;

public class GetTimesTopKInArrayCurrently{

	public static class Node{
		public String str;
		public int times;

		public Node(String value){
			this.str=value;
			this.times=1;
		}
	}

	public static class TopKRecord{
		private Node[] heapArr;
		private int index;
		private HashMap<String,Node> strNodeMap;
		private HashMap<Node,Integer> nodeIndexMap;

		public TopKRecord(int size){
			this.heapArr=new Node[size];
			this.index=0;
			this.strNodeMap=new HashMap<String,Node>();
			this.nodeIndexMap=new HashMap<Node,Integer>();
		}

		public void add(String str){
			Node curNode=null;
			int preIndex=-1;
			if(!this.strNodeMap.containsKey(str)){
				curNode=new Node(str);
				this.strNodeMap.put(str,curNode);
				this.nodeIndexMap.put(curNode,-1);
			}else{
				curNode=this.strNodeMap.get(str);
				curNode.times++;
				preIndex=this.nodeIndexMap.get(curNode);
			}
			if(preIndex==-1){
				if(index==this.heapArr.length){//堆满了，调堆
					if(this.heapArr[0].times<curNode.times){
						this.nodeIndexMap.put(this.heapArr[0],-1);
						this.nodeIndexMap.put(curNode,0);
						this.heapArr[0]=curNode;
						this.heapify(0,index);
					}
				}else{//堆未满，插入
					this.nodeIndexMap.put(curNode,index);
					this.heapArr[index]=curNode;
					this.heapInsert(index++);
				}
			}else{
				this.heapify(preIndex,index);
			}
		}

		public void printTopK(){
			System.out.println("TOP: ");
			for(int i=0;i!=this.heapArr.length;i++){
				if(this.heapArr[i]==null){
					break;
				}
				System.out.print("Num: "+this.heapArr[i].str);
				System.out.println(" Times: "+this.heapArr[i].times);
			}
		}

		private void heapInsert(int index){
			while(index!=0){
				int parent=(index-1)/2;
				if(this.heapArr[index].times<this.heapArr[parent].times){
					swap(parent,index);
					index=parent;
				}else{
					break;
				}
			}
		}

		private void heapify(int index,int heapSize){
			int left=index*2+1;
			int right=index*2+2;
			int smallest=index;
			while(left<heapSize){
				if(this.heapArr[left].times<this.heapArr[index].times){
					smallest=left;
				}
				if(right<heapSize
						&&this.heapArr[right].times<this.heapArr[smallest].times){
					smallest=right;
				}
				if(smallest!=index){
					swap(smallest,index);
				}else{
					break;
				}
				index=smallest;
				left=index*2+1;
				right=index*2+2;
			}
		}

		private void swap(int index1,int index2){//map1,2都要换
			this.nodeIndexMap.put(this.heapArr[index1],index2);
			this.nodeIndexMap.put(this.heapArr[index2],index1);
			Node tmp=this.heapArr[index1];
			this.heapArr[index1]=this.heapArr[index2];
			this.heapArr[index2]=tmp;
		}

	}

	public static String[] generateRandomArray(int len,int max){
		String[] res=new String[len];
		for(int i=0;i!=len;i++){
			res[i]=String.valueOf((int)(Math.random()*(max+1)));
		}
		return res;
	}

	public static void printArray(String[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		TopKRecord record=new TopKRecord(2);
		record.add("zuo");
		record.printTopK();
		record.add("cheng");
		record.add("cheng");
		record.printTopK();
		record.add("Yun");
		record.add("Yun");
		record.printTopK();

	}
}