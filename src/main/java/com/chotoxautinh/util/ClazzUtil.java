package com.chotoxautinh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.chotoxautinh.model.SubClazz;

public class ClazzUtil {

	private final static String DATE_FORMAT = "dd/MM/yy";

	public static void dateHandle(SubClazz clazz, String range) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		clazz.setFromDate(formatter.parse(range.split("-")[0]));
		clazz.setToDate(formatter.parse(range.split("-")[1]));
	}

	public static void periodHandle(SubClazz clazz, String range) {
		clazz.setStartShift(Integer.parseInt(range.split("-")[0]));
		clazz.setFinishShift(Integer.parseInt(range.split("-")[1]));
	}
}
