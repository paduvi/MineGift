package com.chotoxautinh.model;

import java.util.Date;

public class SubClazz {
	private String name;
	private Date fromDate;
	private Date toDate;
	private int day;
	private int startShift;
	private int finishShift;
	private String place;
	private String teacher;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	public int getStartShift() {
		return startShift;
	}

	public void setStartShift(int startShift) {
		this.startShift = startShift;
	}

	public int getFinishShift() {
		return finishShift;
	}

	public void setFinishShift(int finishShift) {
		this.finishShift = finishShift;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}
