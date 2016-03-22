package classFive;

public class PrintStringCombination{
	public static void printAllCombination(String str){
		char[] charArr=str.toCharArray();// ת��stringΪchar�������飬�ڵݹ�ʱʡ�ռ䡣
		printAllProcess(charArr,0,""); // �ݹ麯����ʼ
	}

	/*
	 * ��ӡ���������еĵݹ麯���� ������ charArr : ��������Ķ���ԭ�����Ǹ�String�� currentIndex :
	 * �����������charArr�ĵ�currentIndex���ַ� trace :
	 * һ��String���͵ı�������һ���ַ�����������Ͱ�����ַ��ӵ�trace�ĺ���
	 */
	public static void printAllProcess(char[] charArr,int currentIndex,
			String trace){
		// currentIndex����charArr.lengthʱ��ʾ����û���ַ��ˣ��ݹ����
		if(currentIndex==charArr.length){
			return;
		}
		// ��charArr�еĵ�currentIndex�ַ����뵽trace�������µ�String
		// �����ǣ���ԭ���Ѿ�ѡ���ַ�+��ǰ���ַ��������һ��--traceAddCurrentChar
		String traceAddCurrentChar=trace+String.valueOf(charArr[currentIndex]);
		// ��ӡ�뵱ǰ�ַ���Ϻ���ַ���
		System.out.println(traceAddCurrentChar);
		//ÿ������·���������򡰲�������ǰ�ַ�
		// �ߵݹ�Ĺ����С���������ǰ�ַ��ķ�֧(��traceAddCurrentChar���´�ֵ)
		printAllProcess(charArr,currentIndex+1,traceAddCurrentChar);
		// �ߵݹ�Ĺ����С�����������ǰ�ַ��ķ�֧(��trace���´�ֵ)
		printAllProcess(charArr,currentIndex+1,trace);
	}

	public static void printKLengthCombination(String str,int K){
		char[] charArr=str.toCharArray();
		// ��ԭ����ĵݹ麯��һ����ֻ�Ǽ���һ������K����ʾ��Ҫ��ӡ�������г���
		printKLengthProcess(charArr,0,"",K);
	}

	public static void printKLengthProcess(char[] charArr,int currentIndex,
			String trace,int K){
		if(trace.length()==K){ // ��֦����A
			return;
		}
		if(trace.length()+charArr.length-currentIndex<K){ // ��֦����B������K
			return;
		}
		if(currentIndex==charArr.length){
			return;
		}
		String traceAddCurrentChar=trace+String.valueOf(charArr[currentIndex]);
		if(traceAddCurrentChar.length()==K){ // ��ӡ����C
			System.out.println(traceAddCurrentChar);
		}
		printKLengthProcess(charArr,currentIndex+1,traceAddCurrentChar,K);
		printKLengthProcess(charArr,currentIndex+1,trace,K);
	}

	public static void main(String[] args){
		String str="ABC";
		printAllCombination(str);
		System.out.println("==============");
		printKLengthCombination(str,2);
	}
}
