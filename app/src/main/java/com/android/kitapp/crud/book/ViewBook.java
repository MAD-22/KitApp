package com.android.kitapp.crud.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.kitapp.R;

public class ViewBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        TextView name = findViewById(R.id.textView8);
        TextView publisher = findViewById(R.id.textView9);
        TextView author = findViewById(R.id.textView10);
        TextView type = findViewById(R.id.textView11);
        TextView date = findViewById(R.id.textView12);
        TextView content = findViewById(R.id.textView13);

        name.setText("Name: "+getIntent().getStringExtra("name"));
        publisher.setText("Publisher: "+getIntent().getStringExtra("publisher"));
        author.setText("Author: "+getIntent().getStringExtra("author"));
        type.setText("Type: "+getIntent().getStringExtra("type"));
        date.setText("Date: "+getIntent().getStringExtra("date"));
        content.setText("Content: "+getIntent().getStringExtra("content"));
    }
}