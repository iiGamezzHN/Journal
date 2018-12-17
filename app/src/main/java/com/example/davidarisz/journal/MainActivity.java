package com.example.davidarisz.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;
    private Cursor cursor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext()); // Get the database information
        adapter = new EntryAdapter(this, db.selectAll());

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        registerForContextMenu(listView); // Set context menu on listView
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void toInput(View v) { // Go to InputActivity
        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
        startActivity(intent);
    }

    // Creates the ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu); // Get the menu options
        menu.setHeaderTitle("Select The Action"); // Set the title
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        db = EntryDatabase.getInstance(getApplicationContext());
        cursor = (Cursor) listView.getItemAtPosition(info.position);

        if (item.getItemId() == R.id.edit) { // Do this when click edit
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String ts = cursor.getString(cursor.getColumnIndex("ts"));

            JournalEntry journalEntry = new JournalEntry(0, title, content, mood, ts);

            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            intent.putExtra("editTag", journalEntry); // Put entry in intent
            startActivity(intent);

            int entry_id = cursor.getInt(cursor.getColumnIndex("_id"));
            db.remove(entry_id);
            updateData();
        } else if (item.getItemId() == R.id.delete) { // Do this when clicking delete
            int entry_id = cursor.getInt(cursor.getColumnIndex("_id"));
            db.remove(entry_id); // Delete entry from database
            updateData();
        }
        return true;
    }

    // Set listener on ListView
    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Do something
            db = EntryDatabase.getInstance(getApplicationContext());
            cursor = (Cursor) adapterView.getItemAtPosition(position);

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

    private void updateData() { // Update the data
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter.swapCursor(db.selectAll());
    }
}
