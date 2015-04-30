/**
 * LoginTask.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-29
 */
package com.stocknews.controller;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;


import com.stocknews.constants.Constants;
import com.stocknews.util.LogsUtil;

/**
 * TODO 
 * @author cuiran
 * @version 1.0.0
 */
public class LoginTask extends NetBaseTask {
	private static final String TAG="LoginTask";
	private String idcard;
	private String password;
	private String code;
    private String requestUrl;
    
    
    
	public LoginTask(String idcard, String password, String code) {
		super();
		this.idcard = idcard;
		this.password = password;
		this.code = code;
		
		requestUrl=Constants.LOGIN_URL;
	}

	@Override
	protected String getDestUrl() {
		
		return requestUrl;
	}

	@Override
	protected void onHttpResponseComplete(int statusCode,
			JSONObject responseData) {
		

	}

    @Override
    protected HttpUriRequest getUriRequest() {
        HttpPost httpPut = null;
        JSONObject postString = getPostData();
        if (postString != null) {
            httpPut = new HttpPost(getDestUrl());
            LogsUtil.d(TAG, "post-data:" + postString);
            httpPut.addHeader("content-type", "application/json");
            byte[] data = postString.toString().getBytes();
            ByteArrayEntity entity = new ByteArrayEntity(data);
            entity.setContentType("application/json");
            entity.setContentEncoding("UTF-8");
            httpPut.setEntity(entity);
        }
        return httpPut;
    }
	
	 protected JSONObject getPostData() {
	        JSONObject ret = new JSONObject();
	        try {
	            ret.put("type", "1");
	            ret.put("flag", "3");
	            ret.put("j_username", idcard);
	            ret.put("j_password", password);
	            ret.put("safecode", code);
	        } catch (JSONException e) {
	          LogsUtil.e(TAG, "getPostData error", e);
	        }
	        return ret;
	    }

}
