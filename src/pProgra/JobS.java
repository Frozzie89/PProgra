package pProgra;

import java.util.ArrayList;
import java.util.Arrays;

public class JobS {
	private int[] jobOrder;
	private int[][] jobS;
	private int cost;
	private static JobS jobSInput = ReadJobS.inputJobMatrix();


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
	
	//Build a JobS object with the order (from matric in input file).
	public static JobS JobSBuild(int[] order){
		//creating jobS with order as input.
		jobSInput = ReadJobS.inputJobMatrix();
		
		int[] tempTab = new int[Gui.toolN]; //Used later to fill the matrix.
		int[][] matrix = new int[Gui.jobN][Gui.toolN]; //Order matrix (returned tab)
		int[][] inputMatrix = jobSInput.jobS; //Input matrix.
		
		for(int i=0; i<order.length; i++){
			
			//Fills tempTab with the right tab.
			fillTab(tempTab, inputMatrix[order[i]-1]);
			
			//Fills matrix with tempTab.
			fillTabCol(matrix, tempTab, i);
			
		}
		return new JobS(order, matrix);
	}
	
	//Getters and Setters
	
	public int[] getJobOrder() {
		return jobOrder;
	}
	
	public void setJobOrder(int[] jobOrder) {
		this.jobOrder = jobOrder;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int[][] getJobS() {
		return jobS;
	}
	
	public void setJobS(int[][] jobS) {
		this.jobS = jobS;
	}
	
	//Clone method, creates a JobS object that doesn't share reference with it's original. 
	public static JobS JobSCloner(JobS original){
		
		//Creating clone
		JobS jCloned = new JobS(Gui.jobN, Gui.toolN);
		
		//Filling matrix 
		for(int i=0; i<original.getJobS().length; i++){
			jCloned.jobS[i] = Arrays.copyOf(original.getJobS()[i], original.getJobS()[i].length);
		}
		
		//Filling order
		jCloned.jobOrder = Arrays.copyOf(original.jobOrder, original.jobOrder.length);
		
		//Returning clone
		return jCloned;
		
	}
	
		//used in method below. (getOrderMatrix)
	public static void fillTab(int[] tab1, int[] tab2){ //tab1 is the tab to fill.
		
		//Check tabs are same size (prevent out of bounds errors).
		if(tab1.length == tab2.length){
			
			//Copies second tab in first tab.
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
	

	//Returns the Cost of JobS (only use this if both JobS and JobOrder are filled).
	public int JobSCost(){
		//Ordering tab 
		for(int i=0; i<this.jobS.length; i++){
			Permutations.orderTab(this.jobS[i]);
		}
		//Necessary variables .
		int counter = 0; //Counter.
		int add = 0; //Do calculations here before adding to counter.
		
		//Adds the first row cost. (first job) 
		counter += (this.jobS[0].length) - numberCounter(this.jobS[0], 0); //Length - numbers of 0's.
		
		//Calcultates cost by pair of columns.
		for(int i=0; i<this.jobS.length-1; i++){
			add = compareTwoCol(this, i, i+1);
			counter += add;
		}
		
		
		
		return counter;
	}
	
	//Get the cost to get from one column to another. 
		public static int compareTwoCol(JobS j1, int index1, int index2){ 
			//Var declaration
			int indexData = 0;	//Index 													
			int n = j1.jobS[index1].length; //Cost, starts at max cost and substract from it until we get to the real cost.										 
			
			//Loop will check the first tab until it went trough the whole tab. 
			while(indexData<j1.jobS[index1].length){
				
					//Algorithm explanation :  This method allows you to use multiple times the same tool and will still work. Example : [1,2,2,3]
					//n = the length of the tab. Calculates numbers of same integers in the first tab as j and in the second tab as k.
					//If j > k then n-=k, but if j<=k then subtract  j to n. The n left at the end is the number of changes necessary, or the cost.
					//CAUTION : SAME NUMBERS MUST BE NEXT TO EACH OTHER. Example : [1,1,2,2,5,5,3] OK [1,2,1,5,1,1] NOT OK ! 
					//You can use Permutations.orderTab to order them.
				
					int j = numberCounter(j1.jobS[index1], j1.jobS[index1][indexData]);
					int k = numberCounter(j1.jobS[index2], j1.jobS[index1][indexData]);
					
						if(j <= k){
							n-=j;
						}
						
						if(j > k){
							n-=k;
						}
					//Adding numbers of same integers to index to keep going trough the tab properly. 
					indexData += j;
			}
			
			return n;
		}
		
	
	//Return the index of the best order. (minimize costs)
	public static int getMinCost(ArrayList<JobS> alJobs){
		//Stock index of best cost.
		int index = 0;
		
		//Going trough ArrayList.
		for(int i=1; i<alJobs.size(); i++){
			//If cost is better, stock its index.
			if(alJobs.get(index).getCost() > alJobs.get(i).getCost())
				index = i;
		}
		return index;
	}
	
	//Simple toString.
	public String toString() {
		return "jobOrder=" + Arrays.toString(jobOrder) + ", jobS=" + Arrays.deepToString(jobS) + ", cost = " + this.cost;
	}
}
