package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        initializeBottomView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new MainFragment());
        transaction.commit();
    }

    private void initializeBottomView(){
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.home:
                        transaction.replace(R.id.container,new MainFragment());
                        transaction.commit();
                        break;
                    case R.id.add:
                        transaction.replace(R.id.container,new AddInvoiceFragment());
                        transaction.commit();
                        break;
                    case R.id.chart:
                        transaction.replace(R.id.container,new ChartFragment());
                        transaction.commit();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


    }

    private void initializeView(){
        bottomNavigationView = findViewById(R.id.bottomNavView);
    }
}