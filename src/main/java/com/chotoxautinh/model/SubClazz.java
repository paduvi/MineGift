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
	private int nSlot;

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

	public boolean conflict(SubClazz other) {
		if (other.getToDate().before(this.getFromDate()) || other.getFromDate().after(this.getToDate()))
			return false;
		if (other.getDay() != this.getDay())
			return false;
		if (other.getFinishShift() < this.getStartShift() || other.getStartShift() > this.getFinishShift())
			return false;
		System.out.println("Conflict: " + name + " vs " + other.getName());
		return true;
	}

	public int getNSlot() {
		return nSlot;
	}

	public void setNSlot(int nSlot) {
		this.nSlot = nSlot;
	}
}
