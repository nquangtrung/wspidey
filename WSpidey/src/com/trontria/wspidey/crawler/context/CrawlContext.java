package com.trontria.wspidey.crawler.context;

import java.util.List;

import com.trontria.wspidey.crawler.ICrawler;

/**
 * Contains the contextual information of a certain crawler (eg history, 
 * policies, queues)
 *
 * @author TrungNQ
 */
public class CrawlContext {
	private ICrawler crawler;
	private CrawlQueue waitLine;
	
	public CrawlContext(ICrawler crawler) {
		this.crawler = crawler;
		this.waitLine = new CrawlQueue();
	}
	
	public void foundLinks(List<String> strUrlList) {
		waitLine.queue(strUrlList);
	}
	
	public String nextUrl() {
		return "";
	}

	public ICrawler getCrawler() {
		return crawler;
	}

	public void setCrawler(ICrawler crawler) {
		this.crawler = crawler;
	}

	public CrawlQueue getQueue() {
		return waitLine;
	}

	public void setQueue(CrawlQueue queue) {
		this.waitLine = queue;
	}
}
