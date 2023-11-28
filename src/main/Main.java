package main;
import javax.swing.JFrame;

/**
 * Main is the class that you run/execute in order to start the program
 *  
 * @author Vachia Thoj
 */
public class Main {

	public static void main (String args[])
	{
		//Create Window
		JFrame window = new JFrame("TicTacToe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create GamePanel object
		GamePanel gamePanel = new GamePanel();
		
		//Store Panel in content pane
		window.setContentPane(gamePanel);
		
		window.pack();
		
		//Make window not resizeable
		window.setResizable(false);
		
		//Put window in center of screen
		window.setLocationRelativeTo(null);
		
		//Make window visible
		window.setVisible(true);
	}
}
