package me.xxfreakdevxx.de.game.program;

import java.util.Random;

import me.xxfreakdevxx.de.game.Landscape;

public class UnsmoothedProgram extends Program {

	int tick = 0;
    int new_hill = 1;
    int rise = 0;
    int up_or_down = 1;
    int walked = 0;
    int normal_null = (Landscape.windowHeight/2)+1; 
    double change_polarity = 0.5;
    Random ran = new Random();
    int r = 0;
	
    public UnsmoothedProgram() {
    	
    }
    
	@Override
	public void tick() {
		if(walker != null) {
		    if(tick == 10) {
		        r = ran.nextInt(1);
		        tick = 0;
		    }else{
		        r = ran.nextInt(10);
		    }
		    if(walker.y > 0) {
		        if(ran.nextBoolean()) {// Chance, dass die Höhe sich verändert
		    
		            if(ran.nextBoolean()) {
		                walker.addY((r*(-1)));
		            }else {
		            	 walker.addY(r);
		            }
		            if(walker.y > normal_null) {
		               walker.setY(normal_null-1);
		            }  
		        }   
		    }else System.out.println("A");
		    tick++;
		}
	}
	
}
