package button;

import java.awt.Color;
import java.awt.Graphics2D;

import backend.MarkType;
import manager.ImageManager;

/**
 * BoxIcon class
 * @author Vachia Thoj
 *
 */
public class BoxIcon extends Button
{
	//Colors of BoxIcon
	private Color color1;
	private Color color2;
	private Color currentColor;
	
	//Border color of BoxIcon
	private Color borderColor;
	
	//Flag to see if BoxIcon has a border
	private boolean bordered;
	
	//The type of mark within BoxIcon 
	private MarkType markType;
	
	//Default Colors
	private static final Color DEFAULT_COLOR1 = Color.WHITE;
	private static final Color DEFAULT_COLOR2 = new Color(235, 235, 235);
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK; 
	
	/**
	 * Constructor
	 * @param x (int) The x-coordinate of BoxIcon
	 * @param y (int) The y-coordinate of BoxIcon
	 * @param width (int) The width of the BoxIcon
	 * @param height (int) The height of the BoxIcon
	 */
	public BoxIcon(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.color1 = DEFAULT_COLOR1;
		this.color2 = DEFAULT_COLOR2;
		this.currentColor = color1;
		
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.bordered = true;
		
		this.markType = MarkType.NONE;
		
		this.visible = true;
		this.disabled = false;
	}
	
	//Getter methods
	public Color getColor1() {return color1;}
	public Color getColor2() {return color2;}
	public Color getCurrentColor() {return currentColor;}
	public Color getBorderColor() {return borderColor;}
	public boolean isBordered() {return bordered;}
	public MarkType getMarkType() {return markType;}
	
	//Setter methods
	public void setCurrentColor(Color currentColor) {this.currentColor = currentColor;}
	public void setBorderColor(Color borderColor) {this.borderColor = borderColor;}
	public void setBordered(boolean b) {this.bordered = b;}
	public void setMarkType(MarkType markType) {this.markType = markType;}
	
	/**
	 * Method that updates BoxIcon
	 */
	public void update()
	{
		if(disabled == true || visible == false)
		{
			return;
		}
		
		//Check if mouse has touched BoxIcon
		checkIfMouseTouchingButton();
		
		//Check if mouse has clicked on BoxIcon
		checkIfMouseClickingButton();
		
		//Change color of LabelButton if mouse is touching BoxIcon
		if(mouseTouchingButton)
		{
			currentColor = color2;
		}
		else
		{
			currentColor = color1;
		}
	}
	
	
////////////////////////////////////////////// DRAW METHODS //////////////////////////////////////////////
	
	/**
	 * Method that draws the BoxIcon
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBoxIcon(Graphics2D g)
	{
		g.setColor(currentColor);
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * Method that draws the border
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBorder(Graphics2D g)
	{
		if(bordered)
		{
			g.setColor(borderColor);
			g.drawRect(x, y, width - 1, height - 1);
		}
	}
	
	/**
	 * Method that draws the mark
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawMark(Graphics2D g)
	{
		if(markType != MarkType.NONE)
		{
			g.drawImage(
					ImageManager.instance().getMarkImage(markType),
					x,
					y,
					null
			);
		}
	}
	
	/**
	 * Method that draws BoxIcon
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		drawBoxIcon(g);
		drawMark(g);
		drawBorder(g);
	}
}
