package Lesson0;
import java.util.Arrays;
//首先我们要清楚二叉查找树后序遍历结果的特点：
//1.数组最后一个元素为根节点 
//2.除去根节点前面部分分别为左子树和右子树（即比根节点小的左子树和比根节点大的右子树）
//也就是说违背了上面两特点的都不是二叉查找树的后续遍历，具有判断性的特点只有第2个特点，（左子树都比根节点小，右子树都比根节点大）
public class NetIsPostArrayBST{//借鉴网上的判断：后序数组的方法,L2P15
//思路：布尔型flag=true;找右子树第一个位置firstMore;for判断所有右子树比根小,flag=false;
	public static boolean searchTree(int a[],int length){
		boolean leftFlag=true,rightFlag=true,flag=true;//局部变量
		if(length<=0||a==null){
			return false;// 数组不存在，返回假
		}
		int root=a[length-1];//递归到只剩一个元素时，即为root=a[0];
		int firstMore=0;

		while(a[firstMore]<root){//左子树"比根"全小
			firstMore++;// 得到左子树和右子树的分界线，a[firstMore]为右子树第一个
		}
		
		for(int j=firstMore;j<length-1;++j){//右子树"比根"全大 
			if(a[j]<root){//保证右子树"比根"全大
				flag=false;
				break;
			}
		}
		if(firstMore>0){//因为firstMore初始化0,即0到firstMore-1范围为有左子树,进if.
			leftFlag=searchTree(a,firstMore);
		}
		if(firstMore<length-1){
			rightFlag=searchTree(Arrays.copyOfRange(a,firstMore,length-1),length-firstMore-1);
		}//返回值“复制得到的新数组”。因为递归参数searchTree(int a[],int length),没用范围表示。
		//copyOfRange不包括length-1处的元素，即右子树不包括根节点。长度=(length-2)末标-(firstMore)起标+1.
		return flag&&leftFlag&&rightFlag;
	}

	public static void main(String args[]){
//		int a[]={5, 7, 6, 9, 11, 10, 8}; //左右子树全有 true
//		int a[]={5, 7, 6, 9}; //只有左子树 true
//		int a[]={9, 11, 10, 8}; //只有右子树 true
		int a[]={7, 4, 6, 5} ;// false
		System.out.println(searchTree(a,a.length));
	}

}
