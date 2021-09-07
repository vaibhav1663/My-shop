package com.example.myshop.activities;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myshop.DetailedActivity;
import com.example.myshop.MainActivity;
import com.example.myshop.MyCartFragment;
import com.example.myshop.R;
import com.example.myshop.models.MyCartModel;
import com.example.myshop.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        button = findViewById(R.id.back_to_home);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        List<MyCartModel> list = (ArrayList<MyCartModel>)getIntent().getSerializableExtra("itemList");
        if (list != null && list.size()>0){
            for (MyCartModel model : list){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName", model.getProductName());
                cartMap.put("productPrice", model.getProductPrice().toString());
                cartMap.put("totalQuantity", model.getTotalQuantity().toString());
                cartMap.put("DateTime",model.getDateTime());
                cartMap.put("totalPrice",model.getTotalPrice());
                cartMap.put("img_url",model.getImg_url());

                firestore.collection("MyOrders").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlaceOrderActivity.this, "order placed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceOrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}