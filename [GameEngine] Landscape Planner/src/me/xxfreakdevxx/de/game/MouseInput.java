package me.xxfreakdevxx.de.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public MouseInput() {
	}
	
	@SuppressWarnings("unused")
	public void mousePressed(MouseEvent e) {
		Camera camera = Landscape.getInstance().getCamera();
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());		
	}
}
