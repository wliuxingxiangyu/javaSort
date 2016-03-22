package classThree;

public class FindStringInSortedArrayContainsNull{

	public static int findFirstIndex(String[] strArr,String str){
		if(strArr==null||str==null){
			return -1;
		}
		int left=0;
		int right=strArr.length-1;
		int firstIndex=Integer.MAX_VALUE;
		while(left<=right){
			int mid=(left+right)/2;
			if(strArr[mid]!=null){
				int compare=strArr[mid].compareTo(str);
				if(compare>0){
					right=mid-1;
				}else if(compare<0){
					left=mid+1;
				}else{//ÏàµÈ
					firstIndex=Math.min(mid,firstIndex);
					right=mid-1;
				}
			}else{//Îª¿Õ
				int index=mid-1;
				while(index>-1&&strArr[index]==null){
					index--;
				}
				if(index<left){
					left=mid+1;
				}else{
					int compare=strArr[index].compareTo(str);
					if(compare>0){
						right=index-1;
					}else if(compare<0){
						left=mid+1;
					}else{
						firstIndex=Math.min(index,firstIndex);
						right=index-1;
					}
				}
			}
		}
		return firstIndex==Integer.MAX_VALUE?-1:firstIndex;
	}

	public static void main(String[] args){
		String[] strArr=new String[]{null,"a",null,"a",null,"b",null,null,null,
				"b",null,"c",null,"c",null,null,"d",null,null,null,null,null,
				"d",null,"e",null,null,"e",null,null,null,"f",null,"f",null};
		String str1="a";
		System.out.println(findFirstIndex(strArr,str1));
		String str2="b";
		System.out.println(findFirstIndex(strArr,str2));
		String str3="c";
		System.out.println(findFirstIndex(strArr,str3));
		String str4="d";
		System.out.println(findFirstIndex(strArr,str4));
		String str5="e";
		System.out.println(findFirstIndex(strArr,str5));
		String str6="f";
		System.out.println(findFirstIndex(strArr,str6));

	}

}
