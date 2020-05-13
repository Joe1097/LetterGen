package com.example.lettergen;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MenuSlideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slide);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Se abre el Fragment FragmentFast al momento de iniciar la app
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.escenario, new FragmentFast()).commit();
            navigationView.setCheckedItem(R.id.nav_Fast);
            this.setTitle(R.string.string_fast); // Se asigna el correspondiente titulo
        }
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

    // Fucion que obtiene el MenuItem seleccionado del menu lateral para abrir el Fragment correspondiente
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId(); // Se obtiene el id del Item
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_Fast) {
            this.setTitle(R.string.string_fast); // Se asigna el titulo
            fragmentManager.beginTransaction().replace(R.id.escenario, new FragmentFast()).commit(); // Y se abre el Fragment
        } else if (id == R.id.nav_Classic) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new FragmentClassic()).commit();
            this.setTitle(R.string.string_classic);
        } else if (id == R.id.nav_Settings) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new FragmentChangeDefaultLetters()).commit();
            this.setTitle(R.string.menu_change_default_letters);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
