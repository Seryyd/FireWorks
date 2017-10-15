package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Functions.Calculations;
import Functions.DebugFunctions;
import Functions.ParticlePhysics;

public class Particle extends GameObject {
	
	//	physical forces
	private double momentumScale = 0.5;		//	how much speed particle caries from rocket
	private double momentumDecrase = 10;	//	how fast particle looses rokcet momentum
	private double momentumSpeed;			//	momentum speed
	private double RocketsAngle;			//	angle of momentum
	private double explosionX;
	private double explosionY;
	
	private int colorRed = 255;
	private Color particleColor = Color.red;
	
	private double angle;
	
	private boolean DEBUG = false;
	
	private BufferedImage image;
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param rocketsSpeed
	 * @param rocketsAngle
	 * @param angle
	 */
	public Particle(int x, int y, double rocketsSpeed, double rocketsAngle, double angle){
		
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/graphics/particle_sprite.gif"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//appereance
		radius = 1;
		
		// position
		this.x = x;
		this.y = y;
		
		//	spawn position
		explosionX = x;
		explosionY = y;
		
		//	explosion vector angle speed
		this.angle = Math.toRadians(angle);
		speed = Calculations.randomDouble(10, 25);
		
		// rockets momentum vector
		this.RocketsAngle = rocketsAngle;
		momentumSpeed = rocketsSpeed;
		
	
		if(DEBUG) DebugFunctions.LOG(String.format("Particle angle: %3.2f  : right : %b", angle, right));
		liveTime = 0.033;
	
	}
	

	
	@Override
	public boolean update() {
		
		
		x = explosionX + ParticlePhysics.projectileX(speed, liveTime, angle);
	
		
		y = explosionY - ParticlePhysics.projectileY(speed, liveTime, angle);
	
				
		liveTime += 0.033;
		colorRed -=2;
		particleColor.darker();
		if(liveTime > 5 || colorRed < 3) return true;
		return !Calculations.isOnScreen((int)x, (int)y, radius);
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(particleColor);
		//g.fillOval((int)(x - radius), (int) (y - radius), 2 * radius, 2 * radius);
		g.drawImage(image, (int)x, (int)y, null);
		
	}

}
