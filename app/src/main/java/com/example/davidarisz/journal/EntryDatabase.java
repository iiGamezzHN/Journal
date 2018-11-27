package com.example.davidarisz.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;

public class EntryDatabase extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + "entries" + " (" +
                    "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title" + " TEXT NOT NULL, " +
                    "content" + " TEXT NOT NULL, " +
                    "mood" + " TEXT NOT NULL, " +
                    "timestamp" + " TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + "entries";

    private static final String sql = "INSERT INTO entries (_id, title, content, mood, timestamp) " +
                    "VALUES('1','testing','this is a test','i am tired','03/04/2005')" ;

    private static final String sq2 = "INSERT INTO entries (_id, title, content, mood, timestamp) " +
                    "VALUES('2','tiiitttleeee','this is a tiiitttllleee','1 4m t1r3d','27/11/2018')" ;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(sql);
        db.execSQL(sq2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private EntryDatabase( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static EntryDatabase instance;

    public static EntryDatabase getInstance (Context c) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new EntryDatabase(c, "entries", null, 1);
            return instance;
        }
    }

    public Cursor selectAll() {
        return getWritableDatabase().rawQuery("select * from entries",null);
    }

    public insert (Entry entry) {
        getWritableDatabase(); // Probably wrong
        ContentValues contentValues = new ContentValues();

        contentValues.put("title","title");
        contentValues.put("content","content");
        contentValues.put("mood","mood");

        getWritableDatabase().insert("entries",null,contentValues);

        return;
    }
}
