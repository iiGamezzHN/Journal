package com.example.davidarisz.journal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final JournalEntry journalEntry = (JournalEntry) getIntent().getSerializableExtra("entryTag");

        String string_title = journalEntry.getTitle();
        String string_content = journalEntry.getContent();
        String string_mood = journalEntry.getMood();
        String string_ts = journalEntry.getTimestamp();

        TextView detail_title = findViewById(R.id.detail_title);
        TextView detail_content = findViewById(R.id.detail_content);
        TextView detail_mood = findViewById(R.id.detail_mood);
        TextView detail_ts = findViewById(R.id.detail_timestamp);

        detail_title.setText(string_title);
        detail_content.setText(string_content);
        detail_mood.setText("Mood: " + string_mood);
        detail_ts.setText("Geschreven op: "+string_ts);
    }
}
