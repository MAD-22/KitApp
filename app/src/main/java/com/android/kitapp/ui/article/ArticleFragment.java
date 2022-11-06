package com.android.kitapp.ui.article;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kitapp.R;
import com.android.kitapp.databinding.FragmentArticleBinding;
import com.android.kitapp.model.Article;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArticleFragment extends Fragment {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private ArrayList<Article> daftarCardModel;
    private RecyclerView mRecyclerView;
    private AdapterCardRecyclerView mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);


        FirebaseApp.initializeApp(getActivity());
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("kitapp");
        mDatabaseReference.child("article").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){




                daftarCardModel = new ArrayList<>();
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()){
                    Article article = mDataSnapshot.getValue(Article.class);
                    article.setKey(mDataSnapshot.getKey());
                    daftarCardModel.add(article);
                }


                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                AdapterCardRecyclerView adapter = new AdapterCardRecyclerView(getContext(),daftarCardModel);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
                // TODO: Implement this method
                Toast.makeText(getContext(), databaseError.getDetails()+" "+databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
        return view;
    }


}