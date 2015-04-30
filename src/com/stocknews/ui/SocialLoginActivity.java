/**
 * SocialLoginActivity.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-29
 */
package com.stocknews.ui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import com.stocknews.R;
import com.stocknews.application.UIHandler;
import com.stocknews.constants.Constants;
import com.stocknews.controller.LoginTask;
import com.stocknews.jsoup.HtmlBaseInfo;
import com.stocknews.jsoup.HtmlInfo;
import com.stocknews.util.LogsUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * social login
 * @author cuiran
 * @version 1.0.0
 */
public class SocialLoginActivity extends Activity implements OnClickListener{
	private static final String TAG="SocialLoginActivity";
	
	private ImageView viewCode=null;
	private EditText idcardEdit,passwordEdit,codeEdit;
	private Bitmap bitmap=null;
	private Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		viewCode=(ImageView)findViewById(R.id.imageCode);
		idcardEdit=(EditText)findViewById(R.id.idcard);
		passwordEdit=(EditText)findViewById(R.id.password);
		codeEdit=(EditText)findViewById(R.id.code);
		
		login=(Button)findViewById(R.id.login);
		login.setOnClickListener(this);
		new Thread(new GetCode(Constants.GET_CODE_URL)).start();
		
		EventBus.getDefault().register(this);
		
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

	private void submitLogin(){
		String idcard=idcardEdit.getText().toString();
		String password=passwordEdit.getText().toString();
		String code=codeEdit.getText().toString();
		
		new Thread(new LoginTask(idcard, password, code)).start();
	}
	
	@Subscriber
	private void updateUser(HtmlInfo info ){
		LogsUtil.i(TAG, "info!!"+info.getSubject());
	}
	
	@Subscriber(tag = "my_tag")
	private void updateUserWithTag(HtmlInfo info ){
		LogsUtil.i(TAG, "info!!!"+info.getSubject());
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.login:
			submitLogin();
			break;
		}
		
	}

	@Override
	protected void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
	
	
}
