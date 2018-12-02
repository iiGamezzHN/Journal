package com.example.davidarisz.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(this, db.selectAll());

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new ListViewLongClickListener());
        listView.setOnItemClickListener(new ListViewClickListener());
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void toInput (View v){
        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
        startActivity(intent);
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Do something
            db = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String ts = cursor.getString(cursor.getColumnIndex("ts"));

            JournalEntry journalEntry = new JournalEntry(0, title, content, mood, ts);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("entryTag", journalEntry);
            startActivity(intent);
        }
    }

    private class ListViewLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Do something
            db = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
            int entry_id = cursor.getInt(cursor.getColumnIndex("_id"));

            db.remove(entry_id);
            updateData();
            Log.d("snaphetniet", "Value = " + cursor);
            return true;
        }
    }

    private void updateData() {
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter.swapCursor(db.selectAll());
    }
}
