package Entity;

import GameState.GameStateManeger;

public abstract class GameObject {
	
	//	position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//	is object moving to right
	protected boolean right;
	
	//	dimensions
	protected int radius;
	
	//	movement
	protected double speed;
	
	protected double liveTime;
	
	protected static double GRAVITY = 9.8;
	
	protected GameStateManeger gsm;
	
	public abstract boolean update();
	public abstract void draw(java.awt.Graphics2D g);
//	public abstract void mouseClicked(int k, int x, int y);
	
}
