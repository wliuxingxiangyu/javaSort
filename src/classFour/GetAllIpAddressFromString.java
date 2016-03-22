package classFour;

public class GetAllIpAddressFromString{

	public static void findAllIps(String ipString){
		findProcess(ipString,"",0,4);
	}

	/*
	 * ����IPV4�Ĺ���ip��ַ��4��������ɣ�ÿһ���ֶ���0~256֮ǰ�����֣��Ҳ����Գ���01��010֮��Ĳ��֡� ������ ipString
	 * �������ַ��� previousString ֮ǰ�Ĳ��ְ���ipv4�Ĺ������γɵ�ip�ַ��� currentIndex
	 * Ŀǰ���Ǵ�ipString��currentIndex���λ�ã�����ʣ�²��ֵĻ��� resultIpParts
	 * ���е�ipString��currentIndex���λ�ã���ʣ���ٸ�����û�л���
	 */
	public static void findProcess(String ipString,String previousString,
			int currentIndex,int restIpParts){

		// ���ǵݹ��һ����֦������ip��ַ�У�ÿһ������������Ҫһ���ַ��������Ҫ�����ַ���
		// �������ʣ��restIpParts������δ���֣�
		// ��ô���������ֵġ�ʣ�µ��ַ�����һ�����ܴ���restIpParts * 3,Ҳ��������restIpParts��
		// ����˵������ʣ�µĲ��ֶ��������3���ַ�������ַ���Ҳ���������ꣻ
		// ����˵��ʣ�µ��ַ��������Ͳ�����
		if((ipString.length()-currentIndex>restIpParts*3)//��Ч���
				||(ipString.length()-currentIndex<restIpParts)){
			return;
		}

		// ��ӡ�����������������ļ�֦�߼����У�ͬʱ���е��ַ��������ˣ���˵��������ϡ�
		if(currentIndex==ipString.length()){
			System.out.println(previousString.substring(0,
					previousString.length()-1));
		}

		// �ݹ��֧һ����ǰ�Ĳ���ѡ��һ���ַ�����Ϊ�����ֵ�ֵ��Ȼ��ʣ�²��ֻ���ʣ�µ��ַ�
		if(currentIndex+1<=ipString.length()){
			String currentString=ipString
					.substring(currentIndex,currentIndex+1);
			findProcess(ipString,previousString+currentString+".",
					currentIndex+1,restIpParts-1);//f(i)+i+f(i+1)

			// �����������Ҫ�������ǰ�Ĳ���ѡ����һ���ַ������Ҿ�Ȼ��"0":
			// ��Ϊ��IP��ַ�У�01��02��...���ȵȶ��ǲ��Ϸ��ģ�
			// ���Կ�֪��ǰ����ֻ��ѡ��һ���ַ�������������������ѡ������ֱ�ӷ���
			if(currentString.equals("0")){
				return;
			}
		}

		// �ݹ��֧������ǰ�Ĳ���ѡ�������ַ�����Ϊ�����ֵ�ֵ��Ȼ��ʣ�²��ֻ���ʣ�µ��ַ�
	if(currentIndex+2<=ipString.length()){//f(i)+  (i,i+1)  +f(i+2)
			String currentString=ipString
					.substring(currentIndex,currentIndex+2);
			findProcess(ipString,previousString+currentString+".",
					currentIndex+2,restIpParts-1);
		}

		// �ݹ��֧������ǰ�Ĳ���ѡ�������ַ�����Ϊ�����ֵ�ֵ��Ȼ��ʣ�²��ֻ���ʣ�µ��ַ�
		if(currentIndex+3<=ipString.length()){//f(i)+ (i,i+1,i+2) +f(i+3)
			String currentString=ipString
					.substring(currentIndex,currentIndex+3);

			// ��֧�����Խ��е���������ɸò��ֵ���λ�����ܴ���256
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
