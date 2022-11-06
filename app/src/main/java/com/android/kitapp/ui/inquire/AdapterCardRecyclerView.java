package com.android.kitapp.ui.inquire;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kitapp.R;
import com.android.kitapp.crud.inquire.DeleteInquire;
import com.android.kitapp.crud.inquire.UpdateInquire;
import com.android.kitapp.model.Inquire;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omarshehe.forminputkotlin.FormInputText;

import java.util.ArrayList;

public class AdapterCardRecyclerView extends RecyclerView.Adapter<AdapterCardRecyclerView.ViewHolder>
{
	private Context context;
	private ArrayList<Inquire> daftarInquire;

	private DatabaseReference mDatabaseReference;
	private FirebaseDatabase mFirebaseInstance;

	public AdapterCardRecyclerView(Context context, ArrayList<Inquire> daftarInquire){
		this.context = context;
		this.daftarInquire = daftarInquire;
//		this.listener = (FirebaseDataListener)context;

	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View listItem= layoutInflater.inflate(R.layout.item_inquire, parent, false);
		ViewHolder viewHolder = new ViewHolder(listItem);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
		holder.name.setText("Name: "+daftarInquire.get(position).getName());
		holder.date.setText("Date: "+daftarInquire.get(position).getDate());
		holder.content.setText("Question: "+daftarInquire.get(position).getAbout());
		holder.replyText.setText("  -> Reply: "+daftarInquire.get(position).getReply());
		System.out.println(holder.replyText.getText().toString());

		if("-> Reply: null".equals(holder.replyText.getText().toString().trim())){
			holder.sendReply.setVisibility(View.VISIBLE);
			holder.reply.setVisibility(View.VISIBLE);
			holder.replyText.setVisibility(View.GONE);


		}else {
			holder.sendReply.setVisibility(View.GONE);
			holder.reply.setVisibility(View.GONE);
			holder.replyText.setVisibility(View.VISIBLE);

		}

		holder.edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, UpdateInquire.class);
				intent.putExtra("id", daftarInquire.get(position).getKey());
				intent.putExtra("name", daftarInquire.get(position).getName());
				intent.putExtra("type", daftarInquire.get(position).getType());
				intent.putExtra("user", daftarInquire.get(position).getUser());
				intent.putExtra("date", daftarInquire.get(position).getDate());
				intent.putExtra("about", daftarInquire.get(position).getAbout());
				context.startActivity(intent);
			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, DeleteInquire.class);
				intent.putExtra("id", daftarInquire.get(position).getKey());
				context.startActivity(intent);
			}
		});

		holder.sendReply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!"".equals(holder.reply.getValue().toString())){
					holder.sendReply.setVisibility(View.INVISIBLE);
					holder.reply.setVisibility(View.INVISIBLE);
					holder.replyText.setText(" Reply: "+ holder.reply.getValue().toString());

					daftarInquire.get(position).setReply(holder.reply.getValue().toString());
					updateInquireDetails(daftarInquire.get(position));

				}else{
					Toast.makeText(context, "Please enter reply !", Toast.LENGTH_LONG).show();
				}

			}
		});


	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return daftarInquire.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView name, date, content, replyText;
		public Button edit;
		public ImageView delete, sendReply;
		public FormInputText reply;
		public ViewHolder(View itemView) {
			super(itemView);

			this.name = (TextView) itemView.findViewById(R.id.name);
			this.date = (TextView) itemView.findViewById(R.id.textView5);
			this.content = (TextView) itemView.findViewById(R.id.textView6);
			this.replyText = (TextView) itemView.findViewById(R.id.textView7);
			this.edit = (Button) itemView.findViewById(R.id.btn_update);
			this.delete = (ImageView) itemView.findViewById(R.id.btn_delete);
			this.sendReply = (ImageView) itemView.findViewById(R.id.imageView4);
			this.reply = (FormInputText) itemView.findViewById(R.id.reply_quary);
		}
	}

	private void updateInquireDetails(Inquire inquire){
		FirebaseApp.initializeApp(context);
		mFirebaseInstance = FirebaseDatabase.getInstance();
		mDatabaseReference = mFirebaseInstance.getReference("kitapp");
		mDatabaseReference.child("inquire").child(inquire.getKey()).setValue(inquire).addOnSuccessListener(new OnSuccessListener<Void>(){
			@Override
			public void onSuccess(Void mVoid){
				Toast.makeText(context, "Replied successfully !", Toast.LENGTH_LONG).show();
			}
		});
	}
}
