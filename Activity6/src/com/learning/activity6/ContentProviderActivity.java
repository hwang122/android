package com.learning.activity6;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContentProviderActivity extends Activity {
	private Button query = null;
	private Button insert = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_provider);
		
		query = (Button)findViewById(R.id.query);
		insert = (Button)findViewById(R.id.insert);
		
		query.setOnClickListener(new queryListener());
		insert.setOnClickListener(new insertListener());
	}
	
	class queryListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Cursor cursor = getContentResolver().
					query(ContentProviderMetaData.UserTableMetaData.CONTENT_URI, null, null, null, null);
			while(cursor.moveToNext()){
				System.out.println(cursor.getString(cursor.getColumnIndex("name")));
			}
		}
		
	}
	
	class insertListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentValues values = new ContentValues();
			values.put(ContentProviderMetaData.UserTableMetaData.USER_NAME, "John");
			Uri uri = getContentResolver().insert(ContentProviderMetaData.UserTableMetaData.CONTENT_URI, values);
			System.out.println("uri----> " + uri.toString());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}

}
