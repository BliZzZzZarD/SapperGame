package main;

import javax.swing.JFrame;

import view.GameFrame;

/*Created by Y.Pleska 26.01.2017
 *Sapper
 *A logical game  
 */

public class Game{

	public static void main(String [] args){
		
		GameFrame window = new GameFrame("Sapper");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.pack();
		window.setVisible(true);
		
	}
	
}