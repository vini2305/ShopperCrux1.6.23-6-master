package com.wvs.shoppercrux.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.wvs.shoppercrux.ProductDescription.GetDataAdapter;
import com.wvs.shoppercrux.ProductDescription.RecyclerViewAdapter;
import com.wvs.shoppercrux.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {
    TextView productName, productPrice, productDescription;
    ImageView productImage;
    String STORE_URL;
    JsonArrayRequest jsonArrayRequest;
    RecyclerView.Adapter recyclerViewadapter;
    RequestQueue requestQueue;
    String GET_JSON_DATA_HTTP_URL = "http://prachodayat.in/shopper_android_api/product_page.php?id=";
    List<GetDataAdapter> getDataAdapters;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        productName = (TextView) findViewById(R.id.product_name);
        productPrice = (TextView) findViewById(R.id.product_price);
        productDescription = (TextView) findViewById(R.id.product_description);
        productImage = (ImageView) findViewById(R.id.product_image);
        getDataAdapters = new ArrayList<>();

        Intent intent = getIntent();

        String s1 = intent.getStringExtra("product_id");
        Log.d("productid", s1);
        STORE_URL = GET_JSON_DATA_HTTP_URL + s1;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        JSON_DATA_WEB_CALL();
    }

    public void JSON_DATA_WEB_CALL() {

        jsonArrayRequest = new JsonArrayRequest(STORE_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            GetDataAdapter getDataAdapter = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                getDataAdapter.setProductImage(json.getString("image"));
                getDataAdapter.setProductName(json.getString("name"));

                getDataAdapter.setProductPrice(json.getString("price"));
                getDataAdapter.setProductDesription(json.getString("description"));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            getDataAdapters.add(getDataAdapter);

        }
        recyclerViewadapter = new RecyclerViewAdapter(getDataAdapters, this);

        recyclerView.setAdapter(recyclerViewadapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
