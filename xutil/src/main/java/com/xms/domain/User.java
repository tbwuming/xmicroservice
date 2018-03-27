package com.xms.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User extends BaseDO {
	private static final long serialVersionUID = 4100424364780330747L;

	/* 用户名 */
	private String name;

	/* 年龄 */
	private int age;

	/* 住址 */
	private String address;

	public User() {
	}

	public User(Long id, Date gmtCreate, Date gmtModified, String features, String name, int age, String address) {
		super(id, gmtCreate, gmtModified, features);
		this.name = name;
		this.age = age;
		this.address = address;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
