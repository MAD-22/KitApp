package com.android.kitapp.crud.article;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.kitapp.R;
import com.android.kitapp.model.Article;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputMultiline;
import com.omarshehe.forminputkotlin.FormInputText;


public class CreateArticle extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    FormInputText name, publisher, date, type;
    FormInputMultiline content;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_article);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");


        name = (FormInputText) findViewById(R.id.article_name);
        publisher = (FormInputText) findViewById(R.id.publisher_name);
        date = (FormInputText) findViewById(R.id.publish_date);
        type = (FormInputText) findViewById(R.id.article_type);
        content = (FormInputMultiline) findViewById(R.id.about);

        create = findViewById(R.id.btn_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String articleName = name.getValue().toString();
                String articlePublisher = publisher.getValue().toString();
                String publishDate = date.getValue().toString();
                String articleType = type.getValue().toString();
                String articleContent = content.getValue().toString();

                if(!articleName.isEmpty() && !articlePublisher.isEmpty() && !publishDate.isEmpty() && !articleType.isEmpty() && !articleContent.isEmpty()){
                    createArticleDetails(new Article(articleName, articlePublisher, publishDate, articleType, articleContent));
                }
                else {
                    Toast.makeText(CreateArticle.this, "Please enter values!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void createArticleDetails(Article article){

        mDatabaseReference.child("article").push().setValue(article).addOnSuccessListener(this, new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void mVoid){
                Toast.makeText(CreateArticle.this, "Created Successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}