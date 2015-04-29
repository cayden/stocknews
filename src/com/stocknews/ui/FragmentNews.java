/**
 * FragmentNews.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-23
 */
package com.stocknews.ui;

import java.util.ArrayList;


import com.stocknews.R;
import com.stocknews.adapter.HtmlAdapter;
import com.stocknews.application.UIHandler;
import com.stocknews.constants.Constants;
import com.stocknews.controller.NetTask;
import com.stocknews.customerview.PullDownView;
import com.stocknews.customerview.PullDownView.OnPullDownListener;
import com.stocknews.customerview.ScrollOverListView;
import com.stocknews.jsoup.HtmlBaseInfo;
import com.stocknews.jsoup.HtmlInfo;
import com.stocknews.util.LogsUtil;
import com.stocknews.util.TipsUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * 要闻 
 * @author cuiran
 * @version 1.0.0
 */
public class FragmentNews extends Fragment  {
	private static final String TAG="FragmentNews";
	private PullDownView pullDownView;
	private ScrollOverListView listView;
	private HtmlAdapter  htmlAdapter;
	private ArrayList<HtmlInfo> data;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View v=getView();
		pullDownView=(PullDownView)v.findViewById(R.id.listview);
		pullDownView.enableAutoFetchMore(true, 0);
		pullDownView.enableLoadMore(true);
		listView=pullDownView.getListView();
		data=new ArrayList<HtmlInfo>();
		htmlAdapter=new HtmlAdapter(data, getActivity());
		listView.setAdapter(htmlAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				 HtmlInfo info=data.get(position);
				 Intent intent=new Intent(getActivity(),ActivityDetails.class);
				 Bundle bundle=new Bundle();
				 bundle.putSerializable(Constants.KEY_OBJ, info);
				 intent.putExtras(bundle);
				 startActivity(intent);
			}
			
			
		});
		
	pullDownView.setOnPullDownListener(new OnPullDownListener() {
			
			@Override
			public void onRefresh() {
				
//				pullDownView.notifyDidRefresh(false);
			}
			@Override
			public void onLoadMore() {
				
//				pullDownView.notifyDidLoadMore(false);
			}
		});
		initData();
		
	}
	
	private void initData(){
//		TipsUtil.showProgress(getActivity(), "正在加载...", "提示");
		new Thread(new NetTask(mHandler)).start();
	}
	
	
	Handler mHandler = new UIHandler(getActivity()) {

	        public void onMessage(Message msg) {
	            try{
//	            	TipsUtil.closeProgressDialog();
	            	
	            	switch(msg.what){
	            	case UIHandler.RESPONSE_SUCCESS:
	            		HtmlBaseInfo baseInfo=(HtmlBaseInfo)msg.getData().getSerializable(Constants.KEY_OBJ);
	            		data.addAll(baseInfo.getInfos());
	            		htmlAdapter.notifyDataSetChanged();
	            		break;
	            	case UIHandler.RESPONSE_FAILED:
	            		
	            		break;
	            	default:
	            			
	            		break;
	            	}
	            	pullDownView.notifyDidDataLoad(false);
	            }catch(Exception e){
	            	LogsUtil.d(TAG, "onMessage error", e);
	            }
	           
	        };
	    };
}
