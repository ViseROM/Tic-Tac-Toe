package backend;

/**
 * BoardEvaluator class checks if a Tic Tac Toe has a winning situation
 * @author Vachia Thoj
 *
 */
public class BoardEvaluator 
{
	//Tic Tac Toe board
	private MarkType[][] board;
	
	//The number of rows and columns
	private int numRows;
	private int numCols;
	
	//The current Player
	private Player currentPlayer; 
	
	private WinningBox[] winningBoxes;
	
	/**Constructor
	 * @param board (MarkType) The Tic Tac Toe board
	 * @param numRows (int) The number of rows on the board
	 * @param numCols (int) The numer of columns on the board
	 * @param currentPlayer (Player) The current Player
	 */
	public BoardEvaluator(MarkType[][] board, int numRows, int numCols, Player currentPlayer)
	{
		this.board = board;
		this.numRows = numRows;
		this.numCols = numCols;
		this.currentPlayer = currentPlayer;
	
		this.winningBoxes = new WinningBox[3];
	}
	
	//Getter methods
	public MarkType[][] getBoard() {return board;}
	public int getNumRows() {return numRows;}
	public int getNumCols() {return numCols;}
	public Player getCurrentPlayer() {return currentPlayer;}
	public WinningBox[] getWinningBoxes() {return winningBoxes;}
	
	//Setter methods
	public void setBoard(MarkType[][] board) {this.board = board;}
	public void setNumRows(int numRows) {this.numRows = numRows;}
	public void setNumCols(int numCols) {this.numCols = numCols;}
	public void setCurrentPlayer(Player currentPlayer) {this.currentPlayer = currentPlayer;}
	
	/**
	 * Method that checks if the board has a horizontal Tic Tac Toe
	 * @return true if a horizontal Tic Tac Toe occurred, otherwise false
	 */
	public boolean horizontalCheck()
	{
		for(int i = 0; i < numRows; i++)
		{
			int counter = 0;
			for(int j = 0; j < numCols; j++)
			{
				if(board[i][j] == currentPlayer.getMarkType())
				{
					++counter;
				}
			}
			
			if(counter == numCols)
			{
				winningBoxes[0] = new WinningBox(i, 0);
				winningBoxes[1] = new WinningBox(i, 1);
				winningBoxes[2] = new WinningBox(i, 2);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Method that checks if the board has a vertical Tic Tac Toe
	 * @return true if a vertical Tic Tac Toe occurred, otherwise false
	 */
	public boolean verticalCheck()
	{
		
		for(int i = 0; i < numCols; i++)
		{
			int counter = 0;
			for(int j = 0; j < numRows; j++)
			{
				if(board[j][i] == currentPlayer.getMarkType())
				{
					++counter;
				}
			}
			
			if(counter == numRows)
			{
				winningBoxes[0] = new WinningBox(0, i);
				winningBoxes[1] = new WinningBox(1, i);
				winningBoxes[2] = new WinningBox(2, i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that checks if the board has a diagonal Tic Tac Toe
	 * @return true if a diagonal Tic Tac Toe occurred, otherwise false
	 */
	public boolean diagonalCheck()
	{
		if(board[0][0] == currentPlayer.getMarkType() &&
		   board[1][1] == currentPlayer.getMarkType() &&
		   board[2][2] == currentPlayer.getMarkType())
		{
			winningBoxes[0] = new WinningBox(0, 0);
			winningBoxes[1] = new WinningBox(1, 1);
			winningBoxes[2] = new WinningBox(2, 2);
			return true;
		}
		else if(board[0][2] == currentPlayer.getMarkType() &&
				board[1][1] == currentPlayer.getMarkType() &&
				board[2][0] == currentPlayer.getMarkType())
		{
			winningBoxes[0] = new WinningBox(0, 2);
			winningBoxes[1] = new WinningBox(1, 1);
			winningBoxes[2] = new WinningBox(2, 0);
			return true;
		}
		
		return false;
	}
}
