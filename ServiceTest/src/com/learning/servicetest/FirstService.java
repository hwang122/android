package com.learning.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FirstService extends Service {
	public FirstService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		System.out.println("Service onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("Service onCreate");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("Service onDestory");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("flags--->" + flags);
		System.out.println("startId--->" + startId);
		System.out.println("Service onStartCommand");
		return START_NOT_STICKY;
	}
	
}
