package com.trontria.wspidey.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.trontria.utils.Log;
import com.trontria.wspidey.crawler.context.CrawlContext;
import com.trontria.wspidey.crawler.html.JsoupHtmlProcessor;
import com.trontria.wspidey.crawler.streamer.HtmlLoader;

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
		try {
			String html = HtmlLoader.loadUrl("http://trontria.com");
			JsoupHtmlProcessor processor = new JsoupHtmlProcessor(html);
			processor.process();
			List<String> list = processor.getLinkList();
			context.foundLinks(list);
			
			for (String url : list) {
				 try {
					Log.d(TAG, "===========================================================");
					URL aURL = new URL(url);
					System.out.println("protocol = " + aURL.getProtocol());
					System.out.println("authority = " + aURL.getAuthority());
					System.out.println("host = " + aURL.getHost());
					System.out.println("port = " + aURL.getPort());
					System.out.println("path = " + aURL.getPath());
					System.out.println("query = " + aURL.getQuery());
					System.out.println("filename = " + aURL.getFile());
					System.out.println("ref = " + aURL.getRef());
				 } catch (MalformedURLException e) {
				 }
			}
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	public CrawlContext getContext() {
		return context;
	}

	public void setContext(CrawlContext context) {
		this.context = context;
	}
}