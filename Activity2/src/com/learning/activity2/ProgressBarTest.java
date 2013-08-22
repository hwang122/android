package com.learning.activity2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarTest extends Activity {
	private ProgressBar firstBar = null;
	private Button btn = null;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar_test);
		
		firstBar = (ProgressBar)findViewById(R.id.progressBar1);
		btn = (Button)findViewById(R.id.button1);
		
		btn.setOnClickListener(new ButtonListener());
	}
	
	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(i == 0){
				firstBar.setVisibility(View.VISIBLE);
				firstBar.setMax(100);
			}
			else if(i < firstBar.getMax()){
				firstBar.setProgress(i);
				firstBar.setSecondaryProgress(i+10);
			}
			else{
				firstBar.setVisibility(View.GONE);
			}
			i += 20;
		}
		
	}
	
	public void toList(View v){
		Intent intent = new Intent(this, ListTest.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.progress_bar_test, menu);
		return true;
	}

}
