package pProgra;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Gui extends JFrame implements ActionListener{
	private ArrayList<int[]> alOrders = new ArrayList<int[]>();
	private JButton search;
	private JButton showOrders;
	private JButton optimizotron;
	private JButton back;
	private JLabel path = new JLabel("Chemin vers le fichier input.");
	private JobS jobInput = ReadJobS.inputJobMatrix(); 
	
	public Gui(){
		
		this.setTitle("Optimizotron k2000");
		this.setContentPane(accueil());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private JPanel accueil(){
		JPanel accueil = new JPanel();
		JPanel topContainer = new JPanel();
		JPanel bottomContainer = new JPanel();
		
		accueil.setLayout(new GridLayout(2,1));
		
		search = new JButton("Parcourir...");
		search.addActionListener((ActionListener) this);
		
		showOrders = new JButton("Afficher les combinaisons possibles");
		showOrders.addActionListener((ActionListener) this);
		
		optimizotron = new JButton("Optimiser la séquence");
		optimizotron.addActionListener((ActionListener) this);
		
		topContainer.add(path);
		topContainer.add(search);
		
		bottomContainer.add(showOrders);
		bottomContainer.add(optimizotron);
		
		accueil.add(topContainer);
		accueil.add(bottomContainer);
		
		this.setSize(500,100);
		this.setResizable(false);
		
		return accueil;
	}
	
	private JPanel showOrders(){
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new GridLayout(alOrders.size(), 1));
		
		for(int i = 0; i < alOrders.size(); i++){
			
			String msg = "[ ";
			int[] order = alOrders.get(i);
			for(int j = 0; j < order.length; j++){
				msg = msg + order[j] + " ";
			}
			msg = msg + "]";
			OrderButton temp = new OrderButton(msg, i);
			buttonContainer.add(temp);
			temp.addActionListener(this);
			
		}
		JScrollPane scroller = new JScrollPane(buttonContainer);
		container.add(scroller, BorderLayout.CENTER);
		
		back = new JButton("Retour...");
		back.addActionListener(this);
		container.add(back, BorderLayout.SOUTH);
		
		this.setSize(200,400);
		return container;
	}
	
	private JPanel showDetails(int id){
		
		JPanel container = new JPanel();
		JPanel details = new JPanel();
		
		int height = ReadJobS.inputToolN()+1;
		int width = ReadJobS.inputJobN();
		System.out.println(height);
		System.out.println(width);
		details.setLayout(new GridLayout(0, width));
		
		int[] order = alOrders.get(id);
		for(int j = 0; j < order.length; j++){
			String newStr = ""+order[j];
			JLabel temp = new JLabel(newStr);
			details.add(temp);
		}
		
		
		
		container.add(details);
		
		return container;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
