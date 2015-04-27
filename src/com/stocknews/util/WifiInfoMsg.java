/**
 * WifiInfoMsg.java
 * Copyright (C) 2015
 * All right reserved. 2015-1-7
 */
package com.stocknews.util;

import java.io.Serializable;

/**
 * WifiInfoMsg 
 * @author cuiran
 * @version 1.0.0
 */
public class WifiInfoMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288179990468588593L;

	private String mac;
	private String ssid;
	private String ip;
	private int status;//0 连接 1 未连接
	private int networkID;
	private int speed;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNetworkID() {
		return networkID;
	}
	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}
