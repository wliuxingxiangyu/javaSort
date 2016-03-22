package classThree;

public class IsDeformation{//L3P6
	public static boolean isDeformation(String str1,String str2){
		if(str1==null||str2==null||str1.length()!=str2.length()){
			return false;
		}
		char[] chars1=str1.toCharArray();
		char[] chars2=str2.toCharArray();
		int[] record=new int[256];
		for(int i=0;i!=chars1.length;i++){
			record[(int)chars1[i]]++;//i的ASCII多少次
		}
		for(int i=0;i!=chars2.length;i++){
			if(record[(int)chars2[i]]--==0){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		String A="abcabcabc";
		String B="bcacbaacb";
		System.out.println(isDeformation(A,B));
	}

}
