package com.steve.navigationdrawer.les_activity_secondaires;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steve.navigationdrawer.MainActivity;
import com.steve.navigationdrawer.R;
import com.steve.navigationdrawer.entities.Produit;
import com.steve.navigationdrawer.recycle_view_shopping.MyShoppingHolder;
import com.steve.navigationdrawer.recycle_view_shopping.RecyclerItemTouchHelper;
import com.steve.navigationdrawer.recycle_view_shopping.RecyclerTouchListener;
import com.steve.navigationdrawer.recycle_view_shopping.ShoppingAdapter;
import com.steve.navigationdrawer.recycle_view_shopping.ShoppingListDecoration;

import java.util.ArrayList;
import java.util.List;

public class ListeDeCourse extends AppCompatActivity  implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private List<Produit> produitList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView.LayoutManager produitLayoutManager;

    TextView text;
    DrawerLayout mDrawerLayout;
    FrameLayout contenuFrame;
    FloatingActionButton btnFloat;
    Context ctx;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_de_course);

        ctx = this;

        btnFloat = findViewById(R.id.fab_scan);

        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(ctx,BarcodeActivity.class);
                startActivity(intent);

            }
        });


        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        contenuFrame = findViewById(R.id.content_frame);


        recyclerView =findViewById(R.id.shopping_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);


        //need an adapter ad for ListView
        shoppingAdapter = new ShoppingAdapter(getApplicationContext(),produitList);

        //here we need a layout manager
        //here we could simply apply an horizontal layout manager to obtain an horizontal scrolling, un-comment the below line to try!
        //produitLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);

        produitLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(produitLayoutManager);

        //yes in recycle view we can use an animator!
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //so much fuss about a simple line!
        recyclerView.addItemDecoration(new ShoppingListDecoration(this, LinearLayoutManager.VERTICAL,16));

        //set the adapter to the recycle view and you're good to go
        recyclerView.setAdapter(shoppingAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Produit produit = produitList.get(position);
                Toast.makeText(getApplicationContext(), produit.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //redefined as well, not used up to now!

            }
        }));


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);




        //my own data to test the above =D
        prepareProduitData();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch (menuItem.getItemId()) {
                            case R.id.listeCourse:
                                Intent ActCourse = new Intent(getBaseContext(), ListeDeCourse.class);
                                startActivity(ActCourse);
                                break;

                            case R.id.mesProvisions:
                                Intent ActProvision = new Intent(getBaseContext(), MesProvisions.class);
                                startActivity(ActProvision);
                                break;

                            case R.id.mesRecettes:
                                Intent ActRecettes = new Intent(getBaseContext(), MesRecettes.class);
                                startActivity(ActRecettes);
                                break;

                            case R.id.creerRecette:
                                Intent ActCreerRecette = new Intent(getBaseContext(), CreerRecettes.class);
                                startActivity(ActCreerRecette);
                                break;

                            case R.id.acceuil:
                                Intent ActAcceuil = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(ActAcceuil);
                                break;
                        }

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof MyShoppingHolder) {
            // get the removed item name to display it in snack bar
            String name = produitList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Produit deletedItem = produitList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            shoppingAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    shoppingAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }



    private void prepareProduitData() {
        Produit produit = new Produit("Potatoes", "Cupboard", 15, R.drawable.potatoes);
        produitList.add(produit);

        produit = new Produit("Beans", "Cupboard", 1, R.drawable.beans);
        produitList.add(produit);

        produit = new Produit("Ice Cream", "Freezer", 2, R.drawable.ice_cream);
        produitList.add(produit);

        produit = new Produit("Milk", "Fridge", 3, R.drawable.milk);
        produitList.add(produit);

        produit = new Produit("Potatoes", "Cupboard", 15, R.drawable.potatoes);
        produitList.add(produit);

        produit = new Produit("Beans", "Cupboard", 1, R.drawable.beans);
        produitList.add(produit);

        produit = new Produit("Ice Cream", "Freezer", 2, R.drawable.ice_cream);
        produitList.add(produit);

        produit = new Produit("Milk", "Fridge", 3, R.drawable.milk);
        produitList.add(produit);

        produit = new Produit("Potatoes", "Cupboard", 15, R.drawable.potatoes);
        produitList.add(produit);

        produit = new Produit("Beans", "Cupboard", 1, R.drawable.beans);
        produitList.add(produit);

        produit = new Produit("Ice Cream", "Freezer", 2, R.drawable.ice_cream);
        produitList.add(produit);

        produit = new Produit("Milk", "Fridge", 3, R.drawable.milk);
        produitList.add(produit);

        produit = new Produit("Potatoes", "Cupboard", 15, R.drawable.potatoes);
        produitList.add(produit);

        produit = new Produit("Beans", "Cupboard", 1, R.drawable.beans);
        produitList.add(produit);

        produit = new Produit("Ice Cream", "Freezer", 2, R.drawable.ice_cream);
        produitList.add(produit);

        produit = new Produit("Milk", "Fridge", 3, R.drawable.milk);
        produitList.add(produit);



        shoppingAdapter.notifyDataSetChanged();
    }


}
