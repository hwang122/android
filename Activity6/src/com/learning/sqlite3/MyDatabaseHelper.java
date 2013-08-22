package com.learning.sqlite3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper{
	
	final private static int Version = 1;

	public MyDatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}
	
	public MyDatabaseHelper(Context context, String name){
		super(context, name, null, Version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("ONCREATE");
		String CREATE_TABLE_SQL = "create table users(id integer primary key autoincrement, name varchar(255))";
		db.execSQL(CREATE_TABLE_SQL);
		System.out.println("ONCREATE");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("-------------onUpgrade called-------------"
				+ oldVersion + "---->" +  newVersion);
	}
	
	

}
