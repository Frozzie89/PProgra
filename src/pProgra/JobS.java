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
			System.out.println("Les tableaux doivent �tre de la m�me taille (JobS/fillLine).");
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
			System.out.println("Les tableaux doivent �tre de la m�me taille (JobS/fillColumn).");
		}
	}

	public static void fillLine(int[] tab1, int[]tab2, int lineIndex){ //Tab1 is the array to fill, tab2 is the source.
		if(tab1.length == tab2.length){
			for(int i = 0; i<tab1.length; i++){
				tab1[i]=tab2[i];
			}
		}
		else{
			System.out.println("Les tableaux doivent �tre de la m�me taille (JobS/fillLine).");
		}
	}
	public String toString() {
		return "jobOrder=" + Arrays.toString(jobOrder) + ", jobS=" + Arrays.deepToString(jobS);
	}
}
