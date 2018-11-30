package com.example.davidarisz.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Map;

public class EntryDatabase extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + "entries" + " (" +
                    "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title" + " TEXT NOT NULL, " +
                    "content" + " TEXT NOT NULL, " +
                    "mood" + " TEXT NOT NULL, " +
                    "ts" + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + "entries";

    private static final String sql = "INSERT INTO entries (title, content, mood) " +
                    "VALUES('testing','this is a test','i am tired')" ;

    private static final String sq2 = "INSERT INTO entries (title, content, mood) " +
                    "VALUES('tiiitttleeee','this is a tiiitttllleee','1 4m t1r3d')" ;

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

    public Cursor selectRow(int id) {
        return getWritableDatabase().rawQuery("select * from entries where _id = ?", new String[] { String.valueOf(id) });
    }

    public Long insert (JournalEntry entry) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("title",entry.getTitle());
        contentValues.put("content",entry.getContent());
        contentValues.put("mood",entry.getMood());

        return getWritableDatabase().insert("entries",null,contentValues);
    }

    public long remove (int id) {
        return getWritableDatabase().delete("entries","_id = ?", new String[] { String.valueOf(id) });
    }
}
