package com.chotoxautinh.url;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.client.RedirectLocations;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.chotoxautinh.util.HtmlParser;
import com.chotoxautinh.util.PageUtil;

public class RestfulTest {

	private CookieStore cookieStore = new BasicCookieStore();
	private HttpClient httpClient;
	private final String USER_AGENT = "Mozilla/5.0";
	private final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	private final String ACCEPT_LANGUAGE = "en-US,en;q=0.5";

	public RestfulTest() {
		httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
	}

	private static String readInputStream(InputStream stream) throws Exception {
		StringWriter writer = new StringWriter();
		try {
			int read;
			byte[] bytes = new byte[4 * 1024];
			while ((read = stream.read(bytes)) != -1) {
				writer.write(new String(bytes, 0, read));
			}
		} finally {
			stream.close();
			writer.close();
		}
		return writer.getBuffer().toString();
	}

	public String getPageContent(String path) throws Exception {
		HttpGet request = new HttpGet(path);
		
		HttpContext context = new BasicHttpContext();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

		request.setHeader("Connection", "keep-alive");
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept", ACCEPT);
		request.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		request.setHeader("Host", PageUtil.createHostName(path));

		HttpResponse response = httpClient.execute(request, context);
		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'GET' request to URL : " + path);
		System.out.println("Response Code : " + responseCode);

		return readInputStream(response.getEntity().getContent());
	}

	public String post(String path, Map<String, String> requestParam) throws Exception {
		HttpPost request = new HttpPost(path);
		
		HttpContext context = new BasicHttpContext();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

		request.setHeader("Connection", "keep-alive");
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept", ACCEPT);
		request.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		request.setHeader("Host", PageUtil.createHostName(path));

		ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : requestParam.entrySet()) {
			nameValuePair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		request.setEntity(new UrlEncodedFormEntity(nameValuePair));

		HttpResponse httpRespnse = httpClient.execute(request, context);

		int responseCode = httpRespnse.getStatusLine().getStatusCode();

		System.out.println("\nSending 'POST' request to URL : " + path);
		System.out.println("Response Code : " + responseCode);

		return readInputStream(httpRespnse.getEntity().getContent());
	}

	public String getRedirectedURL(String path) throws Exception {
		HttpGet request = new HttpGet(path);
		
		HttpContext context = new BasicHttpContext();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

		request.setHeader("Connection", "keep-alive");
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept", ACCEPT);
		request.setHeader("Accept-Language", ACCEPT_LANGUAGE);
		request.setHeader("Host", PageUtil.createHostName(path));

		HttpResponse response = httpClient.execute(request, context);
		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'GET' request to URL : " + path);
		System.out.println("Response Code : " + responseCode);

		String finalUrl = request.getURI().toString();
		RedirectLocations locations = (RedirectLocations) context
				.getAttribute(HttpClientContext.REDIRECT_LOCATIONS);
		if (locations != null) {
			finalUrl = locations.getAll().get(locations.getAll().size() - 1).toString();
		}

		return finalUrl;
	}

	public static void main(String[] args) throws Exception {
		RestfulTest test = new RestfulTest();

		String redirectURL = test.getRedirectedURL("http://118.70.217.142:81/index.aspx?page=dangkytc2&ChuyenNganh=1");
		System.out.println(redirectURL);

		String content = test.getPageContent("http://118.70.217.142:81/index.aspx?page=dangkytc2&ChuyenNganh=1");
		System.out.println(content);
		Map<String, String> param = HtmlParser.getStringParam(content);
		param.put("txtUserName", "35A1.19.018");
		param.put("txtPassword", "lehien");
		param.put("cmdLogin", "Đăng+nhập");
		System.out.println(test.post(redirectURL, param));

		System.out.println(test.getPageContent("http://118.70.217.142:81/index.aspx?page=dangkytc1&ChuyenNganh=1"));
	}
}
