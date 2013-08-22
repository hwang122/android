package com.learning.activity3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class HandlerTest extends Activity {

	private ProgressBar bar = null;
	private Button btn1 = null;
	private Button btn2 = null;
	private int i = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_test);
		
		bar = (ProgressBar)findViewById(R.id.bar1);
		btn1 = (Button)findViewById(R.id.start);
		btn2 = (Button)findViewById(R.id.end);
		
		btn1.setOnClickListener(new btn1Listener());
		btn2.setOnClickListener(new btn2Listener());
	}
	
	class btn1Listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			handler.post(updateThreads);
		}
	}
	
	class btn2Listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			handler.removeCallbacks(updateThreads);
		}
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			bar.setProgress(msg.arg1);
			handler.post(updateThreads);
		}
	};
	
	Runnable updateThreads = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			i += 1;
			msg.arg1 = i;
			
			try{
				Thread.sleep(5);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			if(i <= bar.getMax()){
				handler.sendMessage(msg);
			}
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handler_test, menu);
		return true;
	}

}
