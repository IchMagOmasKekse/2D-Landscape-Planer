package me.ichmagomaskekse.de.program;

import me.ichmagomaskekse.de.walker.Walker;

public abstract class Program {
	
	protected Walker walker = null;
	
	public Program() {}
	
	public abstract void tick();
	public void setWalker(Walker walker) {
		this.walker = walker;
	}
	
}
