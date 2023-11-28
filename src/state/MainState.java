package state;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import button.ImageButton;
import helper.TextSize;
import main.GamePanel;
import manager.*;
import transition.*;

/**
 * MainState represents the main menu
 * This is what you will first see when you
 * start up the program
 * @author Vachia Thoj
 *
 */
public class MainState extends State
{
	//ImageManager
	private ImageManager imageManager;
	
	//The next state to go to
	private StateType nextState;
	
	//Title
	private BufferedImage title;
	
	//Buttons
	private ImageButton twoPlayerButton;
	private ImageButton quitButton;
	
	//Strings
	private String authorText;
	private String versionText;
	
	//Transition
	private FadeToBlack fadeToBlack;
	
	/**
	 * Constructor
	 */
	public MainState()
	{
		this.imageManager = ImageManager.instance();
		
		this.nextState = null;
		
		createTitle();
		createButtons();
		createStrings();
		createTransitions();
		
	}
	
////////////////////////////////////////////// CREATE METHODS //////////////////////////////////////////////
	
	private void createTitle()
	{
		this.title = imageManager.getTitleImage();
	}
	
	private void createButtons()
	{
		BufferedImage[] buttonImages = imageManager.getButtonImages();
		
		twoPlayerButton = new ImageButton(buttonImages[2], buttonImages[3]);
		twoPlayerButton.setX((GamePanel.WIDTH / 2) - (twoPlayerButton.getWidth() / 2));
		twoPlayerButton.setY((int) (GamePanel.HEIGHT / 2.5));
		
		quitButton = new ImageButton(buttonImages[4], buttonImages[5]);
		quitButton.setX((GamePanel.WIDTH / 2) - (twoPlayerButton.getWidth() / 2));
		quitButton.setY(twoPlayerButton.getY() + twoPlayerButton.getHeight() + 32);
	}
	
	private void createStrings()
	{
		this.authorText = "VISE";
		this.versionText = "Ver. 1.0";
	}
	
	private void createTransitions()
	{
		this.fadeToBlack = new FadeToBlack(GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
////////////////////////////////////////////// UPDATE METHODS //////////////////////////////////////////////
	/**
	 * Method that updates Buttons
	 */
	private void updateButtons()
	{
		twoPlayerButton.update();
		quitButton.update();
		
		//Check if an action needs to be performed
		performButtonAction();
	}
	
	/**
	 * Method that performs an action if a Button has been clicked
	 */
	private void performButtonAction()
	{
		//Check if mouse is clicking on Buttons
		if(twoPlayerButton.isMouseClickingButton())
		{
			twoPlayerButton.setMouseClickingButton(false);
			
			//Run the fadeToBlack transition
			fadeToBlack.setRunning(true);
			
			//Indicate that the next state to go to is the PlayState
			nextState = StateType.PLAY;
		}
		else if(quitButton.isMouseClickingButton())
		{
			quitButton.setMouseClickingButton(false);
			
			//Exit program
			System.exit(0);
		}
	}
	
	/**
	 * Method that updates the Transitions
	 */
	private void updateTransition()
	{
		fadeToBlack.update();
	}
	
	/**
	 * Method that checks if a change of state is necessary
	 */
	private void changeState()
	{
		if(fadeToBlack.isDone() && nextState != null)
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
		
		if(fadeToBlack.isRunning())
		{
			return;
		}
		
		//Checks if a change of State is necessary
		changeState();
		
		updateButtons();
	}
	
////////////////////////////////////////////// DRAW METHODS //////////////////////////////////////////////
	
	/**
	 * Method that draws the background
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBackground(Graphics2D g)
	{
		g.setColor(new Color(250, 250, 250));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	/**
	 * Method that draws the title
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawTitle(Graphics2D g)
	{
		g.drawImage(title, (GamePanel.WIDTH / 2) - (title.getWidth() / 2), 50, null);
	}
	
	/**
	 * Method that draws Buttons
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawButtons(Graphics2D g)
	{
		twoPlayerButton.draw(g);
		quitButton.draw(g);
	}
	
	/**
	 * Method that draws author String
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawAuthorText(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
		g.drawString(authorText, 4, GamePanel.HEIGHT - 4);
	}
	
	/**
	 * Method that draws version String
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawVersionText(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
		int textWidth = TextSize.getTextWidth(versionText, g); 
		g.drawString(versionText, GamePanel.WIDTH - textWidth, GamePanel.HEIGHT - 4);
	}
	
	/**
	 * Method that draws the Transitions
	 * @param g (Graphics2D g) The Graphics2D object to be drawn on
	 */
	private void drawTransition(Graphics2D g)
	{
		fadeToBlack.draw(g);
	}
	
	/**
	 * Method that draws the MainState
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		drawBackground(g);
		drawTitle(g);
		drawButtons(g);
		drawAuthorText(g);
		drawVersionText(g);
		drawTransition(g);
	}
}
