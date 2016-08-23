package com.wvs.shoppercrux.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.wvs.shoppercrux.R;
import com.wvs.shoppercrux.Product.GetDataAdapter;
import com.wvs.shoppercrux.Product.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity {
    TextView textView;
    List<GetDataAdapter> getDataAdapters;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;
    Toolbar toolbar;
    String GET_JSON_DATA_HTTP_URL = "http://prachodayat.in/shopper_android_api/product.php?id=";
    String JSON_IMAGE_TITLE_NAME = "name";
    String JSON_IMAGE_URL = "image";
    String PRODUCT_ID = "product_id";
    String STORE_URL;
    JsonArrayRequest jsonArrayRequest;
    public static String product_new_data;
    RequestQueue requestQueue;
    public static String sellerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        sellerId = intent.getStringExtra("seller_id");
//        textView = (TextView) findViewById(R.sellerId.display_id);
//        textView.setText(sellerId);
         Log.d("seller_id ",sellerId);
        STORE_URL = GET_JSON_DATA_HTTP_URL + sellerId;
        Log.d("location sellerId", STORE_URL);
        getDataAdapters = new ArrayList<>();

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

                getDataAdapter.setImageTitleNamee(json.getString(JSON_IMAGE_TITLE_NAME));

                getDataAdapter.setImageServerUrl(json.getString(JSON_IMAGE_URL));
                getDataAdapter.setSellerID(json.getString(PRODUCT_ID));

                product_new_data = getDataAdapter.setSellerID(json.getString(PRODUCT_ID));

                Log.d("product_new_data", product_new_data);


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