package com.chotoxautinh.model;

public class Subject {
	private String subjectId;
	private String name;

	public Subject() {
	}

	public Subject(String subjectId, String name) {
		this.subjectId = subjectId;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Subject))
			return false;
		Subject other = (Subject) obj;
		return other.getSubjectId().equals(this.getSubjectId());
	}

	@Override
	public int hashCode() {
		return subjectId.hashCode();
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

}
