package com.tencent.multiprocess.mylooper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.multiprocess.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HandlerLooperActivity extends AppCompatActivity {

	@Bind(R.id.textView)
	TextView textView;
	private LooperThread myLooperThread;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_looper);
		ButterKnife.bind(this);
		myLooperThread = new LooperThread();
		myLooperThread.start();
//		for (int index = 0; index < 5; index++) {
//			Message msg = new Message();
//			msg.obj = "this is from UI thread===" + index;
//			mChildHandler.sendMessage(msg);
//		}
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Message msg = new Message();
				msg.obj = "msg from UI thread";
				myLooperThread.getmChildHandler().sendMessage(msg);
			}
		});
	}

	private class LooperThread extends Thread {
		private Handler mChildHandler;

		public Handler getmChildHandler() {
			return mChildHandler;
		}

		@Override
		public void run() {
			Looper.prepare();
			mChildHandler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					//if you want to use toast in non UI thread, you should call Looper.prepare and Looper.loop, add toast to a message queue
					Toast.makeText(HandlerLooperActivity.this, "msg === " + msg.obj.toString(), Toast.LENGTH_LONG).show();
					Log.i(getClass().toString(), msg.obj.toString());
					super.handleMessage(msg);
				}
			};
			Log.i(getClass().toString(), "this log is before Looper.loop() method");
			Looper.loop();
			Log.i(getClass().toString(), "this log is after Looper.loop() method, never execute until the loop quit");
			super.run();
		}
	}
}
