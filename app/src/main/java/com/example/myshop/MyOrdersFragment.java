package com.example.myshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.Adapters.MyCartAdapter;
import com.example.myshop.Adapters.MyOrdersAdapter;
import com.example.myshop.activities.PlaceOrderActivity;
import com.example.myshop.models.MyCartModel;
import com.example.myshop.models.MyOrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    MyOrdersAdapter myOrderAdapter;
    List<MyOrderModel> orderModelList;


    public MyOrdersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_orders, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView =root.findViewById(R.id.my_orders_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        orderModelList = new ArrayList<>();
        myOrderAdapter = new MyOrdersAdapter(getActivity(),orderModelList);
        recyclerView.setAdapter(myOrderAdapter);




        db.collection("MyOrders").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        MyOrderModel myOrderModel = documentSnapshot.toObject(MyOrderModel.class);
                        orderModelList.add(myOrderModel);
                        myOrderAdapter.notifyDataSetChanged();
                    }

                }
                else {
                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (orderModelList.size()==0){
        }




        return root;
    }
}