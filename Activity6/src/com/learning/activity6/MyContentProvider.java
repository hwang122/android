package com.learning.activity6;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.learning.activity6.ContentProviderMetaData.UserTableMetaData;
import com.learning.sqlite3.MyDatabaseHelper;

public class MyContentProvider extends ContentProvider {
	//uri的规则，返回数字
	public static final int INCOMING_USER_COLLECTION = 1;
	public static final int INCOMING_USER_SINGLE = 2;
	public static final UriMatcher uriMatcher;
	private MyDatabaseHelper Mdh;
	
	static
	{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(ContentProviderMetaData.AUTHORITY, "users", INCOMING_USER_COLLECTION);
		uriMatcher.addURI(ContentProviderMetaData.AUTHORITY, "users/#", INCOMING_USER_SINGLE);
	}

	public static HashMap<String, String> userProjectionMap;
	static
	{
		userProjectionMap = new HashMap<String, String>();
		userProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}
	
	@Override
	public boolean onCreate() {
		// TODO: Implement this to initialize your content provider on startup.
		Mdh = new MyDatabaseHelper(getContext(), ContentProviderMetaData.DATABASE_NAME);
		System.out.println("onCreate");
		@SuppressWarnings("unused")
		SQLiteDatabase db = Mdh.getWritableDatabase();
		return true;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Implement this to handle requests to delete one or more rows.
		System.out.println("delete");
		return 0;
	}

	@Override
	//根据传入的URI， 返回URI所代表的数据类型
	public String getType(Uri uri) {
		// TODO: Implement this to handle requests for the MIME type of the data
		// at the given URI.
		System.out.println("getType");
		switch(uriMatcher.match(uri)){
		case INCOMING_USER_COLLECTION:
			return UserTableMetaData.CONTENT_TYPE;
		case INCOMING_USER_SINGLE:
			return UserTableMetaData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown uri " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO: Implement this to handle requests to insert a new row.
		System.out.println("insert");
		SQLiteDatabase db = Mdh.getWritableDatabase();
		long rowId = db.insert(UserTableMetaData.TABLE_NAME, null, values);
		
		if(rowId > 0){
			Uri insertedUserUri = 
					ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			//通知插入
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;
		}
		
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	/**
	 * projection Which columns to return.
	 * selection WHERE clause.
	 * selectionArgs WHERE clause value substitution
	 * selectionArgs Sort order.
	 */
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO: Implement this to handle query requests from clients.
		System.out.println("query");
		//创建查询语句
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch(uriMatcher.match(uri)){
		case INCOMING_USER_COLLECTION:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(userProjectionMap);
			break;
		case INCOMING_USER_SINGLE:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(userProjectionMap);
			qb.appendWhere(UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));
			break;
		}
		
		String orderBy;
		if(TextUtils.isEmpty(sortOrder)){
			orderBy = UserTableMetaData.DEFAULT_SORT_ORDER;
		}
		else{
			orderBy = sortOrder;
		}
		
		SQLiteDatabase db = Mdh.getWritableDatabase();
		Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO: Implement this to handle requests to update one or more rows.
		return 0;
	}
}
