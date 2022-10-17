package me.ichmagomaskekse.de;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyInput extends KeyAdapter {
	
	public ConcurrentLinkedQueue<Integer> pressed_keys = new ConcurrentLinkedQueue<Integer>();
	public boolean use_W_TpJump = true;
	
	public KeyInput() {}
	public void tick() {}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_RIGHT:
			Landscape.getInstance().getCamera().addX(1);
			break;
		case KeyEvent.VK_LEFT:
			Landscape.getInstance().getCamera().addX(-1);
			break;
		case KeyEvent.VK_UP:
			Landscape.getInstance().getCamera().addY(-1);
			break;
		case KeyEvent.VK_DOWN:
			Landscape.getInstance().getCamera().addY(1);
			break;
		}
	}

}
