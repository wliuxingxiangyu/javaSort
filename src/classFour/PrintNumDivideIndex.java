package classFour;

public class PrintNumDivideIndex{//P13

	public static void printNumDivideIndex(int size){
		if(size<1||size>9){
			return;
		}
		int[] digitArr=new int[size];
		for(int i=0;i!=size;i++){
			digitArr[i]=i+1;
		}
		findProcess(digitArr,0,0);
		return;
	}

	public static void findProcess(int[] digitArr,long sum,int index){
		if(index==digitArr.length-1){
			sum=sum*10+digitArr[index];
			if(sum%(index+1)==0){
				System.out.println(sum);
			}
			return;
		}
		for(int i=index;i<digitArr.length;i+=2){//ÆæÆæ»»
			swap(digitArr,index,i);//
			long nextSum=sum*10+digitArr[index];
			if(nextSum%(index+1)==0){
				findProcess(digitArr,nextSum,index+1);
			}
			swap(digitArr,index,i);//»»»ØÑ­»·
		}
	}

	private static void swap(int[] digits,int i,int j){
		int temp=digits[i];
		digits[i]=digits[j];
		digits[j]=temp;
	}

	public static void main(String[] args){
		printNumDivideIndex(8);

	}
}