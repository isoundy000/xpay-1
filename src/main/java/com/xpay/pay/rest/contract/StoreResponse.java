package com.xpay.pay.rest.contract;

import java.util.List;

import com.xpay.pay.model.StoreChannel;

public class StoreResponse {
	private long id;
	private String code;
	private String name;
	private int bailPercentage;
	private long appId;
	private String csrTel;
	private String proxyUrl;
	private List<StoreChannel> channels;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBailPercentage() {
		return bailPercentage;
	}
	public void setBailPercentage(int bailPercentage) {
		this.bailPercentage = bailPercentage;
	}
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public String getCsrTel() {
		return csrTel;
	}
	public void setCsrTel(String csrTel) {
		this.csrTel = csrTel;
	}
	public String getProxyUrl() {
		return proxyUrl;
	}
	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}
	public List<StoreChannel> getChannels() {
		return channels;
	}
	public void setChannels(List<StoreChannel> channels) {
		this.channels = channels;
	}
}
