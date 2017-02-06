package com.tencent.multiprocess;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by warner on 06/02/2017.
 */

public class ProcessUtil {

	public static String getProcessName(Context context) {

		int pid = android.os.Process.myPid();//获取进程pid
		String processName = null;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);//获取系统的ActivityManager服务
		for (ActivityManager.RunningAppProcessInfo appProcess : am.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				processName = appProcess.processName;
				break;
			}
		}
		return processName;
	}

	public static String getProcessName() {

		int pid = android.os.Process.myPid();
		String processName = null;
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			File file = new File("/proc/" + pid + "/cmdline");
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			processName = bufferedReader.readLine().trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return processName;
	}
}
