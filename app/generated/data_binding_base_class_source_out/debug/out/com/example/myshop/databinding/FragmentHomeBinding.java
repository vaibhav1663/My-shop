// Generated by view binder compiler. Do not edit!
package com.example.myshop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myshop.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ProgressBar HomeProgressBar;

  @NonNull
  public final ScrollView HomescrollView;

  @NonNull
  public final RecyclerView expRec;

  @NonNull
  public final RecyclerView popRec;

  @NonNull
  public final TextView popolarproducts;

  @NonNull
  public final TextView popularviewall;

  @NonNull
  public final RecyclerView recommendedRec;

  @NonNull
  public final RecyclerView searchRec;

  @NonNull
  public final EditText searchbox;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView viewallRecomomded;

  @NonNull
  public final TextView viewallexplore;

  private FragmentHomeBinding(@NonNull RelativeLayout rootView,
      @NonNull ProgressBar HomeProgressBar, @NonNull ScrollView HomescrollView,
      @NonNull RecyclerView expRec, @NonNull RecyclerView popRec, @NonNull TextView popolarproducts,
      @NonNull TextView popularviewall, @NonNull RecyclerView recommendedRec,
      @NonNull RecyclerView searchRec, @NonNull EditText searchbox, @NonNull TextView textView3,
      @NonNull TextView viewallRecomomded, @NonNull TextView viewallexplore) {
    this.rootView = rootView;
    this.HomeProgressBar = HomeProgressBar;
    this.HomescrollView = HomescrollView;
    this.expRec = expRec;
    this.popRec = popRec;
    this.popolarproducts = popolarproducts;
    this.popularviewall = popularviewall;
    this.recommendedRec = recommendedRec;
    this.searchRec = searchRec;
    this.searchbox = searchbox;
    this.textView3 = textView3;
    this.viewallRecomomded = viewallRecomomded;
    this.viewallexplore = viewallexplore;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.HomeProgressBar;
      ProgressBar HomeProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (HomeProgressBar == null) {
        break missingId;
      }

      id = R.id.HomescrollView;
      ScrollView HomescrollView = ViewBindings.findChildViewById(rootView, id);
      if (HomescrollView == null) {
        break missingId;
      }

      id = R.id.exp_rec;
      RecyclerView expRec = ViewBindings.findChildViewById(rootView, id);
      if (expRec == null) {
        break missingId;
      }

      id = R.id.pop_rec;
      RecyclerView popRec = ViewBindings.findChildViewById(rootView, id);
      if (popRec == null) {
        break missingId;
      }

      id = R.id.popolarproducts;
      TextView popolarproducts = ViewBindings.findChildViewById(rootView, id);
      if (popolarproducts == null) {
        break missingId;
      }

      id = R.id.popularviewall;
      TextView popularviewall = ViewBindings.findChildViewById(rootView, id);
      if (popularviewall == null) {
        break missingId;
      }

      id = R.id.recommended_rec;
      RecyclerView recommendedRec = ViewBindings.findChildViewById(rootView, id);
      if (recommendedRec == null) {
        break missingId;
      }

      id = R.id.search_rec;
      RecyclerView searchRec = ViewBindings.findChildViewById(rootView, id);
      if (searchRec == null) {
        break missingId;
      }

      id = R.id.searchbox;
      EditText searchbox = ViewBindings.findChildViewById(rootView, id);
      if (searchbox == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.viewallRecomomded;
      TextView viewallRecomomded = ViewBindings.findChildViewById(rootView, id);
      if (viewallRecomomded == null) {
        break missingId;
      }

      id = R.id.viewallexplore;
      TextView viewallexplore = ViewBindings.findChildViewById(rootView, id);
      if (viewallexplore == null) {
        break missingId;
      }

      return new FragmentHomeBinding((RelativeLayout) rootView, HomeProgressBar, HomescrollView,
          expRec, popRec, popolarproducts, popularviewall, recommendedRec, searchRec, searchbox,
          textView3, viewallRecomomded, viewallexplore);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
