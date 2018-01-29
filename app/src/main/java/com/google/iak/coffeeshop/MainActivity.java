package com.google.iak.coffeeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText kolom_nama;
    CheckBox whipped_cream, chocolate;
    Button decrement_button, increment_button, order_button;
    TextView total_harga, quantity_label;

    int price = 2;
    int whipped_cream_price = 1;
    int chocolate_price = 2;

    int additional_price;
    String use_whipped_cream;
    String use_chocolate;

    private void setupView() {
        kolom_nama = findViewById(R.id.nama_pemesan);
        whipped_cream = findViewById(R.id.add_whipped_cream);
        chocolate = findViewById(R.id.add_chocholate);
        decrement_button = findViewById(R.id.decrement);
        increment_button = findViewById(R.id.increment);
        quantity_label = findViewById(R.id.quantity_text);
        total_harga = findViewById(R.id.total_price);
        order_button = findViewById(R.id.order);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        final CoffeeClass cappucino = new CoffeeClass();
        cappucino.setQuantity(0);
        quantity_label.setText("" + cappucino.getQuantity());


        whipped_cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setPriceTotal(cappucino);
            }
        });

        chocolate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setPriceTotal(cappucino);
            }
        });

        decrement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cappucino.setQuantity(cappucino.decrement(MainActivity.this));
                setPriceTotal(cappucino);

            }
        });

        increment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cappucino.setQuantity(cappucino.increment());

                setPriceTotal(cappucino);
            }
        });

        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kolom_nama.getText().toString() == "") {
                    Toast.makeText(MainActivity.this, "Please input customer name", Toast.LENGTH_LONG).show();
                    return;
                }
                cappucino.setNama(kolom_nama.getText().toString());
                if (whipped_cream.isChecked() == true) {
                    use_whipped_cream = "Ya";
                } else {
                    use_whipped_cream = "Tidak";
                }

                if (chocolate.isChecked() == true) {
                    use_chocolate = "Ya";
                } else {
                    use_chocolate = "Tidak";
                }
                Intent intent = new Intent(MainActivity.this, ReservedAcivity.class);
                intent.putExtra("nama_pemesan", cappucino.getNama());
                intent.putExtra("total_harga", cappucino.getPrice());
                intent.putExtra("jumlah_barang", cappucino.getQuantity());
                intent.putExtra("whipped_cream", use_whipped_cream);
                intent.putExtra("chocolate", use_chocolate);


                startActivity(intent);
            }
        });


    }


    public void setPriceTotal(CoffeeClass coffeeClass) {

        coffeeClass.topping(whipped_cream.isChecked(), chocolate.isChecked(), whipped_cream_price, chocolate_price);
        coffeeClass.setPrice(price);
        additional_price = coffeeClass.getAdditionalPrice();
        coffeeClass.resetAdditionalPrice();
        quantity_label.setText(String.valueOf(coffeeClass.getQuantity()));
        total_harga.setText("$ " + coffeeClass.getPrice());
    }


}
