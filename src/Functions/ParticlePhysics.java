package Functions;



	
	
public class ParticlePhysics {
	
	private static final double GRAVITY = 9.8;

	/**
	 * First elementary formula  relating to projectile motion during time, X coordinate
	 * if shot fired from X = 0
	 * @param speed of entity
	 * @param time	how long entity exists from firing moment
	 * @param angle at which entity was shot
	 * @return	X value of entity position depending on time
	 */
	public static double projectileX(double speed, double time, double angle){
		return speed * time * Math.cos(angle);
	}
	
	/**
	 * Second elementary formula  relating to projectile motion during time, Y coordinate
	 * if shot fired from Y = 0
	 * @param speed of entity
	 * @param time how long entity exists from firing moment
	 * @param angle at which entity was shot
	 * @return  Y value of entity position depending on tim
	 */
	public static double projectileY(double speed, double time, double angle){
		return speed * time * Math.sin(angle) - (GRAVITY * Math.pow(time, 2) / 2);
	}
	
	
	public static double getProjectileDX(double x, int launchX,double speed, double liveTime, double angle, boolean Right) {
		double nextX = x;
		if(Right)  nextX = (launchX + (speed*liveTime*Math.cos(angle)));
		if(!Right ) nextX = (launchX - (speed*liveTime*Math.cos(angle)));
		return nextX - x;
	}

	public static double getProjectileDY(double y, int launchY, double speed, double liveTime, double angle, boolean up) {
		double nextY = y;
		if(!up)  nextY= (launchY -(speed*liveTime*Math.sin(angle)-GRAVITY*liveTime*liveTime/2));
		if(up) nextY= (launchY +(speed*liveTime*Math.sin(angle)-GRAVITY*liveTime*liveTime/2));
		return nextY - y;
	}

		
}
