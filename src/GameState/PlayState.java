package GameState;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

import Entity.Particle;
import Entity.Rocket;
import Main.GamePanel;
import Functions.Calculations;
import Functions.DebugFunctions;

/**
 * PlayState class 
 * handles everything 
 * that happens in play state
 */
public class PlayState extends GameState{
	
	//	DEBUG
	public static boolean DEBUG = false;
	
	//	variables
	public static Color BGCOLOR= new Color(20,20,20);
	public static ArrayList<Rocket> rockets;	//	currently exsisting shots array
	public static ArrayList<Particle> particles;

	
	//	constructor
	public PlayState(GameStateManeger gsm){	//	inits gsm and shot array
		this.gsm = gsm ;
		rockets = new ArrayList<Rocket>();	
		particles = new ArrayList<Particle>();
	}
	
	/**
	 * @see GameState#update()
	 * looks thru shoots array, calls update function
	 * of each individual shot,
	 * updates shots position and
	 * if shot is no longer actual(visible,reached target)
	 * deletes it
	 */
	public void update(){	
		//if(DEBUG) DebugFunctions.LOG("Particle array size: " + particles.size());
		
		//	updates rockets
		for(int i = 0; i < rockets.size(); i++){
			//		if shot is dead		//
			boolean remove = rockets.get(i).update();
			if(remove){
				if(DEBUG) DebugFunctions.LOG("ROCKET OFF SCREEN");
				rockets.remove(i);
				
			}			
			//check if hit target
			if(!remove){
				if( rocketHitTarget(rockets.get(i))){
					if(DEBUG) DebugFunctions.LOG("ROCKET HIT TARGET");
					rocketExplode(rockets.get(i));
					rockets.remove(i);
				}	
			}
		}
		
		//	updates particles
		for(int i = 0; i < particles.size(); i++){
			//		if shot is dead		//
			boolean remove = particles.get(i).update();
			if(remove){
				if(DEBUG) DebugFunctions.LOG("Particle OFF SCREEN");
				particles.remove(i);
				
			}	
		}
	}
	
	
	private void rocketExplode(Rocket rocket) {
		
		// position of explosion
		int x = rocket.getCurentX();
		int y = rocket.getCurentY();
		double rocketSpeed = rocket.getSpeed();
		double rocketAngle = rocket.getAngle(false);
		
		for(int i = 0; i < 360 - 45; i += 45){
			int particleCountPerSector = Calculations.randomInt(4, 5);
			for(int j = 0; j < particleCountPerSector; j++ ){
				double particleAngle = Calculations.randomDouble(0, 45);
				particles.add(new Particle(x, y, rocketSpeed, rocketAngle, particleAngle + (i * 45)));
			}
		}
	}

	/**
	 * detect if colision of shot and target happened
	 * @param shot
	 * @return boolean if shot succesfuly hit target or not
	 */
	private boolean rocketHitTarget(Rocket shot) {
		int shotR = shot.getRadius();
		int targetR = 10;
		int shotX = shot.getCurentX();
		int shotY = shot.getCurentY();
		int targetX = shot.getTargetX();
		int targetY = shot.getTargetY();
		
		return Calculations.colision(shotX, shotY, targetX, targetY, shotR, targetR);
	}
	

	/**
	 * renders graphics, background
	 * and for each shot calls its
	 * render function
	 */
	public void draw(Graphics2D g){
		g.setColor(BGCOLOR);	//	background color 
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);		//	background filling
		
		//		debug log for shots		//
		if( DEBUG ){
			int shotCount = -1;
			shotCount = getRocketCount();
			DebugFunctions.DisplayShotArrayDebugLog(g, shotCount, rockets);
			g.setColor(BGCOLOR);
		}
		
		for(int i = 0; i < rockets.size(); i++){		//	call of each rocket render func.
			rockets.get(i).draw(g);
		}
		
		for(int i = 0; i < particles.size(); i++){		//	call of each particle render func.
			particles.get(i).draw(g);
		}
	}
	/**
	 * 
	 * @return Int Number of active rockets
	 */
	private int getRocketCount() {
		if ( rockets.size() > 0){
			return rockets.size();
		}else{
			return 0;
		}
	}

	//		mouse click		//
	public void mouseClicked(int k, int x, int y) {
		if (k == MouseEvent.BUTTON1) {
			//createShot(x, y);
			//particles.add(new Particle(x, y, 50, 60, Calculations.randomInt(-180, 360)));
			//particles.add(new Particle(x, y, 50, 60, -60));
			rockets.add(new Rocket(x,y));
		}
	}
}