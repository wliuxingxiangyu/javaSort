package classFour;

public class HanoiProblem{//P6汉诺塔3步

	public static int moveHanoi(int restLevel,String from,String tmp,String to){
		if(restLevel==1){         //restLevel为剩下的层数
			System.out.println("Move from "+from+" to "+to);
			return 1;
		}else{
			int part1=moveHanoi(restLevel-1,from,to,tmp);
			int part2=moveHanoi(1,from,tmp,to);//剩下的层数为1
			int part3=moveHanoi(restLevel-1,tmp,from,to);
			return part1+part2+part3;//字符串相连
		}
	}

	public static int countHanoi(int[] status,int levelIndex,int from,int tmp,
			int to){//每个递归,必经一个分支，levelIndex都减1,时间复杂度为O（n）
		if(levelIndex==0){
			return 0;
		}
		if(status[levelIndex]==tmp){//有效层:最后层不可能在中间
			throw new RuntimeException(
					"ERR!,Your status is invalid, it is not the part of best solution!");
		}else if(status[levelIndex]==to){//1,2已完，正在走第三阶段
			int countTimes=(int)(1<<(levelIndex-1));//2^n-1
			int rest=countHanoi(status,levelIndex-1,tmp,from,to);
			return countTimes+rest;
		}else if(status[levelIndex]==from){//正在走第一阶段
			int rest=countHanoi(status,levelIndex-1,from,to,tmp);
			return rest;
		}else{
			throw new RuntimeException(
					"ERR!,Your status is invalid, every level status must be "
							+from+" or "+tmp+"or "+to);
		}

	}

	public static void main(String[] args){
		int leveNum=5;
		System.out.println("Steps :");
		int stepNum=moveHanoi(leveNum,"Left","Mid","Right");
		System.out.println("Move Times : "+stepNum);
		System.out.println("=======================================");
		int[] status=new int[leveNum+1];
		for(int i=1;i!=status.length-1;i++){
			status[i]=2;
		}
		status[status.length-1]=1;
		int countTimes=countHanoi(status,status.length-1,1,2,3);
		System.out.println("The current status is the step of best solution : "
				+countTimes);

	}
}
