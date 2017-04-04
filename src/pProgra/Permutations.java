package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutations {

	public  static void permute(int start, int[] input, ArrayList <int[]> al) { 
		//This method is recursive, it will open multiple instances of the input tab by calling itself and modify them, then stock tab in ArrayList when the operations are done for this tab.
		//ArrayList must be empty. Basically returns all the switching possibilities between an integers array.
			//Printing tab if iterations for that specific tab are done
	        if (start == input.length) {
	        	al.add(Arrays.copyOf(input, input.length)); //Making a deep copy of the tab ("=" would only copy index and...)
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
		        permute(start + 1, input, al);
		        
		       // Changing numbers
		        int temp2 = input[i];
		        input[i] = input[start];
		        input[start] = temp2;
	        
	    }
	}
		

	
	public static int changeColToRef(int[][] tab1, int[][] tab2, int columnIndex1, int columnIndex2){ // tab1 is the one to change, tab2 is the source.
		int counter = 0;
		if(tab1[0].length == tab2[0].length){
			for(int i=0; i<tab1[0].length;i++){
				if(tab1[columnIndex1][i] != tab2[columnIndex2][i]){ //Change if the tool is different 
					tab1[columnIndex1][i] = tab2[columnIndex2][i];
					counter++;
				}
			}
		}
		else{
			System.out.println("Les colonnes doivent être de la même taille.");
		}
		return counter;
	}
	
//The 3 following methods will order numbers in the column i (ascending AND placing 0 at the end) (In case the user didn't already do it in the input file this will prevent further problems).
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void orderCol(int[][] tab, int iCol){  
		int temp;
		int counter = 0;
		for(int i=0; i< tab[0].length-1;i++){
			if(tab[iCol][i] == 0)
				counter++;
			if(tab[iCol][i+1] < tab[iCol][i]){
				//swapping
				temp = tab[iCol][i];
				tab[iCol][i] = tab[iCol][i+1];
				tab[iCol][i+1] = temp;
				i=-1;
			}
		}
		rotate(tab[iCol], tab[iCol].length-counter); //Rotates the tab to place 0's at the end (see the two methods under for more explanations.
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
