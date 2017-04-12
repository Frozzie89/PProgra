package pProgra;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
// This class is used to build the application GUI. 
public class Gui extends JFrame implements ActionListener{ // Implements ActionListener so we can use it later in the class.
	// Instancing those variables as class variables so we can use them in all class.
	private ArrayList<int[]> alOrders = new ArrayList<int[]>();
	private JButton search;
	private JButton showOrders;
	private JButton optimizotron;
	private JButton back;
	private JLabel path = new JLabel("Chemin vers le fichier input.");
	private JobS jobInput = ReadJobS.inputJobMatrix(); 
	
	//Basic GUI settings. (title, home panel, ...)
	public Gui(){
		
		this.setTitle("Optimizotron k2000");
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
		this.setResizable(false);
		
		return accueil;
	}
	
	private JPanel showOrders(){ //Show orders panel, used to show different orders.
		JPanel container = new JPanel(); //Creating panels and setting layouts.
		container.setLayout(new BorderLayout());
		JPanel buttonContainer = new JPanel();
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
		
		back = new JButton("Retour..."); //Creating button back.
		back.addActionListener(this); //Adding action listener.
		container.add(back, BorderLayout.SOUTH); //Placing back at the bottom.
		
		this.setSize(200,400); //Change frame size.
		return container;
	}
	
	private JPanel showDetails(int id){ //Show details panel.
		
		JPanel container = new JPanel(); //Creating panel.
		container.setLayout(new BorderLayout());
		JPanel details = new JPanel(); //Creating panel into the first panel.
		JPanel result = new JPanel(); 
		
		
		int height = ReadJobS.inputToolN()+1;
		int width = ReadJobS.inputJobN();
//		System.out.println(height);
//		System.out.println(width);
		details.setLayout(new GridLayout(0, width)); //Setting second panel layout.
		
		int[] order = alOrders.get(id); //Getting the order
		for(int j = 0; j < order.length; j++){ // Adding the order to the GridLayout.
			String newStr = " "+order[j];
			JLabel temp = new JLabel(newStr);
			details.add(temp);
		}
		//Getting result.
		JLabel stringCostLabel = new JLabel("Coût : ");
		//Getting cost.
		int cost = JobS.JobSCostExt(jobInput, alOrders.get(id));  
		String newStr2 = " " + cost;
		JLabel costLabel = new JLabel(newStr2);
		costLabel.setForeground(Color.RED);
		
		container.add(details, BorderLayout.CENTER); //Adding the details to the container.
		
		result.add(stringCostLabel);
		result.add(costLabel);
		container.add(result, BorderLayout.SOUTH); //Adding result to the bottom.
		
		this.setSize(200,150); // Setting frame size.
		return container;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) { //ActionPerformer, this will tell buttons what to perform when they are pressed.
		
		//First I get the object pressed (see below) then I use multiple If statements to tell the software what to do when a certain button is pressed.
		
		Object source = e.getSource();
		
		if(source == search){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this.getContentPane());
			if (result == JFileChooser.APPROVE_OPTION) {
				
				File selectedFile = fileChooser.getSelectedFile();
				path.setText(selectedFile.getPath());
				System.out.println(selectedFile.getPath());
				ReadJobS.setInputName(selectedFile.getPath());
				
				this.setContentPane(accueil());
				this.setVisible(true);
				
			}
		}
		else if(source == showOrders){
			
			int jobN = ReadJobS.inputJobN();
			
			int[] input = new int[jobN];
			for (int i=0; i<jobN; i++){
				input[i]=i+1;
			}
			
			Permutations.jobSequences(0, input, alOrders);
			
			this.setContentPane(showOrders());
			this.setResizable(true);
			this.setVisible(true);
			
		}
		else if(source == back){
			this.setContentPane(accueil());
			this.setVisible(true);
			alOrders = new ArrayList<int[]>();
		}
		else if(source.getClass() == OrderButton.class){
			OrderButton temp = (OrderButton)source;
			this.setContentPane(showDetails(temp.getID()));
			this.setVisible(true);
			
		}
		
	}
}
