package com.learning.dialogactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}
	
	public void toSecond(View view){
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void toThird(View view){
		Intent intent = new Intent(this, ThirdActivity.class);
		startActivity(intent);
	}

}
