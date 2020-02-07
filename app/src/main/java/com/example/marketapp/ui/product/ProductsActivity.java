package com.example.marketapp.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.marketapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.marketapp.utils.Constants.CATEGORY_ID;
import static com.example.marketapp.utils.Constants.PRODUCT_NAME;

public class ProductsActivity extends AppCompatActivity {

    @BindView(R.id.product_name)
    TextView product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        product_name.setText(getProductName());
    }

    private String getProductName() {
        Intent intent = getIntent();
        String productName = intent.getStringExtra(PRODUCT_NAME);

        Log.e("ID", productName + "");
        return productName;
    }
}