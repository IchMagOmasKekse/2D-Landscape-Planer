package me.xxfreakdevxx.de.game.program;

import me.xxfreakdevxx.de.game.walker.Walker;

public abstract class Program {
	
	protected Walker walker = null;
	
	public Program() {}
	
	public abstract void tick();
	public void setWalker(Walker walker) {
		this.walker = walker;
	}
	
}
