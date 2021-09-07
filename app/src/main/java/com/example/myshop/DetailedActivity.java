package com.example.myshop;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;
import com.example.myshop.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;


public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity=1;
    int totalPrice=0;

    ImageView detailedImg;
    TextView name,price,description,rating;
    Button addToCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;


    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        quantity=findViewById(R.id.detailed_nos);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.detailed_plus);
        removeItem = findViewById(R.id.detailed_minus);
        name = findViewById(R.id.detailed_name);
        price = findViewById(R.id.detailed_price);
        description = findViewById(R.id.detailed_description);
        rating = findViewById(R.id.detailed_rating);
        addToCart = findViewById(R.id.add_to_cart);

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            name.setText(viewAllModel.getName());
            description.setText(viewAllModel.getDescription());
            price.setText(viewAllModel.getPrice());
            rating.setText(viewAllModel.getRating());
            name.setText(viewAllModel.getName());

            totalPrice= Integer.parseInt(viewAllModel.getPrice()) * totalQuantity;
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice= Integer.parseInt(viewAllModel.getPrice()) * totalQuantity;
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice= Integer.parseInt(viewAllModel.getPrice()) * totalQuantity;
                }

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addedToCart() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String datetime = dtf.format(now);

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("DateTime",datetime);
        cartMap.put("totalPrice",totalPrice);
        cartMap.put("img_url",viewAllModel.getImg_url());

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}