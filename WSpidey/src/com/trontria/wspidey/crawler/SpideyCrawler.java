package com.trontria.wspidey.crawler;

import java.util.List;

import com.trontria.wspidey.crawler.context.CrawlContext;
import com.trontria.wspidey.crawler.html.JsoupHtmlProcessor;

public class SpideyCrawler implements ICrawler {
	public static final String TAG = "SpideyCrawler";
	
	private CrawlContext context;
	public SpideyCrawler() {
		// Create a context of this crawler
		this.context  = new CrawlContext(this);
	}

	@Override
	public void crawl() {
		// TODO implement crawl
		String html = "<html><head><title>Test page</title></head><body><a href='http://trontria.com'>Click me!</a><a href='http://trontria.com'>Click me!</a></body></html>";
		JsoupHtmlProcessor processor = new JsoupHtmlProcessor(html);
		processor.process();
		List<String> list = processor.getLinkList();
		context.foundLinks(list);
	}

	public CrawlContext getContext() {
		return context;
	}

	public void setContext(CrawlContext context) {
		this.context = context;
	}
}