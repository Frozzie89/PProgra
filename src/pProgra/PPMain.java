package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class PPMain {

	public static void main(String[] args) {

//		ArrayList<int[]> al = new ArrayList<int[]>();
//		int[] tab = {1,2,3};
//		int[][]tab2 = 
//			{{1,1,8,8},
//			{1,2,7,0}};
//		int[] tabb = {2,1,4,5,3};
//		int[][] tabb2 = {{4,1,3,2},{3,2,1,5}};
//		JobS testt = new JobS(tabb, tabb2);
//		JobS testt2 = new JobS(tab, tab2);
//		JobS test = ReadJobS.inputJobMatrix();
//		JobS test2 = ReadJobS.inputJobMatrix();
//		int[] tabtest = test.selectTabIndex(0);
//		System.out.println(test.toString());
//		int count = Permutations.changeColToRef(test.getJobS(), tab2, 0, 0);
//		System.out.println(test.toString());
//		System.out.println(count);
//		Permutations.orderCol(tab2, 1);
//		Permutations.orderCol(tab2, 0);
//		System.out.println(Arrays.deepToString(tab2));
//		System.out.println(Arrays.toString(tabtest));
//		int c = JobS.amountSameNumb(testt2.getJobS(), 0, 0);
//		System.out.println(c);
//		System.out.println(JobS.numberCounter(tab2[1], 2));
//		int n = JobS.compareTwoCol(testt, testt2, 0, 0);
//		
//		int[] tabbb = {1,2,3,4,5};
//		int[][]tabbb2 = 
//			{{1,1,8,8},
//			{5,10,2,2}};
//		JobS jobtest = new JobS(tabbb, tabbb2);
//		System.out.println("nchanges :" + n);
//		int h = jobtest.JobSCost();
//		System.out.println("cost =" + h);
//		Permutations.jobSequences(0, tab, al);
//		for(int i = 0; i < al.size(); i++){
//			String msg = "[ ";
//			int[] order = al.get(i);
//			for(int j = 0; j < order.length; j++){
//				msg = msg + order[j] + " ";
//			}
//			msg = msg + "]";
//			System.out.println(msg);
//		}
//		JobS jtest = ReadJobS.inputJobMatrix();
//		int[] seq = {1,2,3,4,5,6};
//		int seqC = JobS.JobSCostExt(jtest, seq);
//		System.out.println(seqC);
//		int[] tab1 = {1,2,3,4,6};
//		int[] tab2 = new int[5];
//		JobS.fillTab(tab2, tab1);
//		System.out.println(Arrays.toString(tab2));
//		
		int[][] mat1 = {{1,2,3}, {3,4,4}};
		int[] mat2 = {5,6,4};
		JobS.fillTabCol(mat1, mat2, 0);
		System.out.println(Arrays.deepToString(mat1));

		
		Gui fenetre = new Gui();
	}
	
		
		
		
	

	
	
}
