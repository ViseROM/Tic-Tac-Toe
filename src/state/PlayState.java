package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import button.*;
import context.Context;
import entity.Board;
import helper.TextSize;
import main.GamePanel;
import manager.*;
import transition.*;

/**
 * PlayState class represents the "play area" of Tic Tac Toe 
 * 
 * @author Vachia Thoj
 *
 */
public class PlayState extends State
{
	//ImageManager to obtain images
	private ImageManager imageManager;
	
	//The next state to go to
	private StateType nextState;
	
	//Context to talk to the backend
	private Context context;
	
	//The Tic Tac Toe board
	private Board board;
	
	//Buttons
	private ImageButton menuButton;
	private ImageButton newGameButton;
	
	//To display Current Player
	private String currentPlayerText;
	
	//To display winner text
	private String winnerText;
	
	//To display text that there was no winner; tie/draw
	private String tieText;
	
	private BufferedImage currentMarkImage;
	
	//Transitions
	private FadeToBlack fadeToBlack;
	private VerticalSplit verticalSplit;
	private Transition currentTransition;
	
	/**
	 * Constructor
	 */
	public PlayState()
	{
		this.imageManager = ImageManager.instance();
		
		this.nextState = null;
		
		createContext();
		createBoard();
		createButtons();
		createText();
		createCurrentMarkImage();
		createTransitions();
	}
	
////////////////////////////////////////////// CREATE METHODS //////////////////////////////////////////////
	private void createContext()
	{
		this.context = new Context();
	}
	
	private void createBoard()
	{
		this.board = new Board(context);
	}
	
	private void createButtons()
	{
		BufferedImage[] buttonImages = imageManager.getButtonImages();
		
		this.menuButton = new ImageButton(buttonImages[6], buttonImages[7]);
		this.menuButton.setX(10);
		this.menuButton.setY(GamePanel.HEIGHT - (menuButton.getHeight() + 10));
		
		this.newGameButton = new ImageButton(buttonImages[8], buttonImages[9]);
		this.newGameButton.setX(GamePanel.WIDTH - (newGameButton.getWidth() + 10));
		this.newGameButton.setY(GamePanel.HEIGHT - (newGameButton.getHeight() + 10));
	}
	
	private void createText()
	{
		this.currentPlayerText = "Player " + context.getCurrentPlayer().getId() + " Turn";
		this.winnerText = "";
		this.tieText = "TIE: NO WINNER";
	}
	
	private void createCurrentMarkImage()
	{
		this.currentMarkImage = imageManager.getSmallMarkImage(context.getCurrentPlayer().getMarkType());
	}
	
	private void createTransitions()
	{
		this.fadeToBlack = new FadeToBlack(GamePanel.WIDTH, GamePanel.HEIGHT);
		this.verticalSplit = new VerticalSplit(GamePanel.WIDTH, GamePanel.HEIGHT);
		this.currentTransition = null;
	}
	
////////////////////////////////////////////// UPDATE METHODS //////////////////////////////////////////////
	
	/**
	 * Method that updates Buttons
	 */
	private void updateButtons()
	{
		menuButton.update();
		newGameButton.update();
		
		performButtonAction();
	}
	
	/**
	 * Method that performs an action if a Button has been clicked
	 */
	private void performButtonAction()
	{
		//Check if mouse is clicking on Buttons
		if(menuButton.isMouseClickingButton())
		{
			menuButton.setMouseClickingButton(false);
			
			//Run the fadeToBlack transition
			fadeToBlack.setRunning(true);
			
			currentTransition = fadeToBlack;
			
			//Indicate that the next state to go to is the MainState
			nextState = StateType.MAIN;
		}
		else if(newGameButton.isMouseClickingButton())
		{
			newGameButton.setMouseClickingButton(false);
			
			//Run the verticalSplit transition
			verticalSplit.setRunning(true);
			
			currentTransition = verticalSplit;
			
			//Indicate that the next state to go to is the PlayState
			nextState = StateType.PLAY;
		}
	}
	
	/**
	 * Method that updates the currentMarkImage
	 */
	private void updateCurrentMarkImage()
	{
		currentMarkImage = imageManager.getSmallMarkImage(context.getCurrentPlayer().getMarkType());
	}
	
	/**
	 * Method that updates the Transitions
	 */
	private void updateTransition()
	{
		if(currentTransition != null)
		{
			currentTransition.update();
		}
	}
	
	/**
	 * Method that checks if a change of state is necessary
	 */
	private void changeState()
	{
		if(currentTransition != null &&
		   currentTransition.isDone() &&
		   nextState != null)
		{
			MouseManager.instance().clearPressedPoint();
			MouseManager.instance().clearReleasedPoint();
			
			//Go to the next state
			StateManager.instance().nextState(nextState);
		}
	}
	
	/**
	 * Method that updates the PlayState
	 */
	public void update()
	{	
		updateTransition();
		
		//Do not do certain updates if the fadeToBlack transition is happening
		if(currentTransition != null && currentTransition.isRunning())
		{
			return;
		}
		
		//Checks if a change of State is necessary
		changeState();
		
		//If game is not over
		if(context.isGameOver() == false)
		{
			//Update Tic Tac Toe board; 
			//Checking to see if a Player has clicked on the board
			board.update();
			
			//Get current player as a String
			currentPlayerText = "Player " + context.getCurrentPlayer().getId() + " Turn";
		}
		else
		{
			//If a winner has been decided
			if(context.getWinner() != null)
			{
				//Obtain the winner as a String
				winnerText = "PLAYER " + context.getWinner().getId() + " WINS!!!";
				
				//Highlight on the board the winning solution
				board.highlight();
			}
		}
		
		updateButtons();
		updateCurrentMarkImage();
	}
////////////////////////////////////////////// DRAW METHODS //////////////////////////////////////////////
	
	/**
	 * Method that draws the background
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBackground(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	/**
	 * Method that draws the Tic Tac Toe board
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBoard(Graphics2D g)
	{
		board.draw(g);
	}
	
	/**
	 * Method that draws currentPlayerText
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawCurrentPlayerText(Graphics2D g)
	{
		//Draw current player text
		g.setColor(Color.BLACK);
		g.setFont(new Font ("Courier New", Font.BOLD, 32));
		int textLength = TextSize.getTextWidth(currentPlayerText, g);
		g.drawString(currentPlayerText, ((GamePanel.WIDTH / 2) - (textLength / 2)), 40);
	}
	
	/**
	 * Method that draws the winnerText
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawWinnerText(Graphics2D g)
	{
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD, 32));
		int textLength = TextSize.getTextWidth(winnerText, g);
		g.drawString(winnerText, ((GamePanel.WIDTH / 2) - (textLength / 2)), 40);
	}
	
	/**
	 * Method that draws the noWinnerText
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawNoWinnerText(Graphics2D g)
	{
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD, 32));
		int textLength = TextSize.getTextWidth(tieText, g);
		g.drawString(tieText, ((GamePanel.WIDTH / 2) - (textLength / 2)), 40);
	}
	
	/**
	 * Method that draws Buttons
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawButtons(Graphics2D g)
	{
		menuButton.draw(g);
		newGameButton.draw(g);
	}
	
	/**
	 * Method that draws the currentMarkImage
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawCurrentMarkImage(Graphics2D g)
	{
		if(currentMarkImage != null &&  context.isGameOver() == false)
		{
			g.drawImage(
					currentMarkImage,
					(GamePanel.WIDTH / 2) - (currentMarkImage.getWidth() / 2),
					64,
					null
			);
		}
	}
	
	/**
	 * Method that draws the transitions
	 * @param g
	 */
	private void drawTransition(Graphics2D g)
	{
		if(currentTransition != null)
		{
			currentTransition.draw(g);
		}
	}
	
	/**
	 * Method that draws the PlayState
	 * @param g (Graphics2D) The Graphics2D object to be drawn
	 */
	public void draw(Graphics2D g)
	{
		drawBackground(g);
		drawBoard(g);
		drawButtons(g);
		drawCurrentMarkImage(g);
		
		//If game is not over
		if(context.isGameOver() == false)
		{
			//Draw the text to indicate who the current Player is
			drawCurrentPlayerText(g);
		}
		else
		{
			//If a winner has been decided
			if(context.getWinner() != null)
			{
				//Draw the text to indicate which Player won
				drawWinnerText(g);
			}
			else //A tie/draw occurred
			{
				//Draw the text to indicate that there was no winner; it was a tie/draw
				drawNoWinnerText(g);
			}
		}
		
		drawTransition(g);
	}
}
