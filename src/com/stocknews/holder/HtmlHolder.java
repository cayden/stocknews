/**
 * HtmlHolder.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.holder;

import android.widget.TextView;

/**
 * HtmlHolder 
 * @author cuiran
 * @version 1.0.0
 */
public class HtmlHolder {

	private TextView subject_tv;
	private TextView content_tv;
	public TextView getSubject_tv() {
		return subject_tv;
	}
	public void setSubject_tv(TextView subject_tv) {
		this.subject_tv = subject_tv;
	}
	public TextView getContent_tv() {
		return content_tv;
	}
	public void setContent_tv(TextView content_tv) {
		this.content_tv = content_tv;
	}
}
