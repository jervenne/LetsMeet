package com.mobidev;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDAO extends SQLiteOpenHelper {
	// Logcat tag
    private static final String LOG = EventDAO.class.getName();
    
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "meetingManager";

	// User table name
	private static final String TABLE_USERS = "users";
	private static final String TABLE_EVENTS = "events";
	private static final String TABLE_OPTIONS = "options";
	private static final String TABLE_REPLIES = "replies";

	//Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_EVENTID = "event_id";

	// USER Columns names
	private static final String KEY_EMAIL = "email";
	
	// EVENT Columns names
	private static final String KEY_EVENTNAME = "event_name";
	private static final String KEY_LOCATION = "location";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_OWNERID = "owner_id";
	
	// OPTIONS Columns names
	private static final String KEY_TIMESLOT = "timeslot";
	
	// REPLIES Columns names
	private static final String KEY_USERID = "user_id";
	private static final String KEY_OPTIONID = "option_id";


	// Creating Tables
	//USER table create statement
	private static final String CREATE_TABLE_USERS = "CREATE TABLE "
			+ TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL
			+ " DATETIME" + ")";
	//EVENT table create statement
	private static final String CREATE_TABLE_EVENTS = "CREATE TABLE "
			+ TABLE_EVENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_EVENTNAME + " TEXT," + KEY_LOCATION + " TEXT,"
			+ KEY_DESCRIPTION + " TEXT," + KEY_OWNERID + " INTEGER," + "FOREIGN KEY(" + KEY_OWNERID + ") REFERENCES "+ TABLE_USERS + "("+ KEY_ID +"))";
	//OPTIONS table create statement
	private static final String CREATE_TABLE_OPTIONS = "CREATE TABLE "
			+ TABLE_OPTIONS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_EVENTID + " INTEGER,"
			+ "FOREIGN KEY(" + KEY_EVENTID + ") REFERENCES "+ TABLE_EVENTS +"("+ KEY_ID + ")," 
			+ KEY_TIMESLOT + " DATETIME" + ")";
	//REPLIES table create statement
	private static final String CREATE_TABLE_REPLIES = "CREATE TABLE " + TABLE_REPLIES + "(" 
			+ KEY_USERID + " INTEGER PRIMARY KEY," + KEY_OPTIONID + " INTEGER PRIMARY KEY," + KEY_EVENTID + " INTEGER PRIMARY KEY" 
			+ "FOREIGN KEY(" + KEY_USERID + ") REFERENCES "+ TABLE_USERS +"("+ KEY_ID + "),"
			+ "FOREIGN KEY(" + KEY_OPTIONID + ") REFERENCES "+ TABLE_OPTIONS +"("+ KEY_ID + "),"
			+ "FOREIGN KEY(" + KEY_EVENTID + ") REFERENCES "+ TABLE_EVENTS +"("+ KEY_ID + ")";	
	
	public EventDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_USERS);
		db.execSQL(CREATE_TABLE_EVENTS);
		db.execSQL(CREATE_TABLE_OPTIONS);
		db.execSQL(CREATE_TABLE_REPLIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPLIES);

		// create new tables
		onCreate(db);
	}

	// ------------------------ "USER" table methods ----------------//

	// Adding new User
	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, user.getEmail()); // user email

		// Inserting Row
		db.insert(TABLE_USERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single user
	User getUser(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
				KEY_EMAIL}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		// return user
		return user;
	}
	
	// Getting All Users
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_USERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				User user = new User();
				user.setUserID((Integer.parseInt(cursor.getString(0))));
				user.setEmail((cursor.getString(1)));
				// Adding user to list
				userList.add(user);
			} while (cursor.moveToNext());
		}

		// return user list
		return userList;
	}

	// Updating single user
	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, user.getEmail());

		// updating row
		return db.update(TABLE_USERS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(user.getUserID()) });
	}

	// Deleting single user
	public void deleteUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_ID + " = ?",
				new String[] { String.valueOf(user.getUserID()) });
		db.close();
	}


	// Getting user Count
	public int getUserCount() {
		String countQuery = "SELECT  * FROM " + TABLE_USERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	// ------------------------ "EVENTS" table methods ----------------//
	// Adding new event
	public void addEvent(Event event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, event.getEventName()); // event name
		values.put(KEY_LOCATION, event.getLocation()); // event location
		values.put(KEY_DESCRIPTION, event.getDescription()); // event desc
		values.put(KEY_OWNERID, event.getOwner()); // event owner

		// Inserting Row
		db.insert(TABLE_EVENTS, null, values);
		db.close(); // Closing database connection
	}
	// ------------------------ "OPTIONS" table methods ----------------//
	// ------------------------ "REPLIES" table methods ----------------//
}
