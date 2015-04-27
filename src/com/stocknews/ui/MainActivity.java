package com.stocknews.ui;

import java.util.ArrayList;
import java.util.List;


import com.stocknews.R;
import com.stocknews.adapter.MainPageAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity  extends FragmentActivity implements
OnPageChangeListener, OnCheckedChangeListener {

private ViewPager pager;
private MainPageAdapter adapter;
private List<Fragment> fragments;
private RadioGroup group;
private RadioButton button0, button1, button2,button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentNews());
		fragments.add(new FragmentReport());
		fragments.add(new FragmentStock());
		fragments.add(new FragmentCompany());
		
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(fragments.size() - 1);// 缓存页面,显示第一个缓存最后一个
		pager.setOnPageChangeListener(this);
		
		group = (RadioGroup) findViewById(R.id.radioGroup1);
		button0 = (RadioButton) findViewById(R.id.radio0);
//		Button btn=(Button)findViewById(R.id.bt);
//		BadgeView	  badge4 = new BadgeView(this, btn);
//		badge4.setText("...");
//		badge4.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//		badge4.show();
		button1 = (RadioButton) findViewById(R.id.radio1);
		button2 = (RadioButton) findViewById(R.id.radio2);
		button3 = (RadioButton) findViewById(R.id.radio3);
		group.setOnCheckedChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		Log.v("asdf", "onPageScrollStateChanged");
	}
	
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// Log.v("asdf", "onPageScrolled");
	}
	
	@Override
	public void onPageSelected(int arg0) {
		Log.v("asdf", "onPageSelected");
		getTabState(arg0);
	
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radio0:
			pager.setCurrentItem(0);
			break;
		case R.id.radio1:
			pager.setCurrentItem(1);
			break;
		case R.id.radio2:
			pager.setCurrentItem(2);
			break;
		case R.id.radio3:
			pager.setCurrentItem(3);
			break;
		default:
			break;
		}
	}
	
	private void getTabState(int index) {
		button0.setChecked(false);
		button1.setChecked(false);
		button2.setChecked(false);
		button3.setChecked(false);
		switch (index) {
		case 0:
			button0.setChecked(true);
			break;
		case 1:
			button1.setChecked(true);
			break;
		case 2:
			button2.setChecked(true);
			break;
		case 3:
			button3.setChecked(true);
			break;
		default:
			break;
		}
	
	}
}
