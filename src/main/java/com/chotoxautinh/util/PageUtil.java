package com.chotoxautinh.util;

import java.net.MalformedURLException;
import java.net.URL;

public class PageUtil {
	public static String createHostName(String path) {
		try {
			URL url = new URL(path);
			return url.getHost() + ("http".equals(url.getProtocol()) && url.getPort() == 80
					|| "https".equals(url.getProtocol()) && url.getPort() == 443 ? "" : ":" + url.getPort());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(createHostName("http://118.70.217.142:81/index.aspx?page=dangkytc2&ChuyenNganh=1"));
	}
}
