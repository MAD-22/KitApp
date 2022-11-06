package com.android.kitapp.crud.book;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.kitapp.R;
import com.android.kitapp.model.Book;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteBook extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_book);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");

        Button delete = findViewById(R.id.button2);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBookDetails(getIntent().getStringExtra("id"));

            }
        });

    }

    private void deleteBookDetails(String key){
        mDatabaseReference.child("book").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(DeleteBook.this, "Deleted successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}