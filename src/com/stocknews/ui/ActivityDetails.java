/**
 * ActivityDetails.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.ui;



import com.stocknews.R;
import com.stocknews.constants.Constants;
import com.stocknews.customerview.CustomWebView;
import com.stocknews.jsoup.HtmlInfo;
import com.stocknews.jsoup.JsoupNet;
import com.stocknews.util.LogsUtil;
import com.stocknews.util.NetworkUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class ActivityDetails extends Activity implements OnClickListener {
	private static final String TAG="ActivityDetails";
    private CustomWebView customWv;

    private String htmlUrl;


    private RelativeLayout relativeFail;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        initViews();
        setListener();
       
        loadData();
    }

    /**
     * 
     */
    private void loadData() {
        Intent intent = getIntent();
        HtmlInfo lcoal = null;
        if (intent != null) {
            lcoal = (HtmlInfo)intent.getSerializableExtra(Constants.KEY_OBJ);
            htmlUrl = lcoal.getLink();
        }
        if(!NetworkUtils.isDownLoad(this)){
        	customWv.setVisibility(View.GONE);
        	relativeFail.setVisibility(View.VISIBLE);
        	return;
        }else{
        	 customWv.setVisibility(View.VISIBLE);
        	 relativeFail.setVisibility(View.GONE);
        	
        }
       
        customWv.getSettings().setJavaScriptEnabled(true);
        customWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
            	/**
            	 * 先调用系统浏览器
            	 */
//               Intent intent=new Intent("android.intent.action.VIEW");
//               intent.setAction("android.intent.action.VIEW");
//               Uri content_url = Uri.parse(url);
//               intent.setData(content_url);
//               startActivity(intent);
                return true;
            }
        });
//        htmlUrl=JsoupNet.getHtml(htmlUrl);
//        LogsUtil.d(TAG, htmlUrl);
//        customWv.loadData(htmlUrl, "text/html", "GBK");
        customWv.loadUrl(htmlUrl);
    }

    /**
     * 
     */
    private void setListener() {

        relativeFail.setOnClickListener(this);
    }

    /**
     * 
     */
    private void initViews() {
        customWv = (CustomWebView)findViewById(R.id.webview_message);
        relativeFail=(RelativeLayout)findViewById(R.id.relativeFail);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            
            case R.id.relativeFail:
            	loadData();
            	break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (customWv.canGoBack()) {
            customWv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}