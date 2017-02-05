package com.tencent.multiprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener {

	@Bind(R.id.startTextView)
	TextView startTextView;
	@Bind(R.id.stopTextView)
	TextView stopTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initClickListener();
	}

	private void initClickListener() {
		startTextView.setOnClickListener(this);
		stopTextView.setOnClickListener(this);
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
			default:
				break;
		}
	}
}
