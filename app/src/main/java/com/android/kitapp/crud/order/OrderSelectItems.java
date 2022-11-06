package com.android.kitapp.crud.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.kitapp.R;
import com.omarshehe.forminputkotlin.FormInputText;

public class OrderSelectItems extends AppCompatActivity {
    FormInputText name, count, amount, date;
    Button payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_select_items);

        name = (FormInputText) findViewById(R.id.book_name);
        count = (FormInputText) findViewById(R.id.count);
        amount = (FormInputText) findViewById(R.id.amount);
        date = (FormInputText) findViewById(R.id.date);

        Button create = findViewById(R.id.button4);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namee = name.getValue().toString();
                String countt = count.getValue().toString();
                String amountt = amount.getValue().toString();
                String datee = date.getValue().toString();

                if(!namee.isEmpty() && !countt.isEmpty() && !amountt.isEmpty() && !datee.isEmpty()){
                    Intent intent = new Intent(OrderSelectItems.this, CreateOrder.class);
                    intent.putExtra("name", namee);
                    intent.putExtra("count", countt);
                    intent.putExtra("amount", amountt);
                    intent.putExtra("date", datee);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(OrderSelectItems.this, "Please enter values!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}