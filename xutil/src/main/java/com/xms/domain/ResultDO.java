package com.xms.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 所有结果的基类
 */
public class ResultDO<T> implements Serializable {
	private static final long serialVersionUID = 5399155522865556998L;

	/* 是否成功 */
	private boolean success;

	/* 错误码 */
	private int code;

	/* 错误信息 */
	private String message;

	/* 结果数据 */
	private T data;

	/**
	 * 缺省结果为成功
	 */
	public ResultDO() {
		this.success = true;
	}

	/**
	 * 成功结果
	 */
	public ResultDO(T data) {
		this.data = data;
		this.success = true;
	}

	/**
	 * 失败结果
	 */
	public ResultDO(int code, String message) {
		this.code = code;
		this.message = message;
		this.success = false;
	}

	public ResultDO(boolean success, int code, String message, T data) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
