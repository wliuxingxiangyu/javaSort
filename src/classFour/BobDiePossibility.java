package classFour;

import java.util.HashMap;

public class BobDiePossibility{//p5

	public static String diePossibility(int tLX,int tLY,int bRX,int bRY,
			int bobX,int bobY,int step){
		// tLX �������Ͻǵ�X����
		// tLY �������Ͻǵ�Y����
		// bRX �������½ǵ�X����
		// bRY �������½ǵ�Y����
		// bobX Bobλ�õ�X����
		// bobY Bobλ�õ�Y����
		// step һ��Ҫ�߶��ٲ�
		// ����ֵString��Bob�������ʵ�����
		if(tLX>bRX||tLY>bRY){
			return "Your area has err!";
		}
		// �����һ���ж����������4^allSteps
		long allNum=computeAllNum(step);
		// ������߹�allSteps��֮�󣬴�������Ƕ�����
		long noDieNum=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX,bobY,0,step);
		// �����������Bob���������
		long dieNum=allNum-noDieNum;
		return "Die Possibility: "+String.valueOf(dieNum+"/"+allNum);
	}

	public static long computeAllNum(int allStepNum){
		return (long)(Math.pow(4,allStepNum));
	}

	public static long computeNoDieProcess(int tLX,int tLY,int bRX,int bRY,
			//
			int bobX,int bobY,int curNum,int step){
		// �����������Bob�Ѿ��߳��������Բ�������������
		if(bobX<tLX||bobX>bRX||bobY<tLY||bobY>bRY){
			return 0;
		}
		// �����������Bob�Ѿ��������е�allSteps���������մ����Է�����һ�ִ������
		if(curNum==step){
			return 1;
		}
		// Bob�����ߣ�����ֵ����Bob�ڵ�currentStepNum����ʱ�������ߣ��������˶����ִ�����
		long up=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX-1,bobY,curNum+1,step);
		// Bob�����ߣ�����ֵ����Bob�ڵ�currentStepNum����ʱ�������ߣ��������˶����ִ�����
		long down=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX+1,bobY,curNum+1,step);
		// Bob�����ߣ�����ֵ����Bob�ڵ�currentStepNum����ʱ�������ߣ��������˶����ִ�����
		long left=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX,bobY-1,curNum+1,step);
		// Bob�����ߣ�����ֵ����Bob�ڵ�currentStepNum����ʱ�������ߣ��������˶����ִ�����
		long right=computeNoDieProcess(tLX,tLY,bRX,bRY,bobX,bobY+1,curNum+1,
				step);
		
		return up+down+left+right;// ���еĴ�����������
	}

	public static String diePossibilityDP(int tLX,int tLY,int bRX,int bRY,
			int bobX,int bobY,int step){
		if(tLX>bRX||tLY>bRY){
			return "Your rectangular has err!";
		}
		long allNum=computeAllNum(step);
		// ��map��¼�ݹ�ÿһ����֧�ļ�����,���map����������з�֧��ȫ�ֱ���
		HashMap<String,Long> map=new HashMap<String,Long>();
		long noDieNum=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX,bobY,0,step,
				map);
		long dieNum=allNum-noDieNum;
		return "Die Possibility: "+String.valueOf(dieNum+"/"+allNum);
	}

	public static long computeNoDieProcessDP(int tLX,int tLY,int bRX,int bRY,
			int bobX,int bobY,int curNum,int step,HashMap<String,Long> map){
		// map��key��bob�˿̵�λ��+�߹��Ĳ���
		// map��value����bob�˿̵�λ�ã�����ʣ�µĹ��̣������Ĵ���������
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
		if(map.containsKey(upkey)){ // �Ȳ������ߵļ����֧���Ƿ��Ѿ���������ˣ�����������ֱ��ȡ���
			up=map.get(upkey);
		}else{ // ���û���������������֧����
			up=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX-1,bobY,curNum+1,step,
					map);
		}
		long down=0;
		if(map.containsKey(downkey)){ // �Ȳ������ߵļ����֧���Ƿ��Ѿ���������ˣ�����������ֱ��ȡ���
			down=map.get(downkey);
		}else{ // ���û���������������֧����
			down=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX+1,bobY,curNum+1,
					step,map);
		}
		long left=0;
		if(map.containsKey(leftkey)){ // �Ȳ������ߵļ����֧���Ƿ��Ѿ���������ˣ�����������ֱ��ȡ���
			left=map.get(leftkey);
		}else{ // ���û���������������֧����
			left=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX,bobY-1,curNum+1,
					step,map);
		}
		long right=0;
		if(map.containsKey(rightkey)){ // �Ȳ������ߵļ����֧���Ƿ��Ѿ���������ˣ�����������ֱ��ȡ���
			right=map.get(rightkey);
		}else{ // ���û���������������֧����
			right=computeNoDieProcessDP(tLX,tLY,bRX,bRY,bobX,bobY+1,curNum+1,
					step,map);
		}
		long result=up+down+left+right; // �������������ȫ����֧����������ܺ�
		map.put(bobX+"+"+bobY+"+"+curNum,result); // ���Լ��ļ�������¼����������ķ�֧��ѯ��ʹ��
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
