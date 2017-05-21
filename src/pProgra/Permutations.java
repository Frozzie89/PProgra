package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutations {
private static int jobN = ReadJobS.inputJobN();
private static int toolN = ReadJobS.inputToolN();
	public  static void jobSequences(int start, int[] input, ArrayList <JobS> al) { 
		//This method is recursive, it will open multiple instances of the input tab by calling itself and modify them, then stock tab in ArrayList when the operations are done for this tab.
		//ArrayList must be empty. Basically returns all the switching possibilities between an integers array.
			//Printing tab if iterations for that specific tab are done
	        if (start == input.length) {
	        	al.add(JobS.JobSBuild(Arrays.copyOf(input, input.length))); //Adding JobS to the tab.
	        	
	        	////////////////////////////////
	        	// For printing tabs in console.
	            // for(int x: input){
	            // System.out.print(x);
	            // }
	        	// System.out.println("here");
	        	////////////////////////////////
	        //End the specific tab loop when it's printed 
	        
	        return;
	        }
	        for (int i = start; i < input.length; i++) {
		        // Changing numbers
		        int temp = input[i];
		        input[i] = input[start];
		        input[start] = temp;
		        
		        //////////////////////////////////////////////////
		        // Tests to see algorithm steps
		        //
		        // System.out.print("temp : " + temp + " ... ");
		        // System.out.print("i : "+i + " ... ");
		        // System.out.print("start : " + start);
		        // System.out.println("");
		        // System.out.print("---");
		        // for(int x: input){
		        //  	System.out.print(x);
		        // }
		        // System.out.println("");
		        //////////////////////////////////////////////////
		        
		        //Changing numbers
		        jobSequences(start + 1, input, al);
		        
		       // Changing numbers
		        int temp2 = input[i];
		        input[i] = input[start];
		        input[start] = temp2;
	        
	    }
	}
	
	//Will add every tool keeping possibility to the ArrayList.
	public static void subJobSequences(ArrayList <JobS> al){
		int basealSize = al.size();
		//Going trough ArrayList
		for(int k =0; k< al.size(); k++){
			//Going trough columns (ignoring first one).
			for(int i=1; i<jobN; i++){
				//Going trough lines 
				for(int j=0; j<toolN; j++){
					//Check for 0's.
					if(al.get(k).getJobS()[i][j] == 0){
						//Simulate to keep each tool from the previous job
						for(int m=0; m<toolN; m++){
							//Cloning matrix
							JobS jCloned = JobS.JobSCloner(al.get(k));
							//Modifying matrix 
								//Checks if the number isn't 0 (avoid infinite loop) and isn't already in the column (faster overall).
							if(jCloned.getJobS()[i-1][m] != 0 && (  JobS.numberCounter(jCloned.getJobS()[i], jCloned.getJobS()[i][j]) > 0)  ){
								jCloned.getJobS()[i][j] = jCloned.getJobS()[i-1][m];
								
							//Ordering matrix 
							for(int ibis=0; ibis<jobN; ibis++){
								orderTab(jCloned.getJobS()[ibis]);
							}
							//Add new matrix to arraylist
							al.add(jCloned);
							}
						}
					}
				}
			}
		}
//		//Removing the matrix (all) containing 0.
//		boolean check = false;
//		for(int k=0; k<al.size(); k++){
//			for(int i=1; i<jobN; i++){
//				for(int j=0; j<toolN; j++){
//					if(al.get(k).getJobS()[i][j] == 0){
//						check = true;
//					}
//				}
//			}
//			if(check == true){
//				al.remove(k); 
//				k--;
//				check = false; 
//			}
//		}
	}
	
	//Modify 1 number in a matrix
	public static void jobSMod(JobS j1, int i, int col, int line){
		j1.getJobS()[col][line] = i;
	}
	
//The 3 following methods will order numbers in the column i (ascending AND placing 0 at the end) (In case the user didn't already do it in the input file this will prevent further problems).
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void orderTab(int[] tab){  
		int temp;
		int counter = 0;
		for(int i=0; i< tab.length-1;i++){
			if(tab[i] == 0)
				counter++;
			if(tab[i+1] < tab[i]){
				//swapping
				temp = tab[i];
				tab[i] = tab[i+1];
				tab[i+1] = temp;
				i=-1;
			}
		}
		rotate(tab, tab.length-counter); //Rotates the tab to place 0's at the end (see the two methods under for more explanations.
													 //(tab[iCol].length - counter) value is the numbers of rotations necessary, example : [0,1,2] will return 2 so the tabs becomes [1,2,0].  
	}
	
	private static void rotate(int[] tab, int numbReverse) { // numbReverse is the number of rotations
	    numbReverse %= tab.length;

	    reverse(tab, 0, tab.length - 1); // Reverse all
	    reverse(tab, 0, numbReverse - 1); // Reverse the left rotation part
	    reverse(tab, numbReverse, tab.length - 1); //Reverse the right rotation part
	    
	}

	private static void reverse(int[] tab, int start, int end) { //Reverse numbers in a tab, used in rotate method.
	    while (start < end) {	
	        int temp = tab[start];
	        tab[start] = tab[end];
	        tab[end] = temp;
	        start++;
	        end--;
	    }
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
}
