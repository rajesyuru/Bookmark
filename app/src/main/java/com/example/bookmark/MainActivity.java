package com.example.bookmark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Bookmark> mBookmark;
    private BookmarkAdapter mBookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvListView);

        mBookmark = new ArrayList<>();
        mBookmark.add(new Bookmark("Google", "The most popular search engine", "https://google.com"));
        mBookmark.add(new Bookmark("Youtube", "The most popular video sharing platform", "https://youtube.com"));

        mBookmarkAdapter = new BookmarkAdapter(this, R.layout.bookmark_layout, mBookmark, new BookmarkAdapter.SetClick() {
            @Override
            public void linkClick(int position) {
                Bookmark bookmark = mBookmark.get(position);
                Log.d("!!!", "linkClick: Link Pressed: " + bookmark.getUrl() );

                Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse(bookmark.getUrl()));
                startActivity(browserintent);

            }

            @Override
            public void onEdit(int position) {
                Bookmark bookmark = mBookmark.get(position);

                Intent intent = new Intent(getApplicationContext(), AddBookmark.class);
                intent.putExtra("booktitle", bookmark.getTitle());
                intent.putExtra("bookdesc", bookmark.getDesc());
                intent.putExtra("bookurl", bookmark.getUrl());
                intent.putExtra("pos", position);
                startActivityForResult(intent, 2);

            }

            @Override
            public void onDelete(int position) {
                mBookmark.remove(position);
                mBookmarkAdapter.notifyDataSetChanged();

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mBookmarkAdapter);
        mRecyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_bookmark, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemAddBookmark) {
            Intent intent = new Intent(this, AddBookmark.class);
            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra("title");
                Log.d("!!!", "onActivityResult: " + title);
                String desc = data.getStringExtra("desc");
                String url = data.getStringExtra("url");


                mBookmark.add(new Bookmark(title, desc, url));
                mBookmarkAdapter.notifyDataSetChanged();

            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra("title");
                Log.d("!!!", "onActivityResult: " + title);
                String desc = data.getStringExtra("desc");
                String url = data.getStringExtra("url");
                int position = data.getIntExtra("pos", 0);

                mBookmark.set(position, new Bookmark(title, desc, url));

                mBookmarkAdapter.notifyDataSetChanged();

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
