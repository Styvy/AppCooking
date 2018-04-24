package com.steve.navigationdrawer.recycle_view_kitchen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.steve.navigationdrawer.R;
import com.steve.navigationdrawer.entities.Produit;

import java.util.List;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenHolder> {

    private Context ctx;
    private List<Produit> produitList;



    public KitchenAdapter(Context mContext, List<Produit> albumList) {
        this.ctx = mContext;
        this.produitList = albumList;
    }

    @Override
    public KitchenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new KitchenHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final KitchenHolder holder, int position) {
        Produit produit = produitList.get(position);
        holder.title.setText(produit.getName());
        holder.count.setText(produit.getQuantity() + "");

        // loading album cover using Glide library
        Glide.with(ctx).load(produit.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(ctx, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(ctx, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(ctx, "Eat", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return produitList.size();
    }
}
