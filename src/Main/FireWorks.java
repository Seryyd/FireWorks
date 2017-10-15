package Main;
import javax.swing.JFrame;

/**
 * Fireworks simulation practice,
 * this is entry class for programm, sets defautl window parametrs , 
 * packs it and launches game negine 
 * @author Sergejs
 */
public class FireWorks {
	
	public static void main(String[] args) {
	
		JFrame window = new JFrame("FireWorks");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
