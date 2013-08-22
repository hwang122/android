package com.learning.multiply;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class result extends Activity{
	private TextView resultView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		resultView = (TextView)findViewById(R.id.result);
		
		Intent intent = getIntent();
		
		String factorOneStr = intent.getStringExtra("one");
		String factorTwoStr = intent.getStringExtra("two");
		int factorOne = Integer.parseInt(factorOneStr);
		int factorTwo = Integer.parseInt(factorTwoStr);
		
		int result = factorOne*factorTwo;
		String resultStr = Integer.toString(result);
		
		resultView.setText(resultStr);
	}
	

}
