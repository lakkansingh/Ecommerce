package com.e.ecommerce.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import com.e.ecommerce.R;
import com.e.ecommerce.fragment.CategoriesFragment;
import com.e.ecommerce.fragment.HomeFragment;
import com.e.ecommerce.fragment.OffersFragment;
import com.e.ecommerce.fragment.ProductsCategoryListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView heading_toolbar_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        heading_toolbar_name = findViewById(R.id.heading_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_id);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setProductFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {

            // Handle the camera action
        } else if (id == R.id.nav_profile) {
            Toast.makeText(getApplicationContext(), "Need to implement...", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_show_order) {
            Toast.makeText(getApplicationContext(), "Need to implement...", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_show_interest) {
            Toast.makeText(getApplicationContext(), "Need to implement...", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_about) {
            Toast.makeText(getApplicationContext(), "Need to implement...", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_cart) {
            Toast.makeText(getApplicationContext(), "Need to implement...", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_sign_out) {
            Toast.makeText(getApplicationContext(), "Need to implement...", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    heading_toolbar_name.setText(getString((R.string.e_commerce)));
                    setProductFragment(new HomeFragment());
                    return true;
                case R.id.navigation_dashboard:
                    heading_toolbar_name.setText(getString(R.string.title_category));
                    setProductFragment(new ProductsCategoryListFragment());
                    return true;
                case R.id.navigation_notifications:
                    heading_toolbar_name.setText(getString(R.string.title_offers));

                    setProductFragment(new OffersFragment());
                    return true;
            }
            return false;
        }
    };
    private void setProductFragment(Fragment framentDemo) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentTransaction != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putBoolean("sale_on", true);
                framentDemo.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_body_container, framentDemo);
                fragmentTransaction.commit();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
