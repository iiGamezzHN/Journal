package com.example.davidarisz.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;

import org.jetbrains.annotations.Nullable;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        int id = R.layout.entry_row;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int id = cursor.getColumnIndex("_id"); // Wat moet ik hiermee?
        String title = cursor.getColumnName(cursor.getColumnIndex("title"));
        String content = cursor.getColumnName(cursor.getColumnIndex("content"));
        String mood = cursor.getColumnName(cursor.getColumnIndex("mood"));
        String timestamp = cursor.getColumnName(cursor.getColumnIndex("timestamp"));
        //View v = view.findViewById(); // Wat moet ik hiermee?

    }
}
