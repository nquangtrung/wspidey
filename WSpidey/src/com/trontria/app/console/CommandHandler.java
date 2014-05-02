package com.trontria.app.console;

public abstract class CommandHandler {
	private Console console;
	
	public abstract void handler(String command);
	public abstract void consoleStart();
	public abstract void consoleFinish();
	
	protected void exit() {
		console.exit();
	}
	public Console getConsole() {
		return console;
	}
	public void setConsole(Console console) {
		this.console = console;
	}
}
