package com.steve.navigationdrawer.recycle_view_shopping;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.steve.navigationdrawer.R;

public class MyShoppingHolder extends RecyclerView.ViewHolder {

    public TextView name, category, quantity;
    public ImageView thumbnail;
    public RelativeLayout viewBackground, viewForeground;

    public MyShoppingHolder(View view) {
        super(view);

        name = view.findViewById(R.id.name_produit);
        category = view.findViewById(R.id.category_produit);
        quantity = view.findViewById(R.id.quantity_produit);
        thumbnail = view.findViewById(R.id.thumbnail);

        viewBackground = view.findViewById(R.id.view_background);
        viewForeground = view.findViewById(R.id.view_foreground);

    }
}
