package com.trontria.app.handler;

public abstract class Handler {
	public static final int MESSAGE_CODE_END_LOOP 	= Integer.MIN_VALUE + 0;
	// TODO implement for the below codes
	public static final int MESSAGE_CODE_LAZY 		= Integer.MIN_VALUE + 1;
	public static final int MESSAGE_CODE_SLEEP 		= Integer.MIN_VALUE + 2;
	public static final int MESSAGE_CODE_WAKEUP 	= Integer.MIN_VALUE + 3;
	
	private HandlerMessageQueue queue;
	public Handler() {
		queue = new HandlerMessageQueue();
	}
	
	public abstract void handleMessage(HandlerMessage msg);
	
	public void noMessage() {
		// What to do when the message queue is empty
	}
	
	public HandlerMessage nextMessage() {
		return getQueue().dequeue();
	}
	
	public void endLoop() {
		sendMessage(MESSAGE_CODE_END_LOOP);
	}
	
	public void sendMessage(HandlerMessage msg) {
		getQueue().queue(msg);
	}
	
	public void sendMessage(int what, Object... args) {
		getQueue().queue(new HandlerMessage(what, args));
	}

	public HandlerMessageQueue getQueue() {
		return queue;
	}
}
