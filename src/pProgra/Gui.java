package pProgra;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
// This class is used to build the application GUI. 
public class Gui extends JFrame implements ActionListener{ // Implements ActionListener so we can use it later in the class.
	// Instancing those variables as class variables so we can use them in all class.
	public static ArrayList<int[]> alOrders = new ArrayList<int[]>();
	public static ArrayList<Integer> alCosts = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> alTools = new ArrayList<ArrayList<Integer>>();
	private JButton search;
	private JButton showOrders;
	private JButton optimizotron;
	private JButton backHome;
	private JButton backList; 
	private JButton export;
	private JLabel path = new JLabel("Chemin vers le fichier input.");
	private JobS jobInput = ReadJobS.inputJobMatrix(); 
	public static int jobN = ReadJobS.inputJobN();
	public static int toolN = ReadJobS.inputToolN();
	
	//Basic GUI settings. (title, home panel, ...)
	public Gui(){
		
		this.setTitle("Simple Job Optimizer");
		this.setContentPane(accueil()); //I build the panels as methods and call the method to call panel. (see method below)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true); 
		
	}
	
	private JPanel accueil(){ // Home panel, panel is divided in two panels (up and down), one for search bar and one for other buttons. 
		JPanel accueil = new JPanel();
		JPanel topContainer = new JPanel();
		JPanel bottomContainer = new JPanel();
		
		accueil.setLayout(new GridLayout(2,1)); //Setting layout for placing other panels.
		
		search = new JButton("Parcourir..."); //Creating button
		search.addActionListener((ActionListener) this); //Add action listener (the action listener is at the end of the code).
		
		showOrders = new JButton("Afficher les combinaisons possibles"); //Same
		showOrders.addActionListener((ActionListener) this);
		
		optimizotron = new JButton("Optimiser la séquence"); //Same
		optimizotron.addActionListener((ActionListener) this);
		
		topContainer.add(path); //Adding buttons on the panels.
		topContainer.add(search);
		
		bottomContainer.add(showOrders); //Same
		bottomContainer.add(optimizotron);
		
		accueil.add(topContainer); //Same 
		accueil.add(bottomContainer);
		
		this.setSize(500,100); //Setting frame size.
		this.setResizable(true);
		
		return accueil;
	}
	
	private JPanel showOrders(){ //Show orders panel, used to show different orders.
		JPanel container = new JPanel(); //Creating panels and setting layouts.
		JPanel buttonContainer = new JPanel();
		
		container.setLayout(new BorderLayout());
		buttonContainer.setLayout(new GridLayout(alOrders.size(), 1));

		//Filling the lines one by one using the for loop. 
		for(int i = 0; i < alOrders.size(); i++){
			
			String msg = "[ ";
			int[] order = alOrders.get(i); //Getting order
			for(int j = 0; j < order.length; j++){ //Implementing order in string.
				msg = msg + order[j] + " ";
			}
			msg = msg + "]";
			OrderButton temp = new OrderButton(msg, i); //Creating temporary button. (The button is a class i created so i could add the index as an object value, this way i can incorporate the index in the action listener method and therefore the method will be flexible and adapt to the button clicked, see OrderButton class).
			buttonContainer.add(temp); //Adding it into the line 
			temp.addActionListener(this); //Adding action listener.
			
		}
		
		JScrollPane scroller = new JScrollPane(buttonContainer); //Creating scroll pane (the lines won't fit on the frame otherwise.)
		container.add(scroller, BorderLayout.CENTER); //Placing it in the center of the frame.
		
		backHome = new JButton("Retour..."); //Creating button back.
		backHome.addActionListener(this); //Adding action listener.
		container.add(backHome, BorderLayout.SOUTH); //Placing back at the bottom.
		
		this.setSize(200,400); //Change frame size.
		return container;
	}
	
	private JPanel showDetails(int id, int cost){ //Show details panel.
		
		JPanel container = new JPanel(); //Creating panel.
		JPanel details = new JPanel(); //Creating panel into the first panel.
		JPanel result = new JPanel(); 
		
		JPanel topResult = new JPanel();
		JPanel bottomResult = new JPanel(); 
		
		int width = ReadJobS.inputJobN();
		
		container.setLayout(new BorderLayout()); //Setting container layout.
		details.setLayout(new GridLayout(0, width)); //Setting second panel layout.
		result.setLayout(new GridLayout(2,1)); //Setting bottom part layout.

		
		int[] order = alOrders.get(id); //Getting the order
		for(int j = 0; j < order.length; j++){ // Adding the order to the GridLayout. (details)
			String newStr = " "+order[j];
			JLabel temp = new JLabel(newStr);
			temp.setHorizontalAlignment(JLabel.CENTER);
			details.add(temp);
		}
		
		//Getting result.
		JLabel stringCostLabel = new JLabel("Coût : ");
		
		//Getting cost.
		String newStr2 = " " + cost;
		JLabel costLabel = new JLabel(newStr2);
		costLabel.setForeground(Color.RED);
		
		container.add(details, BorderLayout.CENTER); //Adding the details to the container.
		
		//Dividing result in two Panels.
		result.add(topResult);
		result.add(bottomResult);
		
		//Adding cost to result top part.
		topResult.add(stringCostLabel);
		topResult.add(costLabel);
		
		//Adding back to list button to bottom part.
		backList = new JButton("Retour à la liste");
		backList.addActionListener(this);
		bottomResult.add(backList);
		container.add(result, BorderLayout.SOUTH); //Adding result to the bottom.
		
		
		this.setSize(200,150); // Setting frame size.
		return container;
		
	}
	
	@SuppressWarnings("unchecked")
	private JPanel optiPanel(int minIndex){
		
		ArrayList<Integer> toolss = alTools.get(minIndex);
		
		for(int i=0; i<toolss.size(); i++){
			System.out.print(toolss.get(i));
		}
		//Creating panels.
		JPanel container = new JPanel(); 
		JPanel tab = new JPanel();
		JPanel bottom = new JPanel();
		JPanel upperBottom = new JPanel();
		JPanel lowerBottom = new JPanel();
		JPanel lowUpperBottom = new JPanel();
		JPanel upUpperBottom = new JPanel();
				
		//Creating buttons.
		backHome = new JButton("Retour");
		export = new JButton("Exporter");
		
		//Adding buttons 
		lowerBottom.add(export);
		lowerBottom.add(backHome);
		
		//Adding action listeners
		backHome.addActionListener(this);
		export.addActionListener(this);
		
		//Setting layouts.
		container.setLayout(new BorderLayout());
		tab.setLayout(new GridLayout(toolN+1, jobN));
		bottom.setLayout(new GridLayout(2,1));
		upperBottom.setLayout(new GridLayout(2,1));
		
		//Upper label 
		JLabel upperL = new JLabel("Meilleure solution : ");
		upperL.setHorizontalAlignment(JLabel.CENTER);
		container.add(upperL, BorderLayout.NORTH);
		
		//upUpperBottom label (Cost)
		JLabel upperBottomL = new JLabel("Coût :");
		upUpperBottom.add(upperBottomL);
		String costStr = " " + alCosts.get(minIndex);
		JLabel upperBottom2 = new JLabel(costStr);
		upperBottom2.setForeground(Color.red);
		upUpperBottom.add(upperBottom2);
		
		//lowUpperBottom
		String toolString = "Outils à garder : [";
		String tempStr2 = "";
		for(int i=0; i<toolss.size(); i++){
			tempStr2 = tempStr2 + toolss.get(i);
			if(i<toolss.size()-1)
				tempStr2 += " ";
		}
		tempStr2 = tempStr2 + "]";
		toolString = toolString + tempStr2;
		JLabel toolLabel = new JLabel(toolString);
		lowUpperBottom.add(toolLabel);
		
		//Getting the order.
		int[] tempTab = alOrders.get(minIndex);
		
		//Getting the tab in first line of tab Panel.
		for(int i=0; i<tempTab.length; i++){
			String tempStr = " " + tempTab[i];
			JLabel tempLabel = new JLabel(tempStr);
			Font font = tempLabel.getFont();
			@SuppressWarnings("rawtypes")
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			tempLabel.setFont(font.deriveFont(attributes));
			tempLabel.setForeground(Color.BLUE);
			tempLabel.setHorizontalAlignment(JLabel.CENTER);
			tab.add(tempLabel);
		}
		
		//Getting matrix for filling next lines.
		int[][] tempMatrix = JobS.getOrderMatrix(minIndex, alOrders, jobInput);
		
		//Filling next lines with the matrix.
		for(int i=0; i<tempMatrix[0].length; i++){
			for(int j=0; j<tempMatrix.length; j++){
				String tempStr = " " + tempMatrix[j][i];
				JLabel tempLabel = new JLabel(tempStr);
				if(tempMatrix[j][i] == 0 && j!=0 && j<jobN-1)
					tempLabel.setForeground(new Color(50,205,50));
				tempLabel.setHorizontalAlignment(JLabel.CENTER);
				tab.add(tempLabel);
			}
		}
		
		//Adding panels to their containers.
		container.add(tab, BorderLayout.CENTER);
		container.add(bottom, BorderLayout.SOUTH); 
		bottom.add(upperBottom);
		bottom.add(lowerBottom);
		upperBottom.add(upUpperBottom);
		upperBottom.add(lowUpperBottom);
		
		//Set size and return.
		this.setSize(250,300);
		return container;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) { //ActionPerformer, this will tell buttons what to perform when they are pressed.
		
		//First I get the object pressed (see below) then I use multiple If statements to tell the software what to do when a certain button is pressed.
		
		Object source = e.getSource();
		
		if(source == search){
			//FileChooser, used to choose the input file location. (default is app folder, input.txt)
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this.getContentPane());
			if (result == JFileChooser.APPROVE_OPTION) {
				
				//Changing input file path.
				File selectedFile = fileChooser.getSelectedFile();
				path.setText(selectedFile.getPath());
				System.out.println(selectedFile.getPath());
				ReadJobS.setInputName(selectedFile.getPath());
				
				//Calculating variables with new input file.
				jobInput = ReadJobS.inputJobMatrix(); 
				jobN = ReadJobS.inputJobN();
				toolN = ReadJobS.inputToolN();
				Permutations.factorialN = Permutations.factorial(jobN);
				
				//Settinf pane.
				this.setContentPane(accueil());
				this.setVisible(true);
				
			}
		}
		else if(source == showOrders){
			//Clear AL in case it is not empty to avoid duplicatas.
			alOrders.clear();
			int jobN = ReadJobS.inputJobN();
			
			//Simple 1..n tab.
			int[] input = new int[jobN];
			for (int i=0; i<jobN; i++){
				input[i]=i+1;
			}
			
			//Stocking orders in arraylist.
			Permutations.jobSequences(0, input);
			
			//Setting pane.
			this.setContentPane(showOrders());
			this.setResizable(true);
			this.setVisible(true);
			
		}
		else if(source == backHome){
			
			//Return to home (set pane home).
			this.setContentPane(accueil());
			this.setVisible(true);
			alOrders = new ArrayList<int[]>();
		}
		else if(source == backList){
			
			//Return to list.
			this.setContentPane(showOrders());
			this.setVisible(true);
		}
		else if(source.getClass() == OrderButton.class){
			
			//Used when the user press an orderButton (on the order List).
			//Calculates cost of the order.
			OrderButton temp = (OrderButton)source;
			int cost = JobS.JobSCostExt(jobInput, alOrders.get(temp.getID()));  
			this.setContentPane(showDetails(temp.getID(), cost));
			this.setVisible(true);
			
		}
		else if(source == optimizotron){
			//Clearing in case they're not empty.
			alOrders.clear();
			alCosts.clear();
			alTools.clear();
			
			JobS inputJobS = ReadJobS.inputJobMatrix(); //input matrix.
			int minIndex; //Will stock the best order index.
			int[] input = new int[jobN]; //Just a tab 1..n to use as a parameter of jobSequences.
			
			for (int i=0; i<jobN; i++){ 
				input[i]=i+1;
			}
			//Stocking all orders in ArrayList.
			Permutations.jobSequences(0, input);
			//Stocking all costs in parallel.
			JobS.StockJobSCost(inputJobS);
//			for(int i=0; i< alCosts.size(); i++){
//				System.out.println(alCosts.get(i));
//			}
			//Getting best order index.
			minIndex = JobS.getMinCost(alCosts);
			
			
			//Setting pane.
			this.setContentPane(optiPanel(minIndex));
			this.setVisible(true);
			this.setResizable(true);
			
		}
		else if(source == export){
			
			    JFileChooser chooser = new JFileChooser();
			    int result = chooser.showSaveDialog(null);
			    if (result == JFileChooser.APPROVE_OPTION) {
			        try {
			        	
			        	//Printing top part of the file.
			            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
			            BufferedWriter bw = new BufferedWriter(fw);
			            bw.write("//////////////////////////");
			            bw.newLine();
			            bw.write("// Simple Job Optimizer //");
			            bw.newLine();
			            bw.write("//////////////////////////");
			            bw.newLine();
			            bw.newLine();
			            bw.write("Best solution :");
			            bw.newLine();
			            bw.newLine();
			            
			            //Printing order
			            bw.write("Order : ");
			            bw.newLine();
			            bw.newLine();
			            bw.write("[ ");
			            //Getting best order index.
						int minIndex = JobS.getMinCost(alCosts);
						int[] minOrder = alOrders.get(minIndex);
						
						//Printing
						for(int i=0; i<minOrder.length; i++){
							bw.write(minOrder[i] + " ");
						}
						bw.write("]");
						bw.newLine();
						bw.newLine();
						bw.write("Matrix :");
						bw.newLine();
						bw.newLine();
						
						//Getting best order matrix.
						int[][] tempMatrix = JobS.getOrderMatrix(minIndex, alOrders, jobInput);
						
						//Printing
						for(int i=0; i<tempMatrix[0].length; i++){
							for(int j=0; j<tempMatrix.length; j++){
									bw.write(String.valueOf(tempMatrix[j][i]));
									bw.write(" ");
							}
							bw.newLine();
						}
						
						//Getting cost
						int minCost = alCosts.get(minIndex);
						
						//Printing
						bw.newLine();
						bw.write("Cost : ");
						bw.write(String.valueOf(minCost));
						bw.newLine();
						//Getting tools list.
						ArrayList<Integer> toolsList = alTools.get(minIndex);
						bw.write("Tools to keep : ");
						for(int i=0; i<toolsList.size(); i++){
							bw.write(String.valueOf(toolsList.get(i)));
							bw.write(" ");
						}
						
						//Notes to user.
						bw.newLine();
						bw.newLine();
						bw.write("CAUTION : The tools to keep in 0's are for 0's from up to down, left to right. (So first will be first column from top to bottom, next will be second column from top to bottom,...).");
						
						//Closing writer.
			            bw.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
		}
		
	}
}
