package com.tencent.multiprocess.aidlexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by warner on 04/02/2017.
 * service运行在主线程，跟thread没有任何关系，不要将后台运行和子线程的概念混淆到一起
 * activity在destroy后，还可以通过bind方法将activity与Service之间建立联系，但线程做不到这一点
 */

public class ServiceProcess extends Service {
	private MyBinder myBinder;

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return myBinder;
	}

	/**
	 * onCreate方法只会执行一次
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		myBinder = new MyBinder();
		Log.i(getClass().toString(), "thread id === " + Thread.currentThread().getId());
		Log.i(getClass().toString(), "onCreate method");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(getClass().toString(), "onStartCommand method");
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * service只有在stop状态，并且没有和任何activity关联的情况下才会被销毁
	 * 如果执行了bind操作，需要unbind操作后才能停止service
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(getClass().toString(), "onDestroy method");
	}

	class MyBinder extends IMyAidlInterface.Stub {

		private IMyAidlCallback callback;
		private Person person;

		@Override
		public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

		}

		@Override
		public void plus(int a, int b) throws RemoteException {

			Log.i(getClass().toString(), "base type === " + (a + b));
		}

		@Override
		public void printPerson(Person person) throws RemoteException {

			Log.i(getClass().toString(), "this is person info from another process === " + person.getName() + " & " + person.getSex());
			this.person = person;
		}

		@Override
		public void setCallback(IMyAidlCallback callback) throws RemoteException {
			this.callback = callback;
			this.callback.getPerson(person);
		}
	}
}
