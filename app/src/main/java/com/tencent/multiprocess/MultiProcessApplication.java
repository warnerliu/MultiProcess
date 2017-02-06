package com.tencent.multiprocess;

import android.app.Application;
import android.util.Log;

/**
 * Created by warner on 05/02/2017.
 */

public class MultiProcessApplication extends Application {
	/**
	 * 在设置了组件的android:process属性后，都会执行一次onCreate方法，所以该方法里的初始化逻辑要谨慎
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(getClass().toString(), "process pid === " + android.os.Process.myPid());
	}
}
