package me.ichmagomaskekse.de;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.ichmagomaskekse.de.walker.Walker;

@SuppressWarnings("serial")
public class Landscape extends Canvas implements Runnable {
	
	/* Window */
	public static int windowWidth = 1000;
	public static int windowHeight = 700;
	public static int worldWidth = 3000;
	
	private boolean isRunning = false;
	public static int fps_current = 0;
	public static int fps_maximal = -1;
	public static double tickSpeed = 3000;
	private Thread thread;
	public static Camera camera;
	public Walker walker;
	
	//Manager, Handler, etc.
	public KeyInput keyinput = null;
	public Window window = null;
	
	static Landscape instance;
	public static Landscape getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		new Landscape();
	}
	
	public Landscape() {
		instance = this;
		setBackground(Color.WHITE);
		window = new Window(windowWidth, windowHeight, "Landscape", this);
		start();
	}
	
	public void preInit() {
		keyinput = new KeyInput();
		this.addKeyListener(keyinput);
		this.addMouseListener(new MouseInput());
		this.walker = new Walker(0, (windowHeight / 2));
		Landscape.camera = new Camera(0,0);
	}
	public void init() {}
	public void postInit() {}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		preInit();
		init();
		postInit();		
	}
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	@Override
	public void run() {
		/*
		 * GameLoop - Made by Notch
		 */
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = tickSpeed;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		boolean allowRender = false;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				//update++;
				delta--;
			}
			if(fps_maximal == -1 ) allowRender = true;
			if(frames != fps_maximal || allowRender) {
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps_current = frames;
				frames = 0;
			}
		}
		stop();
	}
	public void tick() {
		Landscape.windowWidth = window.frame.getWidth();
		Landscape.windowHeight = window.frame.getHeight();
		if(walker != null) walker.tick();
		if(keyinput != null) keyinput.tick();
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, windowWidth, windowHeight);
		if(walker != null) walker.render(g);
		
		g.dispose();
		bs.show();
	}
	public Walker getWalker() {
		return walker;
	}
	public static void setTitle(String title) {
		Landscape.getInstance().window.frame.setTitle(title);
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	@SuppressWarnings("deprecation")
	public static String getTimeInString() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
	}
	@SuppressWarnings("deprecation")
	public static String getDateInString() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		int day = d.getDate();
		int mon = d.getMonth();
		int year = d.getYear();
		return day+":"+mon+":"+year;
	}
	public static void log(String prefix, String... strings) {
		if(prefix.equals("")) prefix = "Debug";
		for(String s : strings) {
			System.out.println("["+Landscape.getTimeInString()+"]["+prefix+"] "+s);
		}
	}
}
