package com.tencent.multiprocess.tag;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tencent.multiprocess.R;
import com.tencent.multiprocess.component.FetchBookInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by warner on 11/02/2017.
 */

public class RemoteProcessActivity extends Activity implements FetchBookInfo.FetchBookInfoCallback {

	@Bind(R.id.bookTextView)
	TextView bookTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote_process);
		ButterKnife.bind(this);
		initBookInfo();
	}

	private void initBookInfo() {

		FetchBookInfo fetchBookInfo = new FetchBookInfo(this, this);
		fetchBookInfo.getBookInfo();
	}

	@Override
	public void fetchBookInfoFail() {

	}

	@Override
	public void fetchBookInfoSuccess(BookInfo bookInfo) {

		if (bookInfo != null) {
			Log.i(getClass().toString(), "this is book info from remote process : " + bookInfo.getName());
			bookTextView.setText(bookInfo.getName());
		}
	}
}
