package me.xxfreakdevxx.de.game;

public class Camera {
	private float x=0,y=0;
	private float move_speed = 10f;
	public Camera(float x, float y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return (int)x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void addX(int direction) {
		x+=move_speed * direction;
//		x+=Landscape.blocksize * direction;
	}
	public void addY(int direction) {
		y+=move_speed * direction;
//		y+=Landscape.blocksize * direction;
	}
	
}
