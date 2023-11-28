package context;

import backend.*;

/**
 * Context class is the "middleman" that allows for the frontend (UI)
 * to talk to the backend
 * @author Vachia Thoj
 *
 */
public class Context 
{
	//A game
	private Game game;
	
	/**
	 * Constructor
	 */
	public Context()
	{
		this.game = new Game();
	}
	
	//Getter methods
	public int getNumPlayers() {return game.getNumPlayers();}
	public Player[] getPlayerList() {return game.getPlayerList();}
	public Player getCurrentPlayer() {return game.getCurrentPlayer();}
	public Player getWinner() {return game.getWinner();}
	public MarkType[][] getBoard() {return game.getBoard();}
	public int getNumRows() {return game.getNumRows();}
	public int getNumCols() {return game.getNumCols();}
	public int getNunMarked() {return game.getNumMarked();}
	public boolean isGameOver() {return game.isGameOver();}
	public WinningBox[] getWinningBoxes() {return game.getWinningBoxes();}
	
	
	public void addToBoard(int row, int col) {game.addToBoard(row, col);}
	public void checkForWinner() {game.checkForWinner();}
	public void nextPlayer() {game.nextPlayer();}
	public void newGame() {game = new Game();}
}
