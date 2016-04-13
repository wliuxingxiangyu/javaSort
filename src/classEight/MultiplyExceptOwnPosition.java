package classEight;

public class MultiplyExceptOwnPosition{//¿€≥À£¨ È381.

	public static int[] muliplyAllExceptOwnNoDivision(int[] arr){
		if(arr.length==1){
			return new int[1];
		}
		int[] res=new int[arr.length];
		res[0]=arr[0];
		for(int i=1;i!=arr.length;i++){
			res[i]=res[i-1]*arr[i];
		}
		int mulRightToLeft=1;
		for(int i=arr.length-1;i!=0;i--){
			res[i]=res[i-1]*mulRightToLeft;
			mulRightToLeft*=arr[i];
		}
		res[0]=mulRightToLeft;
		return res;
	}

	public static void main(String[] args){
		int[] test={1, 2, 3, 4};
		int[] result=muliplyAllExceptOwnNoDivision(test);
		for(int i=0;i!=result.length;i++){
			System.out.print(result[i]+" ");
		}
	}
}
