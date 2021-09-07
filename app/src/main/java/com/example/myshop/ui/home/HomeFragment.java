package com.example.myshop.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.Adapters.HomeCategoryAdapter;
import com.example.myshop.Adapters.PopularAdapter;
import com.example.myshop.Adapters.RecommendedAdapter;
import com.example.myshop.Adapters.ViewAllAdapter;
import com.example.myshop.R;
import com.example.myshop.models.HomeCategory;
import com.example.myshop.models.PopularModel;
import com.example.myshop.models.RecommendedModel;
import com.example.myshop.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ScrollView homeScrollView;
    ProgressBar progressBar;

    RecyclerView popularRec,catRec, recommendedRec;
    FirebaseFirestore db;

    //popular items
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    //Search View
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView searchRecyclerView;
    private ViewAllAdapter viewAllAdapter;

    //category items
    List<HomeCategory> categoryList;
    HomeCategoryAdapter homeCategoryAdapter;

    //Recommended items
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db= FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        catRec = root.findViewById(R.id.exp_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        homeScrollView = root.findViewById(R.id.HomescrollView);
        progressBar = root.findViewById(R.id.HomeProgressBar);

        progressBar.setVisibility(View.VISIBLE);
        homeScrollView.setVisibility(View.GONE);

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                homeScrollView.setVisibility(View.VISIBLE);
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });





        // Explore category

        catRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getActivity(),categoryList);
        catRec.setAdapter(homeCategoryAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory category = document.toObject(HomeCategory.class);
                                categoryList.add(category);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });





        //Recommended view starts here...
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        // Search View
        search_box = root.findViewById(R.id.searchbox);
        searchRecyclerView= root.findViewById(R.id.search_rec);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter= new ViewAllAdapter(getContext(),viewAllModelList);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecyclerView.setAdapter(viewAllAdapter);
        searchRecyclerView.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }
                else {
                    searchProduct(s.toString());
                }
            }
        });

        return root;
    }

    private void searchProduct(String type) {
        if(!type.isEmpty()){

            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()&& task.getResult()!=null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel= documentSnapshot.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }

                            }
                        }
                    });

        }
    }

}