package pProgra;

import java.awt.Color;

import javax.swing.JButton;

public class OrderButton extends JButton{
	private int id;
	
	//Id will be used to identify the button (this button is used to show all orders so we need to identify them).
	public OrderButton(String message, int id){
		super(message);
		this.id = id;
		this.setBackground(Color.WHITE);
	}
	
	public int getID(){
		return this.id;
	}

}
