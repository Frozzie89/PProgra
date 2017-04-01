package pProgra;

import java.util.Arrays;

public class PPMain {

	public static void main(String[] args) {
	
		int[] tab = {1,2,3,4,5};
		int[][]tab2 = 
			{{1,1,8,5},
			{2,2,0,5}};
		int[] tabb = {2,1,4,5,3};
		int[][] tabb2 = {{4,1,3,2},{3,2,1,5}};
		JobS testt = new JobS(tabb, tabb2);
		JobS testt2 = new JobS(tab, tab2);
		JobS test = ReadJobS.inputJobMatrix();
		JobS test2 = ReadJobS.inputJobMatrix();
		int[] tabtest = test.selectTabIndex(0);
		System.out.println(test.toString());
		int count = Permutations.changeColToRef(test.getJobS(), tab2, 0, 0);
		System.out.println(test.toString());
		System.out.println(count);
		Permutations.orderCol(tab2, 1);
		Permutations.orderCol(tab2, 0);
		System.out.println(Arrays.deepToString(tab2));
		int yy = JobS.getColIndex(testt, testt2, 4);
		System.out.println(yy);
		System.out.println(Arrays.toString(tabtest));
		int c = JobS.amountSameNumb(testt2.getJobS(), 0, 0);
		System.out.println(c);
		System.out.println(JobS.numberCounter(tab2[1], 2));
		int n = JobS.compareTwoJ(testt, testt2, 0, 0);
		
		System.out.println("nchanges :" + n);
		}
		
		
		
	

	
	
}
