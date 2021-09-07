package com.example.myshop.ui.Profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.databinding.FragmentProfileBinding;
import com.example.myshop.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileFragment extends Fragment {

    ImageView imageView;
    EditText name, email, number, address;
    Button update;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile,container,false);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        imageView = root.findViewById(R.id.profile_img);
        name=root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number =root.findViewById(R.id.profile_phonenumber);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);

        db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel =snapshot.getValue(UserModel.class);

                        Glide.with(getContext()).load(userModel.getProfileImg()).into(imageView);
                        name.setText(userModel.getName());
                        email.setText(userModel.getEmail());
                        address.setText(userModel.getAddress());
                        number.setText(userModel.getNumber());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        return root;
    }

    private void updateUserProfile() {

        db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("name").setValue(name.getText().toString());
        db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("email").setValue(email.getText().toString());
        db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("number").setValue(number.getText().toString());
        db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("address").setValue(address.getText().toString());
        Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData() != null){
            Uri profileUri = data.getData();
            imageView.setImageURI(profileUri);

            Toast.makeText(getContext(), "Uploading Profile Picture\nPlease Wait Minute...", Toast.LENGTH_SHORT).show();

            final StorageReference reference = storage.getReference().child("profile_pics")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Updated.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}