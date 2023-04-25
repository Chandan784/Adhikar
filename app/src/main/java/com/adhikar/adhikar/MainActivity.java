package com.adhikar.adhikar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.adhikar.adhikar.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    public static DrawerLayout drawerLayout;
    private NavigationView navigationView;

    TextView adhikar;
    private ActionBarDrawerToggle toggle;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);


        //Assigning Our Custom Toolbar && Hamburger Icon

        toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        adhikar = findViewById(R.id.main_activity_action_bar_title);
        //Done!! Assigning Toolbar && Hamburger Icon

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_toggle, R.string.close_toggle);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.nav_signout :{
                       SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = preferences.edit();
                       editor.remove("isUserLogin");
                       editor.apply();
                       editor.commit();
                       break;

                   }
                   case R.id.nav_privacy :{
                       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://educhandan.com/adhikar_privacy.html"));
                       startActivity(browserIntent);
                       break;

                   }



               }
               drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        setDefaultFragment(new HomeFragment());
    }

    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
}