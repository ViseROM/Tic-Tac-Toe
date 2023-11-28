package backend;

/**
 * WinningBox class
 * @author Vachia Thoj
 *
 */
public class WinningBox 
{
	//Row and column
	private int row;
	private int col;
	
	/**
	 * Constructor
	 * @param row (int) row
	 * @param col (int) column
	 */
	public WinningBox(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	//Getter methods
	public int getRow() {return row;}
	public int getCol() {return col;}
	
	//Setter methods
	public void setRow(int row) {this.row = row;}
	public void setCol(int col) {this.col = col;}
}
