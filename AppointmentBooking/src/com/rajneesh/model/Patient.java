package com.rajneesh.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Patient {

	private int pid;
	private String pname;
	private Date date;

	private String docid;

	public Patient() {
	}

	public Patient(int pid, String pname, Date date, String docid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.date = date;
		this.docid = docid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", pname=" + pname + ", date=" + date + ", docid=" + docid + "]";
	}
}
