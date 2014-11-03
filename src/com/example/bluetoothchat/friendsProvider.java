package com.example.bluetoothchat;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.net.Uri;

public class friendsProvider extends ContentProvider {
	/* Content Provider will provide  name, description, and location of the user's profile image */
	public final static String DBNAME = "friendsDatabase";
	public final static String TABLE_NAME = "Friends";
	public final static String _ID = "_ID";
	public final static String COLUMN_NAME = "Name";
	public final static String COLUMN_DESCRIPTION = "Description";
	public final static String COLUMN_IMAGEURI = "ImageURI";
	public final static String AUTHORITY = "com.example.bluetoothchat";
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.bluetoothchat.provider/" + TABLE_NAME);
    public static final String BLANK = "";
    
    @SuppressWarnings("unused")
    private MainDatabaseHelper mOpenHelper;
    
	private static final String SQL_CREATE_MAIN = "CREATE TABLE Friends ( " +
						_ID + " INTEGER PRIMARY KEY, " +
						COLUMN_NAME + " TEXT, " + 
						COLUMN_DESCRIPTION + " TEXT, " + 
						COLUMN_IMAGEURI + " TEXT )";
	
	/**
	 * 	@author edwardskrod
	 */
	@Override
	public boolean onCreate() {

		mOpenHelper = new MainDatabaseHelper(getContext());
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		return mOpenHelper.getWritableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		/* Content Provider will provide  name, description, and location of the user's profile image */

		String name = values.getAsString(COLUMN_NAME).trim();
		String description = values.getAsString(COLUMN_DESCRIPTION).trim();
		String imageURI = values.getAsString(COLUMN_IMAGEURI).trim();
		
		if (name.equals(BLANK)) 
			return null;
		if (description.equals(BLANK))
			return null;
		if (imageURI.equals(BLANK))
			return null;
		
		long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
	
		return Uri.withAppendedPath(CONTENT_URI, "" + id);
	}

	@Override
	public int delete(Uri uri, String whereClause, String[] whereArgs) {

		return mOpenHelper.getWritableDatabase().delete(TABLE_NAME, whereClause, whereArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return mOpenHelper.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
	}
	
	/**
	 * Inner class to Create the Database
	 * @author edwardskrod
	 */
	protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
		MainDatabaseHelper(Context context) {
				super(context, DBNAME, null, 1);
		}

		@Override
		/** Pass our SQL statement to execSQL */
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_MAIN);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub	
		}
	}
	
	
	

}
