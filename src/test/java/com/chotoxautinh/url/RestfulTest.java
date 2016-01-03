package com.chotoxautinh.url;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestfulTest {
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

	public static String read(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(15 * 1000);
		connection.setReadTimeout(10 * 1000);
		connection.setRequestMethod("GET");
		return readInputStream(connection.getInputStream());
	}
	
	public static String post(String path, String param) throws Exception {
		URL url = new URL(path);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		httpConnection.setRequestMethod("POST");
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(param.getBytes());
		wr.flush();
		wr.close();
		return readInputStream(connection.getInputStream());
	}
	
	public static String readRedirectedURL(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(15 * 1000);
		connection.setReadTimeout(10 * 1000);
		connection.setRequestMethod("GET");
		InputStream is = connection.getInputStream();
		is.close();
		return connection.getURL().toString();
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(readRedirectedURL("http://118.70.217.142:81/index.aspx?page=dangkytc2&ChuyenNganh=1"));
		System.out.println(read("http://118.70.217.142:81/index.aspx?page=dangkytc2&ChuyenNganh=1"));
//		System.out.println(post("http://118.70.217.142:81/login.aspx?ReturnUrl=%2findex.aspx%3fpage%3ddangkytc1%26ChuyenNganh%3d1", param));
	}
}
