package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Functions.DebugFunctions;
import GameState.GameStateManeger;
	
/**
 * basic game panel
 * runs game loop
 * executes game functions
 * draws to the window screen
 * handle user input
*/

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, MouseListener {

	
	//	DEBUG
	private static final boolean DEBUG = false;
	
	//	dimensions
	public static int HEIGHT = 640;
	public static int WIDTH = 1024;
	
	//	game thread
	private Thread thread;
	private boolean running;
	private int FPS = 30;
	private long targetTime = 1000 / FPS;
	private double averageFPS;
	private long startTime;
	private long elapsedTime;
	private long waitTime;
	
	//	image
	private BufferedImage image;
	private Graphics2D g;
	
	//	game state manager
	private GameStateManeger gsm;
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			addMouseListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		running = true;
		gsm = new GameStateManeger();
	}
	
	public void run(){
		init();
		
		//	average FPS related variables
		long totalTime = 0;
		int frameCount = 0;

		//		GAME LOOP		//
		while(running){
			startTime = System.nanoTime();
			update();
			draw();
			drawToScreen();
			
			elapsedTime = (System.nanoTime() - startTime )/ 1000000;
			waitTime = targetTime - elapsedTime ;
			
			if(waitTime < 0) waitTime = 5;	
			
			try{
				Thread.sleep(waitTime);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//	average fps calculation
			if(!DEBUG){
				totalTime += System.nanoTime() - startTime;
				frameCount++;
				if(frameCount == FPS){
					averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
					
					frameCount = 0;
					totalTime = 0;				
				}
			}
		}
	}
	
	private void update(){
		gsm.update();
	}
	
	private void draw(){
		gsm.draw(g);
		if(!DEBUG){
		DebugFunctions.DrawFPS(g, averageFPS, targetTime, elapsedTime);
		}
	}
	
	private void drawToScreen(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	
	public void mouseClicked(MouseEvent e) {
			
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e.getButton(), e.getX(), e.getY());
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
