package com.android.kitapp.crud.inquire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.kitapp.R;
import com.android.kitapp.model.Inquire;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputMultiline;
import com.omarshehe.forminputkotlin.FormInputText;

public class CreateInquire extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    FormInputText name, user, date, type;
    FormInputMultiline content;
    Button create;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inquire);
        FirebaseApp.initializeApp(this);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");

        name = (FormInputText) findViewById(R.id.name);
        user = (FormInputText) findViewById(R.id.user_name);
        date = (FormInputText) findViewById(R.id.inquire_date);
        type = (FormInputText) findViewById(R.id.category);
        content = (FormInputMultiline) findViewById(R.id.about);

        create = findViewById(R.id.btn_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namee = name.getValue().toString();
                String userr = user.getValue().toString();
                String datee = date.getValue().toString();
                String typee = type.getValue().toString();
                String contentt = content.getValue().toString();

                if(!namee.isEmpty() && !userr.isEmpty() && !datee.isEmpty() && !typee.isEmpty() && !contentt.isEmpty()){
                    createInquireDetails(new Inquire(namee, typee, userr,datee, contentt));
                }
                else {
                    Toast.makeText(CreateInquire.this, "Please enter values!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void createInquireDetails(Inquire article){

        mDatabaseReference.child("inquire").push().setValue(article).addOnSuccessListener(this, new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(CreateInquire.this, "Created Successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}