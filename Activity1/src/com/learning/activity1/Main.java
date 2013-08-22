package com.learning.activity1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main extends Activity {
	private RadioGroup generalGroup = null;
	private RadioButton femaleButton = null;;
	private RadioButton maleButton = null;
	private CheckBox car = null;
	private CheckBox bike = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		generalGroup = (RadioGroup)findViewById(R.id.radioGroup);
		femaleButton = (RadioButton)findViewById(R.id.radioButton1);
		maleButton = (RadioButton)findViewById(R.id.radioButton2);
		
		car = (CheckBox)findViewById(R.id.checkBox1);
		bike = (CheckBox)findViewById(R.id.checkBox2);
		
		//radiogroup's listener
		generalGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(femaleButton.getId() == checkedId){
					Toast.makeText(Main.this, "female", Toast.LENGTH_SHORT).show();
				}
				else if(maleButton.getId() == checkedId){
					Toast.makeText(Main.this, "male", Toast.LENGTH_SHORT).show();	
				}
			}
		});
		
		car.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					Toast.makeText(Main.this, "car", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		bike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					Toast.makeText(Main.this, "bike", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
