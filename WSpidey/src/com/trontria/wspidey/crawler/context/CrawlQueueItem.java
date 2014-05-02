package com.trontria.wspidey.crawler.context;

public class CrawlQueueItem {
	enum CrawlPriority {
		PRIORITY_IMMEDIATE,
		PRIORITY_HIGH,
		PRIORITY_MEDIUM,
		PRIORITY_LOW
	}
	private String strURL;
	private CrawlPriority priLevel;
	
	// = Getters & setters =====================================================
	public String getStringURL() {
		return strURL;
	}
	public void setStringURL(String strURL) {
		this.strURL = strURL;
	}
	public CrawlPriority getPriorityLevel() {
		return priLevel;
	}
	public void setPriorityLevel(CrawlPriority priLevel) {
		this.priLevel = priLevel;
	}
}
