package com.tencent.multiprocess.tag;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by warner on 07/02/2017.
 */

public class AidlTagService extends Service {
	private AidlTagBinder iBinder;

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return iBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		iBinder = new AidlTagBinder();
		Log.i(getClass().toString(),"tag service create");
	}

	class AidlTagBinder extends IAidlTagInterface.Stub {
		@Override
		public BookInfo addBookIn(BookInfo book) throws RemoteException {
			Log.i(getClass().toString(),book.toString());
			book.setName("SERVICE AIDL IN");
			return book;
		}

		@Override
		public BookInfo addBookOut(BookInfo book) throws RemoteException {
			Log.i(getClass().toString(),book.toString());
			book.setName("SERVICE AIDL OUT");
			return book;
		}

		@Override
		public BookInfo addBookInOut(BookInfo book) throws RemoteException {
			Log.i(getClass().toString(),book.toString());
			book.setName("SERVICE AIDL INOUT");
			return book;
		}

		@Override
		public BookInfo getBookInfo() throws RemoteException {

			BookInfo bookInfo = new BookInfo();
			bookInfo.setName("remote client");
			bookInfo.setPrice(66);
			return bookInfo;
		}
	}
}
