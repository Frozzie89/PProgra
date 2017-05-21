package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class PPMain {

	private static JobS jobtest = ReadJobS.inputJobMatrix();
	
	public static void main(String[] args) {
			Gui fenetre = new Gui();
		int[] tab = {1,3,2,4,5,6};
		int[] tab3 = {1,2,3,3};
		int[] tab2 = {0,0,1,2};
		JobS test = JobS.JobSBuild(tab);
		System.out.println(test.toString());
		
		ArrayList<JobS> al = new ArrayList <JobS>();
		Permutations.jobSequences(0, ReadJobS.basicOrder(6), al);
		Permutations.subJobSequences(al);
			for(int i=0; i<al.size(); i++){
				System.out.println(al.get(i).toString());
				System.out.println(i);
			}
		JobS jCloned = JobS.JobSCloner(test);
		System.out.println(jCloned.toString());
		System.out.println(JobS.numberCounter(tab2, 0));
		
		System.out.println(JobS.compareTwoCol(tab2, tab3));
		System.out.println(JobS.compareTwoCol(tab3, tab2));
		
		System.out.println(jobtest.JobSCost());
		
		
		}
		
		
	}
	
