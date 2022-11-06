package com.android.kitapp.crud.order;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.kitapp.R;
import com.android.kitapp.model.Order;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputText;

public class UpdateOrder extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    FormInputText name, count, amount, date;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");

        name = (FormInputText) findViewById(R.id.name);
        count = (FormInputText) findViewById(R.id.count);
        amount = (FormInputText) findViewById(R.id.amount);
        date = (FormInputText) findViewById(R.id.date);

        name.setValue(getIntent().getStringExtra("name"));
        count.setValue(getIntent().getStringExtra("count"));
        amount.setValue(getIntent().getStringExtra("amount"));
        date.setValue(getIntent().getStringExtra("date"));

        update = findViewById(R.id.button4);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namee = name.getValue().toString();
                String countt = count.getValue().toString();
                String amountt = amount.getValue().toString();
                String datee = date.getValue().toString();


                if(!namee.isEmpty() && !countt.isEmpty() && !amountt.isEmpty() && !datee.isEmpty()){
                    updateOrderDetails(new Order(namee,amountt,countt, datee));
                }
                else {
                    Toast.makeText(UpdateOrder.this, "Please enter values!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void updateOrderDetails(Order order){
        mDatabaseReference.child("order").child(getIntent().getStringExtra("id")).setValue(order).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(UpdateOrder.this, "Updated successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}