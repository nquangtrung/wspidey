package com.trontria.wspidey.crawler.context;

import java.util.LinkedList;
import java.util.List;

import com.trontria.utils.Log;
import com.trontria.wspidey.crawler.context.CrawlQueueItem.CrawlPriority;

public class CrawlQueue {
	public static final String TAG = CrawlQueue.class.getSimpleName();
	private LinkedList<CrawlQueueItem> mQueueList;
	
	public CrawlQueue() {
		mQueueList = new LinkedList<>();
	}
	
	public synchronized void queue(String strUrl) {
		queue(strUrl, CrawlPriority.PRIORITY_MEDIUM);
	}
	
	public synchronized void queue(String strUrl, CrawlPriority priority) {
		Log.v(TAG, "Queueing [" + strUrl + "] with priority [" + priority + "]");
		CrawlQueueItem item = new CrawlQueueItem();
		item.setStringURL(strUrl);
		item.setPriorityLevel(priority);
		mQueueList.addLast(item);
	}
	
	public synchronized void queue(List<String> strUrlList) {
		for (String url : strUrlList) {
			queue(url);
		}
	}
	
	public synchronized boolean isEmpty() {
		return mQueueList.size() == 0;
	}
	
	public synchronized String dequeue() {
		String url = peek();
		mQueueList.removeFirst();
		return url;
	}
	
	public synchronized String peek() {
		if (isEmpty()) return "";
		CrawlQueueItem item = mQueueList.getFirst();
		return item.getStringURL();
	}
}