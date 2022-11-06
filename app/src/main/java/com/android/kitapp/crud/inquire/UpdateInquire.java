package com.android.kitapp.crud.inquire;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.kitapp.R;
import com.android.kitapp.model.Inquire;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputMultiline;
import com.omarshehe.forminputkotlin.FormInputText;

public class UpdateInquire extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;

    FormInputText name, type, user, date;
    FormInputMultiline about;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inquire);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");

        name = (FormInputText) findViewById(R.id.name);
        type = (FormInputText) findViewById(R.id.category);
        user = (FormInputText) findViewById(R.id.user_name);
        about = (FormInputMultiline) findViewById(R.id.about);
        date = (FormInputText) findViewById(R.id.inquire_date);

        name.setValue(getIntent().getStringExtra("name"));
        type.setValue(getIntent().getStringExtra("type"));
        user.setValue(getIntent().getStringExtra("user"));
        about.setValue(getIntent().getStringExtra("about"));
        date.setValue(getIntent().getStringExtra("date"));

        update = findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namee = name.getValue().toString();
                String typee = type.getValue().toString();
                String userr = user.getValue().toString();
                String aboutt = about.getValue().toString();
                String datee = date.getValue().toString();


                if(!namee.isEmpty() && !typee.isEmpty() && !userr.isEmpty() && !datee.isEmpty() && !aboutt.isEmpty() ){
                    updateInquireDetails(new Inquire(namee,typee,userr, datee,aboutt));

                }
                else {
                    Toast.makeText(UpdateInquire.this, "Please enter values!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void updateInquireDetails(Inquire inquire){
        mDatabaseReference.child("inquire").child(getIntent().getStringExtra("id")).setValue(inquire).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(UpdateInquire.this, "Updated successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}