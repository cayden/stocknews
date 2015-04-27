/**
 * NetTask.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.controller;


import com.stocknews.application.UIHandler;
import com.stocknews.constants.Constants;
import com.stocknews.jsoup.HtmlBaseInfo;
import com.stocknews.jsoup.JsoupNet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class NetTask implements Runnable {

    private Handler handler;
	

	public NetTask(Handler handler) {
		super();
		this.handler = handler;
	}



	@Override
	public void run() {
		  Message msg = new Message();
	      msg.what = UIHandler.RESPONSE_FAILED;
	      HtmlBaseInfo baseInfo=new HtmlBaseInfo();
	      try {
	    	  
	    	  baseInfo.setInfos(JsoupNet.getHtmlInfos());
	    	  msg.what = UIHandler.RESPONSE_SUCCESS;
              Bundle bundle=new Bundle();
              bundle.putSerializable(Constants.KEY_OBJ, baseInfo);
              msg.setData(bundle);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            handler.sendMessage(msg);
	        }
	}

}
