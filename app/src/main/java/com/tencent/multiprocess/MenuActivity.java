package com.tencent.multiprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tencent.multiprocess.aidlexample.AidlExampleActivity;
import com.tencent.multiprocess.tag.AidlTagActivity;
import com.tencent.multiprocess.tag.RemoteProcessActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by warner on 07/02/2017.
 */

public class MenuActivity extends Activity implements View.OnClickListener {
	@Bind(R.id.serviceTextView)
	TextView serviceTextView;
	@Bind(R.id.aidlTagTextView)
	TextView aidlTagTextView;
	@Bind(R.id.remoteTextView)
	TextView remoteTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ButterKnife.bind(this);
		setClickEvent();
	}

	private void setClickEvent() {
		serviceTextView.setOnClickListener(this);
		aidlTagTextView.setOnClickListener(this);
		remoteTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.serviceTextView:
				Intent aidlIntent = new Intent(this, AidlExampleActivity.class);
				startActivity(aidlIntent);
				break;
			case R.id.aidlTagTextView:
				Intent tagIntent = new Intent(this, AidlTagActivity.class);
				startActivity(tagIntent);
				break;
			case R.id.remoteTextView:
				Intent remoteIntent = new Intent(this, RemoteProcessActivity.class);
				startActivity(remoteIntent);
				break;
			default:
				break;
		}
	}
}
