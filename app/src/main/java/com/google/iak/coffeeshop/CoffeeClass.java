package com.google.iak.coffeeshop;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by immobi on 26/01/18.
 */

public class CoffeeClass {
    private String mNama;
    private int mQuantity;
    private int mPrice;
    private int mAdditionalPrice = 0;
    private int mChocolatePrice = 0;

    int mWhippedCreamPrice;

     public void setNama(String nama) {
         mNama = nama;
     }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public void setPrice(int product_price) {

        mPrice = (product_price + mAdditionalPrice) * mQuantity;
    }


        public String getNama() {
            return mNama;
        }

    public int getQuantity() {
        return mQuantity;
    }

    public int getPrice() {
        return mPrice;
    }


    public int increment() {
        mQuantity = mQuantity + 1;
        return mQuantity;
    }


    public int decrement(Context context) {

        if (mQuantity == 0) {
            Toast.makeText(context, "Cannot less than zero", Toast.LENGTH_SHORT).show();
            return 0;
        }
        mQuantity = mQuantity - 1;
        return mQuantity;
    }

    public void topping(Boolean whippedCream, Boolean chocolate, int whippedCreamPrice, int chocolatePrice) {
        mWhippedCreamPrice = whippedCreamPrice;
        mChocolatePrice = chocolatePrice;

        if (whippedCream == false) {
            mWhippedCreamPrice = 0;
        }

        if (chocolate == false) {
            mChocolatePrice = 0;
        }
        mAdditionalPrice = mAdditionalPrice + mWhippedCreamPrice + mChocolatePrice;

    }

    public void resetAdditionalPrice(){
        mAdditionalPrice=mAdditionalPrice -mWhippedCreamPrice-mChocolatePrice;
    }


    public void setAdditionalPrice(int additionalPrice) {
        mAdditionalPrice = additionalPrice;
    }
    public int getAdditionalPrice(){
        return mAdditionalPrice;
    }


}
