package com.android.kitapp.crud.book;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.kitapp.R;
import com.android.kitapp.crud.book.UpdateBook;
import com.android.kitapp.model.Book;
import com.android.kitapp.model.Book;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputMultiline;
import com.omarshehe.forminputkotlin.FormInputText;

public class UpdateBook extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    FormInputText name, publisher,author, date, type;
    FormInputMultiline content;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");

        name = (FormInputText) findViewById(R.id.book_name);
        publisher = (FormInputText) findViewById(R.id.publisher_name);
        author = (FormInputText) findViewById(R.id.author_name);
        date = (FormInputText) findViewById(R.id.publish_date);
        type = (FormInputText) findViewById(R.id.category);
        content = (FormInputMultiline) findViewById(R.id.about);

        name.setValue(getIntent().getStringExtra("name"));
        publisher.setValue(getIntent().getStringExtra("publisher"));
        author.setValue(getIntent().getStringExtra("author"));
        type.setValue(getIntent().getStringExtra("type"));
        date.setValue(getIntent().getStringExtra("date"));
        content.setValue(getIntent().getStringExtra("content"));

        update = findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookName = name.getValue().toString();
                String bookPublisher = publisher.getValue().toString();
                String bookAuthor = author.getValue().toString();
                String publishDate = date.getValue().toString();
                String bookType = type.getValue().toString();
                String bookContent = content.getValue().toString();

                if(!bookName.isEmpty() && !bookPublisher.isEmpty() && !bookAuthor.isEmpty() && !publishDate.isEmpty() && !bookType.isEmpty() && !bookContent.isEmpty()){
                    updateBookDetails(new Book(bookName, bookPublisher,bookAuthor, publishDate, bookType, bookContent));
                }
                else {
                    Toast.makeText(UpdateBook.this, "Please enter values!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void updateBookDetails(Book book){
        mDatabaseReference.child("book").child(getIntent().getStringExtra("id")).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(UpdateBook.this, "Updated successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}