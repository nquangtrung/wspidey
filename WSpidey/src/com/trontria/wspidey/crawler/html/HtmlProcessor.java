package com.trontria.wspidey.crawler.html;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public abstract class HtmlProcessor {
	private String strHtmlSource;
	private List<String> strLinkList;
	public HtmlProcessor(String html) {
		setHtmlSource(html);
		setLinkList(new LinkedList<String>());
	}
	public HtmlProcessor(InputStream is) {
		// TODO should this be put here???
	}
	public void process() {
		// First, index the source
		// TODO pass the data to an index processor
		index();
		
		//
		setLinkList(retrieveLinks());
	}
	
	/**
	 * 
	 */
	protected abstract void index();
	
	/**
	 * 
	 */
	protected abstract List<String> retrieveLinks();
	
	public String getHtmlSource() {
		return strHtmlSource;
	}
	public void setHtmlSource(String strHtmlSource) {
		this.strHtmlSource = strHtmlSource;
	}
	public List<String> getLinkList() {
		return strLinkList;
	}
	public void setLinkList(List<String> strLinkList) {
		this.strLinkList = strLinkList;
	}
}