/**
 * NetworkUtils.java
 * Copyright (C) 2015
 * All right reserved. 2015-1-7
 */
package com.stocknews.util;


import java.util.List;



import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * 监测网络、状态的工具类 
 * @author cuiran
 * @version 1.0.0
 */
public class NetworkUtils {
	
	public static boolean isDownLoad(Context context){
		if(isNetworkAvailable(context)){
			
			return true;
		}else{
			
			ToastUtils.showShortToast(context, "当前无网络连接,请连接后重试!");
			return false;
		}
	}
	
	/** 
     * 网络是否可用 
     *  
     * @param activity 
     * @return 
     */  
    public static boolean isNetworkAvailable(Context context) {  
        ConnectivityManager connectivity = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (connectivity == null) {  
        } else {  
            NetworkInfo[] info = connectivity.getAllNetworkInfo();  
            if (info != null) {  
                for (int i = 0; i < info.length; i++) {  
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {  
                        return true;  
                    }  
                }  
            }  
        }  
        return false;  
    }  
  
  
    /** 
     * Gps是否打开 
     *  
     * @param context 
     * @return 
     */  
    public static boolean isGpsEnabled(Context context) {  
        LocationManager locationManager = ((LocationManager) context  
                .getSystemService(Context.LOCATION_SERVICE));  
        List<String> accessibleProviders = locationManager.getProviders(true);  
        return accessibleProviders != null && accessibleProviders.size() > 0;  
    }  
  
    /** 
     * wifi是否打开 
     */  
    public static boolean isWifiEnabled(Context context) {  
        ConnectivityManager mgrConn = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        TelephonyManager mgrTel = (TelephonyManager) context  
                .getSystemService(Context.TELEPHONY_SERVICE);  
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn  
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel  
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);  
    }  
  
    /** 
     * 判断当前网络是否是wifi网络 
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) {  
     *  
     * @param context 
     * @return boolean 
     */  
    public static boolean isWifi(Context context) {  
        ConnectivityManager connectivityManager = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();  
        if (activeNetInfo != null  
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {  
            return true;  
        }  
        return false;  
    }  
  
    /** 
     * 判断当前网络是否3G网络 
     *  
     * @param context 
     * @return boolean 
     */  
    public static boolean is3G(Context context) {  
        ConnectivityManager connectivityManager = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();  
        if (activeNetInfo != null  
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {  
            return true;  
        }  
        return false;  
    }  
    /**
     * 
     * 获取所连接的WIFI信息
     * @param context
     * @return
     *
     */
    public static WifiInfoMsg getWifiInfo(Context context){
    	
    	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
    	WifiInfo info = wifi.getConnectionInfo();  
    	if(info==null)return null;
    	WifiInfoMsg wifiInfo=new WifiInfoMsg();
    	wifiInfo.setMac(info.getMacAddress());
    	String ssid=info.getSSID();
    	ssid=ssid.replace("\"", "");
    	wifiInfo.setSsid(ssid);
    	wifiInfo.setIp(intToIp(info.getIpAddress()));
    	wifiInfo.setNetworkID(info.getNetworkId());
    	wifiInfo.setSpeed(info.getLinkSpeed());
    	if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED)  
    	{  
    		wifiInfo.setStatus(0);
    	}else{
    		wifiInfo.setStatus(1);
    	}
    	return wifiInfo;
    }
    private static String intToIp(int ip)  
    {  
    	return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "."  
    			+ ((ip >> 24) & 0xFF);  
    }  
    
    /**
     * 打开网络设置界面
     * */
    public static void setNetworkMethod(final Context context){
        //提示对话框
        AlertDialog.Builder builder=new Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本 
                if(android.os.Build.VERSION.SDK_INT>10){
                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

}

