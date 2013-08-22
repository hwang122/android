package com.learning.activity3;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;

public class HandlerThreadTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_thread_test);
		
		System.out.println("Activity--->" + Thread.currentThread().getId());
		
		HandlerThread ht = new HandlerThread("handler_thread");
		ht.start();
		
		Myhandler mh = new Myhandler(ht.getLooper());
		Bundle bd = new Bundle();
		bd.putInt("age", 25);
		bd.putString("name", "wanghuaxia");
		Message msg = mh.obtainMessage();
		msg.setData(bd);
		mh.sendMessage(msg);
	}
	
	static class Myhandler extends Handler{
		public Myhandler(Looper loop){
			super(loop);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			System.out.println("Handler--->" + Thread.currentThread().getId());
			
			String name = msg.getData().getString("name");
			int age = msg.getData().getInt("age");
			System.out.println("Name is " + name + " ; age is " + age);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handler_thread_test, menu);
		return true;
	}

}
