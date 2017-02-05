package com.tencent.multiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by warner on 04/02/2017.
 */

public class ServiceProcess extends Service {
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(getClass().toString(), "onCreate method");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(getClass().toString(), "onStartCommand method");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(getClass().toString(), "onDestroy method");
	}
}
