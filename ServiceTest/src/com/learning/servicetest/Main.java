package com.learning.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	private Button startService = null;
	private Button stopService = null;
	private Button bindService = null;
	private Button unbindService = null;
	private Boolean mBound = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService = (Button)findViewById(R.id.startService);
		stopService = (Button)findViewById(R.id.stopService);
		bindService = (Button)findViewById(R.id.bindService);
		unbindService = (Button)findViewById(R.id.unbindService);
		
		startService.setOnClickListener(new startServiceListener());
		stopService.setOnClickListener(new stopServiceListener());
		bindService.setOnClickListener(new bindServiceListener());
		unbindService.setOnClickListener(new unbindServiceListener());
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(mBound){
			unbindService(mConnection);
			mBound = false;
		}
	}

	class startServiceListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Main.this, FirstService.class);
			startService(intent);
		}
		
	}
	
	class stopServiceListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Main.this, FirstService.class);
			stopService(intent);
		}
		
	}
	
	class bindServiceListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Main.this, FirstService.class);
			bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
			mBound = true;
		}
		
	}
	
	class unbindServiceListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			unbindService(mConnection);
			mBound = false;
		}
		
	}
	
	private ServiceConnection mConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			System.out.println("onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			System.out.println("onServiceUnconnected");
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
