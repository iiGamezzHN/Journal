package com.example.davidarisz.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry (View view) {
        // Send entry to the databsse
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

        EditText editTitle = findViewById(R.id.add_title);
        EditText editContent = findViewById(R.id.add_content);

        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();

        if (title.isEmpty()) {
            title = "No Title";
        }

        if (content.isEmpty()) {
            content = "No Content";
        }

        JournalEntry journalEntry = new JournalEntry(0, title, content, mood, null);
        db.insert(journalEntry);

        Toast.makeText(this, "Added entry!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void buttonSad (View v){
        mood = "Sad";
    }

    public void buttonDead (View v){
        mood = "Dead";
    }

    public void buttonTired (View v){
        mood = "Tired";
    }

    public void buttonHappy (View v){
        mood = "Happy";
    }
}
