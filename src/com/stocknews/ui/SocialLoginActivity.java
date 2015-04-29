/**
 * SocialLoginActivity.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-29
 */
package com.stocknews.ui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.stocknews.R;
import com.stocknews.application.UIHandler;
import com.stocknews.constants.Constants;
import com.stocknews.jsoup.HtmlBaseInfo;
import com.stocknews.util.LogsUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 社保登陆 
 * @author cuiran
 * @version 1.0.0
 */
public class SocialLoginActivity extends Activity {
	private static final String TAG="SocialLoginActivity";
	
	private ImageView viewCode=null;
	
	private Bitmap bitmap=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		viewCode=(ImageView)findViewById(R.id.imageCode);
		
		new Thread(new GetCode(Constants.GET_CODE_URL)).start();
		
		
	}
	
	Handler mHandler = new UIHandler(this) {

        public void onMessage(Message msg) {
            try{
            	
            	switch(msg.what){
            	case UIHandler.RESPONSE_SUCCESS:
            		viewCode.setImageBitmap(bitmap);
            		break;
            	case UIHandler.RESPONSE_FAILED:
            		
            		break;
            	default:
            			
            		break;
            	}
            	
            }catch(Exception e){
            	LogsUtil.d(TAG, "onMessage error", e);
            }
           
        };
    };

	class GetCode implements Runnable{
		
		String url;
		
		
		public GetCode(String url) {
			super();
			this.url = url;
		}


		@Override
		public void run() {
			bitmap=returnBitMap(url);
			Message msg=new Message();
			msg.what=UIHandler.RESPONSE_SUCCESS;
			mHandler.sendMessage(msg);
		}
		
	}
	
	public Bitmap returnBitMap(String url){
		URL myFileUrl=null;
		Bitmap bitmap=null;
		try{
			myFileUrl=new URL(url);
			HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is=conn.getInputStream();
			bitmap=BitmapFactory.decodeStream(is);
			is.close();
			LogsUtil.i(TAG, "get bitmap success");
		}catch(Exception e){
			LogsUtil.e(TAG, "returnBitMap", e);
		}
		
		return bitmap;
	}
	
}
