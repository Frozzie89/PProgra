package pProgra;

import java.util.Arrays;

public class PPMain {

	public static void main(String[] args) {
	
		int[] tab = {1,2,3,4,5};
		int[][]tab2 = 
			{{1,4,0,5},
			{2,4,0,5}};
		JobS test = ReadJobS.inputJobMatrix();
		System.out.println(test.toString());
		int count = Permutations.changeColToRef(test.getJobS(), tab2, 0, 0);
		System.out.println(test.toString());
		System.out.println(count);
		Permutations.orderCol(tab2, 1);
		Permutations.orderCol(tab2, 0);
		System.out.println(Arrays.deepToString(tab2));
		}
		
		
		
	

	
	
}
