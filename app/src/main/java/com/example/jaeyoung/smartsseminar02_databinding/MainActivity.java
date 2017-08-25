package com.example.jaeyoung.smartsseminar02_databinding;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.jaeyoung.smartsseminar02_databinding.databinding.ActivityMainBinding;
import com.example.jaeyoung.smartsseminar02_databinding.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setMainviewmodel(mainViewModel);


        binding.spinnerCompanies.setAdapter(
                new ArrayAdapter<String>(
                        this,
                        R.layout.support_simple_spinner_dropdown_item,
                        new ArrayList<String>()
                )
        );
    }

}
