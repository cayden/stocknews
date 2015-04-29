/**
 * App.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-29
 */
package com.stocknews.application;



import android.app.Application;

/**
 * TODO 
 * @author cuiran
 * @version 1.0.0
 */
public class App extends Application {

	private static final String TAG="EduApplication";

    private static App application = null;
    
    public static App getApplication() {
        return application;
    }

	@Override
	public void onCreate() {
		application=this;
		super.onCreate();
	}
    
    
}
