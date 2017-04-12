package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class JobS {
	private int[] jobOrder;
	private int[][] jobS;

	//Constructors
	public JobS(int[][] jobS) {
		super();
		this.jobS = jobS;
	}
	
	public JobS(int[] jobOrder, int[][] jobS) {
		super();
		this.jobOrder = jobOrder;
		this.jobS = jobS;
	}
	
	public JobS(int jobs, int tools){
		super();
		int[][] tab = new int[jobs][tools]; 
		int[] tab2 = new int[jobs];
		this.jobS = tab;
		this.jobOrder = tab2;
	}
	
	//Getters and Setters
	
	public int[] getJobOrder() {
		return jobOrder;
	}
	
	public void setJobOrder(int[] jobOrder) {
		this.jobOrder = jobOrder;
	}
	
	public int[][] getJobS() {
		return jobS;
	}
	
	public void setJobS(int[][] jobS) {
		this.jobS = jobS;
	}
	
	//Fill line
	public static void fillLine(int[][] tab1, int[]tab2, int lineIndex){ //Tab1 is the array to fill, tab2 is the source.
		if(tab1.length == tab2.length){
			for(int i = 0; i<tab1.length; i++){
				tab1[i][lineIndex]=tab2[i];
			}
		}
		else{
			System.out.println("Les tableaux doivent être de la même taille (JobS/fillLine).");
		}
	}

	//Fill column
	public static void fillColumn(int[][] tab1, int[]tab2, int columnIndex){ //Tab1 is the array to fill, tab2 is the source.
		if(tab1[0].length == tab2.length){
			for(int i = 0; i<tab1.length; i++){
				tab1[columnIndex][i]=tab2[i];
			}
		}
		else{
			System.out.println("Les tableaux doivent être de la même taille (JobS/fillColumn).");
		}
	}
	
	//Fill Line
	public static void fillLine(int[] tab1, int[]tab2){ //Tab1 is the array to fill, tab2 is the source.
		if(tab1.length == tab2.length){
			for(int i = 0; i<tab1.length; i++){
				tab1[i]=tab2[i];
			}
		}
		else{
			System.out.println("Les tableaux doivent être de la même taille (JobS/fillLine).");
		}
	}
	
	//Fill column
		public static void fillColumn(int[] tab1, int[]tab2){ //Tab1 is the array to fill, tab2 is the source.
			if(tab1.length == tab2.length){
				for(int i = 0; i<tab1.length; i++){
					tab1[i]=tab2[i];
				}
			}
			else{
				System.out.println("Les tableaux doivent être de la même taille (JobS/fillColumn).");
			}
		}
		//used in method below. (getOrderMatrix)
		//TODO : end this
		
//	private static void fillTab(int[] tab1, int[][] tab2, int index){ //tab1 is the tab to fill.
//		for(int j=0; j<tab1.length; j++){
//			tab1[j] = tab2[index][j];
//		}
//	}
//	//Returns dual tab (matrix of the order)
//	public static int[][] getOrderMatrix(int ID, ArrayList alOrders, JobS input){
//		int jobN = ReadJobS.inputJobN();
//		int toolN = ReadJobS.inputToolN();
//		int[][] tabRet = new int[jobN][toolN];
//		int[] fillTab = new int[jobN];
//		for(int i=0; i<jobN; i++){
//			fillTab(fillTab, input.getJobS(), i);
//		}
		
//	}
	//Gives the index of the corresponding job (example : [1,2,3] and [1,3,2] with index 1 will return 2 (position of integer 2 in the second array).
	public static int getColIndex(int[] j1, int[] j2, int indexJ1){  
		int numb = j1[indexJ1];
		int index = -1;
		for(int i=0; i<j2.length;i++){
			if(j2[i] == numb){
				index = i;
				break;
			}
		}
		return index;	
	}
	//return the tab with index from this JobS job sequence. (vertical)
	public int[] selectTabIndex(int index){ 
		int[] tab = new int [this.jobS[0].length];
		for(int i=0; i<this.jobS[0].length;i++){
			tab[i] = this.jobS[index][i];
		}
		return tab;
	}
	
	public static int amountSameNumb(int tab[][], int colIndex, int startIndex){ //Returns the amount of same numbers in a tab (ordered one) example : [1,1,2] starting at index 0 will return 2 because there is two 1's.
		int numb = tab[colIndex][startIndex];									 // TODO : Is this method necessary ? (numberCounter seems better)
		int counter = 0;
		for(int i=0; i<tab[colIndex].length; i++){
			if(tab[colIndex][i] == numb)
				counter++;
			else{
				break;
			}
		}
		return counter; 
	}
	/////////////////////////////////////////////////////////////////////////// 
	public static int numberCounter(int[] tab, int number){						// Returns the amount of same numbers in a tab.
		int counter = 0;
		for(int i=0; i<tab.length; i++){
			if(tab[i] == number)
				counter++;
		}
		return counter;
	}
	
	public static int numberCounter(int[] tab, int number, int startIndex){
		int counter = 0;
		for(int i=startIndex; i<tab.length; i++){
			if(tab[i] == number)
				counter++;
		}
		return counter;
	}
	/////////////////////////////////////////////////////////////////////
	//Returns the Cost of JobS (only use this if both JobS and JobOrder are filled).
	public int JobSCost(){
		int counter = 0;
		int add = 0;
		for(int i=0; i<this.jobS.length-1; i++){
			add = compareTwoCol(this, this, i, i+1);
			counter += add;
		}
		return counter;
	}
	

		public static int compareTwoCol(JobS j1, JobS j2, int index1, int index2){ 
			int indexData = 0;													 // This method allows you to use multiple times the same tool and will still work. Example : [1,2,2,3]
			int n = j1.jobS[index1].length;										 // n = the length of the tab. Calculates numbers of same integers in the first tab as j and in the second tab as k. (except for 0's)
			int nZeros = numberCounter(j2.jobS[index2], 0);						 // If j > k then n-=k, but if j<=k then subtract  j to n. Then subtract the numbers of 0's in tab2 to n. The n left at the end is the number of changes necessary, or the cost.
			while(indexData<j1.jobS[index1].length){
				if(j1.jobS[index1][indexData] != 0){
					int j = numberCounter(j1.jobS[index1], j1.jobS[index1][indexData]);
					int k = numberCounter(j2.jobS[index2], j1.jobS[index1][indexData]);
						if(j <= k){
							n-=j;
						}
						else{
							n-=k;
						}
						
					indexData += j;
				}
				else{
					indexData++;
				}
			}
			n -= nZeros;
			return n;
		}
		
		public static int compareTwoCol(JobS j1, int index1, int index2){ //Polymorph method above for only 1 JobS as parameter.
			int indexData = 0;													
			int n = j1.jobS[index1].length;										 
			int nZeros = numberCounter(j1.jobS[index2], 0);						
			while(indexData<j1.jobS[index1].length){
				if(j1.jobS[index1][indexData] != 0){
					int j = numberCounter(j1.jobS[index1], j1.jobS[index1][indexData]);
					int k = numberCounter(j1.jobS[index2], j1.jobS[index1][indexData]);
						if(j <= k){
							n-=j;
						}
						else{
							n-=k;
						}
						
					indexData += j;
				}
				else{
					indexData++;
				}
			}
			n -= nZeros;
			return n;
		}
		//Used to calculate cost of the different orders. This method allows us to compare the costs by using only the input tab as reference, so we don't need to create n JobS objects, resulting in less memory used.
		//j1 will be the input JobS for our project.
	public static int JobSCostExt(JobS j1, int[] seq){ 
		int cost = 0; 	//Total cost stock variable.
		//Adds the first row cost. (first job) 
		cost += (j1.jobS[0].length) - numberCounter(j1.selectTabIndex(getColIndex(seq, j1.jobOrder, 0)), cost); //Length - numbers of 0's.
		if(j1.jobOrder.length == seq.length){ //Requires both tabs to bad the same length 
			for(int i=0; i<seq.length-1; i++){
				cost += compareTwoCol(j1, getColIndex(seq, j1.jobOrder, i), getColIndex(seq, j1.jobOrder, i+1)); //Get the right columns indexes in the input matrix and compare them to get the cost.
			}
		}
		else{
			System.out.println("Les tableaux doivent être de la même taille.");
		}
		return cost;
	}
	
	// Calculate all orders' costs then stock them in an ArrayList. The second ArrayList must be empty. Order and costs will be affected at the same index of corresponding ArrayList.
	public static void StockJobSCost(JobS j1, ArrayList<int[]> alOrders, ArrayList<Integer> alCosts){
		
		//Check alCosts isEmpty.
		if(alCosts.isEmpty()){
			for(int i=0; i<alOrders.size(); i++){
				alCosts.add(i, JobSCostExt(j1, alOrders.get(i)));
			}
		}
		else{
			System.out.println("alCosts must be empty, StockJobSCost method");
		}
	}
	
	//Return the index of the best order. (minimize costs)
	public static int getMinCost(ArrayList<Integer> alCosts){
		int index = 0;
		for(int i=1; i<alCosts.size(); i++){
			if(alCosts.get(index) > alCosts.get(i))
				index = i;
		}
		return index;
	}
	public String toString() {
		return "jobOrder=" + Arrays.toString(jobOrder) + ", jobS=" + Arrays.deepToString(jobS);
	}
}
