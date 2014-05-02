package com.trontria.utils;

import java.io.PrintStream;

public class Log {
	private static final int LOG_VERBOSE 	= 0;
	private static final int LOG_DEBUG 		= 1;
	private static final int LOG_WARNING 	= 2;
	private static final int LOG_ERROR	 	= 3;
	
	private static PrintStream mLogStream;
	// Start out with print all filter
	private static int filterLevel = Integer.MIN_VALUE;
	
	public static void logStream(PrintStream os) {
		mLogStream = os;
	}
	
	public static void filter(int level) {
		filterLevel = level;
	}
	
	public static void shutup() {
		filter(Integer.MAX_VALUE);
	}
	
	public static void iamsorry() {
		filter(Integer.MIN_VALUE);
	}
	
	public static void printLog(int level, String tag, String message, Throwable e) {
		if (level < filterLevel) return;
		
		// Print normal message
		StringBuilder builder = new StringBuilder();
		builder.append("[" + LOG_LEVEL_NAME[level] + "] ");
		builder.append("[" + tag + "] ");
		builder.append(message);
		String text = builder.toString();
		stream().println(text);
		
		// Print exception and throwable if presented
		if (e == null) return;
		e.printStackTrace(errstream());
	}
	
	public static void v(String tag, String message) {
		printLog(LOG_VERBOSE, tag, message, null);
	}
	
	public static void d(String tag, String message) {
		printLog(LOG_DEBUG, tag, message, null);
	}
	
	public static void w(String tag, String message) {
		printLog(LOG_WARNING, tag, message, null);
	}
	
	public static void e(String tag, String message) {
		printLog(LOG_ERROR, tag, message, null);
	}
	
	public static void e(String tag, String message, Throwable e) {
		printLog(LOG_ERROR, tag, message, e);
	}
	
	private static PrintStream stream() {
		if (mLogStream == null) {
			mLogStream = System.out;
		}
		return mLogStream;
	}
	
	private static PrintStream errstream() {
		return System.err;
	}
	
	private static final String[] LOG_LEVEL_NAME = { 
		"VERBOSE",
		"DEBUG",
		"WARNING",
		"ERROR"
	};
}
