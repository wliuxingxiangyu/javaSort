package classThree;

public class RegularExpressionMatching{//L3P18匹配正则表达式

	public static boolean isValid(char[] s,char[] e){//判断s和e数组是否有效
		for(int i=0;i!=s.length;i++){
			if(s[i]=='*'||s[i]=='.'){
				return false;//源串含*或.时,源串有误
			}//判断s数组是否有效
		}
		for(int i=0;i!=e.length;i++){
			if(e[i]=='*'&&(i-1<0||e[i-1]=='*')){
				return false;//*开头或连续*时,正则表达式有误
			}//判断e数组是否有效
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
		//若isValid(s,e)=true,则返回process(s,e,0,0)的值
	}

	public static boolean process(char[] s,char[] e,int si,int ei){
	//si位置开始  是否与ei位置开始匹配，
		if(ei==e.length){//当ei超过  最后一元素时,再判si是否超过  最后一元素(说明全部匹配)
			return si==s.length;//si结束
		}
		if(ei+1==e.length||e[ei+1]!='*'){//ei到最后一元素 “或” e[ei+1]非*即a.b.c单个点匹配
			return si!=s.length&&(e[ei]==s[si]||e[ei]=='.')//e[ei]==s[si]匹配
		&&process(s,e,si+1,ei+1);//&&一旦前两个条件为真e[ei]或.=s[si],就执行递归,比较下一组si+1,ei+1
		}
		while(si!=s.length&&(e[ei]==s[si]||e[ei]=='.')){//作用？
			if(process(s,e,si,ei+2)){//si不变,ei+2,如果e[ei+2]==s[si],则直接返回true,不进行往后匹配si++,
//				System.out.println("ei_if="+ei);//调试
				return true;
			}
			si++;//如果e[ei+2]!=s[si],往后匹配si++
		}
		return process(s,e,si,ei+2);
	}
//第二种方法：动态规划------------------------------------------------------------------
	public static boolean isMatchDP(String str,String exp){
		if(str==null||exp==null){
			return false;
		}
		char[] s=str.toCharArray();
		char[] e=exp.toCharArray();
		if(!isValid(s,e)){//判断s和e数组是否有效
			return false;
		}
		boolean[][] dp=initDPMap(s,e);
		for(int i=s.length-1;i>-1;i--){
			for(int j=e.length-2;j>-1;j--){
				if(e[j+1]!='*'){//e[j+1]即后一个 非'*',判等或.(任意字符)
					dp[i][j]=(s[i]==e[j]||e[j]=='.')&&dp[i+1][j+1];//从后往前匹配,
				}else{//e[j+1]即后一个 是'*'
					int si=i;
					while(si!=s.length&&(s[si]==e[j]||e[j]=='.')){
						if(dp[si][j+2]){//如果(.*后面的字符串)匹配
							dp[i][j]=true;//则(包括.*及后面的字符串)也匹配
							break;//dp[i][j]赋true完,跳出while
						}
						si++;//一个.*中的.能匹配多个源字符s,所以si++直到si=s.length跳出while
					}
					if(dp[i][j]!=true){
						dp[i][j]=dp[si][j+2];//(包括.*及后面的字符串)与(.*后面的字符串)匹配结果相同
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean[][] initDPMap(char[] s,char[] e){
		boolean[][] dp=new boolean[s.length+1][e.length+1];
		dp[s.length][e.length]=true;//两空串匹配
		for(int j=e.length-2;j>-1;j=j-2){
			if(e[j]!='*'&&e[j+1]=='*'){//从后往前 :第一次遇到本元素为.且后为元素*时赋真,
				dp[s.length][j]=true;
			}else{
				break;//跳出for
			}
		}
		if(s.length>0&&e.length>0){
			if((e[e.length-1]=='.'||s[s.length-1]==e[e.length-1])){
				dp[s.length-1][e.length-1]=true;//e的最后一字符(或.) 与 s的最后一字符 同
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
