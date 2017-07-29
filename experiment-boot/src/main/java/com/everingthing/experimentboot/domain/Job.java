package com.everingthing.experimentboot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Java")
public class Job implements Serializable{

	@Id
	private String cid;

	private String company;

	public Job() {
	}

	@Override
	public String toString() {
		return "Job{" +
				"cid='" + cid + '\'' +
				", company='" + company + '\'' +
				'}';
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
