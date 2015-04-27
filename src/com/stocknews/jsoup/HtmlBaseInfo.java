/**
 * HtmlBaseInfo.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.jsoup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO 
 * @author cuiran
 * @version 1.0.0
 */
public class HtmlBaseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1564357345299675359L;
	private ArrayList<HtmlInfo> infos;
	public ArrayList<HtmlInfo> getInfos() {
		return infos;
	}
	public void setInfos(ArrayList<HtmlInfo> infos) {
		this.infos = infos;
	}
	
}
