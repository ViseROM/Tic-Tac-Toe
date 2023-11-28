package manager;

import java.awt.Graphics2D;

import state.*;

/**
 * StateManager class manages the flow of states
 * (State Design Pattern)
 * @author Vachia Thoj
 *
 */
public class StateManager 
{
	//For singleton
	private static StateManager stateManager;
	
	//The current state
	private State currentState;
	
	/**
	 * Constructor (private)
	 */
	private StateManager()
	{
		currentState = new MainState();
	}
	
	/**
	 * Method to be called to obtain StateManager object (Singleton)
	 * @return StateManager object
	 */
	public static StateManager instance()
	{
		if(stateManager == null)
		{
			stateManager = new StateManager();
		}
		
		return stateManager;
	}
	
	/**
	 * Method that goes to the next state
	 * @param nextState (StateType) The next state to go to
	 */
	public void nextState(StateType stateType)
	{
		switch(stateType)
		{
			case MAIN:
				currentState = new MainState();
				break;
			case PLAY:
				currentState = new PlayState();
				break;
			default:
				break;
		}
	}
	
	/**
	 * Method that updates the current state
	 */
	public void update()
	{
		currentState.update();
	}
	
	/**
	 * Method that draws the current state
	 * 
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		currentState.draw(g);
	}
}
