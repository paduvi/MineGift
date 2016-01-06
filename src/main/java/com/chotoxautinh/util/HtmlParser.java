package com.chotoxautinh.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	public static Map<String, String> getStringParam(String html) {
		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementsByTag("form").first();
		Elements hiddenElements = loginform.select("input[type=hidden]");
		Map<String, String> map = new HashMap<>();
		for (Element inputElement : hiddenElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");
			map.put(key, value);
		}
		return map;
	}
}
