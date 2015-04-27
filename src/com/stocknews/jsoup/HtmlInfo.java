/**
 * HtmlInfo.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.jsoup;

import java.io.Serializable;

/**
 * html message 
 * @author cuiran
 * @version 1.0.0
 */
public class HtmlInfo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5185864172253189448L;
	private int id;
	private String subject;
	private String content;
	private String link;
	private String createDate;
	private String isread="0";
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsread() {
		return isread;
	}
	public void setIsread(String isread) {
		this.isread = isread;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
