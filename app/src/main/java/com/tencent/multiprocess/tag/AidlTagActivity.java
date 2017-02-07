package com.tencent.multiprocess.tag;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.multiprocess.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by warner on 07/02/2017.
 */

public class AidlTagActivity extends Activity implements View.OnClickListener {
	@Bind(R.id.inTextView)
	TextView inTextView;
	@Bind(R.id.outTextView)
	TextView outTextView;
	@Bind(R.id.inoutTextView)
	TextView inoutTextView;

	private ServiceConnection tagServiceConnection;
	private IAidlTagInterface tagBinder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aidl_tag);
		ButterKnife.bind(this);
		bindClickEvent();
		initServiceProcess();
	}

	private void bindClickEvent() {
		inTextView.setOnClickListener(this);
		outTextView.setOnClickListener(this);
		inoutTextView.setOnClickListener(this);
	}

	private void initServiceProcess() {

		tagServiceConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {

				tagBinder = IAidlTagInterface.Stub.asInterface(service);
				Log.i(getClass().toString(), "service connected");
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {

			}
		};
		Intent intent = new Intent(this, AidlTagService.class);
		startService(intent);
		bindService(intent, tagServiceConnection, BIND_AUTO_CREATE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.inTextView:
				inTag();
				break;
			case R.id.outTextView:
				outTag();
				break;
			case R.id.inoutTextView:
				inoutTag();
				break;
		}
	}

	private void inTag() {

		BookInfo bookInfo = getBookInfo("AIDL IN", 10);
		try {
			tagBinder.addBookIn(bookInfo);
		} catch (Exception e) {

		}
		Log.i(getClass().toString(),bookInfo.toString());
	}

	private void outTag() {

		BookInfo bookInfo = getBookInfo("AIDL OUT", 20);
		try {
			tagBinder.addBookOut(bookInfo);
		} catch (Exception e) {

		}
		Log.i(getClass().toString(),bookInfo.toString());
	}

	private void inoutTag() {

		BookInfo bookInfo = getBookInfo("AIDL INOUT", 30);
		try {
			tagBinder.addBookInOut(bookInfo);
		} catch (Exception e) {

		}
		Log.i(getClass().toString(),bookInfo.toString());
	}

	private BookInfo getBookInfo(String bookName, int price) {
		BookInfo bookInfo = new BookInfo();
		bookInfo.setName(bookName);
		bookInfo.setPrice(price);
		return bookInfo;
	}
}
