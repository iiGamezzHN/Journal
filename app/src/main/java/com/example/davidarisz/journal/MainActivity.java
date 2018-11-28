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

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public EntryDatabase entryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

        EntryAdapter entryAdapter = new EntryAdapter(this, db.selectAll());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(entryAdapter);
    }

    public void toInput (View v){
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
        startActivity(intent);
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Do something
        }
    }

    private class ListViewLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Do something
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
            int entry_id = cursor.getInt(cursor.getColumnIndex("_id"));
            entryDatabase.remove(entry_id);
            Log.d("snaphetniet", String.valueOf(entryDatabase.selectAll().getCount()));
            return true;
        }
    }
}
