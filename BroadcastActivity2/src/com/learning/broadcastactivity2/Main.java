package com.learning.broadcastactivity2;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	private Button register = null;
	private Button unregister = null;
	private SMSReceiver sms = null;
	
	private static final String SMS_ACTION = 
			"android.provider.Telephony.SMS_RECEIVED";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		register = (Button)findViewById(R.id.register);
		unregister = (Button)findViewById(R.id.unregister);
		register.setOnClickListener(new registerListener());
		unregister.setOnClickListener(new unregisterListener());
	}
	
	class registerListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sms = new SMSReceiver();
					//create filter
					IntentFilter filter = new IntentFilter();
					filter.addAction(SMS_ACTION);
					//register this broadcast receiver
					Main.this.registerReceiver(sms, filter);
					System.out.println("Register successful!");	
				}
			};
			thread.start();
			thread = null;
		}
		
	}
	
	class unregisterListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Main.this.unregisterReceiver(sms);
					System.out.println("Unregister successful!");
				}	
			};
			thread.start();
			thread = null;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
