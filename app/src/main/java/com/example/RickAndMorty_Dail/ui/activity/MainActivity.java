package com.example.RickAndMorty_Dail.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.RickAndMorty_Dail.R;
import com.example.RickAndMorty_Dail.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationInit();
    }

    private void navigationInit() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterFragment, R.id.episodeFragment, R.id.locationFragment, R.id.dialogFragment
                ).build();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolBar, navController, appBarConfiguration);
    }
}