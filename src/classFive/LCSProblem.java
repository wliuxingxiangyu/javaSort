package classFive;

public class LCSProblem{

	public static int getLCSLength(String str1,String str2){
		char[] charArrL=str1.length()>=str2.length()?str1.toCharArray():str2
				.toCharArray();
		char[] charArrS=str1.length()<str2.length()?str1.toCharArray():str2
				.toCharArray();
		int max=0;
		int[] record=new int[charArrS.length];
		for(int i=0;i!=record.length;i++){
			if(charArrL[0]==charArrS[i]){//long数组，short数组
				record[i]=1;//第一排
				max=1;
			}
		}
		for(int i=1;i!=charArrL.length;i++){
			for(int j=charArrS.length-1;j!=0;j--){
				if(charArrL[i]==charArrS[j]){
					record[j]=record[j-1]+1;
					if(record[j]>max){
						max=record[j];
					}
				}else{
					record[j]=0;
				}
			}
			record[0]=charArrL[i]==charArrS[0]?1:0;
			if(record[0]>max){
				max=1;
			}
		}
		return max;
	}

	public static String getLCSString(String str1,String str2){
		char[] chars1=str1.length()>=str2.length()?str1.toCharArray():str2
				.toCharArray();
		char[] chars2=str1.length()<str2.length()?str1.toCharArray():str2
				.toCharArray();
		int[] record=new int[chars2.length];
		int len=0;
		int end=0;
		for(int i=0;i!=record.length;i++){
			if(chars1[0]==chars2[i]){
				record[i]=1;
				len=1;
				end=i;
			}
		}
		for(int i=1;i!=chars1.length;i++){
			for(int j=chars2.length-1;j!=0;j--){
				if(chars1[i]==chars2[j]){
					record[j]=record[j-1]+1;
					if(record[j]>len){
						len=record[j];
						end=j;
					}
				}else{
					record[j]=0;
				}
			}
			record[0]=chars1[i]==chars2[0]?1:0;
			if(record[0]>len){
				len=1;
				end=0;
			}
		}
		return (str1.length()<str2.length()?str1:str2).substring(end-len+1,
				end+1);//取小的substring
	}

	public static void main(String[] args){
		String str1="aa111111cac";
		String str2="aca111111ccbabb";
		System.out.println("Length: "+getLCSLength(str1,str2));
		System.out.println("LCS: "+getLCSString(str1,str2));

	}

}
