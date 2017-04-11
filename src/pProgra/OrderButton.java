package pProgra;

import java.awt.Color;

import javax.swing.JButton;

public class OrderButton extends JButton{
	private int id;
	
	public OrderButton(String message, int id){
		super(message);
		this.id = id;
		this.setBackground(Color.WHITE);
        //this.setFocusPainted(false);
	}
	
	public int getID(){
		return this.id;
	}

}
