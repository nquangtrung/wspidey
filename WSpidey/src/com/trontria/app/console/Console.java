package com.trontria.app.console;

import java.io.InputStream;
import java.util.Scanner;

public class Console {
	private CommandHandler handler;
	private InputStream commandInputStream;
	private boolean looping = false;
	private Console(CommandHandler handler) {
		this.handler = handler;
		commandInputStream = System.in;
	}
	
	private Console(CommandHandler handler, InputStream is) {
		this.handler = handler;
		commandInputStream = is;
	}
	
	private void start() {
		// Prepare the environment for the console
		prepareEnvironment();
		looping = true;
		loop();
	}
	
	protected void prepareEnvironment() {
		// TODO devise an environment preparing scheme
	}
	
	private void loop() {
		handler.consoleStart();
		Scanner scanner = new Scanner(commandInputStream);
		while (looping) {
			if (commandInputStream == System.in)
				System.out.print("> ");
			
			String strCommand = scanner.nextLine().trim();
			// TODO Handle exit
			if ("exit".equals(strCommand)) break;
			if ("".equals(strCommand)) continue;
			
			handler.handler(strCommand);
		}
		scanner.close();
		handler.consoleFinish();
	}
	
	protected void exit() {
		looping = false;
	}
	
	public static void start(CommandHandler handler) {
		Console console = new Console(handler);
		console.start();
	}
	
	public static void start(CommandHandler handler, InputStream is) {
		Console console = new Console(handler, is);
		console.start();
	}
}
