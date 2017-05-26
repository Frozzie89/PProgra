package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutations {

	private  static int counter = 1; 
	public static int factorialN = factorial(Gui.jobN);
	
	public  static void jobSequences(int start, int[] input) { 
		//This method is recursive, it will open multiple instances of the input tab by calling itself and modify them, then stock tab in ArrayList when the operations are done for this tab.
		//ArrayList must be empty. Basically returns all the switching possibilities between an integers array.
			//Printing tab if iterations for that specific tab are done
	        if (start == input.length) {
	        	System.out.println("Generating order "+counter+" out of " + factorialN+".");
	        	counter++;
	        	Gui.alOrders.add(Arrays.copyOf(input, input.length)); //Making a deep copy of the tab ("=" would only copy index and...)
	        	
	        	//End the specific tab loop when it's printed 
	        
	        return;
	        }
	        for (int i = start; i < input.length; i++) {
		        // Changing numbers
		        int temp = input[i];
		        input[i] = input[start];
		        input[start] = temp;
		        
		        //recursion
		        jobSequences(start + 1, input);
		        
		       // Changing numbers
		        int temp2 = input[i];
		        input[i] = input[start];
		        input[start] = temp2;
	        
	    }
	}
	
	//Gets the factorial of n.
    public static int factorial(int n) {
        int result = 1; 
        for (int i=1; i<=n; i++) {
            result = result * i;
        }
        return result;
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
