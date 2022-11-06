package com.android.kitapp.crud.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.kitapp.R;

public class ViewOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        TextView id = findViewById(R.id.textView8);
        TextView name = findViewById(R.id.textView9);
        TextView count = findViewById(R.id.textView10);
        TextView date = findViewById(R.id.textView11);
        TextView amount = findViewById(R.id.textView12);


        id.setText("Order id: "+getIntent().getStringExtra("id"));
        name.setText("Customer name: "+getIntent().getStringExtra("name"));
        count.setText("Item count: "+getIntent().getStringExtra("count"));
        date.setText("Order date: "+getIntent().getStringExtra("date"));
        amount.setText("Total amount: "+getIntent().getStringExtra("amount"));

    }
}