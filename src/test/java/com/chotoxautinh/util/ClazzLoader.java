package com.chotoxautinh.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chotoxautinh.model.ClazzWrapper;
import com.chotoxautinh.model.SubClazz;
import com.chotoxautinh.model.Subject;

public class ClazzLoader {

	public static Map<Subject, List<ClazzWrapper>> loadClazzFromPlain(File file) {
		Map<Subject, List<ClazzWrapper>> map = new HashMap<>();

		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			isr = new InputStreamReader(new FileInputStream(file));
			reader = new BufferedReader(isr);

			String temp;
			while ((temp = reader.readLine()) != null) {
				String[] portion = temp.trim().split("\t");
				Subject subject = new Subject(portion[0], portion[1]);
				ClazzWrapper wrapper = new ClazzWrapper();
				wrapper.setSubject(subject);
				wrapper.setnCredit(Integer.parseInt(portion[2]));

				do {
					portion = temp.trim().split("\t");
					SubClazz clazz = new SubClazz();
					clazz.setName(portion[3]);
					ClazzUtil.dateHandle(clazz, portion[4]);
					clazz.setDay(Integer.parseInt(portion[5]));
					ClazzUtil.periodHandle(clazz, portion[6]);
					clazz.setPlace(portion[7]);
					clazz.setNSlot(Integer.parseInt(portion[9]));

					wrapper.add(clazz);
				} while (!(temp = reader.readLine()).contains("======="));

				addToMap(map, subject, wrapper);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Resource not found");
		} catch (IOException e) {
			System.out.println("Error exists when read resource");
		} catch (ParseException e) {
			System.out.println("Invalid Date Format");
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	private static void addToMap(Map<Subject, List<ClazzWrapper>> map, Subject subject, ClazzWrapper wrapper) {
		if (map.containsKey(subject)) {
			map.get(subject).add(wrapper);
		} else {
			List<ClazzWrapper> list = new ArrayList<>();
			list.add(wrapper);
			map.put(subject, list);
		}
	}
}
