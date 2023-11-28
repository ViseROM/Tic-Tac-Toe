package backend;

/**
 * Game class "plays" the Tic Tac Toe game; the possible
 * actions that can happen within the game
 * @author Vachia Thoj
 *
 */
public class Game
{	
	//The list of Players
	private Player[] playerList;

	//The number of Players
	private int numPlayers;
	
	//The current Player
	private Player currentPlayer;
	
	//The winning Player
	private Player winner;
	
	//The board
	private MarkType [][] board;
	
	//The number of rows and columns for the board
	private int numRows;
	private int numCols;
	
	//The number of boxes within the board that has been marked
	private int numMarked;
	
	//Flag to see if the game is over
	private boolean gameOver;
	
	//BoardEvaluator
	private BoardEvaluator boardEvaluator;
	
	//Default values
	private static final int MAX_PLAYERS = 2;
	private static final int DEFAULT_NUM_ROWS =  3;
	private static final int DEFAULT_NUM_COLS = 3;
	
	/**
	 * Constructor
	 */
	public Game()
	{
		this.numPlayers = MAX_PLAYERS;
		this.numRows = DEFAULT_NUM_ROWS;
		this.numCols = DEFAULT_NUM_COLS;
		
		createPlayers();
		createBoard();
		
		this.numMarked = 0;
		
		this.winner = null;
		this.gameOver = false;
		
		createBoardEvaluator();
	}
	
	private void createPlayers()
	{
		this.playerList = new Player[numPlayers];
		this.playerList[0] = new Player(1, MarkType.X);
		this.playerList[1] = new Player(2, MarkType.O);
		
		this.currentPlayer = playerList[0];
	}
	
	private void createBoard()
	{
		this.board = new MarkType[numRows][numCols];
		
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				board[i][j] = MarkType.NONE;
			}
		}
	}
	
	private void createBoardEvaluator()
	{
		this.boardEvaluator = new BoardEvaluator(board, numRows, numCols, currentPlayer);
	}
	
	//Getter methods
	public int getNumPlayers() {return numPlayers;}
	public Player[] getPlayerList() {return playerList;}
	public Player getCurrentPlayer() {return currentPlayer;}
	public Player getWinner() {return winner;}
	public MarkType[][] getBoard() {return board;}
	public int getNumRows() {return numRows;}
	public int getNumCols() {return numCols;}
	public int getNumMarked() {return numMarked;}
	public boolean isGameOver() {return gameOver;}
	public WinningBox[] getWinningBoxes() {return boardEvaluator.getWinningBoxes();}
	
	//Setter methods
	public void setGameOver(boolean b) {gameOver = b;}
	
	/**
	 * Method that marks a box on the board with an X or O
	 * Will not mark the box if there is already an X or O in that box
	 * @param row (int) The row of the box on the board
	 * @param col (int) The col of the box of the board
	 */
	public void addToBoard(int row, int col)
	{		
		//If the box on the board has not been marked and the game is not over...
		if(board[row][col] == MarkType.NONE && gameOver == false)
		{
			//Mark the board; an X or O depending on who the Player is
			board[row][col] = currentPlayer.getMarkType();
			
			++numMarked;
		}
	}
	
	/**
	 * Method that goes to the next Player's turn
	 */
	public void nextPlayer()
	{
		//Do nothing if the game is already over
		if(gameOver == true)
		{
			return;
		}
		
		//Change who's turn it is
		if(currentPlayer == playerList[0])
		{
			currentPlayer = playerList[1];
		}
		else if(currentPlayer == playerList[1])
		{
			currentPlayer = playerList[0];
		}
	}
	
	/**
	 * Method that checks if a Player has won
	 */
	public void checkForWinner()
	{
		boardEvaluator.setCurrentPlayer(currentPlayer);
		
		//Check to see if a Player has won
		if(boardEvaluator.horizontalCheck() || 
		   boardEvaluator.verticalCheck() ||
		   boardEvaluator.diagonalCheck())
		{
			//Set winner
			winner = currentPlayer;
			
			//Game is over
			gameOver = true;
			
			return;
		}
		
		//Tie game
		if(numMarked == (numRows * numCols))
		{
			gameOver = true;
		}
	}
}