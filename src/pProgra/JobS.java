package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class JobS {
	private int[] jobOrder;
	private int[][] jobS;
	public static ArrayList<Integer> tools = new ArrayList<Integer>();
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
	private static void fillTab(int[] tab1, int[] tab2){ //tab1 is the tab to fill.
		
		//Check tabs are same size (prevent out of bounds errors).
		if(tab1.length == tab2.length){
			
			//Copies second tab in forst tab.
			for(int i=0; i<tab1.length; i++){
				tab1[i] = tab2[i];
			}
		}
		
	}
	//used below, fills a line in a integer dual tab.
	private static void fillTabCol(int[][] tab1, int[] tab2, int index){
		
		//Fills column.
		for(int i=0; i<tab1[0].length; i++){
			tab1[index][i] = tab2[i];
		}
		
	}
	//Returns the tool matrix of the order alOrders.get(ID) 
	public static int[][] getOrderMatrix(int ID, ArrayList<int[]> alOrders, JobS input){
		
		//Creating necessary tabs.
		int[] order = alOrders.get(ID); //Stocks order
		int[] tempTab = new int[ReadJobS.inputToolN()]; //Used later to fill the matrix.
		int[][] matrix = new int[ReadJobS.inputJobN()][ReadJobS.inputToolN()]; //Order matrix (returned tab)
		int[][] inputMatrix = input.jobS; //Input matrix.
		
		//Actionsfor each column on matrix.
		for(int i = 0; i<matrix.length;i++){
			
			//Fills tempTab with the right tab.
			fillTab(tempTab, inputMatrix[order[i]-1]);
			//Fills matric with tempTab.
			fillTabCol(matrix, tempTab, i);
		}
		return matrix;
	}
	//Gives the index of the corresponding job (example : [1,2,3] and [1,3,2] with index 1 will return 2 (position of integer 2 in the second array).
	public static int getColIndex(int[] j1, int[] j2, int indexJ1){  
		
		//Necessary variables.
		int numb = j1[indexJ1];
		int index = -1;
		
		//Search for the right index.
		for(int i=0; i<j2.length;i++){
			
			//If found, returns it and break the loop.
			if(j2[i] == numb){
				index = i;
				break;
			}
		}
		return index;	
	}
	
	//return the tab with index from this JobS job sequence. (vertical)
	public int[] selectTabIndex(int index){ 
		
		//Tab used to stock the return.
		int[] tab = new int [this.jobS[0].length];
		
		//Filling the tab.
		for(int i=0; i<this.jobS[0].length;i++){
			tab[i] = this.jobS[index][i];
		}
		return tab;
	}

	/////////////////////////////////////////////////////////////////////////// 
	public static int numberCounter(int[] tab, int number){						// Returns the amount of same numbers in a tab.
		int counter = 0; //Simple counter.
		
		//For each part of the tab, counter++ if the number is what we are searching for.
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
	
	public static boolean isIn(int[] tab, int number){ //Return true if the number is in the tab.
		if(numberCounter(tab, number)>0){
			return true; 
		}
		return false;
	}
	/////////////////////////////////////////////////////////////////////
	//Returns the Cost of JobS (only use this if both JobS and JobOrder are filled).
	public int JobSCost(){
		
		//Necessary variables .
		int counter = 0; //Counter.
		int add = 0; //Do calculations here before adding to counter.
		
		//Calcultates cost by pair of columns.
		for(int i=0; i<this.jobS.length-1; i++){
			add = compareTwoCol(this, this, i, i+1);
			counter += add;
		}
		
		return counter;
	}
	

	
	
		public static int compareTwoCol(JobS j1, JobS j2, int index1, int index2){ 
			
			//Index used in the while loop.
			int indexData = 0;													 // This method allows you to use multiple times the same tool and will still work. Example : [1,2,2,3]
			
			//Cost, starts at maximum capacity.
			int n = j1.jobS[index1].length;										 // n = the length of the tab. Calculates numbers of same integers in the first tab as j and in the second tab as k. (except for 0's)
			
			//Numbers of zeros
			int nZeros = numberCounter(j2.jobS[index2], 0);						 // If j > k then n-=k, but if j<=k then subtract  j to n. Then subtract the numbers of 0's in tab2 to n. The n left at the end is the number of changes necessary, or the cost.
			
			//Actions for each part of the tab.
			while(indexData<j1.jobS[index1].length){
				
				//If the number isn't 0 then calculates j and k and according modifications to n.
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
				
				//If number is 0 then doesn't calculates j and k but index++.
				else{
					indexData++;
				}
			}
			
			//Substract nZeros to n to get final cost.
			n -= nZeros;//
			return n;
		}
		
		public static int compareTwoCol(JobS j1, int index1, int index2){ //Polymorph method above for only 1 JobS as parameter. (tools is not used here but i added it anyway for clarity and similarity with the methode under this one.
			int indexData = 0;												
			int cost = j1.jobS[index1].length;										 
			int nZeros = numberCounter(j1.jobS[index2], 0);
			assert(nZeros >= 0);
			
			while(indexData<j1.jobS[index1].length){
				if(j1.jobS[index1][indexData] != 0){
					int j = numberCounter(j1.jobS[index1], j1.jobS[index1][indexData]);
					int k = numberCounter(j1.jobS[index2], j1.jobS[index1][indexData]);
						if(j <= k){
							cost-=j;
						}
						if(j > k){
							cost-=k;
						}
						
					indexData += j;
				}
				else{
					indexData++;
				}
			}
			cost -= nZeros;
			return cost;
		}
		
		public static int subCompareTwoCol(JobS j1, int index1, int index2, int indexTab1){ //Polymorph method above for only 1 JobS as parameter.
			int indexData = 0;												
			int cost = j1.jobS[index1].length;										 
			int nZeros = numberCounter(j1.jobS[index2], 0);	
			int tab1Index = 0;

			
			while(indexData<j1.jobS[index1].length){
				
				if(j1.jobS[index1][indexData] != 0){
						assert(index2<6);
						int j = numberCounter(j1.jobS[index1], j1.jobS[index1][indexData]);
						int k = numberCounter(j1.jobS[index2], j1.jobS[index1][indexData]);
						
							if(j <= k){
								cost-=j;
							}
							if(j > k){
								cost-=k;
							}
							
						indexData += j;
					
				}else{
			
					//Initialize 3 tabs
					int[] tab1 = j1.jobS[indexTab1];
					int[] tab2 = j1.jobS[index1];
					int[] tab3 = j1.jobS[index2];
					assert(tab2[indexData] == 0);
					for(int i=tab1Index; i<Gui.toolN; i++){
						tab1Index++;
						if(isIn(tab3, tab1[i]) == true && isIn(tab2, tab1[i]) == false){ //TODO multiple zeros handle
							nZeros-=1;
							cost -=2;
							tools.add(tab1[i]);
							
							break;
							
						}
					}
					indexData++;
				}
			}
			
			cost -= nZeros;
			return cost;
		}
		
		
		//Used to calculate cost of the different orders. This method allows us to compare the costs by using only the input tab as reference, so we don't need to create n JobS objects, resulting in less memory used.
		//j1 will be the input JobS for our project.
	public static int JobSCostExt(JobS j1, int[] seq){ 
		int cost = 0; 	//Total cost stock variable.
		//Adds the first row cost. (first job) 
		cost += (j1.jobS[0].length) - numberCounter(j1.selectTabIndex(getColIndex(seq, j1.jobOrder, 0)), 0); //Length - numbers of 0's of first row. (Cost of first row).
		
			for(int i=0; i<Gui.jobN-1; i++){
//				System.out.print(getColIndex(seq, j1.jobOrder, i));
//				System.out.println(getColIndex(seq, j1.jobOrder, i+1));
//				System.out.println("-");
//				System.out.print(j1.jobOrder[seq[i]]-2);
//				System.out.println(j1.jobOrder[seq[i]]-1);
				//If first or last row then do the normal method.
				if(i == 0 || i == Gui.jobN-1){
					cost += compareTwoCol(j1, getColIndex(seq, j1.jobOrder, i), getColIndex(seq, j1.jobOrder, i+1)); //Get the right columns indexes in the input matrix and compare them to get the cost.
				}
				
				//Else check for tools in previous column.
				else{
					assert(i > 0):i;
					assert(i <= 4):i;
//					System.out.print(Arrays.toString(seq));
//					System.out.println(Arrays.toString(j1.jobOrder));	
					cost += subCompareTwoCol(j1, seq[i]-1, seq[i+1]-1, seq[i-1]-1);
					
				}
			}
		System.out.println("here");
		for(int i=0; i<tools.size(); i++){
			System.out.print(tools.get(i));
		}
		System.out.println("size"+tools.size());
		Gui.alTools.add(new ArrayList<Integer>(tools));
		tools.clear();
		return cost;
	}
	
	// Calculate all orders' costs then stock them in an ArrayList. The second ArrayList must be empty. Order and costs will be affected at the same index of corresponding ArrayList. j1 is the input matrix.
	public static void StockJobSCost(JobS j1){
		
		//Check alCosts isEmpty.
		Gui.alCosts.clear();
			
		//Stocks one by one in alCosts.
			for(int i=0; i<Gui.alOrders.size(); i++){
				System.out.println("StockJobSCost " + (i+1) + " out of " + Gui.alOrders.size()+".");
				Gui.alCosts.add(i, JobSCostExt(j1, Gui.alOrders.get(i)));
				assert(Gui.alOrders.get(i).length == 6);
				//System.out.println(Arrays.toString(Gui.alOrders.get(i)));
			}
		
	}
	
	//Return the index of the best order. (minimize costs)
	public static int getMinCost(ArrayList<Integer> alCosts){
		int index = 0;
		for(int i=1; i<alCosts.size(); i++){
			System.out.println("mincost");
			if(alCosts.get(index) > alCosts.get(i))
				index = i;
		}
		return index;
	}
	
	//Simple toString.
	public String toString() {
		return "jobOrder=" + Arrays.toString(jobOrder) + ", jobS=" + Arrays.deepToString(jobS);
	}
}
