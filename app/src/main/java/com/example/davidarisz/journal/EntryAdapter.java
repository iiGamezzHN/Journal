package com.example.davidarisz.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        String ts = cursor.getString(cursor.getColumnIndex("ts"));

        TextView title_text = view.findViewById(R.id.list_title);
        TextView title_moood = view.findViewById(R.id.list_mood);
        TextView title_timestamp = view.findViewById(R.id.list_timestamp);

        title_text.setText(title);
        title_moood.setText(mood);
        title_timestamp.setText(ts);
    }
}
