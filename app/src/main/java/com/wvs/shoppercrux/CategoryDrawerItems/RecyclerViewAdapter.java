package com.wvs.shoppercrux.CategoryDrawerItems;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wvs.shoppercrux.R;
import com.wvs.shoppercrux.activities.StoreMapView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ItemObject> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        holder.songTitle.setText("" + itemList.get(position).getSongTitle());
        holder.songYear.setText("" + itemList.get(position).getSongYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              int pos = getItemCount();
//                Intent i = new Intent(context, StoreActivity.class);
////                i.putExtra("location_id",itemList.get(position).getSongYear());
//                context.startActivity(i);
                String data =itemList.get(position).getSongYear();
                Intent i = new Intent(context, StoreMapView.class);
                i.putExtra("location_id",data);
                context.startActivity(i);


                Toast.makeText(context, itemList.get(position).getSongYear(), Toast.LENGTH_SHORT).show();

            }
        });
        //holder.songAuthor.setText("Song Author: " + itemList.get(position).getSongAuthor());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
