package classOne;

import java.util.Stack;

public class HanoiProblem{// L1p6改进的汉诺塔

	public static int hanoiProblem(int levelNumber,String leftTowerName,// 递归实现Hanoi
			String midTowerName,String rightTowerName){
		if(levelNumber<1){
			return 0;
		}
		return computeProcess(levelNumber,leftTowerName,midTowerName,// levelNumber第几层
				rightTowerName,leftTowerName,rightTowerName);
	}

	public static int computeProcess(int levelNumber,String left,String mid,
			String right,String nowPosition,String wantPosition){// nowPosition=leftTowerName
		if(levelNumber==1){ // 递归终止条件，只剩一个盘
			if((nowPosition.equals(left)&&wantPosition.equals(right))// 左《-》右
					||(nowPosition.equals(right)&&wantPosition.equals(left))){
				System.out.println("Move 1 from "+nowPosition+" to "+mid);
				System.out.println("Move 1 from "+mid+" to "+wantPosition);
				return 2;// 标记的步数为2
			}else{// 开始或结束为 中间柱子的 可一步到位，
				System.out.println("Move 1 from "+nowPosition+" to "
						+wantPosition);
				return 1;
			}
		}else{// 剩下不止一层
			if(nowPosition.equals(left)){// 出发地为左
				if(wantPosition.equals(mid)){// 将n从左到中:分解如下
					int part1=computeProcess(levelNumber-1,left,mid,right,left,
							right);// 两个left为实参,后一个left表示 前n-1层从左到右
					int part2=1;// 下面的println算一步
					System.out.println("Move "+levelNumber+" from "+left+" to "
							+mid);// 第n层:从左到中确实移动,
					int part3=computeProcess(levelNumber-1,left,mid,right,
							right,mid);// 从右到中
					return part1+part2+part3;// 步数相加
				}else{// 将n从左到右:分解如下，
					int part1=computeProcess(levelNumber-1,left,mid,right,left,
							right);// n-1从左到右

					int part2=1;// 下面的println算一步
					System.out.println("Move "+levelNumber+" from "+left+" to "
							+mid);// 从左到中确实移动,

					int part3=computeProcess(levelNumber-1,left,mid,right,
							right,left);// 从右到左
					int part4=1;// 下面的println算一步
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+right);// 从中到右确实移动,
					int part5=computeProcess(levelNumber-1,left,mid,right,left,
							right);// 从左到右
					return part1+part2+part3+part4+part5;// 返回:步数相加
				}
			}else if(nowPosition.equals(mid)){// 出发地为中
				if(wantPosition.equals(left)){// 将n从中到左:分解如下
					int part1=computeProcess(levelNumber-1,left,mid,right,mid,
							right);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+left);
					int part3=computeProcess(levelNumber-1,left,mid,right,
							right,left);
					return part1+part2+part3;// 返回:步数相加
				}else{// 将n从中到右:分解如下
					int part1=computeProcess(levelNumber-1,left,mid,right,mid,
							left);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+mid+" to "
							+right);
					int part3=computeProcess(levelNumber-1,left,mid,right,left,
							right);
					return part1+part2+part3;// 返回:步数相加
				}
			}else{// 出发地为右
				if(wantPosition.equals(left)){// 将n从右到左:分解如下
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
					return part1+part2+part3+part4+part5;// 返回:步数相加
				}else{// 将n从右到中:分解如下
					int part1=computeProcess(levelNumber-1,left,mid,right,
							right,left);
					int part2=1;
					System.out.println("Move "+levelNumber+" from "+right
							+" to "+mid);
					int part3=computeProcess(levelNumber-1,left,mid,right,left,
							mid);
					return part1+part2+part3;// 返回:步数相加
				}
			}
		}
	}

	public static class TowersOfHanoiStack{// 非递归实现Hanoi，用栈，
		public Stack<Integer> tower1;// 定义放int的栈tower1
		public Stack<Integer> tower2;
		public Stack<Integer> tower3;
		public int perviousAction;// 上一步动作，123排列
		/*
		 * 0 means: previous action is null, this status is initial 12
		 * means:previous action is moving something from1 to2 21 means:previous
		 * action is moving something from2 to1 23 means:previous action is
		 * moving something from2 to3 32 means:previous action is moving
		 * something from3 to2
		 */
		public int size;
		public int step;

		public TowersOfHanoiStack(int numberlayer){// 初始化汉诺塔栈
			tower1=new Stack<Integer>();// 实例化，
			tower1.push(Integer.MAX_VALUE);// 打底，不用判空
			tower2=new Stack<Integer>();
			tower2.push(Integer.MAX_VALUE);
			tower3=new Stack<Integer>();
			tower3.push(Integer.MAX_VALUE);
			for(int i=numberlayer;i>0;i--){
				tower1.push(i);// 3个栈只有tower1存了:numberlayer..1栈顶
			}
			this.perviousAction=0;
			this.size=numberlayer+1;// 栈底存了个MAX_VALUE
			this.step=0;
		}

		public void tower1ToTower2(){// 3个if为 3个原则，
			if(tower1.peek()!=Integer.MAX_VALUE){// 原则1:出发地栈顶不是初始化的最大值,即非空可以pop
				if(this.perviousAction!=21){// 原则2:前一步不与此步 逆步
					if(tower1.peek()<tower2.peek()){// 原则3:只能小压大
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
						this.perviousAction=32;// 已变下一步判断
						this.step++;
					}
				}
			}
		}

		public void showSolution(){// 总动作，
			while(tower3.size()!=this.size){// tower3未满
				tower1ToTower2();// 同一时刻，以下4步只有一步会发生，且第一步只能从左到中
				tower2ToTower1();
				tower2ToTower3();
				tower3ToTower2();
			}
			System.out.println("finish! It will move "+this.step+" steps.");
		}
	}

	public static void main(String[] args){

		int testNumber=2;

		// // solution 1递归实现
		// int times = hanoiProblem(testNumber, "left", "mid", "right");
		// System.out.println("It will move " + times + " steps.");
		// System.out.println("===================================");

		// solution 2非递归实现
		TowersOfHanoiStack test=new TowersOfHanoiStack(testNumber);// 实例化调构造函数
		test.showSolution();
		System.out.println("===================================");

	}

}