package me.ichmagomaskekse.de.walker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import me.ichmagomaskekse.de.Landscape;
import me.ichmagomaskekse.de.program.Program;
import me.ichmagomaskekse.de.program.SmoothProgram;
import me.ichmagomaskekse.de.program.UnsmoothedProgram;

public class Walker {
	
	public double x = 0;
	public double y = 0;
	public double x_prev = 0;
	public double y_prev = 0;
	private Program program;
	private HashMap<Double, Double> points = new HashMap<Double, Double>();
	
	public Walker(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(!(x >= Landscape.worldWidth)) {
			if(program == null) {
				//program = new SmoothProgram();
				program = new UnsmoothedProgram();
				program.setWalker(this);
			}
			addX(1);
			if(program != null) program.tick();
			register(x, y);
			Landscape.setTitle("Generating...");
		}else Landscape.setTitle("Generated!");
	}
	public void register(double xp, double yp) {
		if(xp > x_prev && xp - x_prev >= 3) xp-= 3;
		if(yp > y_prev && yp - y_prev >= 3) yp-= 3;
		points.put(xp, yp);
	}
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		for(double xp : points.keySet()) {
			g.fillRect((int)((xp-Landscape.camera.getX())),
					(int)((points.get(xp)-Landscape.camera.getY())),
					1,
					(int)(Landscape.windowHeight - points.get(xp)));
		}
	}
	private void addX(double x) {
		this.x_prev = this.x;
		this.x += x;
	}
	public void addY(double y) {
		this.y_prev = this.y;
		this.y += y;
	}
	public void setY(double y) {
		this.y_prev = this.y;
		this.y = y;
	}
	
}