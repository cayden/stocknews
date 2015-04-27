/**
 * HtmlAdapter.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.adapter;

import java.util.ArrayList;
import java.util.List;


import com.stocknews.R;
import com.stocknews.holder.HtmlHolder;
import com.stocknews.jsoup.HtmlInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * TODO 
 * @author cuiran
 * @version 1.0.0
 */
public class HtmlAdapter extends BaseAdapter {

	private ArrayList<HtmlInfo> data;
	private Context context = null;
	private LayoutInflater inflater = null;
	private HtmlHolder holder;
	public HtmlAdapter(ArrayList<HtmlInfo> data, Context context) {
		super();
		this.data = data;
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		inflater = LayoutInflater.from(this.context);
		if (convertView == null) {
			holder=new HtmlHolder();
			convertView = inflater.inflate(R.layout.fragment_item, null);
			
			holder.setSubject_tv((TextView)convertView.findViewById(R.id.subject_tv));
				
			
			convertView.setTag(holder);
		}else{
			holder=(HtmlHolder)convertView.getTag();
		}
		HtmlInfo info=data.get(position);
		holder.getSubject_tv().setText("• "+info.getSubject());
		
		
		return convertView;
	}
}
