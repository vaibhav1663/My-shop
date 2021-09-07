package com.example.myshop.models;

import java.io.Serializable;

public class MyOrderModel {

    String productName;
    String productPrice;
    String totalQuantity;
    String DateTime;
    int totalPrice;
    String img_url;


        public MyOrderModel(String productName, String productPrice, String totalQuantity, String dateTime, int totalPrice, String img_url) {
            this.productName = productName;
            this.productPrice = productPrice;
            this.totalQuantity = totalQuantity;
            DateTime = dateTime;
            this.totalPrice = totalPrice;
            this.img_url = img_url;
        }

        public MyOrderModel() {
        }


        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(String totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String dateTime) {
            DateTime = dateTime;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

    }

