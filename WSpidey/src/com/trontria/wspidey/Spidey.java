package com.trontria.wspidey;

import com.trontria.app.console.CommandHandler;
import com.trontria.app.console.Console;
import com.trontria.app.handler.Handler;
import com.trontria.app.handler.HandlerMessage;
import com.trontria.app.handler.HandlerThread;
import com.trontria.utils.Log;
import com.trontria.wspidey.crawler.ICrawler;
import com.trontria.wspidey.crawler.SpideyCrawler;

public class Spidey {
	public static void main(String[] args) {
		// TODO run spidey here
//		testCrawler();
//		testHandler();
		testConsole();
	}
	
	static void testCrawler() {
		ICrawler crawler = new SpideyCrawler();
		crawler.crawl();
	}
	
	static void testConsole() {
		Console.start(new CommandHandler() {
			@Override
			public void handler(String command) {
				Log.d("main", "Handling command " + command);
			}

			@Override
			public void consoleStart() {
				System.out.println("Console started! Type exit to quit.");
			}

			@Override
			public void consoleFinish() {
				System.out.println("Console finished.");
			}
		});
	}
	
	static void testHandler() {
		Handler handler = new Handler() {
			@Override
			public void handleMessage(HandlerMessage msg) {
				Log.v("main", "Read message " + msg.what());
			}
			
			@Override
			public void noMessage() {
				super.noMessage();
				Log.v("main", "Waiting for message");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		HandlerThread t = new HandlerThread(handler);
		
		for (int i = 0; i < 10; i ++) {
			handler.sendMessage(-i);
		}
		t.start();
		for (int i = 0; i < 5; i ++) {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handler.sendMessage(i);
		}
		
		handler.endLoop();
	}
}
