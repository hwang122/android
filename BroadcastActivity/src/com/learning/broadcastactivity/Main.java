package com.learning.broadcastactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	private Button broadcast = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		broadcast = (Button)findViewById(R.id.broadcast);
		broadcast.setOnClickListener(new broadcastListener());		

	}
	
	class broadcastListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					//this is a filter tag to identify the receiver
					intent.setAction(Intent.ACTION_EDIT);
					//content value
					Bundle bd = new Bundle();
					bd.putString("name", "wanghuaxia");
					bd.putInt("id", 1);
					intent.putExtras(bd);
					//broadcast
					Main.this.sendBroadcast(intent);
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
