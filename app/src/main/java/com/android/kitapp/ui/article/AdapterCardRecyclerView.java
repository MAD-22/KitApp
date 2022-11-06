package com.android.kitapp.ui.article;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kitapp.R;
import com.android.kitapp.crud.article.DeleteArticle;
import com.android.kitapp.crud.article.UpdateArticle;
import com.android.kitapp.crud.article.ViewArticle;
import com.android.kitapp.model.Article;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterCardRecyclerView extends RecyclerView.Adapter<AdapterCardRecyclerView.ViewHolder>
{
	private Context context;
	private ArrayList<Article> daftarArticle;

	public AdapterCardRecyclerView(Context context, ArrayList<Article> daftarArticle){
		this.context = context;
		this.daftarArticle = daftarArticle;
//		this.listener = (FirebaseDataListener)context;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View listItem= layoutInflater.inflate(R.layout.item_article, parent, false);
		ViewHolder viewHolder = new ViewHolder(listItem);


		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
		holder.name.setText( daftarArticle.get(position).getName());
		holder.edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, UpdateArticle.class);
				intent.putExtra("id", daftarArticle.get(position).getKey());
				intent.putExtra("name", daftarArticle.get(position).getName());
				intent.putExtra("publisher", daftarArticle.get(position).getPublisher());
				intent.putExtra("type", daftarArticle.get(position).getType());
				intent.putExtra("date", daftarArticle.get(position).getDate());
				intent.putExtra("content", daftarArticle.get(position).getContent());
				context.startActivity(intent);
			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, DeleteArticle.class);
				intent.putExtra("id", daftarArticle.get(position).getKey());
				context.startActivity(intent);
			}
		});

		holder.articleView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, ViewArticle.class);
				intent.putExtra("id", daftarArticle.get(position).getKey());
				intent.putExtra("name", daftarArticle.get(position).getName());
				intent.putExtra("publisher", daftarArticle.get(position).getPublisher());
				intent.putExtra("type", daftarArticle.get(position).getType());
				intent.putExtra("date", daftarArticle.get(position).getDate());
				intent.putExtra("content", daftarArticle.get(position).getContent());
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return daftarArticle.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView name;
		public Button edit, delete;
		public View articleView;

		public ViewHolder(View itemView) {
			super(itemView);

			this.name = (TextView) itemView.findViewById(R.id.article_name);
			this.edit = (Button) itemView.findViewById(R.id.btn_update);
			this.delete = (Button) itemView.findViewById(R.id.btn_delete);

			this.articleView = itemView;

		}
	}
}
