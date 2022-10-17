package me.ichmagomaskekse.de.program;

import java.util.Random;

import me.ichmagomaskekse.de.Landscape;

public class SmoothProgram extends Program {
	
	private int tick = 0;
	private final int normal_null = (Landscape.windowHeight/2)+1;
	private final Random ran = new Random();
	
	public SmoothProgram() {}
	
	@Override
	public void tick() {
		double r = 0;
		if(tick == 50) {
	        r = randomDouble(1, 4);
	        tick = 0;
	    }else{
	        r = randomDouble(5, 10);
	    }
	    if(walker.y > 0) {
	        if(ran.nextBoolean()) {// Chance, dass die Höhe sich verändert
	            if(ran.nextBoolean()) {
	            	r = smoothY(r);
	            	r *= (-1);
	                walker.addY(r);
	            } else {
	            	r = smoothY(r);
	            	walker.addY(r);
	            }
	            if(walker.y > normal_null) {
	               walker.setY(normal_null-1);
	            }  
	        }
	    }else System.out.println("A");
	    tick++;
	}

	public double smoothY(double y) {
		//Höhen Unterschied zwischen tatsächlichen Y Wert und neuen Y Wert
		int y_range = 1;
		if(y > y_range) return y_range;
		return y;
	}
	
	public double randomDouble(double min, double max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("max must be greater than min");
	    }
	    Random r = new Random();
	    return min + (max - min) * r.nextDouble();
	}
	
}
