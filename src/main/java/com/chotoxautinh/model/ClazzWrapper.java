package com.chotoxautinh.model;

import java.util.ArrayList;
import java.util.List;

public class ClazzWrapper {
	private Subject subject;
	private int nCredit;
	private List<SubClazz> subClazzes;

	public ClazzWrapper() {
		subClazzes = new ArrayList<>();
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getnCredit() {
		return nCredit;
	}

	public void setnCredit(int nCredit) {
		this.nCredit = nCredit;
	}

	public List<SubClazz> getSubClazzes() {
		return subClazzes;
	}

	public void setSubClazzes(List<SubClazz> subClazzes) {
		this.subClazzes = subClazzes;
	}

	public void add(SubClazz subClazz) {
		this.subClazzes.add(subClazz);
	}
}
