package com.chotoxautinh;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import com.chotoxautinh.model.ClazzWrapper;
import com.chotoxautinh.model.SubClazz;
import com.chotoxautinh.model.Subject;
import com.chotoxautinh.util.ClazzLoader;

public class LoaderTest {
	public static void main(String[] args) throws URISyntaxException {
		File folder = new File(LoaderTest.class.getResource("/").toURI());
		File resource = new File(folder, "clazz.txt");

		Map<Subject, List<ClazzWrapper>> map = ClazzLoader.loadClazzFromResource(resource);

		for (Map.Entry<Subject, List<ClazzWrapper>> entry : map.entrySet()) {
			for (ClazzWrapper wrapper : entry.getValue()) {
				for (SubClazz clazz : wrapper.getSubClazzes()) {
					System.out.println(clazz.getName());
				}
			}
		}
	}
}
