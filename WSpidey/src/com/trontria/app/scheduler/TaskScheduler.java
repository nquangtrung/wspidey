package com.trontria.app.scheduler;

import java.util.LinkedList;

import com.trontria.app.handler.Handler;
import com.trontria.app.handler.HandlerMessage;
import com.trontria.app.handler.HandlerThread;

public class TaskScheduler extends HandlerThread {
	private LinkedList<Task> mTaskQueue;
	private TaskHandler mTaskHandler;
	
	public TaskScheduler() {
		// TODO use default task handler
		super(new TaskHandler());
		mTaskHandler = (TaskHandler) getHandler();
		init();
	}
	
	public TaskHandler getTaskHandler() {
		return mTaskHandler;
	}
	
	private static class TaskHandler extends Handler {
		@Override
		public void handleMessage(HandlerMessage msg) {
		}
		
		@Override
		public void noMessage() {
			// TODO get the next task and handle it
		}
	}
	
	public TaskScheduler(TaskHandler handler) {
		super(handler);
		init();
	}
	private void init() {
		mTaskQueue = new LinkedList<>();
	}
	
	public synchronized void queueTask(Task task) {
		mTaskQueue.add(task);
	}
	
	public synchronized Task dequeueTask() {
		Task t = peek();
		mTaskQueue.removeFirst();
		return t;
	}
	
	public synchronized Task peek() {
		return mTaskQueue.getFirst();
	}
}
