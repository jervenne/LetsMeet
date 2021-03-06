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
    //private static final String LOG = EventDAO.class.getName();
    
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "meetingManager.db";

	// User table name
	private static final String TABLE_USERS = "users";
	public static final String TABLE_EVENTS = "events";
	private static final String TABLE_OPTIONS = "options";
	public static final String TABLE_REPLIES = "replies";

	//Common column names
	public static final String KEY_ID = "id";
	private static final String KEY_EVENTID = "event_id";
	private static final String KEY_USERID = "user_id";

	// USER Columns names
	private static final String KEY_EMAIL = "email";
	
	// EVENT Columns names
	public static final String KEY_EVENTNAME = "event_name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_DESCRIPTION = "description";
	
	// OPTIONS Columns names
	private static final String KEY_TIMESLOT = "timeslot";
	
	// REPLIES Columns names
	private static final String KEY_OPTIONID = "option_id";


	// Creating Tables
	//USER table create statement
	private static final String CREATE_TABLE_USERS = "CREATE TABLE "
			+ TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL
			+ " TEXT" + ")";
	//EVENT table create statement
	private static final String CREATE_TABLE_EVENTS = "CREATE TABLE "
			+ TABLE_EVENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_EVENTNAME + " TEXT," + KEY_LOCATION + " TEXT,"
			+ KEY_DESCRIPTION + " TEXT)";
	//OPTIONS table create statement
	private static final String CREATE_TABLE_OPTIONS = "CREATE TABLE "
			+ TABLE_OPTIONS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_EVENTID + " INTEGER,"
			+ KEY_TIMESLOT + " TEXT,"
			+ "FOREIGN KEY(" + KEY_EVENTID + ") REFERENCES "+ TABLE_EVENTS + "("+ KEY_ID +"))";
	//REPLIES table create statement
	private static final String CREATE_TABLE_REPLIES = "CREATE TABLE " + TABLE_REPLIES + "(" 
			+ KEY_USERID + " INTEGER," + KEY_OPTIONID + " INTEGER NULL," + KEY_EVENTID + " INTEGER, " 
			+ "FOREIGN KEY(" + KEY_USERID + ") REFERENCES "+ TABLE_USERS +"("+ KEY_ID + "),"
			+ "FOREIGN KEY(" + KEY_OPTIONID + ") REFERENCES "+ TABLE_OPTIONS +"("+ KEY_ID + "),"
			+ "FOREIGN KEY(" + KEY_EVENTID + ") REFERENCES "+ TABLE_EVENTS +"("+ KEY_ID + "))";	
	
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
	public User getUser(String email) {
		
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE email='"+email+"'";
        User user = new User();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            user.setEmail(cursor.getString(1));
            System.out.println("ID=" + user.getUserID());
            System.out.println("Email=" + user.getEmail());
        }else{
        	user = null;
        }
        cursor.close();
        db.close();
        // return user
        return user;
        
	}
	
	// Getting single user
	public User getUser(int id) {
		
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE id='"+id+"'";
        User user = new User();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            user.setEmail(cursor.getString(1));
            System.out.println("ID=" + user.getUserID());
            System.out.println("Email=" + user.getEmail());
        }else{
        	user = null;
        }
        cursor.close();
        db.close();
        // return user
        return user;
        
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
	public void addEvent(Event event, User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(KEY_EVENTNAME, event.getEventName()); // event name
		if (event.getLocation() != null && !event.getLocation().isEmpty()){
			values.put(KEY_LOCATION, event.getLocation()); // event location
		}
		if (event.getDescription() != null && !event.getDescription().isEmpty()){
			values.put(KEY_DESCRIPTION, event.getDescription()); // event desc
		}
		// Inserting Row
		db.insert(TABLE_EVENTS, null, values);
		db.close(); // Closing database connection
	}
	
	
	// Getting All Events of a user
	public List<Event> getEventsOfAUser(User user) {
		List<Event> eventList = new ArrayList<Event>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " e, " + TABLE_REPLIES +" r WHERE e.id=r.event_id AND user_id='" +user.getUserID()+ "'";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Event event = new Event();
				event.setEventID(Integer.parseInt((cursor.getString(0))));
				event.setEventName(cursor.getString(1));
				event.setLocation(cursor.getString(2));
				event.setDescription(cursor.getString(3));
				// Adding events of a user to a list
				eventList.add(event);
			} while (cursor.moveToNext());
		}else{
			eventList = null;
		}

		// return event list
		return eventList;
	}
	
	// Getting single event
	public Event getEvent(int id) {
		
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE id='"+id+"'";
        Event event = new Event();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	event.setEventID(Integer.parseInt(cursor.getString(0)));
			event.setEventName(cursor.getString(1));
			event.setLocation(cursor.getString(2));
			event.setDescription(cursor.getString(3));
			
        }else{
        	event = null;
        }
        cursor.close();
        db.close();
        // return event
        return event;
        
	}
	
	public int getLatestEventID(){
		int lastId=0;
		SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT ID FROM " + TABLE_EVENTS + " ORDER BY id DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	lastId = Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        db.close();
        
        return lastId;
	}
	
	// ------------------------ "OPTIONS" table methods ----------------//
	//add suggested timeslots of a event
	public void addOptions(ArrayList<Option> timeslotList) {
		//to use, format the timeslot to "DD/MM/YYYY HH:MM - HH:MM" format first
		
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//for each timeslot in the timeslotList, add to db
		for (Option timeslot : timeslotList){
			values.put(KEY_EVENTID, timeslot.getEvent().getEventID()); // event id
			values.put(KEY_TIMESLOT, timeslot.getTimeslot()); // timeslot
		}
		
		// Inserting Row
		db.insert(TABLE_OPTIONS, null, values);
		db.close(); // Closing database connection
	} 
	
	// Getting All Options of an Event
	public List<Option> getOptionsOfAEvent(Event event) {
		List<Option> optionList = new ArrayList<Option>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_OPTIONS + " WHERE event_id='" +event.getEventID()+ "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Option option = new Option();
				option.setOptionID(Integer.parseInt((cursor.getString(0))));
				option.setEvent(event);
				option.setTimeslot(cursor.getString(2));
				// Adding options of a meeting to a list
				optionList.add(option);
			} while (cursor.moveToNext());
		}else{
			optionList = null;
		}

		// return event list
		return optionList;
	}
	
	// Getting single option
	public Option getOption(int id) {
		
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_OPTIONS + " WHERE id='"+id+"'";
        Option option = new Option();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            option.setOptionID(Integer.parseInt(cursor.getString(0)));
            //get event with id
            Event event = getEvent(Integer.parseInt(cursor.getString(1)));
            option.setEvent(event);
            option.setTimeslot(cursor.getString(2));
        }else{
        	option = null;
        }
        cursor.close();
        db.close();
        // return user
        return option;
        
	}
	
	// ------------------------ "REPLIES" table methods ----------------//
	//Add event to respondents
	public void addEventRespondents(Event event, ArrayList<User> respondentList){
		// for each user in the respondent list,
		for(User user : respondentList){
			//add event with their ID
			addRespondent(event.getEventID(), user.getUserID());
		}
	}
	
	//join respondents with events without replies yet
	public void addRespondent(int eventID, int userID) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EVENTID, eventID);
		values.put(KEY_OPTIONID, 0);
		values.put(KEY_USERID, userID);

		// Inserting Row
		db.insert(TABLE_REPLIES, null, values);
		db.close(); // Closing database connection
		
	}
	
	/**	
	// Updating replies from respondent
	public int addReply(ArrayList<Reply> replyList) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, user.getEmail());

		// updating row
		return db.update(TABLE_REPLIES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(user.getUserID()) });
	}
	**/
	// Getting All replies of an Event
		public List<Reply> getRepliesOfAEvent(Event event) {
			List<Reply> replyList = new ArrayList<Reply>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_OPTIONS + " WHERE event_id='" +event.getEventID()+ "'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Reply reply = new Reply();
					reply.setEvent(event);
					//find user with user ID
					User user = getUser(Integer.parseInt(cursor.getString(0)));
					reply.setUser(user);
					//find option with option ID
					Option option = getOption(Integer.parseInt(cursor.getString(1)));
					reply.setOption(option);
					
					// Adding replies of an event to a list
					replyList.add(reply);
				} while (cursor.moveToNext());
			}else{
				replyList = null;
			}

			// return reply list
			return replyList;
		}

	public ArrayList<User> getRespondents(Event event) {
		ArrayList<User> list = new ArrayList<User>();
		list=null;
		// Select All Query
		String selectQuery = "SELECT user_id, email FROM " + TABLE_USERS + " u, " + TABLE_REPLIES +" r WHERE u.id=r.user_id AND event_id='" +event.getEventID()+ "'";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				User user = new User();
				user.setUserID(Integer.parseInt((cursor.getString(0))));
				user.setEmail(cursor.getString(1));
				// Adding events of a user to a list
				list.add(user);
			} while (cursor.moveToNext());
		}

		// return event list
		return list;
	}
}
