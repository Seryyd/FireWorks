package Functions;

import java.util.Random;

import Main.GamePanel;

public class Calculations {
	static Random rnd = new Random();	

	/**
	 * Method that calculates requered launch angle of projectile
	 * to hit target at given position at x y coordinates
	 * @param v		velocity of projectile
	 * @param g		earth gravity constant
	 * @param targetPos		coordinates of target
	 * @param launchPos		coordinats of launchposition
	 * @return		return of two angles
	 */
	public static doubleVec2D getAngleToHit(double v, double g, int targetX, int targetY, int launchX, int launchY) {
		doubleVec2D angles = new doubleVec2D(0,0);
		doubleVec2D coordinates = new doubleVec2D(0,0);
		
		//calculates 
		coordinates.setValues(getDifference(launchX,targetX), getDifference(launchY,targetY));
		
		double stepOne, stepTwo;
		double sqrtVal, upperValPlus, upperValMinus, atanInpPlus, atanInpMinus;
		
		stepOne = (g * Math.pow(coordinates.getX(), 2)) + (2 * coordinates.getY() * Math.pow(v, 2));
		stepTwo = Math.pow(v, 4) - g * stepOne;
		sqrtVal = Math.sqrt(stepTwo);
		upperValPlus = Math.pow(v, 2) + sqrtVal;
		upperValMinus = Math.pow(v, 2) - sqrtVal;

		atanInpPlus = upperValPlus/(g*coordinates.getX());
		atanInpMinus = upperValMinus/(g*coordinates.getX());
		
		
		angles.setValues(Math.atan(atanInpPlus),Math.atan(atanInpMinus));
		return angles;
	}

	/**
	 * Methad that calculates and returns
	 * pozitive abs difference between two given values
	 * @param initialValue
	 * @param endValue
	 * @return abs value of difference
	 */
	public static double getDifference(double initialValue, double endValue) {
		double difference = Math.abs(endValue - initialValue);
		return difference;
	}
	
	/**
	 * Check if two objects has colision
	 * takes coordinates of both objects and their radius to determin wether thei have colision, with a litl posibility of error becouse
	 * colision happens when distance between two object is less than 5 pixels
	 * @param x1 coordinate of first object
	 * @param y1 coordinate of first object
	 * @param x2 coordinate of second object
	 * @param y2 coordinate of second object
	 * @param r1 radius of first object
	 * @param r2 radius of second object
	 * @return Boolean value whether colision happened or not
	 */
	public static boolean colision(int x1, int y1, int x2, int y2, int r1, int r2){
		//	variables
		boolean colision = false;
		double distanceX;
		double distanceY;
		double distance;
		
		distanceX = getDifference(x1, x2);
		distanceY = getDifference(y1, y2);
		distance = (Math.sqrt((Math.pow(distanceX, 2) + Math.pow(distanceY, 2)))) - (r1 + r2);
		
		if (distance < 5) colision = true; else colision = false;
		return colision;
		
	}
	
	/**
	 * Ranodm double value generator, takes int minimal value and int value of spread, multiplys spred value with randem double 
	 * @param minValue
	 * @param spread
	 * @return double value between minimal value and minimal value + spread
	 */
	public static double randomDouble(int minValue, int spread) {
		return (minValue + (rnd.nextDouble() * spread));
	}
	
	/**
	 * Ranodm int value generator, takes int minimal value and int value of spread
	 * @param minValue
	 * @param spread
	 * @return int value between minimal value and minimal value + spread
	 */
	public static int randomInt(int minValue, int spread) {
		return (minValue + rnd.nextInt(spread));
		
	}

	/**
	 * method to determin wheter object is visible on screen
	 * @param x coordinate of object
	 * @param y coordinate of object
	 * @param radius  of the object
	 * @return Boolean value wheter object at given coordinates is visible on screen
	 */
	public static boolean isOnScreen(int x, int y, int radius) {
		boolean isOnScreen = true;
		if(x < 0 - radius) isOnScreen = false;
		if(x > GamePanel.WIDTH + radius) isOnScreen = false;
		if(y < 0 - radius) isOnScreen = false;
		if(y > GamePanel.HEIGHT + radius) isOnScreen = false;
		return isOnScreen;
	}


}







