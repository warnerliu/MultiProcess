package com.tencent.multiprocess.component;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import com.tencent.multiprocess.tag.AidlTagService;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by warner on 11/02/2017.
 */

public class BinderConfig {

	public BinderConfig(Context context, ServiceConnection tagServiceConnection) {
		Intent intent = new Intent(context, AidlTagService.class);
		context.bindService(intent, tagServiceConnection, BIND_AUTO_CREATE);
	}
}
