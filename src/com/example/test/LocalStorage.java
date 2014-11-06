package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalStorage extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "bluechat.db";

	// Table Names
	private static final String TABLE_PROFILES = "profiles";
	private static final String TABLE_MESSAGES = "messages";

	// Common Column Names
	private static final String KEY_ID = "id";

	// Profiles Table column names
	private static final String key_name = "name";
	private static final String key_number = "number";
	private static final String key_picture = "picture";
	private static final String key_BTName = "BTname";

	// Messages Table column name
	private static final String key_timestamp = "timestamp";
	private static final String key_body = "body";
	private static final String key_to = "to";
	private static final String key_from = "from";
	private static final String key_attachment = "attachment";

	// Table Create Statements
	// Profiles create statement
	private static final String CREATE_TABLE_PROFILES = "CREATE TABLE "
			+ TABLE_PROFILES + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ key_name + " TEXT, " + key_number + " TEXT, " + key_picture
			+ " BLOB, " + key_BTName + " TEXT" + ")";

	private static final String CREATE_TABLE_MESSAGES = " CREATE TABLE "
			+ TABLE_MESSAGES + "(" + KEY_ID + "INTEGER PRIMARY KEY, "
			+ key_body + " TEXT, " + key_to + " TEXT, " + key_from + " TEXT, "
			+ key_attachment + " BLOB" + ")";

	public LocalStorage(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PROFILES);
		db.execSQL(CREATE_TABLE_MESSAGES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);

		onCreate(db);

	}

	public long createProfile(String name, String number, String BTName) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(key_name, name);
		values.put(key_number, number);
		values.put(key_BTName, BTName);

		// insert row
		long profile_id = db.insert(TABLE_PROFILES, null, values);

		return profile_id;
	}

	public Profile getProfile(long profile_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_PROFILES + " WHERE "
				+ KEY_ID + " = " + profile_id;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Profile p = new Profile();
		p.setName(c.getString(c.getColumnIndex(key_name)));
		p.setNumber(c.getString(c.getColumnIndex(key_number)));
		p.setBTName(c.getString(c.getColumnIndex(key_BTName)));

		return p;
	}

	public List<Profile> getAllProfiles() {
		List<Profile> profiles = new ArrayList<Profile>();
		String selectQuery = "SELECT  * FROM " + TABLE_PROFILES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Profile p = new Profile();
				p.setName(c.getString(c.getColumnIndex(key_name)));
				p.setNumber(c.getString(c.getColumnIndex(key_number)));
				p.setBTName(c.getString(c.getColumnIndex(key_BTName)));

				// adding to todo list
				profiles.add(p);
			} while (c.moveToNext());
		}

		return profiles;
	}

	public int updateProfile(long profile_id, String name, String number,
			String BTName) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(key_name, name);
		values.put(key_number, number);
		values.put(key_BTName, BTName);

		return db.update(TABLE_PROFILES, values, KEY_ID + " =?",
				new String[] { String.valueOf(profile_id) });
	}
	
	public void deleteProfile(long profile_id) {
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_PROFILES, KEY_ID + " = ?",
	            new String[] { String.valueOf(profile_id) });
	}
	
	public long createMessage(String to, String from, String body) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(key_to, to);
		values.put(key_from, from);
		values.put(key_body, body);
		//values.put(key_timestamp, timestamp);

		// insert row
		long message_id = db.insert(TABLE_MESSAGES, null, values);

		return message_id;
	}
	
	// get messages from specific person
	public List<Messages> getAllMessages(String friend) {
		List<Messages> allMessages = new ArrayList<Messages>();
		String selectQuery = "SELECT  * FROM " + TABLE_MESSAGES + " WHERE "
				+ key_from + " = " + friend + " || " + key_to + " = " + friend;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Messages m = new Messages();
				
				m.setTo(c.getString(c.getColumnIndex(key_to)));
				m.setFrom(c.getString(c.getColumnIndex(key_from)));
				m.setBody(c.getString(c.getColumnIndex(key_body)));
				m.setTimestamp(c.getString(c.getColumnIndex(key_timestamp)));				

				// adding to todo list
				allMessages.add(m);
			} while (c.moveToNext());
		}

		return allMessages;
	}
}
