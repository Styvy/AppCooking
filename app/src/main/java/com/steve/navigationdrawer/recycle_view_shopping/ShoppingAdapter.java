package com.steve.navigationdrawer.recycle_view_shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.steve.navigationdrawer.R;
import com.steve.navigationdrawer.entities.Produit;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<MyShoppingHolder> {

    private Context ctx;
    private List<Produit> produitList;

    public ShoppingAdapter(Context context, List<Produit> produitList) {
        this.produitList = produitList;
        this.ctx = context;
    }

    @NonNull
    //this method inflates the layout
    @Override
    public MyShoppingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.produit_list_row, parent, false);
        return new MyShoppingHolder(itemview);
    }

    @Override
    //this method set the data
    public void onBindViewHolder(MyShoppingHolder holder, int position) {
        Produit produit = produitList.get(position);
        holder.name.setText(produit.getName());
        holder.category.setText(produit.getCategory());
        holder.quantity.setText(""+produit.getQuantity());

        Glide.with(ctx)
                .load(produit.getThumbnail())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return produitList.size();
    }


    public void removeItem(int position) {
        produitList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Produit item, int position) {
        produitList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
