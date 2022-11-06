package com.android.kitapp.crud.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.kitapp.R;
import com.android.kitapp.model.Order;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputText;

public class CreateOrder extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_create);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");

        String name = getIntent().getStringExtra("name");
        String amount = getIntent().getStringExtra("amount");
        String count = getIntent().getStringExtra("count");
        String date = getIntent().getStringExtra("date");

        FormInputText cardName = findViewById(R.id.name);
        FormInputText cardno = findViewById(R.id.cardno);
        FormInputText ccv = findViewById(R.id.ccv);
        FormInputText Expdate = findViewById(R.id.expiredate);

        Button create = findViewById(R.id.button3);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cardName.getValue().toString().isEmpty() && !cardno.getValue().toString().isEmpty() && !Expdate.getValue().toString().isEmpty()&& !ccv.getValue().toString().isEmpty()){
                    createOrderDetails(new Order(name, amount, count, date));
                }else {
                    Toast.makeText(CreateOrder.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void createOrderDetails(Order article){

        mDatabaseReference.child("order").push().setValue(article).addOnSuccessListener(this, new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(CreateOrder.this, "Created Successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}