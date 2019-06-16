package com.example.inheritance.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "feedsManager" ;
    private static final String TABLE_FEED = "feed";
    private static final String KEY_DATE = "date" ;
    private static final String KEY_TITLE = "title" ;
    private static final String KEY_DESCRIPTION = "description" ;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDS_TABLE = "CREATE TABLE " + TABLE_FEED + "(" + KEY_DATE + "DATE " + KEY_TITLE + "TITLE" + KEY_DESCRIPTION + "DESC )" ;
                db.execSQL(CREATE_FEEDS_TABLE);

    }

    //Upgrade Table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop old table if existed
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_FEED);

        //Create tables again
        onCreate(db);

    }

     public void addFeed(Feed feed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, feed.getDate());//Feed date
        values.put(KEY_TITLE, feed.getTitle()); //Feed title
        values.put(KEY_DESCRIPTION, feed.getDescription());//get Description

        //Insert Row
        db.insert(TABLE_FEED,null,values);
        db.close(); //Closing database connection.

    }

    //code to get all feeds in a list view
    public List <Feed> getAllFeeds() {
        List <Feed> feedList = new ArrayList<Feed>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_FEED ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                Feed feed = new Feed() ;
                feed.setDate(cursor.getString(0));
                feed.setTitle(cursor.getString(1));
                feed.setDescription(cursor.getString(2));

                //Adding contact to List
                feedList.add(feed);
            } while (cursor.moveToNext());
        }

        //return feed list
        return feedList;
    }
    //code to get the single contact
//    Feed getFeed(String title) {
//        SQLiteDatabase db = this.getWri
//    }

    //code to update single contact
    public int updateContact(Feed feed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, feed.getDate());
        values.put(KEY_DESCRIPTION,feed.getDescription());

        //Updating row
        return db.update(TABLE_FEED,values,KEY_TITLE+ "= ?", new String[] { String.valueOf(feed.getTitle())} );
    }

    //Deleting single contact
    public void deleteContact(Feed feed) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FEED , KEY_TITLE +"=?" , new String[] {String.valueOf(feed.getTitle())});
        db.close();
    }

    //Getting contacts count
    public int getFeedCount() {
        String feedQuery = "SELECT * FROM " + TABLE_FEED ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(feedQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

}
