package Functions;
/**
 * class that holds vector of two double values
 * @author acer PC
 *
 */
public class doubleVec2D {
	private double m_x, m_y;//member values to hold data
	public doubleVec2D(double x, double y){
		/**
		 * asinging given values to the class member values
		 */
		m_x = x;
		m_y = y;
	}
	public void setValues(double x, double y){
		m_x = x;
		m_y = y;
	}
	public double getX(){
		return m_x;	
	}
	public double getY(){
		return m_y;	
	}
	
}
