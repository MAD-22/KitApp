package com.android.kitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.android.kitapp.crud.article.CreateArticle;
import com.android.kitapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_article, R.id.nav_book, R.id.nav_inquire, R.id.nav_order)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action"+navigationView.getCheckedItem(), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                if("Book".equals(Objects.requireNonNull(navigationView.getCheckedItem()).toString())){
                    Intent intent = new Intent(MainActivity.this, CreateBook.class);
                    startActivity(intent);
                }else if("Article".equals(navigationView.getCheckedItem().toString())){
                    Intent intent = new Intent(MainActivity.this, CreateArticle.class);
                    startActivity(intent);
                }else if("Inquire".equals(navigationView.getCheckedItem().toString())){
                    Intent intent = new Intent(MainActivity.this, CreateInquire.class);
                    startActivity(intent);
                }else if("Order".equals(navigationView.getCheckedItem().toString())){
                    Intent intent = new Intent(MainActivity.this, OrderSelectItems.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}