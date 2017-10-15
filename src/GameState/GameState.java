package GameState;

/**
 * Abstract for game states to inherit from
 * contains all MUST HAVE functions
 * that games states must inherit
 */
public abstract class GameState {
	
	protected GameStateManeger gsm;
	
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void mouseClicked(int k, int x, int y);
	

}
