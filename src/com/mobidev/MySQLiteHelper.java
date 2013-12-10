package com.mobidev;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_EVENTS = "events";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_EVENTNAME = "eventname";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_DESCRIPTION = "description";
	
	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_EVENTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_EVENTNAME
			+ " text not null, " + COLUMN_LOCATION + " text not null, "
			+ COLUMN_DESCRIPTION + " text);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		onCreate(db);
	}
	
	public long addEvent(Event newEvent) {
		// open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();

		// storage for your data
		ContentValues cv = new ContentValues();
		
		cv.put("eventname", newEvent.getEventName());
		cv.put("location", newEvent.getLocation());
		cv.put("description", newEvent.getDescription());
				
		// insert values
		long success = db.insert("events", null, cv);
		Log.d("add event", success + "");

		// close DB
		db.close();
		
		return success;
		
	}
	
	public int getEventCount(){
		// open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cur = db.rawQuery("SELECT * from events", null);
		int x = cur.getCount();
		
		cur.close();
		db.close();

		return x;
	}
	
	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cur = db.rawQuery("SELECT * FROM events", null);

		cur.moveToFirst();
		while (!cur.isAfterLast()) {
			Event event = cursorToEvent(cur);
			events.add(event);
			cur.moveToNext();
		}

		db.close();
		return events;
	}
	
	private Event cursorToEvent(Cursor cursor) {
		Event event = new Event();
		event.setEventID(cursor.getInt(0));
		event.setEventName(cursor.getString(1));
		event.setLocation(cursor.getString(2));
		event.setDescription(cursor.getString(3));
		return event;
	}
}
