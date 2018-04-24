package com.steve.navigationdrawer.les_activity_secondaires;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.steve.navigationdrawer.MainActivity;
import com.steve.navigationdrawer.R;

public class CreerRecettes extends AppCompatActivity {

    TextView text;
    DrawerLayout mDrawerLayout;
    FrameLayout contenuFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_recettes);

        text = findViewById(R.id.creerRecette);
        text.setText("Creer des recettes");
        text.setTextSize(30);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        contenuFrame = findViewById(R.id.content_frame);

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
    }
