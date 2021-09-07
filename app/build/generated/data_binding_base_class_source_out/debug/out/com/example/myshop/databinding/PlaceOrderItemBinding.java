// Generated by view binder compiler. Do not edit!
package com.example.myshop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myshop.R;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PlaceOrderItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView ordersDatetime;

  @NonNull
  public final RoundedImageView ordersImg;

  @NonNull
  public final TextView ordersName;

  @NonNull
  public final TextView ordersPrice;

  @NonNull
  public final TextView ordersPriceTextOnly;

  @NonNull
  public final TextView ordersQuantity;

  @NonNull
  public final TextView ordersQuantityTextOnly;

  @NonNull
  public final TextView ordersTotalPrice;

  @NonNull
  public final TextView ordersTotalPriceTextOnly;

  private PlaceOrderItemBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView ordersDatetime, @NonNull RoundedImageView ordersImg,
      @NonNull TextView ordersName, @NonNull TextView ordersPrice,
      @NonNull TextView ordersPriceTextOnly, @NonNull TextView ordersQuantity,
      @NonNull TextView ordersQuantityTextOnly, @NonNull TextView ordersTotalPrice,
      @NonNull TextView ordersTotalPriceTextOnly) {
    this.rootView = rootView;
    this.ordersDatetime = ordersDatetime;
    this.ordersImg = ordersImg;
    this.ordersName = ordersName;
    this.ordersPrice = ordersPrice;
    this.ordersPriceTextOnly = ordersPriceTextOnly;
    this.ordersQuantity = ordersQuantity;
    this.ordersQuantityTextOnly = ordersQuantityTextOnly;
    this.ordersTotalPrice = ordersTotalPrice;
    this.ordersTotalPriceTextOnly = ordersTotalPriceTextOnly;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PlaceOrderItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PlaceOrderItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.place_order_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PlaceOrderItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.orders_datetime;
      TextView ordersDatetime = ViewBindings.findChildViewById(rootView, id);
      if (ordersDatetime == null) {
        break missingId;
      }

      id = R.id.orders_img;
      RoundedImageView ordersImg = ViewBindings.findChildViewById(rootView, id);
      if (ordersImg == null) {
        break missingId;
      }

      id = R.id.orders_name;
      TextView ordersName = ViewBindings.findChildViewById(rootView, id);
      if (ordersName == null) {
        break missingId;
      }

      id = R.id.orders_price;
      TextView ordersPrice = ViewBindings.findChildViewById(rootView, id);
      if (ordersPrice == null) {
        break missingId;
      }

      id = R.id.orders_price_text_only;
      TextView ordersPriceTextOnly = ViewBindings.findChildViewById(rootView, id);
      if (ordersPriceTextOnly == null) {
        break missingId;
      }

      id = R.id.orders_quantity;
      TextView ordersQuantity = ViewBindings.findChildViewById(rootView, id);
      if (ordersQuantity == null) {
        break missingId;
      }

      id = R.id.orders_quantity_text_only;
      TextView ordersQuantityTextOnly = ViewBindings.findChildViewById(rootView, id);
      if (ordersQuantityTextOnly == null) {
        break missingId;
      }

      id = R.id.orders_total_price;
      TextView ordersTotalPrice = ViewBindings.findChildViewById(rootView, id);
      if (ordersTotalPrice == null) {
        break missingId;
      }

      id = R.id.orders_total_price_text_only;
      TextView ordersTotalPriceTextOnly = ViewBindings.findChildViewById(rootView, id);
      if (ordersTotalPriceTextOnly == null) {
        break missingId;
      }

      return new PlaceOrderItemBinding((ConstraintLayout) rootView, ordersDatetime, ordersImg,
          ordersName, ordersPrice, ordersPriceTextOnly, ordersQuantity, ordersQuantityTextOnly,
          ordersTotalPrice, ordersTotalPriceTextOnly);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}