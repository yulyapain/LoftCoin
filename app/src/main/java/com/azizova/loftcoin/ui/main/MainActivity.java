package com.azizova.loftcoin.ui.main;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.azizova.loftcoin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NavController navController = Navigation.findNavController(this, R.id.main_host);
        final BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(navigationView, navController);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController, new AppBarConfiguration
                .Builder(navigationView.getMenu())
                .build());
        final int startDestination = navController.getGraph().getStartDestination();
        final NavDestination node = navController.getGraph().findNode(startDestination);
        if (node != null) {
            setTitle(node.getLabel());
        }
    }
}
