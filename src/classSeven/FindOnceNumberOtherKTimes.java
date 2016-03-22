package classSeven;

public class FindOnceNumberOtherKTimes{

	public static int findOnceNum(int[] array,int k){
		int[] eORes=new int[32];
		for(int i=0;i!=array.length;i++){
			setExclusiveOr(eORes,array[i],k);
		}
		int result=getNumFromKSysNum(eORes,k);
		return result;
	}

	public static void setExclusiveOr(int[] eORes,int value,int k){
		int[] curKSysNum=getKSysNumFromNum(value,k);
		for(int i=0;i!=eORes.length;i++){
			eORes[i]=(eORes[i]+curKSysNum[i])%k;//不进位相加
		}
	}

	public static int[] getKSysNumFromNum(int value,int k){
		int[] result=new int[32];
		int index=0;
		while(value!=0){
			result[index++]=value%k;//10的8进制为：2100
			value=value/k;
		}
		return result;
	}

	public static int getNumFromKSysNum(int[] eORes,int k){
		int result=0;
		for(int i=eORes.length-1;i!=-1;i--){
			result=result*k+eORes[i];
		}
		return result;
	}

	public static void main(String[] args){
		int[] test1={1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9};
		System.out.println(findOnceNum(test1,3));

		int[] test2={-1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2};
		System.out.println(findOnceNum(test2,5));

	}

}