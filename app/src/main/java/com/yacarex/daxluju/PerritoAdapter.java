package com.yacarex.daxluju;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PerritoAdapter extends RecyclerView.Adapter<PerritoAdapter.ViewHolder> {

    ArrayList<PerritoModel> arrayList;
    Context context;

    public PerritoAdapter(ArrayList<PerritoModel> list, Context context) {
        this.arrayList = list;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.perrito_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.text.setText(arrayList.get(i).getName());
        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.perrito_layout_title);
            image = itemView.findViewById(R.id.perrito_layout_image);
        }
    }
}
