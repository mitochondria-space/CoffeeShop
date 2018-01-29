package com.google.iak.coffeeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ReservedAcivity extends AppCompatActivity {
    TextView orderSummary,title;

    String nama;
    int harga;
    int jumlah_barang;
    String whipped_cream;
    String chocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved);
        getData();
        orderSummary=findViewById(R.id.order_summary);
        title=findViewById(R.id.title);
        title.setText(Html.fromHtml("<u>Summary Order</u>"));
        orderSummary.setText(
                "Pemesan : " +nama+"\n"+
                "Topping Whipped Cream: "+whipped_cream+"\n"+
                "Topping Chocolate: "+chocolate+"\n"+
                "Jumlah : "+jumlah_barang+"\n"+
                "Total Harga: $ "+harga+"\n\n"+
                "Selamat Menikmati !");
    }


    private void getData() {
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama_pemesan");
        harga = intent.getIntExtra("total_harga", 0);
        jumlah_barang = intent.getIntExtra("jumlah_barang", 0);
        whipped_cream = intent.getStringExtra("whipped_cream");
        chocolate = intent.getStringExtra("chocolate");
    }
}
