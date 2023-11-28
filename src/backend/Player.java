package backend;

/**
 * Player class represents a Player
 * @author Vachia Thoj
 *
 */
public class Player 
{
	//Player's Id
	private int id;
	
	//The MarkType the Player has
	private MarkType markType;
	
	//Number of times the Player has won
	private int wins;
	
	//Default values
	private static final int MIN_WINS = 0;;
	private static final int MAX_WINS = 999;
	
	/**
	 * Constructor
	 * @param id (int) The Id of the Player
	 * @param markType (MarkType) The type of mark the Player has
	 */
	public Player(int id, MarkType markType)
	{
		this.id = id;
		this.markType = markType;
		this.wins = MIN_WINS;
	}
	
	//Getter methods
	public int getId() {return id;}
	public int getWins() {return wins;}
	public MarkType getMarkType() {return markType;}
	
	//Setter methods
	public void setId(int id) {this.id = id;}
	public void setMarkType(MarkType markType) {this.markType = markType;}
	public void setWins(int wins) {this.wins = wins;}
	
	/**
	 * Method that increases the number of wins the Player has
	 * @param amount (int) The amount of wins to increase
	 */
	public void increaseWins(int amount)
	{
		wins += amount;
		if(wins > MAX_WINS)
		{
			wins = MAX_WINS;
		}
	}
}
