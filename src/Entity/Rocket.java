package Entity;
import java.awt.Color;
import java.awt.Graphics2D;

import Functions.Calculations;
import Functions.DebugFunctions;
import Functions.ParticlePhysics;
import Functions.doubleVec2D;
import Main.GamePanel;


public class Rocket extends GameObject {
	//	DEBUG
	private static boolean DEBUG = false;
	
	//	position of rockets target
	private int targetX;
	private int targetY;
	
	//	rockets launch positopn
	private  int launchX;	
	private  int launchY;	
	
	//	angles
	private doubleVec2D idealAngles;
	private double angle;			
	
	private Color color = Color.YELLOW;			//	color

	/**
	 * @param x position of target x
	 * @param y	position of target y
	 */
	public Rocket (int targetX, int targetY){
		
		// rocket targets position
		this.targetX = targetX;
		this.targetY = targetY;
		
		radius = 2;
		
		liveTime = 0.033; // 1 second  divedet  30 updates per second
		
		//		determin if rocket is flying right or left
		if(targetX > launchX) right = true; else right = false;
		
		
		//	rocket launch position
	
		launchX = Calculations.randomInt(0, GamePanel.WIDTH);
		launchY = GamePanel.HEIGHT - 10;
		
		//		rockets current positin
		x = launchX;
		y = launchY;
		
		speed = Calculations.randomDouble(100,50);
		
		idealAngles = Calculations.getAngleToHit(speed,GRAVITY, targetX, targetY, launchX, launchY);
		
		//	check if rocket can reach target by any of two angles
		if (!Double.isNaN(idealAngles.getX()) && !Double.isNaN(idealAngles.getY())){
			
			//		finding smaller shot angle to shot directly		//
			if(idealAngles.getX() <= idealAngles.getY()){
				angle = idealAngles.getX();
			}else{
				angle = idealAngles.getY();
			}	
			
			
		
		}else{
			//	sends rockets that cant reach target directly down off screen
			angle = Math.toRadians(-90);
		}
		
		if(targetX > launchX) right = true; else right = false;
	}

	public boolean update(){
		
		if(right){
		x = launchX + ParticlePhysics.projectileX(speed, liveTime, angle);
		}else{
			x = launchX - ParticlePhysics.projectileX(speed, liveTime, angle);
		}
		
		y = launchY - ParticlePhysics.projectileY(speed, liveTime, angle);
	
		
		//DebugFunctions.LOG(String.format("Current Position: %4d , %4d  |  realWorld Position %4d , %4d", (int)x, (int)y  , (int)(x-500), (int)(y+40) ));
		liveTime += 0.1;
	
		return !Calculations.isOnScreen((int)x, (int)y, radius);
	}
	
	public void draw (Graphics2D g){
		
		g.setColor(color);
		g.fillOval((int)(x - radius), (int) (y - radius), 2 * radius, 2 * radius);
		
		//		drawing target		//
		
		//		outer glow
		if(DEBUG){
		g.setColor(new Color(128,0,0));
		g.drawOval(targetX - 3*radius ,targetY - 3*radius, 6 * radius, 6 * radius);
		}
		
		
	}

	//	Return functions		//
	public int getTargetX(){ return targetX;}
	public int getTargetY(){ return targetY;}
	public int getCurentX(){ return (int) x;}
	public int getCurentY(){ return (int) y;}
	public int getInitialX(){	return launchX; }
	public int getInitialY(){	return launchY; }
	public double getSpeed(){ return speed; }
	public int getRadius(){ return radius;}
	public double getAngle(boolean degree) {
		if( degree){
			return Math.toDegrees(angle);
		}
		return angle; 
	}
	
	
	
	
}