package Functions;

public class intVec2D {
	private int m_x, m_y;//member values to hold data
	
	public intVec2D(int x, int y){
		/**
		 * asinging given values to the class member values
		 */
		m_x = x;
		m_y = y;
	}
	
	public void setValues(int x, int y){
		m_x = x;
		m_y = y;
	}
	
	public int getX(){	return m_x;	}
	public int getY(){	return m_y;	}
	public void setX(int i) {	m_x = i;	}
	public void setY(int i) {	m_y = i;	}
	
}
