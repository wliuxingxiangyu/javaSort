package classEight;

import java.util.Arrays;
import java.util.HashSet;

public class FindBiggestSumFromTwoArray{

	public static class HeapNode{
		public int row;
		public int col;
		public int value;

		public HeapNode(int row,int col,int value){
			this.row=row;
			this.col=col;
			this.value=value;
		}
	}

	public static int[] getKBiggestSumArraySortAll(int[] arr1,int[] arr2,
			int topK){
		int[] all=new int[arr1.length*arr2.length];
		int index=0;
		for(int i=0;i!=arr1.length;i++){
			for(int j=0;j!=arr2.length;j++){
				all[index++]=arr1[i]+arr2[j];
			}
		}
		Arrays.sort(all);
		int[] res=new int[Math.min(topK,all.length)];
		index=all.length-1;
		for(int i=0;i!=res.length;i++){
			res[i]=all[index--];
		}
		return res;
	}

	public static int[] getKBiggestSumArray(int[] a1,int[] a2,int topK){
		if(a1==null||a2==null||topK<1){
			return null;
		}
		topK=Math.min(topK,a1.length*a2.length);
		HeapNode[] heapArr=new HeapNode[topK+1];
		heapArr[0]=new HeapNode(a1.length-1,a2.length-1,a1[a1.length-1]
				+a2[a2.length-1]);
		int heapSize=1;
		HashSet<String> positionSet=new HashSet<String>();
		int[] res=new int[topK];
		int resIndex=0;
		while(resIndex!=topK){
			HeapNode head=heapArr[0];
			res[resIndex++]=head.value;
			int headR=head.row;
			int headC=head.col;
			int leftR=headR-1;
			int leftC=headC;
			int downR=headR;
			int downC=headC-1;
			boolean isContainsLeft=isContains(leftR,leftC,positionSet);
			boolean isContainsDown=isContains(downR,downC,positionSet);
			if(headR==0&&headC==0){
				removeHeapHead(heapArr,heapSize--);
			}else if(headR==0){
				if(isContainsDown){
					removeHeapHead(heapArr,heapSize--);
				}else{
					addPositionToSet(downR,downC,positionSet);
					changeHead(heapArr,heapSize,downR,downC,a1[downR]+a2[downC]);
				}
			}else if(headC==0){
				if(isContainsLeft){
					removeHeapHead(heapArr,heapSize--);
				}else{
					addPositionToSet(leftR,leftC,positionSet);
					changeHead(heapArr,heapSize,leftR,leftC,a1[leftR]+a2[leftC]);
				}
			}else{
				if(isContainsLeft&&isContainsDown){//堆已加过左、上节点了，
					removeHeapHead(heapArr,heapSize--);
				}else if(isContainsLeft){
					addPositionToSet(downR,downC,positionSet);
					changeHead(heapArr,heapSize,downR,downC,a1[downR]+a2[downC]);
				}else if(isContainsDown){
					addPositionToSet(leftR,leftC,positionSet);
					changeHead(heapArr,heapSize,leftR,leftC,a1[leftR]+a2[leftC]);
				}else{//堆未加过左、上节点了，
					addPositionToSet(downR,downC,positionSet);
					addPositionToSet(leftR,leftC,positionSet);
					changeHead(heapArr,heapSize,downR,downC,a1[downR]+a2[downC]);
					heapInsert(heapArr,heapSize++,leftR,leftC,a1[leftR]
							+a2[leftC]);
				}
			}
		}
		return res;
	}

	public static void changeHead(HeapNode[] heapArr,int heapSize,int headRow,
			int headCol,int headValue){
		heapArr[0]=new HeapNode(headRow,headCol,headValue);
		heapifyFromHead(heapArr,heapSize);
	}

	public static void removeHeapHead(HeapNode[] heapArr,int heapSize){
		swap(heapArr,0,heapSize-1);
		heapArr[heapSize-1]=null;//安全，释放空间
		heapifyFromHead(heapArr,heapSize-1);
	}

	public static void heapifyFromHead(HeapNode[] heapArr,int heapSize){
		int index=0;
		int left=index*2+1;
		int right=index*2+2;
		int largest=index;
		while(left<heapSize){
			if(heapArr[left].value>heapArr[index].value){
				largest=left;
			}
			if(right<heapSize&&heapArr[right].value>heapArr[largest].value){
				largest=right;
			}
			if(largest!=index){
				swap(heapArr,largest,index);
			}else{
				break;
			}
			index=largest;
			left=index*2+1;
			right=index*2+2;
		}
	}

	public static void heapInsert(HeapNode[] heapArr,int index,int row,int col,
			int value){
		heapArr[index]=new HeapNode(row,col,value);
		int parent=(index-1)/2;
		while(index!=0){
			if(heapArr[index].value>heapArr[parent].value){
				swap(heapArr,parent,index);
				index=parent;
				parent=(index-1)/2;
			}else{
				break;
			}
		}
	}

	public static void swap(HeapNode[] heapArr,int index1,int index2){
		HeapNode tmp=heapArr[index1];
		heapArr[index1]=heapArr[index2];
		heapArr[index2]=tmp;
	}

	public static boolean isContains(int row,int col,HashSet<String> set){
		return set.contains(String.valueOf(row+"_"+col));
	}

	public static void addPositionToSet(int row,int col,HashSet<String> set){
		set.add(String.valueOf(row+"_"+col));
	}

	public static int[] generateRandomSortArray(int len){
		int[] res=new int[len];
		for(int i=0;i!=res.length;i++){
			res[i]=(int)(Math.random()*10)+1;
		}
		Arrays.sort(res);
		return res;
	}

	public static void printArray(int[] arr){
		for(int i=0;i!=arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static boolean isEqual(int[] arr1,int[] arr2){
		if(arr1==null||arr2==null||arr1.length!=arr2.length){
			return false;
		}
		for(int i=0;i!=arr1.length;i++){
			if(arr1[i]!=arr2[i]){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		int a1Len=5000;
		int a2Len=4000;
		int k=200;
		int[] arr1=generateRandomSortArray(a1Len);
		int[] arr2=generateRandomSortArray(a2Len);
		long start=System.currentTimeMillis();
		int[] absoluteRight=getKBiggestSumArraySortAll(arr1,arr2,k);
		long end=System.currentTimeMillis();
		System.out.println(end-start+" ms");
		start=System.currentTimeMillis();
		int[] res=getKBiggestSumArray(arr1,arr2,k);
		end=System.currentTimeMillis();
		System.out.println(end-start+" ms");
		System.out.println(isEqual(res,absoluteRight));

	}

}
