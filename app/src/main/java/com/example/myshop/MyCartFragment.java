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
import com.example.myshop.activities.PlaceOrderActivity;
import com.example.myshop.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView total;
    Button checkout;


    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;

    public MyCartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView =root.findViewById(R.id.cart_rec);
        checkout= root.findViewById(R.id.checkout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(),myCartModelList);
        recyclerView.setAdapter(myCartAdapter);

        total = root.findViewById(R.id.cart_total);

            db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                            String documentId= documentSnapshot.getId();

                            MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                            myCartModel.setDocumentId(documentId);
                            myCartModelList.add(myCartModel);
                            myCartAdapter.notifyDataSetChanged();
                        }
                        calculateTotalAmount(myCartModelList);

                    }
                    else {
                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });



        checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getContext(), PlaceOrderActivity.class);
                    intent1.putExtra("itemList",(Serializable)myCartModelList);
                    startActivity(intent1);


                    for (MyCartModel a: myCartModelList) {

                        String documentId = a.getDocumentId();


                        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                                .collection("CurrentUser").document(documentId).delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            myCartModelList.remove(a);
                                            myCartAdapter.notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }

                }
            });



        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> myCartModelList) {

        double totalAmount = 0.0;
        for (MyCartModel myCartModel : myCartModelList){
            totalAmount += myCartModel.getTotalPrice();

        }
        total.setText("TOTAL : "+totalAmount+ "$");
    }

}