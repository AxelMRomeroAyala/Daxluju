package com.yacarex.daxluju;

import android.app.LauncherActivity;
import android.content.Context;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ElAdapter extends RecyclerView.Adapter<ElAdapter.ViewHolder> {

    private List<ImageListModel> listItems;
    private Context context;

    public ElAdapter(List<ImageListModel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @Override

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.item_layout_model,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ElAdapter.ViewHolder viewHolder, int i) {

        ImageListModel listItem = listItems.get(i);


        Glide.with(this.context)
                .load(listItem.getItemThumbnail())
                .into(viewHolder.itemThumbnail);

        viewHolder.itemTitle.setText(listItem.getItemTitle());
        viewHolder.itemDesc.setText(listItem.getItemDesc());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemThumbnail;
        public TextView itemTitle, itemDesc;


        public ViewHolder(View itemView) {
            super(itemView);

            itemThumbnail   = itemView.findViewById(R.id.itemThumbnail);
            itemTitle       = itemView.findViewById(R.id.itemTitle);
            itemDesc        = itemView.findViewById(R.id.itemDescription);

        }

    }


}









