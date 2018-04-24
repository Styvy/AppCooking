package com.steve.navigationdrawer.recycle_view_kitchen;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.steve.navigationdrawer.R;

public class KitchenHolder extends RecyclerView.ViewHolder {

    public TextView title, count;
    public ImageView thumbnail, overflow;

    public KitchenHolder(View view) {
        super(view);
        title = view.findViewById(R.id.title);
        count =view.findViewById(R.id.count);
        thumbnail =view.findViewById(R.id.thumbnail);
        overflow =view.findViewById(R.id.overflow);
    }
}
