package com.android.kitapp.ui.book;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kitapp.R;
import com.android.kitapp.crud.book.DeleteBook;
import com.android.kitapp.crud.book.UpdateBook;
import com.android.kitapp.crud.book.ViewBook;
import com.android.kitapp.model.Book;

import java.util.ArrayList;

public class AdapterCardRecyclerView extends RecyclerView.Adapter<AdapterCardRecyclerView.ViewHolder>
{
	private Context context;
	private ArrayList<Book> daftarBook;

	public AdapterCardRecyclerView(Context context, ArrayList<Book> daftarBook){
		this.context = context;
		this.daftarBook = daftarBook;
//		this.listener = (FirebaseDataListener)context;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View listItem= layoutInflater.inflate(R.layout.item_book, parent, false);
		ViewHolder viewHolder = new ViewHolder(listItem);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.name.setText(daftarBook.get(position).getName());
		holder.edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, UpdateBook.class);
				intent.putExtra("id", daftarBook.get(position).getKey());
				intent.putExtra("name", daftarBook.get(position).getName());
				intent.putExtra("publisher", daftarBook.get(position).getPublisher());
				intent.putExtra("author", daftarBook.get(position).getAuthor());
				intent.putExtra("type", daftarBook.get(position).getType());
				intent.putExtra("date", daftarBook.get(position).getDate());
				intent.putExtra("content", daftarBook.get(position).getContent());
				context.startActivity(intent);
			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, DeleteBook.class);
				intent.putExtra("id", daftarBook.get(position).getKey());
				context.startActivity(intent);
			}
		});

		holder.bookView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, ViewBook.class);
				intent.putExtra("id", daftarBook.get(position).getKey());
				intent.putExtra("name", daftarBook.get(position).getName());
				intent.putExtra("publisher", daftarBook.get(position).getPublisher());
				intent.putExtra("author", daftarBook.get(position).getAuthor());
				intent.putExtra("type", daftarBook.get(position).getType());
				intent.putExtra("date", daftarBook.get(position).getDate());
				intent.putExtra("content", daftarBook.get(position).getContent());
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return daftarBook.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView name;
		public Button edit, delete;
		public View bookView;
		public ViewHolder(View itemView) {
			super(itemView);

			this.name = (TextView) itemView.findViewById(R.id.book_name);
			this.edit = (Button) itemView.findViewById(R.id.btn_update);
			this.delete = (Button) itemView.findViewById(R.id.btn_delete);

			this.bookView = itemView;
		}
	}
}
