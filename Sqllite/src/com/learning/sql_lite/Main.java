package com.learning.sql_lite;

import com.learining.db.MyDatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {

	private TextView info = null;
	private Button createDatabase = null;
	private Button updateDatabase = null;
	private Button insert = null;
	private Button update = null;
	private Button query = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		info = (TextView)findViewById(R.id.TextView1);
		createDatabase = (Button)findViewById(R.id.btn1);
		updateDatabase = (Button)findViewById(R.id.btn2);
		insert = (Button)findViewById(R.id.btn3);
		update = (Button)findViewById(R.id.btn4);
		query = (Button)findViewById(R.id.btn5);
		
		createDatabase.setOnClickListener(new createDatebaseListener());
		updateDatabase.setOnClickListener(new updateDatebaseListener());
		insert.setOnClickListener(new insertListener());
		update.setOnClickListener(new updateListener());
		query.setOnClickListener(new queryListener());
	}
	
	class createDatebaseListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MyDatabaseHelper dbHelper = new MyDatabaseHelper(Main.this, "test_user.db");
			@SuppressWarnings("unused")
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			
			info.setText("Create Database Successful!");
		}
	}
	
	class updateDatebaseListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MyDatabaseHelper dbHelper = new MyDatabaseHelper(Main.this, "test_user.db", 2);
			@SuppressWarnings("unused")
			SQLiteDatabase db = dbHelper.getWritableDatabase();	
			
			info.setText("Update Database Successful!");
		}
	}
	
	class insertListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentValues values = new ContentValues();
			values.put("id", 1);
			values.put("name", "wanghuaxia");
			
			MyDatabaseHelper dbHelper = new MyDatabaseHelper(Main.this, "test_user.db", 2);
			SQLiteDatabase db = dbHelper.getWritableDatabase();	
			db.insert("user", null, values);
			
			info.setText("insert value successful!");
		}
	}
	
	class updateListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentValues values = new ContentValues();
			values.put("name", "guoxiaohui");
			
			MyDatabaseHelper dbHelper = new MyDatabaseHelper(Main.this, "test_user.db", 2);
			SQLiteDatabase db = dbHelper.getWritableDatabase();	
			//forth value
			db.update("user", values, "id=?", new String[]{"1"} );
			
			info.setText("update value successful!");
		}
	}
	
	class queryListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MyDatabaseHelper dbHelper = new MyDatabaseHelper(Main.this, "test_user.db", 2);
			SQLiteDatabase db = dbHelper.getReadableDatabase();	
			
			Cursor cursor = db.query("user", new String[]{"id", "name"}, 
					"id=?", new String[]{"1"}, null, null, "id asc");
			
			while(cursor.moveToNext()){
				String name = cursor.getString(cursor.getColumnIndex("name"));
				info.setText("Query--->" + name);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
