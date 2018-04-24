package com.steve.navigationdrawer.les_activity_secondaires;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.steve.navigationdrawer.MainActivity;
import com.steve.navigationdrawer.R;
import com.steve.navigationdrawer.entities.Produit;
import com.steve.navigationdrawer.recycle_view_kitchen.KitchenAdapter;

import java.util.ArrayList;
import java.util.List;

public class MesProvisions extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KitchenAdapter kitchenAdapter;
    private List<Produit> produitList;

    DrawerLayout mDrawerLayout;
    FrameLayout contenuFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_provisions);



        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        initCollapsingToolbar();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        contenuFrame = findViewById(R.id.content_frame);



        recyclerView = findViewById(R.id.kitchen_recycler_view);
        produitList = new ArrayList<>();
        kitchenAdapter = new KitchenAdapter(this, produitList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(kitchenAdapter);

        prepareProduitData();

        try {
            Glide.with(this).load(R.drawable.beans).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }





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


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }

    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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


        kitchenAdapter.notifyDataSetChanged();
    }

}
