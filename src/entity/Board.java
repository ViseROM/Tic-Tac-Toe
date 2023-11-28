package entity;

import java.awt.Graphics2D;
import java.awt.Color;

import backend.MarkType;
import backend.WinningBox;
import button.BoxIcon;
import context.Context;
import main.GamePanel;

/**
 * Board class represent a Tic Tac Toe board
 * @author Vachia Thoj
 *
 */
public class Board extends Entity
{	
	//Context
	private Context context;
	
	//BoxIcons within the Board
	private BoxIcon [][] boxes;
	
	//Number of rows and columns within the Board
	private int numRows;
	private int numCols;	
	
	//Width and height of one BoxIcon within the Board
	private int boxWidth;
	private int boxHeight;
	
	//Default values
	private static final int DEFAULT_NUM_ROWS = 3;
	private static final int DEFAULT_NUM_COLS = 3;
	private static final int DEFAULT_BOX_WIDTH = 150;
	private static final int DEFAULT_BOX_HEIGHT = 150;
	
	/**
	 * Constructor
	 * @param context
	 */
	public Board(Context context)
	{
		this.context = context;
		
		this.numRows = DEFAULT_NUM_ROWS;
		this.numCols = DEFAULT_NUM_COLS;
		this.boxWidth = DEFAULT_BOX_WIDTH;
		this.boxHeight = DEFAULT_BOX_HEIGHT;
		
		this.width = numCols * boxWidth;
		this.height = numRows * boxHeight;
		
		this.x = (GamePanel.WIDTH / 2) - (width / 2);
		this.y = (GamePanel.HEIGHT / 2) - (height /2);
	
		createBoxes();
	}
	
	private void createBoxes()
	{
		this.boxes = new BoxIcon[numRows][numCols];
		
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				boxes[i][j] = new BoxIcon(
						(x + (boxWidth * i)),
						(y + (boxHeight * j)),
						boxWidth,
						boxHeight
				);
			}
		}
	}
	
	/**
	 * Method that updates the boxes on the Board
	 */
	private void updateBoxes()
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				//Update the boxes
				boxes[i][j].update();
				
				//Check if a box on the Board has been clicked on by a Player
				if(boxes[i][j].isMouseClickingButton())
				{
					//Set that box's mouseclicking flag to false
					boxes[i][j].setMouseClickingButton(false);
					
					boxes[i][j].setCurrentColor(boxes[i][j].getColor1());
					
					//Disable that box; cannot be allowed to change once it has been marked
					boxes[i][j].setDisabled(true);
					
					//Add Player's choice to board
					context.addToBoard(i, j);
					
					if(boxes[i][j].getMarkType() == MarkType.NONE)
					{
						//Mark the board
						boxes[i][j].setMarkType(context.getCurrentPlayer().getMarkType());
					}
					
					//Check if player has won
					context.checkForWinner();
					
					//Go to next player
					context.nextPlayer();
					
					return;
				}
			}
		}
	}
	
	/**
	 * Method that "highlights" the boxes on the Board that is the winning solution
	 */
	public void highlight()
	{		
		WinningBox[] winningBoxes = context.getWinningBoxes();
		
		for(int i = 0; i < winningBoxes.length; i++)
		{
			boxes[winningBoxes[i].getRow()][winningBoxes[i].getCol()].setCurrentColor(new Color(255, 255, 0, 100));
		}
	}
	
	/**
	 * Method that updates the Board
	 */
	public void update()
	{
		//Update boxes
		updateBoxes();
	}
	
	/**
	 * Method that draws the boxes
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBoxes(Graphics2D g)
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				boxes[i][j].draw(g);
			}
		}
	}
	
	/**
	 * Method that draws the Board
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		drawBoxes(g);
	}
}
