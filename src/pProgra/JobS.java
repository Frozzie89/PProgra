package pProgra;

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
	
	public JobS(int jobs, int tools){// Creates one more line for the title (jobOrder).
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

	public static void fillLine(int[] tab1, int[]tab2, int lineIndex){ //Tab1 is the array to fill, tab2 is the source.
		if(tab1.length == tab2.length){
			for(int i = 0; i<tab1.length; i++){
				tab1[i]=tab2[i];
			}
		}
		else{
			System.out.println("Les tableaux doivent être de la même taille (JobS/fillLine).");
		}
	}
	
	public static int getColIndex(JobS jobs1, JobS jobs2, int indexJ1){ //Gives the index of the corresponding job (example : [1,2,3] and [1,3,2] with index 1 will return 2 (position of integer 2 in the second array). 
		int numb = jobs1.getJobOrder()[indexJ1];
		int index = -1;
		for(int i=0; i<jobs2.getJobOrder().length;i++){
			if(jobs2.getJobOrder()[i] == numb){
				index = i;
			}
		}
		return index;	
	}
	
	public int[] selectTabIndex(int index){
		int[] tab = new int [this.jobS[0].length];
		for(int i=0; i<this.jobS[0].length;i++){
			tab[i] = this.jobS[index][i];
		}
		return tab;
	}
	
	public String toString() {
		return "jobOrder=" + Arrays.toString(jobOrder) + ", jobS=" + Arrays.deepToString(jobS);
	}
}
