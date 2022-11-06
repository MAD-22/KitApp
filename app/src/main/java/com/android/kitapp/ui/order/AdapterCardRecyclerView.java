package com.android.kitapp.ui.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kitapp.R;
import com.android.kitapp.crud.order.DeleteOrder;
import com.android.kitapp.crud.order.UpdateOrder;
import com.android.kitapp.crud.order.ViewOrder;
import com.android.kitapp.model.Order;

import java.util.ArrayList;

public class AdapterCardRecyclerView extends RecyclerView.Adapter<AdapterCardRecyclerView.ViewHolder>
{
	private Context context;
	private ArrayList<Order> daftarOrder;

	public AdapterCardRecyclerView(Context context, ArrayList<Order> daftarOrder){
		this.context = context;
		this.daftarOrder = daftarOrder;
//		this.listener = (FirebaseDataListener)context;

	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View listItem= layoutInflater.inflate(R.layout.item_order, parent, false);
		ViewHolder viewHolder = new ViewHolder(listItem);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
		holder.name.setText(daftarOrder.get(position).getName());

		holder.edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, UpdateOrder.class);
				intent.putExtra("id", daftarOrder.get(position).getKey());
				intent.putExtra("name", daftarOrder.get(position).getName());
				intent.putExtra("count", daftarOrder.get(position).getQuantity());
				intent.putExtra("date", daftarOrder.get(position).getDate());
				intent.putExtra("amount", daftarOrder.get(position).getPrice());
				context.startActivity(intent);
			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, DeleteOrder.class);
				intent.putExtra("id", daftarOrder.get(position).getKey());
				context.startActivity(intent);
			}
		});

		holder.viewOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, ViewOrder.class);
				intent.putExtra("id", daftarOrder.get(position).getKey());
				intent.putExtra("name", daftarOrder.get(position).getName());
				intent.putExtra("count", daftarOrder.get(position).getQuantity());
				intent.putExtra("date", daftarOrder.get(position).getDate());
				intent.putExtra("amount", daftarOrder.get(position).getPrice());
				context.startActivity(intent);
			}
		});

	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return daftarOrder.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView name;
		public Button edit, delete;
		public View viewOrder;
		public ViewHolder(View itemView) {
			super(itemView);

			this.name = (TextView) itemView.findViewById(R.id.name);
			this.edit = (Button) itemView.findViewById(R.id.btn_update);
			this.delete = (Button) itemView.findViewById(R.id.btn_delete);

			this.viewOrder = itemView;
		}
	}
}
