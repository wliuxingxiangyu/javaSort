package classFour;

public class GetAllIpAddressFromString{

	public static void findAllIps(String ipString){
		findProcess(ipString,"",0,4);
	}

	/*
	 * 根据IPV4的规则，ip地址由4个部分组成，每一部分都是0~256之前的数字，且不可以出现01，010之类的部分。 参数： ipString
	 * 给定的字符串 previousString 之前的部分按照ipv4的规则所形成的ip字符串 currentIndex
	 * 目前考虑从ipString的currentIndex这个位置，进行剩下部分的划分 resultIpParts
	 * 进行到ipString的currentIndex这个位置，还剩多少个部分没有划分
	 */
	public static void findProcess(String ipString,String previousString,
			int currentIndex,int restIpParts){

		// 这是递归的一个剪枝条件，ip地址中，每一个部分最少需要一个字符，最多需要三个字符。
		// 所以如果剩下restIpParts个部分未划分，
		// 那么可用来划分的、剩下的字符数，一定不能大于restIpParts * 3,也不能少于restIpParts。
		// 过大说明即便剩下的部分都含有最多3个字符，这个字符串也不可能用完；
		// 过少说明剩下的字符，根本就不够；
		if((ipString.length()-currentIndex>restIpParts*3)//无效组合
				||(ipString.length()-currentIndex<restIpParts)){
			return;
		}

		// 打印条件，如果不被上面的剪枝逻辑命中，同时所有的字符都分完了，则说明划分完毕。
		if(currentIndex==ipString.length()){
			System.out.println(previousString.substring(0,
					previousString.length()-1));
		}

		// 递归分支一：当前的部分选择一个字符，作为本部分的值。然后剩下部分划分剩下的字符
		if(currentIndex+1<=ipString.length()){
			String currentString=ipString
					.substring(currentIndex,currentIndex+1);
			findProcess(ipString,previousString+currentString+".",
					currentIndex+1,restIpParts-1);//f(i)+i+f(i+1)

			// 这个条件很重要，如果当前的部分选择了一个字符，并且居然是"0":
			// 因为在IP地址中，01，02，...，等等都是不合法的，
			// 所以可知当前部分只能选择一个字符，而不可能有其他的选择，所以直接返回
			if(currentString.equals("0")){
				return;
			}
		}

		// 递归分支二：当前的部分选择两个字符，作为本部分的值。然后剩下部分划分剩下的字符
	if(currentIndex+2<=ipString.length()){//f(i)+  (i,i+1)  +f(i+2)
			String currentString=ipString
					.substring(currentIndex,currentIndex+2);
			findProcess(ipString,previousString+currentString+".",
					currentIndex+2,restIpParts-1);
		}

		// 递归分支三：当前的部分选择三个字符，作为本部分的值。然后剩下部分划分剩下的字符
		if(currentIndex+3<=ipString.length()){//f(i)+ (i,i+1,i+2) +f(i+3)
			String currentString=ipString
					.substring(currentIndex,currentIndex+3);

			// 分支三可以进行的条件，组成该部分的三位数不能大于256
			if(Integer.valueOf(currentString)<256){
				findProcess(ipString,previousString+currentString+".",
						currentIndex+3,restIpParts-1);
			}
		}
	}

	public static void main(String[] args){
		String test="11100111";
		findAllIps(test);
	}

}
