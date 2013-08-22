package com.learning.broadcastactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
	
	public MyReceiver() {
		System.out.println("Create MyReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		System.out.println("onReceive");
		String name = intent.getExtras().getString("name");
		int id = intent.getExtras().getInt("id");
		System.out.println("id is " + id + ", name is " + name);
	}
}
