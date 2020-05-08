package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.ui.startSearchFragment.StartSearchViewModel;


public class MainActivity extends AppCompatActivity {
    NavController navController;
    StartSearchViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        viewModel = new ViewModelProvider(this).get(StartSearchViewModel.class);
    }

    public StartSearchViewModel getViewModel(){
        return viewModel;
    }
}
