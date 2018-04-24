package com.steve.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.steve.navigationdrawer.les_activity_secondaires.CreerRecettes;
import com.steve.navigationdrawer.les_activity_secondaires.ListeDeCourse;
import com.steve.navigationdrawer.les_activity_secondaires.MesRecettes;
import com.steve.navigationdrawer.les_activity_secondaires.MesProvisions;
import com.steve.navigationdrawer.lestabsfragments.FreezerFragment;
import com.steve.navigationdrawer.lestabsfragments.FrigoFragment;
import com.steve.navigationdrawer.lestabsfragments.PlacardFragment;


public class MainActivity extends AppCompatActivity {
    public FragmentTabHost mTabHost;
    DrawerLayout mDrawerLayout;
    FrameLayout contenuFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        contenuFrame = findViewById(R.id.content_frame);


        mTabHost = findViewById(android.R.id.tabhost);


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

//        mDrawerLayout.addDrawerListener(
//                new DrawerLayout.DrawerListener() {
//                    @Override
//                    public void onDrawerSlide(View drawerView, float slideOffset) {
//                        // Respond when the drawer's position changes
//                    }
//
//                    @Override
//                    public void onDrawerOpened(View drawerView) {
//
//
//                    }
//
//                    @Override
//                    public void onDrawerClosed(View drawerView) {
//                        // Respond when the drawer is closed
//                    }
//
//                    @Override
//                    public void onDrawerStateChanged(int newState) {
//                        // Respond when the drawer motion state changes
//                    }
//                }
//        );


        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Frigo", null),
                FrigoFragment.class, null);


        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Placard", null),
                PlacardFragment.class, null);


        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Freezer", null),
                FreezerFragment.class, null);
//        ici

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


}
