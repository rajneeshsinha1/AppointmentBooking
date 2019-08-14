package com.rajneesh.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Doctor {

	private String docid;
	private String docname;

	public Doctor() {
	}

	public Doctor(String docid, String docname) {
		super();
		this.docid = docid;
		this.docname = docname;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	@Override
	public String toString() {
		return "Doctor [docid=" + docid + ", docname=" + docname + "]";
	}
}
