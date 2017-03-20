package com.example.ola.lagosjavadevs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.squareup.picasso.Picasso;

/**
 * Created by 246 on 3/15/2017.
 */

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.MyViewHolder> {

    private List<Dev.ItemComponents> itemComponents;
    private Context mContext;





    public DevAdapter(Context context,  List<Dev.ItemComponents> itemComponents) {
        mContext = context;
        this.itemComponents = itemComponents;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row_item, parent, false);


        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(DevAdapter.MyViewHolder holder, int position) {

        final DevAdapter.MyViewHolder myHolder = holder;

        // myHolder.title.setImageAlpha(itemsEntities.get(position).getAvatar_url());
        myHolder.devNames.setText(itemComponents.get(position).getLogin());


        Picasso.with(mContext).load(itemComponents.get(position).getAvatar_url())
                .into(myHolder.picture);



    }

    @Override
    public int getItemCount() {
        return itemComponents.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView devNames;
        public ImageView picture;

        public MyViewHolder(View view) {
            super(view);
            picture = (ImageView) view.findViewById(R.id.avatar);
            devNames = (TextView) view.findViewById(R.id.name);
        }
    }

}
