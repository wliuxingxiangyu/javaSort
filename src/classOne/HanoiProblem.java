package classOne;

import java.util.Stack;

public class HanoiProblem{// L1p6�Ľ��ĺ�ŵ��

	public static int hanoiProblem(int levelNumber,String leftTowerName,// �ݹ�ʵ��Hanoi
			String midTowerName,String rightTowerName){
		if(levelNumber<1){
			return 0;
		}
		return computeProcess(levelNumber,leftTowerName,midTowerName,// levelNumber�ڼ���
				rightTowerName,leftTowerName,rightTowerName);
	}

	public static int computeProcess(int levelNumber,String left,String mid,
			String right,String nowPosition,String wantPosition){// nowPosition=leftTowerName
		if(levelNumber==1){ // �ݹ���ֹ������ֻʣһ����
			if((nowPosition.equals(left)&&wantPosition.equals(right))// ��-����
					||(nowPosition.equals(right)&&wantPosition.equals(left))){
				System.out.println("Move 1 from "+nowPosition+" to "+mid);
				System.out.println("Move 1 from "+mid+" to "+wantPosition);
				return 2;// ��ǵĲ���Ϊ2
			}else{// ��ʼ�����Ϊ �м����ӵ� ��һ����λ��
				System.out.println("Move 1 from "+nowPosition+" to "
						+wantPosition);
				return 1;
			}
		}else{// ʣ�²�ֹһ��
			if(nowPosition.equals(left)){// ������Ϊ��
				if(wantPosition.equals(mid)){// ��n������:�ֽ�����
					int part1=computeProcess(levelNumber-1,left,mid,right,left,
							right);// ����leftΪʵ��,��һ��left��ʾ ǰn-1�������
					int part2=1;// �����println��һ��
					System.out.println("Move "+levelNumber+" from "+left+" to "
							+mid);// ��n��:������ȷʵ�ƶ�,
					int part3=computeProcess(levelNumber-1,left,mid,right,
							right,mid);// ���ҵ���
					return part1+part2+part3;// �������
				}else{// ��n������:�ֽ����£�
					int part1=computeProcess(levelNumber-1,left,mid,right,left,
							right);// n-1������

					int part2=1;// �����println��һ��
					System.out.println("Move "+levelNumber+" from "+left+" to "
							+mid);// ������ȷʵ�ƶ�,

					int part3=computeProcess(levelNumber-1,left,mid,right,
							right,left);// ���ҵ���
					int part4=1;// �����println��һ��
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+right);// ���е���ȷʵ�ƶ�,
					int part5=computeProcess(levelNumber-1,left,mid,right,left,
							right);// ������
					return part1+part2+part3+part4+part5;// ����:�������
				}
			}else if(nowPosition.equals(mid)){// ������Ϊ��
				if(wantPosition.equals(left)){// ��n���е���:�ֽ�����
					int part1=computeProcess(levelNumber-1,left,mid,right,mid,
							right);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+left);
					int part3=computeProcess(levelNumber-1,left,mid,right,
							right,left);
					return part1+part2+part3;// ����:�������
				}else{// ��n���е���:�ֽ�����
					int part1=computeProcess(levelNumber-1,left,mid,right,mid,
							left);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+right);
					int part3=computeProcess(levelNumber-1,left,mid,right,left,
							right);
					return part1+part2+part3;// ����:�������
				}
			}else{// ������Ϊ��
				if(wantPosition.equals(left)){// ��n���ҵ���:�ֽ�����
					int part1=computeProcess(levelNumber-1,left,mid,right,
							right,left);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+right
							+" to "+mid);
					int part3=computeProcess(levelNumber-1,left,mid,right,left,
							right);
					int part4=1;
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+left);
					int part5=computeProcess(levelNumber-1,left,mid,right,
							right,left);
					return part1+part2+part3+part4+part5;// ����:�������
				}else{// ��n���ҵ���:�ֽ�����
					int part1=computeProcess(levelNumber-1,left,mid,right,
							right,left);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+right
							+" to "+mid);
					int part3=computeProcess(levelNumber-1,left,mid,right,left,
							mid);
					return part1+part2+part3;// ����:�������
				}
			}
		}
	}

	public static class TowersOfHanoiStack{// �ǵݹ�ʵ��Hanoi����ջ��
		public Stack<Integer> tower1;// �����int��ջtower1
		public Stack<Integer> tower2;
		public Stack<Integer> tower3;
		public int perviousAction;// ��һ��������123����
		/*
		 * 0 means: previous action is null, this status is initial 12
		 * means:previous action is moving something from1 to2 21 means:previous
		 * action is moving something from2 to1 23 means:previous action is
		 * moving something from2 to3 32 means:previous action is moving
		 * something from3 to2
		 */
		public int size;
		public int step;

		public TowersOfHanoiStack(int numberlayer){// ��ʼ����ŵ��ջ
			tower1=new Stack<Integer>();// ʵ������
			tower1.push(Integer.MAX_VALUE);// ��ף������п�
			tower2=new Stack<Integer>();
			tower2.push(Integer.MAX_VALUE);
			tower3=new Stack<Integer>();
			tower3.push(Integer.MAX_VALUE);
			for(int i=numberlayer;i>0;i--){
				tower1.push(i);// 3��ջֻ��tower1����:numberlayer..1ջ��
			}
			this.perviousAction=0;
			this.size=numberlayer+1;// ջ�״��˸�MAX_VALUE
			this.step=0;
		}

		public void tower1ToTower2(){// 3��ifΪ 3��ԭ��
			if(tower1.peek()!=Integer.MAX_VALUE){// ԭ��1:������ջ�����ǳ�ʼ�������ֵ,���ǿտ���pop
				if(this.perviousAction!=21){// ԭ��2:ǰһ������˲� �沽
					if(tower1.peek()<tower2.peek()){// ԭ��3:ֻ��Сѹ��
						tower2.push(tower1.pop());// --------------
						System.out.println("Move "+""+tower2.peek()+""
								+" form1 to2");
						this.perviousAction=12;
						this.step++;
					}
				}
			}
		}

		public void tower2ToTower1(){
			if(tower2.peek()!=Integer.MAX_VALUE){
				if(this.perviousAction!=12){
					if(tower2.peek()<tower1.peek()){
						tower1.push(tower2.pop());
						System.out.println("Move "+""+tower1.peek()+""
								+" form2 to1");
						this.perviousAction=21;
						this.step++;
					}
				}
			}
		}

		public void tower2ToTower3(){
			if(tower2.peek()!=Integer.MAX_VALUE){
				if(this.perviousAction!=32){
					if(tower2.peek()<tower3.peek()){
						tower3.push(tower2.pop());
						System.out.println("Move "+""+tower3.peek()+""
								+" form2 to3");
						this.perviousAction=23;
						this.step++;
					}
				}
			}
		}

		public void tower3ToTower2(){
			if(tower3.peek()!=Integer.MAX_VALUE){
				if(this.perviousAction!=23){
					if(tower3.peek()<tower2.peek()){
						tower2.push(tower3.pop());
						System.out.println("Move "+""+tower2.peek()+""
								+" form3 to2");
						this.perviousAction=32;// �ѱ���һ���ж�
						this.step++;
					}
				}
			}
		}

		public void showSolution(){// �ܶ�����
			while(tower3.size()!=this.size){// tower3δ��
				tower1ToTower2();// ͬһʱ�̣�����4��ֻ��һ���ᷢ�����ҵ�һ��ֻ�ܴ�����
				tower2ToTower1();
				tower2ToTower3();
				tower3ToTower2();
			}
			System.out.println("finish! It will move "+this.step+" steps.");
		}
	}

	public static void main(String[] args){

		int testNumber=2;

		// // solution 1�ݹ�ʵ��
		// int times = hanoiProblem(testNumber, "left", "mid", "right");
		// System.out.println("It will move " + times + " steps.");
		// System.out.println("===================================");

		// solution 2�ǵݹ�ʵ��
		TowersOfHanoiStack test=new TowersOfHanoiStack(testNumber);// ʵ���������캯��
		test.showSolution();
		System.out.println("===================================");

	}

}