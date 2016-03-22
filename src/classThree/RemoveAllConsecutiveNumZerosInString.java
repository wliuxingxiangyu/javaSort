package classThree;

public class RemoveAllConsecutiveNumZerosInString{//15ɾ������K��0

	public static String removeConsecutiveNumZeros(String str,int num){
		char[] charArr=str.toCharArray();
		int count=0;
		int zeroStart=-1;
		for(int i=0;i!=charArr.length;i++){
			if(charArr[i]=='0'){
				count++;
				zeroStart=zeroStart==-1?i:zeroStart;
			}else{
				if(count==num){
					while(count--!=0){
						charArr[zeroStart++]=0;
					}
				}
				count=0;
				zeroStart=-1;
			}
		}
		if(count==num){//��βҲ��0��ʱ��ҲҪ���㣨��Ϊ�����ǰ���0���õģ�
			while(count--!=0){
				charArr[zeroStart++]=0;
			}
		}
		return String.valueOf(charArr);
	}

	public static void main(String[] args){
		String test1="0A0B0C00D0";
		System.out.println(removeConsecutiveNumZeros(test1,1));

		String test2="00A00B0C00D0";
		System.out.println(removeConsecutiveNumZeros(test2,2));

		String test3="000A00B000C0D00";
		System.out.println(removeConsecutiveNumZeros(test3,3));

		String test4="0000A0000B00C000D0000";
		System.out.println(removeConsecutiveNumZeros(test4,4));

		String test5="00000000";
		System.out.println(removeConsecutiveNumZeros(test5,5));

	}

}
