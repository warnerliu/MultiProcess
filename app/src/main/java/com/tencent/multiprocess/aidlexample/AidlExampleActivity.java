package com.tencent.multiprocess.aidlexample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.multiprocess.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AidlExampleActivity extends Activity implements View.OnClickListener {

	@Bind(R.id.startTextView)
	TextView startTextView;
	@Bind(R.id.stopTextView)
	TextView stopTextView;
	@Bind(R.id.bindTextView)
	TextView bindTextView;
	@Bind(R.id.unbindTextView)
	TextView unbindTextView;

	private ServiceConnection serviceConnection;
	private IMyAidlInterface iMyAidlInterface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_aidl);
		Log.i(getClass().toString(), "thread id === " + Thread.currentThread().getId());
		ButterKnife.bind(this);
		initClickListener();
		initServiceConnection();
	}

	private void initClickListener() {
		startTextView.setOnClickListener(this);
		stopTextView.setOnClickListener(this);
		bindTextView.setOnClickListener(this);
		unbindTextView.setOnClickListener(this);
	}

	private void initServiceConnection() {

		final IMyAidlCallback callback = new IMyAidlCallback.Stub() {
			@Override
			public void getPerson(Person p) throws RemoteException {
				Log.i(getClass().toString(), "callback value === " + p.getName() + " === " + p.getSex());
			}
		};
		serviceConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {

				iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
				Log.i(getClass().toString(), "onServiceConnected === " + service.toString());
				try {
					iMyAidlInterface.plus(10, 18);
					Person person = new Person();
					person.setName("warner");
					person.setSex("male");
					iMyAidlInterface.printPerson(person);
					iMyAidlInterface.setCallback(callback);
				} catch (Exception e) {

				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.i(getClass().toString(), "onServiceDisconnected");
			}
		};
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.startTextView:
				Intent startIntent = new Intent(this, ServiceProcess.class);
				startService(startIntent);
				break;
			case R.id.stopTextView:
				Intent stopIntent = new Intent(this, ServiceProcess.class);
				stopService(stopIntent);
				break;
			case R.id.bindTextView:
				Intent bindIntent = new Intent(this, ServiceProcess.class);
				bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
				break;
			case R.id.unbindTextView:
				unbindService(serviceConnection);
				break;
			default:
				break;
		}
	}
}
