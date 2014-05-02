package com.trontria.app.scheduler;

import java.util.LinkedList;

import com.trontria.app.handler.Handler;
import com.trontria.app.handler.HandlerMessage;
import com.trontria.app.handler.HandlerThread;
import com.trontria.app.scheduler.Task.TaskWatcher;

public class TaskScheduler extends HandlerThread {
	private LinkedList<Task> mTaskQueue;
	private TaskHandler mTaskHandler;
	private SchedulerWatcher mSchedulerWatcher;
	private int mTaskCount;
	
	public TaskScheduler() {
		// TODO use default task handler
		super(new TaskHandler());
		init();
	}
	
	public TaskScheduler(TaskHandler handler) {
		super(handler);
		init();
	}
	
	private static class TaskHandler extends Handler {
		private int mTaskCount;
		private TaskScheduler mScheduler;
		@Override
		public void handleMessage(HandlerMessage msg) {
		}
		
		@Override
		public final void noMessage() {
			// Limit the task count
			if (getTaskCount() > 1000) {
				try {
					// XXX pause awhile before starting the next task
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				return;
			}
			
			// get the next task and start it
			Task t = getScheduler().dequeueTask();
			if (t == null) {
				try {
					// FIXME use sleep/lazy state instead
					// Meaning sendMessage to go into the sleep or lazy state
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				return;
			}
			
			// Run the task right away
			Thread thr = new Thread(t);
			thr.start();
		}

		public int getTaskCount() {
			return mTaskCount;
		}

		public void setTaskCount(int mTaskCount) {
			this.mTaskCount = mTaskCount;
		}

		public TaskScheduler getScheduler() {
			return mScheduler;
		}

		public void setScheduler(TaskScheduler mScheduler) {
			this.mScheduler = mScheduler;
		}
	}
	
	private class SchedulerWatcher implements TaskWatcher {
		@Override
		public void taskStarted() {
			synchronized (this) {
				mTaskCount++;
				if (getTaskHandler() != null) {
					getTaskHandler().setTaskCount(mTaskCount);
				}
			}
		}

		@Override
		public void taskFinished() {
			synchronized (this) {
				mTaskCount--;
				if (getTaskHandler() != null) {
					getTaskHandler().setTaskCount(mTaskCount);
				}
			}
		}
		
	}
	
	/**
	 * Initialize components
	 */
	private void init() {
		// Handle tasks
		mTaskHandler = (TaskHandler) getHandler();
		mTaskHandler.setScheduler(this);
		
		// The queue to keep track of task
		mTaskQueue = new LinkedList<>();
		
		// Watching which task is running
		mSchedulerWatcher = new SchedulerWatcher();
		
		// Count the running tasks
		mTaskCount = 0;
	}
	
	public TaskHandler getTaskHandler() {
		return mTaskHandler;
	}
	
	public synchronized void queueTask(Task task) {
		task.setTaskWatcher(mSchedulerWatcher);
		mTaskQueue.add(task);
	}
	
	public synchronized Task dequeueTask() {
		if (mTaskQueue.isEmpty()) return null;
		Task t = peek();
		mTaskQueue.removeFirst();
		return t;
	}
	public synchronized Task peek() {
		if (mTaskQueue.isEmpty()) return null;
		return mTaskQueue.getFirst();
	}
}
