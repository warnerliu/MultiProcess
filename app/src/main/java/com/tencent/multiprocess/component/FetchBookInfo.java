package com.tencent.multiprocess.component;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.tencent.multiprocess.tag.BookInfo;
import com.tencent.multiprocess.tag.IAidlTagInterface;

/**
 * Created by warner on 11/02/2017.
 */

public class FetchBookInfo {

	public interface FetchBookInfoCallback {
		void fetchBookInfoSuccess(BookInfo bookInfo);

		void fetchBookInfoFail();
	}

	private FetchBookInfoCallback callback;
	private Context context;
	private BinderConfig binderConfig;

	public FetchBookInfo(Context context, FetchBookInfoCallback callback) {

		this.context = context;
		this.callback = callback;
	}

	public void getBookInfo() {

		/**
		 * 调用者不需要关心从哪里获取book info，只需调用getBookInfo方法
		 * 1、当前进程
		 * 2、多进程通信，必须等onServiceConnected回调完成后才可以获取IBinder对象
		 * 还有一点需要注意，更新UI操作必须在主线程中，所以需要将回调post到主线程中
		 * */

		ServiceConnection tagServiceConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {

				IAidlTagInterface tagBinder = IAidlTagInterface.Stub.asInterface(service);
				Log.i(getClass().toString(), "service connected");
				if (tagBinder == null) {
					if (callback != null) {
						callback.fetchBookInfoFail();
					}
				}
				BookInfo bookInfo = null;
				try {
					bookInfo = tagBinder.getBookInfo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (callback != null) {
					callback.fetchBookInfoSuccess(bookInfo);
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {

			}
		};
		binderConfig = new BinderConfig(context, tagServiceConnection);
	}
}
