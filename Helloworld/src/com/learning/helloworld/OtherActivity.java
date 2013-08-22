package com.learning.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity{
	private TextView myTextView2 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_activity);
		Intent intent = getIntent();
		String value = intent.getStringExtra("testintent");
		myTextView2 = (TextView)findViewById(R.id.textview2);
		myTextView2.setText(value);
	}

}
