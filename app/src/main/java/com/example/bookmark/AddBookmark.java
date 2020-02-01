package com.example.bookmark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookmark extends AppCompatActivity {

    EditText editTitle;
    EditText editDesc;
    EditText editURL;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bookmark);

        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        editURL = findViewById(R.id.editURL);

        Intent intent = getIntent();
        String title = intent.getStringExtra("booktitle");
        String desc = intent.getStringExtra("bookdesc");
        String url = intent.getStringExtra("bookurl");
        Log.d("!!!", "onCreate: " + url);
        position = intent.getIntExtra("pos", 0);

        editTitle.setText(title);
        editDesc.setText(desc);
        editURL.setText(url);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save_bookmark, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemSaveBookmark) {
            if (editTitle.getText().length() == 0 || editDesc.getText().length() == 0 || !editURL.getText().toString().contains("https://")) {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("title", editTitle.getText().toString());
                intent.putExtra("desc", editDesc.getText().toString());
                intent.putExtra("url", editURL.getText().toString());
                intent.putExtra("pos", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
