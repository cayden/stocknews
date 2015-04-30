/**
 * NetBaseTask.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-29
 */
package com.stocknews.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.stocknews.application.App;
import com.stocknews.net.SingleHttpClient;
import com.stocknews.util.LogsUtil;
import com.stocknews.util.NetworkUtils;

import android.text.TextUtils;



/**
 * TODO 
 * @author cuiran
 * @version 1.0.0
 */
public abstract class NetBaseTask implements Runnable {
	private String loggerName = "NetBaseTask";
	
	private static final String TAG="NetBaseTask";
	
	static App application=App.getApplication();
	
	@Override
	public void run() {
		 boolean ret = doRequest();
		 if (!ret) {
			 onHttpResponseComplete(-1, null);
		 }
	}
	protected boolean doRequest() {
        int httpReturnStatusCode = -1;
        HttpUriRequest httpRequest = null;
        JSONObject jsonResponse = null;
        String responseBodyString = null;
        try {
        	/**
        	 * 先判断是否有网络，没有网络不需要请求服务端
        	 */
        	boolean isNet=NetworkUtils.isNetworkAvailable(application);
        	if(!isNet)return false;
            httpRequest = getUriRequest();
            if (null != httpRequest) {
            	
                HttpResponse httpResponse = SingleHttpClient.getInstance(application).execute(
                        httpRequest);

                httpReturnStatusCode = httpResponse.getStatusLine().getStatusCode();
                Header hce = httpResponse.getLastHeader("Content-Encoding");
                responseBodyString = retrieveInputStream(httpResponse.getEntity(), hce);
                if (!TextUtils.isEmpty(responseBodyString)) {
                    jsonResponse = new JSONObject(responseBodyString);
                }
                onHttpResponseComplete(httpReturnStatusCode, jsonResponse);
            }
        } catch (ConnectTimeoutException e) {
            LogsUtil.d(loggerName, "connection time out",e);
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            onHttpResponseComplete(httpReturnStatusCode, null);
        } finally {
            if (null != httpRequest) {
                LogsUtil.d(loggerName, "destUrl:" + httpRequest.getURI());
                LogsUtil.d(loggerName, "httpReturnStatusCode:" + httpReturnStatusCode);
                LogsUtil.d(loggerName, "class name:" + this.getClass().getName());
                LogsUtil.d(loggerName, "responseBodyString:" + responseBodyString);
            }

        }
        return true;
    }
	
	 private String retrieveInputStream(HttpEntity httpEntity, Header contetEncoding) {
	        StringBuffer stringBuffer = new StringBuffer();
	        try {
	            InputStream in = httpEntity.getContent();

	            InputStreamReader inputStreamReader = new InputStreamReader(in, HTTP.UTF_8);
	            char buffer[] = new char[4096];
	            int count;
	            while ((count = inputStreamReader.read(buffer, 0, 4096)) > 0) {
	                stringBuffer.append(buffer, 0, count);
	            }
	        } catch (UnsupportedEncodingException e) {
	            LogsUtil.d(loggerName, e.toString());
	        } catch (IllegalStateException e) {
	            LogsUtil.d(loggerName, e.toString());
	        } catch (IOException e) {
	            LogsUtil.d(loggerName, e.toString());
	        }
	        return stringBuffer.toString();
	    }
	
	protected abstract String getDestUrl();

	protected HttpUriRequest getUriRequest() {
	    	String destUrl=getDestUrl();
	    	if(TextUtils.isEmpty(destUrl)){
	    		return null;
	    	}else{
	    		 HttpUriRequest httpRequest = new HttpGet(destUrl);
	    	     httpRequest.addHeader("content-type", "application/json");
	    	     return httpRequest;
	    	}
	       
	 }
	
	 protected abstract void onHttpResponseComplete(int statusCode, JSONObject responseData);
}
