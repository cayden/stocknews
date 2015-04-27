
package com.stocknews.application;

import java.lang.ref.WeakReference;

import com.stocknews.util.LogsUtil;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;


public abstract class UIHandler extends Handler implements Parcelable {
    public static final int TAB_NONE = -1;

    public static final int RESPONSE_SUCCESS = 1;

    public static final int RESPONSE_UNKNOWN_ERROR = -1;

    public static final int RESPONSE_TIMEOUT = -2;

    // Tab for features

    public static final int TAB_APP = 0;

    public static final int TAB_MSG = TAB_APP + 1;

    public static final int RESPONSE_FAILED = TAB_MSG + 1;

    public static final int MSG_APP_CHANGED = RESPONSE_FAILED + 1;

    public static final int APP_UPDATE_MSG = MSG_APP_CHANGED + 1;

    public static final int ADD_DEVICE_SUCCESS = APP_UPDATE_MSG + 1;

    public static final int UPDATE_NICKNAME_SUCCESS = ADD_DEVICE_SUCCESS + 1;

    public static final int FETCH_DEVICE_SUCCESS = UPDATE_NICKNAME_SUCCESS + 1;

    public static final int DELETE_DEVICE_SUCCESS = FETCH_DEVICE_SUCCESS + 1;

    public static final int FETCH_DEVICE_APP = DELETE_DEVICE_SUCCESS + 1;

    public static final int FETCH_APP_USE_SUCCESS = FETCH_DEVICE_APP + 1;

    public static final int FETCH_APP_USE_FAILED = FETCH_APP_USE_SUCCESS + 1;

    public static final int RESPONSE_FAILED_NO_REASON = FETCH_APP_USE_FAILED + 1;

    //
    public static final int NO_CONNECT_INTERNET = 13;

    public static final int VIEW_CONTROL = 14;

    public static final int LOGIN_SUCCESS = 15;

    public static final int REG_SUCC_TOKEN = 16;

    public static final int REGISTER_CONNECT_TIMEOUT = 18;

    public static final int GET_CLIENTID = 19;

    // 对话框标识
    public static final int CANCEL_REQUEST_DIALOG = 20;

    public static final int SHOW_REQUEST_DIALOG = 21;

    public static final int SHOW_REQUESTED_DIALOG = 22;

    public static final int MSG_NETWORK_INVALID = 23;

    // status code
    public static final int VERIFICATION_STATU_CODE_403 = 403;

    public static final int AUTHEN_FAILED = 401;

    public static final int DEVICE_NOT_FOUND = 404;

    public static final int DEVICE_NOT_ACTIVE = 409;

    public static final int PASSCODE_EMAIL_MISMATCH = DEVICE_NOT_ACTIVE + 1;

    private static final String loggerName = "UIHandler";

    private WeakReference<Activity> mActivity;

    private String componentName;

    public UIHandler(Activity activity) {
        this.mActivity = new WeakReference<Activity>(activity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Activity getActivity() {
        return mActivity.get();
    }

    public String getComponentName() {
        return componentName;
    }

    @Override
    public void handleMessage(Message paramMessage) {
        LogsUtil.d(loggerName, "Recieved message  " + paramMessage.what);
        onMessage(paramMessage);
    }

    public abstract void onMessage(Message paramMessage);

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Override
    public void writeToParcel(Parcel paramParcel, int paramInt) {
    }
}
