package me.ichmagomaskekse.de.program;

import java.util.Random;

import me.ichmagomaskekse.de.Landscape;

public class UnsmoothedProgram extends Program {

	private int tick = 0;
	private final int normal_null = (Landscape.windowHeight/2)+1;
	private final Random ran = new Random();

	public UnsmoothedProgram() {
    	
    }
    
	@Override
	public void tick() {
		if(walker != null) {
			int r = 0;
			if(tick == 10) {
				tick = 0;
		    }else{
		        r = ran.nextInt(10);
		    }
		    if(walker.y > 0) {
		        if(ran.nextBoolean()) {// Chance, dass die H�he sich ver�ndert
		    
		            if(ran.nextBoolean()) {
		                walker.addY((r *(-1)));
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
