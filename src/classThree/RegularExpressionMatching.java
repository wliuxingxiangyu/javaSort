package classThree;

public class RegularExpressionMatching{//L3P18ƥ��������ʽ

	public static boolean isValid(char[] s,char[] e){//�ж�s��e�����Ƿ���Ч
		for(int i=0;i!=s.length;i++){
			if(s[i]=='*'||s[i]=='.'){
				return false;//Դ����*��.ʱ,Դ������
			}//�ж�s�����Ƿ���Ч
		}
		for(int i=0;i!=e.length;i++){
			if(e[i]=='*'&&(i-1<0||e[i-1]=='*')){
				return false;//*��ͷ������*ʱ,������ʽ����
			}//�ж�e�����Ƿ���Ч
		}
		return true;
	}

	public static boolean isMatch(String str,String exp){
		if(str==null||exp==null){
			return false;
		}
		char[] s=str.toCharArray();
		char[] e=exp.toCharArray();
		return isValid(s,e)?process(s,e,0,0):false;
		//��isValid(s,e)=true,�򷵻�process(s,e,0,0)��ֵ
	}

	public static boolean process(char[] s,char[] e,int si,int ei){
	//siλ�ÿ�ʼ  �Ƿ���eiλ�ÿ�ʼƥ�䣬
		if(ei==e.length){//��ei����  ���һԪ��ʱ,����si�Ƿ񳬹�  ���һԪ��(˵��ȫ��ƥ��)
			return si==s.length;//si����
		}
		if(ei+1==e.length||e[ei+1]!='*'){//ei�����һԪ�� ���� e[ei+1]��*��a.b.c������ƥ��
			return si!=s.length&&(e[ei]==s[si]||e[ei]=='.')//e[ei]==s[si]ƥ��
		&&process(s,e,si+1,ei+1);//&&һ��ǰ��������Ϊ��e[ei]��.=s[si],��ִ�еݹ�,�Ƚ���һ��si+1,ei+1
		}
		while(si!=s.length&&(e[ei]==s[si]||e[ei]=='.')){//���ã�
			if(process(s,e,si,ei+2)){//si����,ei+2,���e[ei+2]==s[si],��ֱ�ӷ���true,����������ƥ��si++,
//				System.out.println("ei_if="+ei);//����
				return true;
			}
			si++;//���e[ei+2]!=s[si],����ƥ��si++
		}
		return process(s,e,si,ei+2);
	}
//�ڶ��ַ�������̬�滮------------------------------------------------------------------
	public static boolean isMatchDP(String str,String exp){
		if(str==null||exp==null){
			return false;
		}
		char[] s=str.toCharArray();
		char[] e=exp.toCharArray();
		if(!isValid(s,e)){//�ж�s��e�����Ƿ���Ч
			return false;
		}
		boolean[][] dp=initDPMap(s,e);
		for(int i=s.length-1;i>-1;i--){
			for(int j=e.length-2;j>-1;j--){
				if(e[j+1]!='*'){//e[j+1]����һ�� ��'*',�еȻ�.(�����ַ�)
					dp[i][j]=(s[i]==e[j]||e[j]=='.')&&dp[i+1][j+1];//�Ӻ���ǰƥ��,
				}else{//e[j+1]����һ�� ��'*'
					int si=i;
					while(si!=s.length&&(s[si]==e[j]||e[j]=='.')){
						if(dp[si][j+2]){//���(.*������ַ���)ƥ��
							dp[i][j]=true;//��(����.*��������ַ���)Ҳƥ��
							break;//dp[i][j]��true��,����while
						}
						si++;//һ��.*�е�.��ƥ����Դ�ַ�s,����si++ֱ��si=s.length����while
					}
					if(dp[i][j]!=true){
						dp[i][j]=dp[si][j+2];//(����.*��������ַ���)��(.*������ַ���)ƥ������ͬ
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean[][] initDPMap(char[] s,char[] e){
		boolean[][] dp=new boolean[s.length+1][e.length+1];
		dp[s.length][e.length]=true;//���մ�ƥ��
		for(int j=e.length-2;j>-1;j=j-2){
			if(e[j]!='*'&&e[j+1]=='*'){//�Ӻ���ǰ :��һ��������Ԫ��Ϊ.�Һ�ΪԪ��*ʱ����,
				dp[s.length][j]=true;
			}else{
				break;//����for
			}
		}
		if(s.length>0&&e.length>0){
			if((e[e.length-1]=='.'||s[s.length-1]==e[e.length-1])){
				dp[s.length-1][e.length-1]=true;//e�����һ�ַ�(��.) �� s�����һ�ַ� ͬ
			}
		}
		return dp;
	}

	public static void main(String[] args){
		String str="abcccdefg";
		String exp="ab.*d.*e.*";
		System.out.println(isMatch(str,exp));
		System.out.println(isMatchDP(str,exp));

	}

}
