package GameState;
import java.util.ArrayList;

/**
 * Games state manager
 * handles all the switches between game states
 * parses functions and values from game panel
 * to active game state
 */
public class GameStateManeger {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int PLAYSTATE = 0;

	public GameStateManeger(){
		gameStates = new ArrayList<GameState>();
		gameStates.add(new PlayState(this));
		currentState = PLAYSTATE;
	}
	
	public void setCurrentState(int i){
		currentState = i;
	}
	
	public int getCurrentState(){ return currentState; }
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	
	public void mousePressed(int k, int x, int y) {
		gameStates.get(currentState).mouseClicked(k, x, y);
		
	}
}