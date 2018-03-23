package com.xms.usercenter.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseDO implements Serializable {
	private static final long serialVersionUID = -35001556314421743L;

	private static final String FEATURE_KV_SPLIT_MARK = ":";

	private static final String FEATURE_ITEM_SPLIT_MARK = ";";

	/* 主键 */
	protected Long id;

	/* 创建时间 */
	protected Date gmtCreate;

	/* 修改时间 */
	protected Date gmtModified;

	/* 特征字段 k1:v1;k2:v2;k3:v3,v4,v5; */
	protected String features;

	/* 特征map存储 */
	private Map<String, String> featureMap = new HashMap<String, String>();

	public BaseDO() {
	}

	public BaseDO(Long id, Date gmtCreate, Date gmtModified, String features) {
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.features = features;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 添加某个特征
	 * 
	 * @param key
	 * @param value
	 */
	public void addFeature(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return;
		}

		featureMap.put(key, value);
	}

	/**
	 * 移除某个特征
	 * 
	 * @param key
	 */
	public String removeFeature(String key) {
		return featureMap.remove(key);
	}

	/**
	 * 获取指定特征值
	 * 
	 * @param key
	 * @return
	 */
	public String getFeature(String key) {
		return featureMap.get(key);
	}

	/**
	 * 获取指定特征整数值
	 * 
	 * @param key
	 * @return
	 */
	public Integer getIntFeature(String key) {
		String value = featureMap.get(key);
		if (StringUtils.isBlank(value)) {
			return null;
		}

		return Integer.valueOf(value);
	}

	/**
	 * 获取指定特征长整数值
	 * 
	 * @param key
	 * @return
	 */
	public Long getLongFeature(String key) {
		String value = featureMap.get(key);
		if (StringUtils.isBlank(value)) {
			return null;
		}

		return Long.valueOf(value);
	}

	/**
	 * 判断是否有某个特征值
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasFeature(String key) {
		return StringUtils.isNotBlank(featureMap.get(key));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * 从map中获取特征字符串
	 * @return
	 */
	public String getFeatures() {
		if (featureMap.isEmpty()) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : featureMap.entrySet()) {
			sb.append(entry.getKey()).append(FEATURE_KV_SPLIT_MARK).append(entry.getValue())
					.append(FEATURE_ITEM_SPLIT_MARK);
		}

		return sb.toString();
	}

	/**
	 * 设置特征字符串，转成map
	 * @param features
	 */
	public void setFeatures(String features) {
		if (StringUtils.isBlank(features)) {
			return;
		}

		String[] items = features.split(FEATURE_ITEM_SPLIT_MARK);
		if (items == null || items.length == 0) {
			return;
		}

		for (String item : items) {
			String[] kv = item.split(FEATURE_KV_SPLIT_MARK);
			if (kv != null && kv.length == 2 && StringUtils.isNotBlank(kv[0]) && StringUtils.isNotBlank(kv[1])) {
				featureMap.put(kv[0], kv[1]);
			}
		}
	}

}
