package dev.rabbul.colleger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.cardview.widget.CardView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    CardView cd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadtooLbar();

        boolean isFirstRun = getSharedPreferences("Colleger_User_settings", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            startActivity(new Intent(MainActivity.this, login.class));
            Toast.makeText(MainActivity.this, "Run only once", Toast.LENGTH_LONG).show();
        }

        getSharedPreferences("Colleger_User_settings", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

        if(!isFirstRun) {
            SharedPreferences sh = getSharedPreferences("Colleger_User_settings", MODE_PRIVATE);
            String s1 = sh.getString("name", "");
        }


        cd = findViewById(R.id.card_routine);
        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.drawer_layout),
                        R.string.app_name, Snackbar.LENGTH_SHORT);
                mySnackbar.setAction(R.string.app_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"SnakbarClicked",Toast.LENGTH_SHORT).show();
                    }
                });
                mySnackbar.show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_first:
                Toast.makeText(MainActivity.this, "Menu Item First clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }

    private void LoadtooLbar() {
        //Initializes The Toolbar and Navigation drawer
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_activity1:
                Toast.makeText(MainActivity.this,"Navigation Item First clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_activity2:
                startAnimatedActivity(new Intent(getApplicationContext(), SecondActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void close_nav_nav(View view) {
        Toast.makeText(MainActivity.this,"Close Clicked",Toast.LENGTH_SHORT).show();
    }
    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
