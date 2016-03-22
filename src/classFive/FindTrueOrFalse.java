package classFive;

import java.util.HashMap;

public class FindTrueOrFalse{

	public static int getDesiredResultWays(String exp,boolean desired){
		if(exp==null||exp.equals("")){
			return 0;
		}
		char[] expChars=exp.toCharArray();
		if(!isValidExp(expChars)){//校验表达式
			return 0;
		}
		HashMap<String,Integer> record=new HashMap<String,Integer>();
		return computeProcess(expChars,desired,0,expChars.length-1,record);
	}

	public static boolean isValidExp(char[] exp){
		if(exp.length%2==0){
			return false;
		}
		for(int i=0;i<exp.length;i=i+2){
			if((exp[i]!='1')&&(exp[i]!='0')){
				return false;
			}
		}
		for(int i=1;i<exp.length;i=i+2){
			if((exp[i]!='&')&&(exp[i]!='|')&&(exp[i]!='^')){
				return false;
			}
		}
		return true;
	}

	public static int computeProcess(char[] exp,boolean desired,int start,
			int end,HashMap<String,Integer> record){//desired结果的  start~end的组合。record记忆化搜索
		String key=desired+"+"+start+"+"+end;
		if(record.containsKey(key)){//有就查map
			return record.get(key);
		}
		if(start==end){//只有0或1
			if((exp[start]=='1'&&desired==true)
					||(exp[start]=='0'&&desired==false)){
				record.put(key,1);
				return 1;
			}
			record.put(key,0);
			return 0;
		}
		int count=0;
		if(desired){//要true
			for(int i=start+1;i<=end;i+=2){//i+=2表明只取符号，二目运算符
				char op=exp[i];
				switch(op){
					case '&':
						count+=computeProcess(exp,true,start,i-1,record)
								*computeProcess(exp,true,i+1,end,record);
						break;
					case '|':
						count+=computeProcess(exp,true,start,i-1,record)
								*computeProcess(exp,false,i+1,end,record);
						count+=computeProcess(exp,false,start,i-1,record)
								*computeProcess(exp,true,i+1,end,record);
						count+=computeProcess(exp,true,start,i-1,record)
								*computeProcess(exp,true,i+1,end,record);
						break;
					case '^':
						count+=computeProcess(exp,true,start,i-1,record)
								*computeProcess(exp,false,i+1,end,record);
						count+=computeProcess(exp,false,start,i-1,record)
								*computeProcess(exp,true,i+1,end,record);
						break;
				}
			}
		}else{//要flase
			for(int i=start+1;i<=end;i+=2){
				char op=exp[i];
				switch(op){
					case '&':
						count+=computeProcess(exp,false,start,i-1,record)
								*computeProcess(exp,true,i+1,end,record);
						count+=computeProcess(exp,true,start,i-1,record)
								*computeProcess(exp,false,i+1,end,record);
						count+=computeProcess(exp,false,start,i-1,record)
								*computeProcess(exp,false,i+1,end,record);
						break;
					case '|':
						count+=computeProcess(exp,false,start,i-1,record)
								*computeProcess(exp,false,i+1,end,record);
						break;
					case '^':
						count+=computeProcess(exp,true,start,i-1,record)
								*computeProcess(exp,true,i+1,end,record);
						count+=computeProcess(exp,false,start,i-1,record)
								*computeProcess(exp,false,i+1,end,record);
						break;
				}
			}
		}
		record.put(key,count);//压入map中
		return count;
	}

	public static void main(String[] args){
		String expression="1^0&0|1&1^0";
		boolean desired=true;
		System.out.println(getDesiredResultWays(expression,desired));
	}

}
