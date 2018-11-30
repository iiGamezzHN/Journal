package com.example.davidarisz.journal;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
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

        ImageView mood_image = view.findViewById(R.id.list_image);

        title_text.setText(title);
        title_moood.setText(mood);
        title_timestamp.setText(ts);

        switch (mood) {
            case "Sad":
                mood_image.setBackgroundResource(R.drawable.sad);
                break;
            case "Dead":
                mood_image.setBackgroundResource(R.drawable.dead);
                break;
            case "Tired":
                mood_image.setBackgroundResource(R.drawable.tired);
                break;
            case "Happy":
                mood_image.setBackgroundResource(R.drawable.smile);
                break;
            default:
                mood_image.setBackgroundResource(R.drawable.dead);
                break;
        }


    }
}
