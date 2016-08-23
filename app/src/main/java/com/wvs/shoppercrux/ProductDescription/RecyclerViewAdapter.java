package com.wvs.shoppercrux.ProductDescription;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wvs.shoppercrux.R;
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

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_description_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        GetDataAdapter getDataAdapter1 = getDataAdapter.get(position);

        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getProductImage(),
                ImageLoader.getImageListener(
                        viewHolder.productImage,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        viewHolder.productImage.setImageUrl(getDataAdapter1.getProductImage(), imageLoader1);

        viewHolder.productName.setText(getDataAdapter1.getProductName());
        viewHolder.productPrice.setText(getDataAdapter1.getProductPrice());
        viewHolder.productDescription.setText(getDataAdapter1.getProductPrice());

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, ProductList.class);
//                context.startActivity(i);
//
////                Toast.makeText(context, "sdfsdfds", Toast.LENGTH_SHORT).show();
////                Log.d("productlist","dfskdkjdnf");
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public NetworkImageView productImage;
        public TextView productPrice;
        public TextView productDescription;

        public ViewHolder(View itemView) {

            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name);

            productImage = (NetworkImageView) itemView.findViewById(R.id.product_image);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productDescription = (TextView) itemView.findViewById(R.id.product_description);
        }
    }
}
