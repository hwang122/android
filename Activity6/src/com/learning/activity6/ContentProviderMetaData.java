package com.learning.activity6;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContentProviderMetaData {
	public static final String AUTHORITY = "com.learning.mycontentprovider";
	public static final String DATABASE_NAME = "MyProvider.db";
	public static final int DATABASE_VERSION = 1;
	public static final String USER_TABLE_NAME = "users";
	
	public static final class UserTableMetaData implements BaseColumns{
		//table name
		public static final String TABLE_NAME = "users";
		//uri
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/users");
		//this content provider's returning content type 
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.myprovider.users";
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.myprovider.users";
		//column name
		public static final String USER_NAME = "name";
		//sort order
		public static final String DEFAULT_SORT_ORDER = "_id desc";		
	}

}
