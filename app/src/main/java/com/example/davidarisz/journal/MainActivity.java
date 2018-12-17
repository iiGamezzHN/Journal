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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;
    private Cursor cursor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(this, db.selectAll());

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        registerForContextMenu(listView);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        db = EntryDatabase.getInstance(getApplicationContext());
        cursor = (Cursor) listView.getItemAtPosition(info.position);

        if(item.getItemId()==R.id.edit){
            Toast.makeText(getApplicationContext(),"Editing",Toast.LENGTH_SHORT).show();

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String ts = cursor.getString(cursor.getColumnIndex("ts"));

            JournalEntry journalEntry = new JournalEntry(0, title, content, mood, ts);

            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            intent.putExtra("editTag", journalEntry);
            startActivity(intent);

            int entry_id = cursor.getInt(cursor.getColumnIndex("_id"));
            db.remove(entry_id);
            updateData();
        }
        else if(item.getItemId()==R.id.delete){
            Toast.makeText(getApplicationContext(),"Deleting",Toast.LENGTH_SHORT).show();

            int entry_id = cursor.getInt(cursor.getColumnIndex("_id"));
            db.remove(entry_id);
            updateData();
        }
        return true;
    }

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

    private void updateData() {
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter.swapCursor(db.selectAll());
    }
}
