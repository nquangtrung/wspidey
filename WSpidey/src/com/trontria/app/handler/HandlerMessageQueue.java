package com.trontria.app.handler;

import java.util.LinkedList;

public class HandlerMessageQueue extends LinkedList<HandlerMessage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6421189710232472267L;
	
	public synchronized void queue(HandlerMessage e) {
		addLast(e);
	}
	
	public synchronized HandlerMessage peek() {
		if (isEmpty()) return null;
		return getFirst();
	}
	
	public synchronized HandlerMessage dequeue() {
		if (isEmpty()) return null;
		HandlerMessage msg = peek();
		removeFirst();
		return msg;
	}

}
