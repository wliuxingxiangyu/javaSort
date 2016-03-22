package classFive;

public class NumberStringConvertToLetterCombinationProblem{

	public static int getLetterCombinationNum(String str){
		if(str==null||str.equals("")){
			return 0;
		}
		int[] arr=numberStringToIntArr(str);
		if(arr==null){
			return 0;
		}else{
			return computeProcess(arr,0);
		}
	}

	public static int computeProcess(int[] arr,int index){//从index到最后，
		if(index==arr.length){
			return 1;
		}
		if(arr[index]==0){//不能以0开头
			return 0;
		}
		int result=computeProcess(arr,index+1);//本位,后面
		if(index+1<arr.length){
			if(arr[index]*10+arr[index+1]<27){
				result+=computeProcess(arr,index+2);
			}
		}
		return result;
	}

	public static int getLetterCombinationNumBetter(String str){
		if(str==null||str.equals("")){
			return 0;
		}
		int[] arr=numberStringToIntArr(str);
		if(arr==null){
			return 0;
		}else{
			int[] record=new int[arr.length+1];
			record[record.length-1]=1;
			return computeProcessBetter(arr,0,record);
		}
	}

	public static int computeProcessBetter(int[] arr,int index,int[] record){
		if(arr[index]==0){
			record[index]=-1;
			return 0;
		}
		int result=0;
		if(record[index+1]==0){
			result=computeProcessBetter(arr,index+1,record);
		}else{
			result=record[index+1]==-1?0:record[index+1];
		}
		if(index+1<arr.length){
			if(arr[index]*10+arr[index+1]<27){
				if(record[index+2]==0){
					result+=computeProcessBetter(arr,index+2,record);
				}else{
					result+=record[index+2]==-1?0:record[index+2];
				}
			}
		}
		record[index]=result==0?-1:result;
		return result;
	}

	public static int getLetterCombinationNumGreat(String str){//最好的，3变量
		if(str==null||str.equals("")){
			return 0;
		}
		int[] arr=numberStringToIntArr(str);
		if(arr==null){
			return 0;
		}
		int nextNext=1;
		int next=arr[arr.length-1]==0?0:1;
		int current=0;
		for(int i=arr.length-2;i!=-1;i--){
			if(arr[i]==0){
				current=0;
			}else{
				current=next;
				if(arr[i]*10+arr[i+1]<27){
					current+=nextNext;
				}
			}
			nextNext=next;
			next=current;
		}
		return current;
	}

	public static int[] numberStringToIntArr(String numStr){
		char[] charArr=numStr.toCharArray();
		int[] result=new int[charArr.length];
		for(int i=0;i!=charArr.length;i++){
			if(charArr[0]<'0'||charArr[0]>'9'){
				return null;
			}else{
				result[i]=charArr[i]-'0';
			}
		}
		return result;
	}

	public static void main(String[] args){
		String numStr="111111111111111";
		System.out.println(getLetterCombinationNum(numStr));
		System.out.println(getLetterCombinationNumBetter(numStr));
		System.out.println(getLetterCombinationNumGreat(numStr));

	}
}
