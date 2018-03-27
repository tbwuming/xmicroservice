package com.xms.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Music extends BaseDO {
	private static final long serialVersionUID = 4100424364780330747L;

	/* 用户名 */
	private String name;

	/* 年龄 */
	private String singer;

	/* 住址 */
	private String company;

	public Music() {
	}

	public Music(Long id, Date gmtCreate, Date gmtModified, String features, String name, String singer, String company) {
		super(id, gmtCreate, gmtModified, features);
		this.name = name;
		this.singer = singer;
		this.company = company;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
