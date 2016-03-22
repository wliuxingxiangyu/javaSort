package classFour;

import java.util.HashMap;

public class BobDiePossibility{//p5

	public static String diePossibility(int tLX,int tLY,int bRX,int bRY,
			int bobX,int bobY,int step){
		// tLX 区域左上角的X坐标
		// tLY 区域左上角的Y坐标
		// bRX 区域右下角的X坐标
		// bRY 区域右下角的Y坐标
		// bobX Bob位置的X坐标
		// bobY Bob位置的Y坐标
		// step 一共要走多少步
		// 返回值String对Bob死亡概率的描述
		if(tLX>bRX||tLY>bRY){
			return "Your area has err!";
		}
		// 计算出一共有多少种情况，4^allSteps
		long allNum=computeAllNum(step);
		// 计算出走过allSteps步之后，存活的情况是多少种
		long noDieNum=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX,bobY,0,step);
		// 两者相减就是Bob死亡的情况
		long dieNum=allNum-noDieNum;
		return "Die Possibility: "+String.valueOf(dieNum+"/"+allNum);
	}

	public static long computeAllNum(int allStepNum){
		return (long)(Math.pow(4,allStepNum));
	}

	public static long computeNoDieProcess(int tLX,int tLY,int bRX,int bRY,
			//
			int bobX,int bobY,int curNum,int step){
		// 这种情况代表Bob已经走出区域，所以不会产生存活的情况
		if(bobX<tLX||bobX>bRX||bobY<tLY||bobY>bRY){
			return 0;
		}
		// 这种情况代表Bob已经走完所有的allSteps步，并最终存活，所以发现了一种存活的情况
		if(curNum==step){
			return 1;
		}
		// Bob向上走；返回值代表，Bob在第currentStepNum步的时候向上走，最后包含了多少种存活情况
		long up=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX-1,bobY,curNum+1,step);
		// Bob向下走；返回值代表，Bob在第currentStepNum步的时候向下走，最后包含了多少种存活情况
		long down=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX+1,bobY,curNum+1,step);
		// Bob向左走；返回值代表，Bob在第currentStepNum步的时候向左走，最后包含了多少种存活情况
		long left=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX,bobY-1,curNum+1,step);
		// Bob向右走；返回值代表，Bob在第currentStepNum步的时候向左走，最后包含了多少种存活情况
		long right=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX,bobY+1,curNum+1,
				step);
		
		return up+down+left+right;// 所有的存活情况数返回
	}

	public static String diePossibilityDP(int tLX,int tLY,int bRX,int bRY,
			int bobX,int bobY,int step){
		if(tLX>bRX||tLY>bRY){
			return "Your rectangular has err!";
		}
		long allNum=computeAllNum(step);
		// 用map记录递归每一个分支的计算结果,这个map相对于是所有分支的全局变量
		HashMap<String,Long> map=new HashMap<String,Long>();
		long noDieNum=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX,bobY,0,step,
				map);
		long dieNum=allNum-noDieNum;
		return "Die Possibility: "+String.valueOf(dieNum+"/"+allNum);
	}

	public static long computeNoDieProcessDP(int tLX,int tLY,int bRX,int bRY,
			int bobX,int bobY,int curNum,int step,HashMap<String,Long> map){
		// map的key：bob此刻的位置+走过的步数
		// map的value：从bob此刻的位置，走完剩下的过程，产生的存活情况数量
		if(bobX<tLX||bobX>bRX||bobY<tLY||bobY>bRY){
			map.put(String.valueOf(bobX+"+"+bobY+"+"+curNum),(long)0);
			return 0;
		}
		if(curNum==step){
			map.put(String.valueOf(bobX+"+"+bobY+"+"+curNum),(long)1);
			return 1;
		}
		String upkey=String.valueOf((bobX-1)+"+"+bobY+"+"+(curNum+1));
		String downkey=String.valueOf((bobX+1)+"+"+bobY+"+"+(curNum+1));
		String leftkey=String.valueOf((bobX)+"+"+(bobY-1)+"+"+(curNum+1));
		String rightkey=String.valueOf(bobX+"+"+(bobY+1)+"+"+(curNum+1));
		long up=0;
		if(map.containsKey(upkey)){ // 先查向上走的计算分支，是否已经被计算过了，如果计算过，直接取结果
			up=map.get(upkey);
		}else{ // 如果没计算过，进入计算分支计算
			up=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX-1,bobY,curNum+1,step,
					map);
		}
		long down=0;
		if(map.containsKey(downkey)){ // 先查向下走的计算分支，是否已经被计算过了，如果计算过，直接取结果
			down=map.get(downkey);
		}else{ // 如果没计算过，进入计算分支计算
			down=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX+1,bobY,curNum+1,
					step,map);
		}
		long left=0;
		if(map.containsKey(leftkey)){ // 先查向左走的计算分支，是否已经被计算过了，如果计算过，直接取结果
			left=map.get(leftkey);
		}else{ // 如果没计算过，进入计算分支计算
			left=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX,bobY-1,curNum+1,
					step,map);
		}
		long right=0;
		if(map.containsKey(rightkey)){ // 先查向右走的计算分支，是否已经被计算过了，如果计算过，直接取结果
			right=map.get(rightkey);
		}else{ // 如果没计算过，进入计算分支计算
			right=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX,bobY+1,curNum+1,
					step,map);
		}
		long result=up+down+left+right; // 结果是上下左右全部分支的情况数量总和
		map.put(bobX+"+"+bobY+"+"+curNum,result); // 把自己的计算结果记录下来，供别的分支查询和使用
		return result;

	}

	public static void main(String[] args){
		int tLX=0;
		int tLY=0;
		int bRX=20;
		int bRY=20;
		int bobX=10;
		int bobY=10;
		int step=15;

		long start=System.currentTimeMillis();
		String description=diePossibility(tLX,tLY,bRX,bRY,bobX,bobY,step);
		long end=System.currentTimeMillis();
		System.out.println(description);
		System.out.println("cost time : "+(end-start)+"(ms)");

		System.out.println("================================");

		start=System.currentTimeMillis();
		description=diePossibilityDP(tLX,tLY,bRX,bRY,bobX,bobY,step);
		end=System.currentTimeMillis();
		System.out.println(description);
		System.out.println("cost time : "+(end-start)+"(ms)");

	}

}
