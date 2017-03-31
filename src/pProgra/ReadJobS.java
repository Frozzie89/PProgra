package pProgra;
import java.io.*;
import java.util.StringTokenizer;
public class ReadJobS {
//This class is designed to handle the reading of the input. inputName variable is static and is used to set your filename.
	private static String inputName = "input.txt";

	//Getters and Setters
	
	public static String getInputName() {
		return inputName;
	}

	public static void setInputName(String inputName) {
		ReadJobS.inputName = inputName;
	}
	
	//Read file toolN
	public static int inputToolN(){
		int toolN = 0;
		String line = ""; // Line in tokenizer
		try {
			// Initializing reader.
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			toolN = Integer.parseInt(st.nextToken());
			br.close();
			
		} catch (IOException e) {
			System.out.println("File not found exception in inputToN.");
		}
		return toolN; 	
	}
	//Read file JobN
	public static int inputJobN(){
		int jobN = 0;
		String line = ""; // Line in tokenizer
		try {
			// Initializing reader.
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			for(int i=0; i<2; i++){ //Reach second line
				line = br.readLine();
			}
			StringTokenizer st = new StringTokenizer(line);
			jobN = Integer.parseInt(st.nextToken());
			br.close();
			
		} catch (IOException e) {
			System.out.println("File not found exception in inputJobN.");
		}
		return jobN; 	
	}
	
	public static JobS inputJobMatrix(){
		String line = ""; // Line in tokenizer
		int jobN = inputJobN();
		int toolN = inputToolN();
		//Instancing JobS object 
		JobS inputJobS = new JobS(jobN, toolN);
		int[][] tabFill = new int[jobN][toolN];
		int[] tabFillOrder = new int[jobN];
		try {
			// Initializing reader.
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			for(int i=0; i<3; i++){ //ReachFirstLine of matrix
				line = br.readLine();
				//System.out.println(line);
			}
			
			//Instancing tab for Job Order 1...n
			int[] a = new int[jobN];
			for (int i=0; i<jobN; i++){
				a[i]=i+1;
			}
			//Filling Order tab with Job order
			JobS.fillLine(tabFillOrder, a, 0);
			//Reading the matrix line by line and filling tab line
			for(int j=0; j<toolN; j++){
				StringTokenizer st = new StringTokenizer(line);
				for(int i=0; i<jobN; i++){
					String str = st.nextToken();
					//System.out.print(str);
					tabFill[i][j] = Integer.parseInt(str);
				}
				//System.out.println("");
				line = br.readLine();
				
				
			}
			inputJobS.setJobS(tabFill);
			inputJobS.setJobOrder(tabFillOrder);
			br.close();
			
		} catch (IOException e) {
			System.out.println("File not found exception in inputJobMatrix.");
		}
		return inputJobS;
		
	}
	

	
}
