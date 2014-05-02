package com.trontria.wspidey.crawler.html;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupHtmlProcessor extends HtmlProcessor {
	private Document htmlDocument;
	public JsoupHtmlProcessor(String html) {
		super(html);
		htmlDocument = Jsoup.parse(getHtmlSource());
	}
	
	@Override
	protected void index() {
		// TODO Index it here
		
	}

	@Override
	protected List<String> retrieveLinks() {
		List<String> urlList = new LinkedList<String>();
		Elements els = htmlDocument.select("a");
		for (int i = 0; i < els.size(); i++) {
			urlList.add(els.get(i).attr("href"));
		}
		return urlList;
	}

}
