package Functions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Entity.Rocket;

public class DebugFunctions {
	
	public static void LOG(String debugMessage){
		System.out.println(debugMessage);
	}
	public static void DrawFPS(Graphics2D g, double averageFPS,long targetTime, long elapsed ){
		g.setColor(Color.white);
		//double load = 0;
		//load = ((double)elapsed / (double)targetTime) * 100;
		String fpsOutput = "";
		fpsOutput += String.format("FPS: %2.2f ", averageFPS);
		//fpsOutput += String.format("Load: %2.2f ", load);
		g.drawString(fpsOutput ,25,25);
	}
	
	public static void DisplayShotArrayDebugLog(Graphics2D g, int rocketCount, ArrayList<Rocket> rocket){
		g.setColor(Color.white);
		g.drawString("Number of active shots: " + rocketCount  ,25,50);
		
		
		if(rocketCount > 0){
			for	( int i = 0; i < rocketCount; i++){
				boolean Degree = true;
				String output = "";
				//	number of shot
				output += String.format("  Rocket%3d", i);
				
				//	launch coordinates
				output += String.format("   LaunchPos (%5d,%5d)  ", rocket.get(i).getInitialX(),rocket.get(i).getInitialY());
				
				//	target coordinates
				//output += String.format("   TargerPos (%5d,%5d)  ", rocket.get(i).getTargetX(),rocket.get(i).getTargetY());
				
				//	current rocket position
				output += String.format("   CurrentPos (%5d,%5d)  ", rocket.get(i).getCurentX(),rocket.get(i).getCurentY());
				
				//	curent angle of rocket
				output += String.format("  Angle = %.2f ", rocket.get(i).getAngle(Degree));
				
				//	speed of the rocket
				output += String.format("  Speed = %4.2f", rocket.get(i).getSpeed());
				
				g.drawString(	output, 25, 70 + (i * 15));
			}
				
			//		drawing trajectories		//
			for	( int i = 0; i < rocketCount; i++){
				g.setColor(new Color(40,40,40));
				g.drawLine(	rocket.get(i).getInitialX(), rocket.get(i).getInitialY(),
							rocket.get(i).getTargetX(), rocket.get(i).getTargetY());
			}
		}	
	}
	
	

}
