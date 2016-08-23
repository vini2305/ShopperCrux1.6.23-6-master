package com.wvs.shoppercrux.Product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wvs.shoppercrux.R;
import com.wvs.shoppercrux.activities.Product;
import com.wvs.shoppercrux.activities.ProductList;

import java.util.List;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<GetDataAdapter> getDataAdapter;

    ImageLoader imageLoader1;

    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context) {

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        GetDataAdapter getDataAdapter1 = getDataAdapter.get(position);

        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getImageServerUrl(),
                ImageLoader.getImageListener(
                        viewHolder.networkImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        viewHolder.networkImageView.setImageUrl(getDataAdapter1.getImageServerUrl(), imageLoader1);

        viewHolder.ImageTitleNameView.setText(getDataAdapter1.getImageTitleName());
        viewHolder.sellerId.setText(getDataAdapter1.getSellerID());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataAdapter getDataAdapter1 = getDataAdapter.get(position);
                String sell = Product.sellerId;
                Log.d("sellnewid", sell);
                String product_id = getDataAdapter1.getSellerID();

                Intent i = new Intent(context, ProductList.class);
              //  i.putExtra("seller_id", sell);
                i.putExtra("product_id", product_id);
                context.startActivity(i);

//                Toast.makeText(context, "sdfsdfds", Toast.LENGTH_SHORT).show();
//                Log.d("productlist","dfskdkjdnf");
            }
        });
    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ImageTitleNameView;
        public NetworkImageView networkImageView;
        public TextView sellerId;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item);

            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1);
            sellerId = (TextView) itemView.findViewById(R.id.tx_seller_id);
        }
    }
}
