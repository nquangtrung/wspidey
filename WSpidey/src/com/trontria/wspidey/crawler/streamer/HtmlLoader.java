package com.trontria.wspidey.crawler.streamer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.trontria.utils.Log;

public class HtmlLoader implements ILoader {
	public static final String TAG = HtmlLoader.class.getSimpleName();

	@Override
	public String load(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);

		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line = "";
		String html = "";
		while ((line = rd.readLine()) != null) {
			Log.v(TAG, line);
			html += line;
		}

		return html;
	}

	public static String loadUrl(String url) throws ClientProtocolException,
			IOException {
		HtmlLoader loader = new HtmlLoader();
		return loader.load(url);
	}
}
