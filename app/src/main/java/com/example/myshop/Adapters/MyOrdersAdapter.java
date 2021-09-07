package com.example.myshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.models.MyCartModel;
import com.example.myshop.models.MyOrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>{

    Context context;
    List<MyOrderModel> orderModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyOrdersAdapter(Context context, List<MyOrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOrdersAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(orderModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(orderModelList.get(position).getProductName());
        holder.price.setText(orderModelList.get(position).getProductPrice());
        holder.dateTime.setText(orderModelList.get(position).getDateTime());
        holder.quantity.setText(orderModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(orderModelList.get(position).getTotalPrice()));

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,dateTime,quantity,totalPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.orders_img);
            name = itemView.findViewById(R.id.orders_name);
            price = itemView.findViewById(R.id.orders_price);
            dateTime = itemView.findViewById(R.id.orders_datetime);
            quantity = itemView.findViewById(R.id.orders_quantity);
            totalPrice = itemView.findViewById(R.id.orders_total_price);
        }
    }
}

