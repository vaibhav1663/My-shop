package com.example.myshop.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.Adapters.NavCategoryAdaper;
import com.example.myshop.Adapters.PopularAdapter;
import com.example.myshop.R;
import com.example.myshop.databinding.FragmentCategoryBinding;
import com.example.myshop.databinding.FragmentCategoryBinding;
import com.example.myshop.models.NavCategoryModel;
import com.example.myshop.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    FirebaseFirestore db;

    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdaper navCategoryAdaper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category,container,false);

        recyclerView = root.findViewById(R.id.cat_rec);

        db= FirebaseFirestore.getInstance();


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        navCategoryAdaper = new NavCategoryAdaper(getActivity(),categoryModelList);
        recyclerView.setAdapter(navCategoryAdaper);

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                categoryModelList.add(navCategoryModel);
                                navCategoryAdaper.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        return root;
    }

}