package com.trontria.app.scheduler;

public abstract class Task implements Runnable {
	private boolean mRunning = false;
	private Runnable mRunner;
	private TaskWatcher mTaskWatcher;
	public Task(Runnable runner, TaskWatcher watcher) {
		mRunner = runner;
		mTaskWatcher = watcher;
	}
	@Override
	public void run() {
		if (getTaskWatcher() != null) {
			getTaskWatcher().taskStarted();
		}
		mRunner.run();
		if (getTaskWatcher() != null) {
			getTaskWatcher().taskFinished();
		}
	}
	public boolean isRunning() {
		return mRunning;
	}
	public void setRunning(boolean running) {
		this.mRunning = running;
	}
	public Runnable getRunner() {
		return mRunner;
	}
	public void setRunner(Runnable mRunner) {
		this.mRunner = mRunner;
	}
	
	public TaskWatcher getTaskWatcher() {
		return mTaskWatcher;
	}
	public void setTaskWatcher(TaskWatcher mTaskWatcher) {
		this.mTaskWatcher = mTaskWatcher;
	}

	public static interface TaskWatcher {
		public void taskStarted();
		public void taskFinished();
	}
}
