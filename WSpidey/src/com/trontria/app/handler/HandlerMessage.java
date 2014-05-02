package com.trontria.app.handler;

public class HandlerMessage {
	private int what;
	private Object[] arguments;
	
	public HandlerMessage() {
	}
	
	public HandlerMessage(int what, Object... args) {
		what(what);
		arguments(args);
	}

	public int what() {
		return what;
	}

	public void what(int what) {
		this.what = what;
	}

	public Object[] arguments() {
		return arguments;
	}

	public void arguments(Object[] arguments) {
		this.arguments = arguments;
	}
}
