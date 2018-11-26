package com.example.davidarisz.journal;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + "entries" + " (" +
                "_id" + " TEXT PRIMARY KEY, " +
                "title" + " TEXT NOT NULL, " +
                "content" + " TEXT NOT NULL, " +
                "mood" + " TEXT NOT NULL, " +
                "timestamp" + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
